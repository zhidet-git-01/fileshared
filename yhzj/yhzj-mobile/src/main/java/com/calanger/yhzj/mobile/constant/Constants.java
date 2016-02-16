package com.calanger.yhzj.mobile.constant;

import org.apache.commons.codec.digest.DigestUtils;

import com.calanger.common.util.Config;

public final class Constants {
    public static final String BASE_DOMAIN = Config.getConfig().get("base.domain");
    public static final String STATIC_DOMAIN = Config.getConfig().get("static.domain");
    public static final String HOME_DOMAIN = Config.getConfig().get("home.domain");
    public static final String SERVICE_DOMAIN = Config.getConfig().get("service.domain");
    public static final String MOBILE_HOME_DOMAIN = Config.getConfig().get("mobile.home.domain");

    public static final String COOKIE_NAME = Config.getConfig().get("cookie.name");
    public static final String COOKIE_DOMAIN = Config.getConfig().get("cookie.domain");
    public static final String COOKIE_PATH = Config.getConfig().get("cookie.path");

    public static final String SECRET_KEY = Config.getConfig().get("secret.key");
    public static final String USER_IDENTITY_KEY = Config.getConfig().get("user.identity.key");

    public static final int SESSION_TIMEOUT = Integer.parseInt(Config.getConfig().get("session.timeout"));

    public static final String SIGN_KEY = "DJFHJ34556#@$ghg$%^&DFG561"; // MD5加密秘钥
    
    /** 摘要算法 key */
    public static final String PASSWORD_KEY= "RyiI<?;{mSQ-aEwbZ+)F5e0:Yj@n\"(21t._|BM#U";
    
    /** 手机验证码 */
    public static final String SMS_URL = Config.getConfig().get("mobile.sms.url");
    public static final String SMS_ACCOUNT = Config.getConfig().get("mobile.sms.account");// 序列号
    public static final String SMS_PASSWD = Config.getConfig().get("mobile.sms.passwd");
    
    public static final String VALIDATE_CODE= "_zhidet_code";
    public static final Integer CODE_VALID_TIME = 300; // 验证码有效时间，300秒
    public static final String CHECK_CODE_CHARACTER = "123456789"; // 验证码组成元素
    public static final Integer CHECK_CODE_NUMBER = 4; // 4位数字验证码
    public static final Integer CHECK_CODE_TYPE_REG = 1; // 用户注册
    public static final Integer CHECK_CODE_TYPE_FIND = 2; // 忘记密码
    public static final Integer CHECK_CODE_TYPE_BANK = 3; // 绑银行卡
    public static final Integer CHECK_CODE_TYPE_PAY = 4; // 提现

    public static final String TRADER_ACCOUNT_CHARACTER = "1234567890"; // 资金账号密码组成元素
    public static final Integer TRADER_ACCOUNT_NUMBER = 6; // 6位数字密码

    public static final String USER_PHONE_KEY = "user_phone_key";

    /** 短信验证码接口 */
    public static final String MobileAreaInterfaceURL = Config.getConfig().get("mobile.area.interface.url"); // 财付通接口
    public static final String MobileAreaInterfaceParam = Config.getConfig().get("mobile.area.interface.param"); // 参数

    public static final int DEFAULT_PAGE_SIZE =5;

    public static final int DEFAULT_NOTICE_PAGE_SIZE = 8;

    public static final String A_STOCK_SEARCH_HISTORY = "_search_history"; //股指点买搜索历史

    /** 周周发杠杆为5倍， 交易天数为5天 */
    public static final int WEEK_APPLY_LEVER = 5;
    public static final int WEEK_APPLY_DAYS = 5;

    /** 易宝支付请求url*/
    public static final String YEEPAY_REQ_URL = "https://www.yeepay.com/app-merchant-proxy/node";
    public static final String YEEPAY_RES_URL = "http://" + HOME_DOMAIN + "/yeepay/pay-success";

