package com.calanger.yhzj.mobile.utils;

public class PlatRetMoneyUtils {
    
    
    
    public static void main(String[] args) {
       int a = PlatRetMoneyUtils.TouZhiQuRetMoney(1000, "2015-12-24 08:10:10");
       System.out.println(TimeUtils.getCurrDateTime());
       System.out.println(a);
    }
    
    public static int HuanLeRetMoney(int m) {
        int retuenMoney = 0;
        if (m >= 500) {
            retuenMoney = 50;
        } else if (m >= 100 && m < 500) {
            retuenMoney = 10;
        }
        return retuenMoney;
    }
    
    public static int QianXiangRetMoney(int m) {
        int retuenMoney = 0;
        if (m >= 1000) {
            retuenMoney = 50;
        }
        return retuenMoney;
    }

    public static int BaiLiShiRetMoney(int m) {
        int retuenMoney = 0;
        if (m >= 1000) {
            retuenMoney = 45;
        }
        return retuenMoney;
    }

    public static int NiuBanJinRetMoney(int m) {
        int retuenMoney = 0;
        if (m >= 5000) {
            retuenMoney = 150;
        } else if (m >= 3000 && m < 5000) {
            retuenMoney = 100;
        } else if (m >= 2000 && m < 3000) {
            retuenMoney = 70;
        } else if (m >= 1000 && m < 2000) {
            retuenMoney = 30;
        }
        return retuenMoney;
    }

    public static int TouZhiQuRetMoney(int m, String date) {
        String ndate = "2015-12-24 09:50:00";
        int retuenMoney = 0;

        if (ndate.compareTo(date) <= 0) {
            if (m >= 1000) {
                retuenMoney = 50;
            }
        } else {
            if (m >= 5000) {
                retuenMoney = 100;
            } else if (m >= 1000 && m < 5000) {
                retuenMoney = 55;
            }
        }
        return retuenMoney;
    }

    public static int ZhangZhiRetMoney(int m) {
        int retuenMoney = 0;
        if (m >= 1000) {
            retuenMoney = 35;
        } else if (m >= 500 && m < 1000) {
            retuenMoney = 25;
        } else if (m >= 100 && m < 500) {
            retuenMoney = 15;
        }
        
        return retuenMoney;
    }

    public static int DianDianRetMoney(int m, String date) {
        String ndate = "2015-12-10 00:00:00";
        int retuenMoney = 0;
        if (ndate.compareTo(date) <= 0) {
            if (m >= 500) {
                retuenMoney = 30;
            } else if (m >= 100 && m < 500) {
                retuenMoney = 20;
            }
        }else {
            if (m >= 500) {
                retuenMoney = 30;
            } else if (m >= 100 && m < 500) {
                retuenMoney = 10;
            }
        } 
        
        return retuenMoney;
    }

    public static int ChuangLiTouRetMoney(int m) {
        int retuenMoney = 0;
        if (m > 1500) {
            retuenMoney = 50;
        } else if (m > 1000 && m <= 1500) {
            retuenMoney = 40;
        } else if (m >= 500 && m <= 1000) {
            retuenMoney = 30;
        }
        
        return retuenMoney;
    }
    public static int SuRongDaiRetMoney(int m,String date) {
        String ndate = "2015-12-29 09:50:00";
        int retuenMoney = 0;
        
        if (ndate.compareTo(date) <= 0) {
            if (m >= 5000) {
                retuenMoney = 150;
            }
        } else {
            if (m >= 1000) {
                retuenMoney = 50;
            } else if (m >= 500 && m < 1000) {
                retuenMoney = 35;
            } else if (m >= 100 && m < 500) {
                retuenMoney = 15;
            }
        }
        return retuenMoney;
    }
}

