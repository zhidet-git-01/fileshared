package com.calanger.yhzj.mobile.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.calanger.common.util.Config;
import com.google.common.base.Strings;

public final class SendSMSUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendSMSUtils.class);

    private static String serviceURL = Config.getConfig().get("mobile.sms.serviceURL");
    private static String sn = Config.getConfig().get("mobile.sms.sn");// 序列号
    private static String password = Config.getConfig().get("mobile.sms.password");
    private static String pwd = DigestUtils.md5Hex(sn + password).toUpperCase();// 密码
    

    public static void main(String[] args) {
//        System.out.println(mdgetSninfo());
//        JSONObject result = JSONObject.parseObject(mdgetSninfo());
//        System.out.println(result.get("Balance"));

        sendSMS("18667116783", "1234", "123456789");
    }

    /*
     * 方法名称：mdgetSninfo
     * 功    能：获取信息
     * 参    数：sn,pwd(软件序列号，加密密码md5(sn+password))
     * 
     */
    public static String mdgetSninfo() {
        String result = "";
        String soapAction = "http://entinfo.cn/mdgetSninfo";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdgetSninfo xmlns=\"http://entinfo.cn/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "<mobile>" + "" + "</mobile>";
        xml += "<content>" + "" + "</content>";
        xml += "<ext>" + "" + "</ext>";
        xml += "<stime>" + "" + "</stime>";
        xml += "<rrid>" + "" + "</rrid>";
        xml += "<msgfmt>" + "" + "</msgfmt>";
        xml += "</mdgetSninfo>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpconn.setRequestProperty("Content-Type", "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdgetSninfoResult>(.*)</mdgetSninfoResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("mdgetSninfo", e);
            return "";
        }
    }

    /*
     * 方法名称：mdsmssend
     * 功    能：发送短信
     * 参    数：mobile,content,ext,stime,rrid,msgfmt(手机号，内容，扩展码，定时时间，唯一标识，内容编码)
     * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public static String mdsmssend(String mobile, String content, String ext, String stime, String rrid,String msgfmt) {
        String result = "";
        String soapAction = "http://entinfo.cn/mdsmssend";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdsmssend  xmlns=\"http://entinfo.cn/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + pwd + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "<msgfmt>" + msgfmt + "</msgfmt>";
        xml += "</mdsmssend>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpconn.setRequestProperty("Content-Type", "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            LOGGER.error("mdsmssend",e);
            return "";
        }
    }

    public static void sendSMS(String mobile, String checkCode, String clientIp) {
        String content = "验证码 " + checkCode + "（百股顺平台激活码，五分钟内有效）";
        if (!content.contains("【百股顺】")) {
            content = content + "【百股顺】";
        }
        try {
            String result_mt = mdsmssend(mobile, URLEncoder.encode(content, "utf8"), "", "", "", "");
            if (Strings.isNullOrEmpty(result_mt)) {
                if (serviceURL.contains("sdk2")) {
                    serviceURL = serviceURL.replace("sdk2", "sdk");
                } else {
                    serviceURL = serviceURL.replace("sdk", "sdk2");
                }
                result_mt = mdsmssend(mobile, URLEncoder.encode(content, "utf8"), "", "", "", "");
            }
            LOGGER.info(mobile + ":" + content + ":" + result_mt);
        } catch (Exception e) {
            LOGGER.error("sendSMS",e);
        }
    }
}