    /** 免息操盘杠杆为1倍 */
    public static final int FREE_OF_INTEREST_LEVER = 1;

    public static final String YEEPAY_MERCHANT_ID = Config.getConfig().get("yeepay.merchant.id");
    public static final String YEEPAY_HMAC_KEY = Config.getConfig().get("yeepay.hmac.key");

    /** 免费体验订单的保证金和操盘金额 */
    public static final String EXPERIENCE_BZJ = "100";
    public static final String EXPERIENCE_CPJE = "2000";

    public static final String ALIPAY_REQ_URL = "http://www.facaijin.cn/alipay";

    public final static int GUZHI_BASE_CPJE = 200000;//期指一手交易金额为200000

	public final static int HUJIN_BASE_CPJE = 40000;//沪金一手交易金额为40000

	public final static int HUYIN_BASE_CPJE = 10000;//沪银一手交易金额为10000

	/** 连连支付 */
	public static final String LLPAY_URL = "https://yintong.com.cn/payment/bankgateway.htm";
	public static final String LLPAY_VERSION = "1.0"; // 版本号
	public static final String LLPAY_CHARSET_NAME = "UTF-8"; // 参数字符编码集
	public static final String LLPAY_OID_PARTNER = "201408071000001543"; // 支付交易商户编号：
	public static final String LLPAY_TIMESTAMP = "yyyyMMddHHmmss"; // 时间戳
	public static final String LLPAY_SIGN_TYPE = "RSA"; // 签名方式:RSA 或 MD5
	public static final String LLPAY_BUSI_PARTNER = "101001"; // 商户业务类型：虚拟商品销售(101001)、实物商品销售(109001)、外部账户充值(108001)
	public static final String LLPAY_RES_URL = "http://" + HOME_DOMAIN + "/llpay/pay-success";
	// MD5 KEY
	public static final String LLPAY_MD5_KEY = "201408071000001543test_20140812";
	// 商户私钥
    public static final String LLPAY_TRADER_PRI_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMlGNh/WsyZSYnQcHd9t5qUkhcOhuQmozrAY9DM4+7fhpbJenmYee4chREW4RB3m95+vsz9DqCq61/dIOoLK940/XmhKkuVjfPqHJpoyHJsHcMYy2bXCd2fI++rERdXtYm0Yj2lFbq1aEAckciutyVZcAIHQoZsFwF8l6oS6DmZRAgMBAAECgYAApq1+JN+nfBS9c2nVUzGvzxJvs5I5qcYhY7NGhySpT52NmijBA9A6e60Q3Ku7vQeICLV3uuxMVxZjwmQOEEIEvXqauyYUYTPgqGGcwYXQFVI7raHa0fNMfVWLMHgtTScoKVXRoU3re6HaXB2z5nUR//NE2OLdGCv0ApaJWEJMwQJBAPWoD/Cm/2LpZdfh7oXkCH+JQ9LoSWGpBDEKkTTzIqU9USNHOKjth9vWagsR55aAn2ImG+EPS+wa9xFTVDk/+WUCQQDRv8B/lYZD43KPi8AJuQxUzibDhpzqUrAcu5Xr3KMvcM4Us7QVzXqP7sFc7FJjZSTWgn3mQqJg1X0pqpdkQSB9AkBFs2jKbGe8BeM6rMVDwh7TKPxQhE4F4rHoxEnND0t+PPafnt6pt7O7oYu3Fl5yao5Oh+eTJQbyt/fwN4eHMuqtAkBx/ob+UCNyjhDbFxa9sgaTqJ7EsUpix6HTW9f1IirGQ8ac1bXQC6bKxvXsLLvyLSxCMRV/qUNa4Wxu0roI0KR5AkAZqsY48Uf/XsacJqRgIvwODstC03fgbml890R0LIdhnwAvE4sGnC9LKySRKmEMo8PuDhI0dTzaV0AbvXnsfDfp";

    private Constants() {
    }
}
