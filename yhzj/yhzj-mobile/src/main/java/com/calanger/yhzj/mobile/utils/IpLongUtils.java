package com.calanger.yhzj.mobile.utils;

public final class IpLongUtils {
    /**
     * 把IP转换成Long类型数据
     * @param ip
     * @return
     */
    public static Long getIpToLong(String ip) {
        String[] ipStr = ip.split("\\.");
        return 256 * 256 * 256 * Long.parseLong(ipStr[0])
                + 256 * 256 * Long.parseLong(ipStr[1])
                + 256 * Long.parseLong(ipStr[2])
                + Long.parseLong(ipStr[3]);
    }
    
    public static String getIpToString(Long ipaddress){
    	 StringBuffer sb = new StringBuffer("");  
         sb.append(String.valueOf((ipaddress >>> 24)));  
         sb.append(".");  
         sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));  
         sb.append(".");  
         sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));  
         sb.append(".");  
         sb.append(String.valueOf((ipaddress & 0x000000FF)));  
         return sb.toString();  
    }
}
