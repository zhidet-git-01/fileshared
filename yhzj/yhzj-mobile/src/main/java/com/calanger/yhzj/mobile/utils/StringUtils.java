package com.calanger.yhzj.mobile.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * 字符串操作工具类
 * 
 */
public class StringUtils {
    private static final String email_reg = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
    private static final String mobile_reg = "^1[34578][0-9]{9}$";
    private static final String days_reg = "^[0-9]{4}-((0[1-9])|(1[0-2]))-" +
            "((0[1-9])|([1-2][0-9])|(3[0-1]))\\s+(([0-1][0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])$";

    private StringUtils(){}
    
    public static void main(String[] args) {
        System.out.println("是否为银行卡:"+isBankCard("0123456789012345678"));
    }
	/**
	 * 检查字符串数组中是否有空的字符串。
	 * @param strs
	 */
	public static boolean isEmpty(String... strs) {
		for (String s : strs) {
			if (Strings.isNullOrEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
     * 验证日期字符串格式 yyyy-MM-dd HH:mm:ss
     */
	public static boolean isDays(String dateStr) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(days_reg);
            Matcher matcher = regex.matcher(dateStr);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
	
    /**
     * 检查邮件是否合乎规范
     * @param email
     */
    public static boolean isEmail(String email) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(email_reg);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            //log.error("验证邮箱地址错误", e);
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     * @param mobiles
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile(mobile_reg);
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 局部隐藏手机号位，隐藏从第4位到第7位
     * @param mobileNo 传入的手机号码
     * @return 被局部隐藏后的手机号码
     */
    public static String maskMobileNo(String mobileNo){       
        StringBuilder sb = new StringBuilder();
        sb.append(mobileNo.substring(0, 3)).append("****").append(mobileNo.substring(7));
        return sb.toString();      
    }

    /**
     * 局部隐藏身份证号位，隐藏生日部分
     * @param idCardNo 传入的身份证号
     * @return 被局部隐藏后的身份证号码
     */
    public static String maskIDCardNo(String idCardNo){
        StringBuilder sb = new StringBuilder();
        sb.append(idCardNo.substring(0,6)).append("********").append(idCardNo.substring(14));
        return sb.toString();
    }

    /**
     * 局部隐藏银行卡号，只显示前4后4
     * @param bankCardNo 银行卡号
     * @return 被局部隐藏后的银行卡号
     */
    public static String maskBankCardNo(String bankCardNo){
        StringBuilder sb = new StringBuilder();
        sb.append(bankCardNo.substring(0,4));
        for(int i = 0;i<bankCardNo.length()-10;i++)
            sb.append("*");
        sb.append(bankCardNo.substring(bankCardNo.length()-4,bankCardNo.length()));
        return sb.toString();
    }
    
    public static List getTags(String tag){
       List<String> list = new ArrayList<>();
       String[] tStrings = tag.split(";");
       if (tStrings != null) {
           for (String string : tStrings) {
               if (Strings.isNullOrEmpty(string)) {
                   list.add(string);
            }
           }
       }
       return list;
    }
    
    public static String maskAlipayNo(String alipayNo){
        StringBuilder sb = new StringBuilder();
        if (alipayNo.contains("@")) {
            sb.append(alipayNo.substring(0,4));
            int index = alipayNo.lastIndexOf("@");
            if (index <= 4) {
               index = 4;
            }
            for (int i = 0; i < index-4; i++) {
                sb.append("*");
            }
            sb.append(alipayNo.substring(index,alipayNo.length()));
        }else {
           return maskMobileNo(alipayNo);
        }
        return sb.toString();
    }
    
        public static boolean isBankCard(String cardId) {
            char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
            if(bit == 'N'){
                return false;
            }
            return cardId.charAt(cardId.length() - 1) == bit;
        }

        /**
        * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
        * @param nonCheckCodeCardId
        * @return
        */
        public static char getBankCardCheckCode(String nonCheckCodeCardId){
           if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                   || !nonCheckCodeCardId.matches("\\d+")) {
               //如果传的不是数据返回N
               return 'N';
           }
           char[] chs = nonCheckCodeCardId.trim().toCharArray();
           int luhmSum = 0;
           for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
               int k = chs[i] - '0';
               if(j % 2 == 0) {
                   k *= 2;
                   k = k / 10 + k % 10;
               }
               luhmSum += k;           
           }
           return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
        }
    }
