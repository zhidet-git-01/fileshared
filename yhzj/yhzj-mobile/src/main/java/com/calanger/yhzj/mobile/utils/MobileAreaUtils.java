package com.calanger.yhzj.mobile.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public final class MobileAreaUtils {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MobileAreaUtils.class);
    /**
     * 手机号码归属地查询
     * 
     * API地址：
     * http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi?chgmobile=15012345678
     * 
     * @param url 接口地址：http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi
     * @param param 参数：chgmobile
     * @param mobile 手机号码 ：15012345678
     * @return 手机号码归属地，例如：浙江 杭州
     * @throws ParserConfigurationException 
     * @throws IOException 
     * @throws SAXException 
     * @throws XPathExpressionException 
     */
    public static String getMobileArea(String url, String param, String mobile) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String xmlStr = sendGet(url, param + "=" + mobile);

        // 解析xml，生成document对象
        DocumentBuilder  builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        ByteArrayInputStream in = new ByteArrayInputStream(xmlStr.getBytes("GBK"));
        Document document = builder.parse(in);
        // 生成XPath对象
        XPath xpath = XPathFactory.newInstance().newXPath();

        // 获取节点值
        String province = (String)xpath.evaluate("/root/province", document, XPathConstants.STRING);
        String city = (String)xpath.evaluate("/root/city", document, XPathConstants.STRING);
        if (province == null && city == null) {
            return "--";
        }

        return province + city;
    }

    /**
     * 向指定URL发送GET方法的请求 
     * @param url  发送请求的URL
     * @param param  请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应 
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url + "?" + param;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 

            // 建立实际的连接 
            conn.connect();

            // 定义BufferedReader输入流来读取URL的响应 
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line); // 输出每行
                result += "" + line;
            }
        } catch (Exception e) {
            LOGGER.error("发送GET请求出现异常！",e);
        }
        finally { // 使用finally块来关闭输入流 
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            	LOGGER.error("MobileAreaUtils:connect error", ex);
            }
        }
        return result;
    }
}
