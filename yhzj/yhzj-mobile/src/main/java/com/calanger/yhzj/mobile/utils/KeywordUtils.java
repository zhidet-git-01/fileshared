package com.calanger.yhzj.mobile.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class KeywordUtils {
    public static String getKeyword(String refererUrl) {
        StringBuffer sb = new StringBuffer();
        if (refererUrl != null) {
            sb.append("(google\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
              .append("|iask\\.[a-zA-Z]+/.+[\\&|\\?]k=([^\\&]*)")
              .append("|iask\\.[a-zA-Z]+/.+[\\&|\\?]_searchkey=([^\\&]*)")
              .append("|sogou\\.[a-zA-Z]+/.+[\\&|\\?]query=([^\\&]*)")
              .append("|163\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
              .append("|yahoo\\.[a-zA-Z]+/.+[\\&|\\?]p=([^\\&]*)")
              .append("|baidu\\.[a-zA-Z]+/.+[\\&|\\?]wd=([^\\&]*)")
              .append("|baidu\\.[a-zA-Z]+/.+[\\&|\\?]word=([^\\&]*)")
              .append("|lycos\\.[a-zA-Z]+/.*[\\&|\\?]query=([^\\&]*)")
              .append("|aol\\.[a-zA-Z]+/.+[\\&|\\?]encquery=([^\\&]*)")
              .append("|3721\\.[a-zA-Z]+/.+[\\&|\\?]p=([^\\&]*)")
              .append("|3721\\.[a-zA-Z]+/.+[\\&|\\?]name=([^\\&]*)")
              .append("|search\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
              .append("|soso\\.[a-zA-Z]+/.+[\\&|\\?]w=([^\\&]*)")
              .append("|zhongsou\\.[a-zA-Z]+/.+[\\&|\\?]w=([^\\&]*)")
              .append("|alexa\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
              .append("|haosou\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
              .append(")");
            Pattern p = Pattern.compile(sb.toString());
            Matcher m = p.matcher(refererUrl);
            return decoderKeyword(m,refererUrl);
        }
        return null;
      }

      public static String decoderKeyword(Matcher m, String refererUrl) {
          String keyword = null;
          String encode = "UTF-8";
          String searchEngine = getSearchEngine(refererUrl);
          if(searchEngine != null) {
            if ((checkCode("3721|iask|sogou|163|baidu|soso|zhongsou",searchEngine) || (checkCode("yahoo",searchEngine)&&!checkCode("ei=utf-8",refererUrl.toLowerCase())))) {
              encode = "UTF-8";
            }
            if (m.find()) {
              for (int i = 2; i <= m.groupCount(); i++) {
                if (m.group(i) != null) { //在这里对关键字分组就用到了
                  try {
                    keyword = URLDecoder.decode(m.group(i), encode);
                  } catch(UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                  }
                  break;
                }
              }
            }
          }
          return keyword;
      }

      public static String getSearchEngine(String refUrl) {
          if(refUrl.length() > 11) {
              //p是匹配各种搜索引擎的正则表达式
              Pattern p = Pattern.compile("http:\\/\\/.*\\.(google\\.com(:\\d{1,}){0,1}\\/|"
                     + "google\\.cn(:\\d{1,}){0,1}\\/|baidu\\.com(:\\d{1,}){0,1}\\/|"
                     + "yahoo\\.com(:\\d{1,}){0,1}\\/|iask\\.com(:\\d{1,}){0,1}\\/|"
                     + "sogou\\.com(:\\d{1,}){0,1}\\/|163\\.com(:\\d{1,}){0,1}\\/|"
                     + "lycos\\.com(:\\d{1,}){0,1}\\/|aol\\.com(:\\d{1,}){0,1}\\/|"
                     + "3721\\.com(:\\d{1,}){0,1}\\/|search\\.com(:\\d{1,}){0,1}\\/|"
                     + "soso.com(:\\d{1,}){0,1}\\/|zhongsou\\.com(:\\d{1,}){0,1}\\/|"
                     + "haosou\\.com(:\\d{1,}){0,1}\\/|"
                     + "alexa\\.com(:\\d{1,}){0,1}\\/)");
              Matcher m = p.matcher(refUrl);
              if (m.find()) {
                return insteadCode(m.group(1), "(\\.com(:\\d{1,}){0,1}\\/|\\.cn(:\\d{1,}){0,1}\\/|\\.org(:\\d{1,}){0,1}\\/)","");
              }
          }
          return "未发现有搜索引擎";
      }

      public static String insteadCode(String str, String regEx, String code) {
          Pattern p = Pattern.compile(regEx);
          Matcher m = p.matcher(str);
          String s = m.replaceAll(code);
          return s;
      }

      public static boolean checkCode(String regEx, String str) {
          Pattern p = Pattern.compile(regEx);
          Matcher m = p.matcher(str);
          return m.find();
      }
}
