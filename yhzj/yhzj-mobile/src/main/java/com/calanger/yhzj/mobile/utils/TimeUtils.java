package com.calanger.yhzj.mobile.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * Title: vote
 * Description:时间转换工具类
 * Company:
 * 创建时间:
 * 最后修改时间:
 * @author 
 * @version 1.0
 * 
 */
public class TimeUtils {
    
    private TimeUtils() {}
    
    public final static Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);
    
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_MM_DD = "MM-dd";
    
    /**
     * 获取当天日期字符串 格式为：yyyyMMdd
     * @return 当天日期字符串
     * @see getCurrDate(Date currDate)
     */
    public static String getCurrDate() {
        Date currDate = new Date();
        
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String strCurrDate = df.format(currDate);
        
        return strCurrDate;
    }
    
    public static String getCurrDateTime() {
        Date currDate = new Date();
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strCurrDate = df.format(currDate);
        
        return strCurrDate;
    }
    public static String getCurrDateTime2() {
        Date currDate = new Date();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strCurrDate = df.format(currDate);

        return strCurrDate;
    }
    
    /**
     * 
     * 获取当天日期字符串 格式为：yyyyMMdd
     * @return 当天日期字符串
     * @see getCurrDate()
     */
    public static String getCurrDate(Date currDate) {

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String strCurrDate = df.format(currDate);

        return strCurrDate;
    }
    
    /**
     * 传入一个日期字符串，并将其转换成 yyyyMMdd 格式Date类，再将该Date类转换成罗马日历时间
     * @param strDate 日期字符串
     * @return 罗马日历时间
     */
    public static Calendar formatCalendar(String strDate) {

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date date = df.parse(strDate);
            Calendar tmpCal = Calendar.getInstance();
            tmpCal.setTime(date);
            
            return tmpCal;
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 时间字符串按 YYMMDDHH24MISS 格式转换成date类型
     * @param strDate 时间格式字符串
     * @return 按 yyMMddHHmmss 格式转换后的Date
     */
    public static Date convertDate(String strDate) {
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    /**
     * 将一个时间字符串按自定义格式转换成Date
     * @param strDate  时间字符串
     * @param format 自定义时间格式
     * @return 返回自定义时间格式后的Date类
     */
    public static Date convertDateFrom(String strDate,String format) {
        Date date = null;

        DateFormat df = new SimpleDateFormat(format);
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    /**
     * 按自定义格式返回加、减运算后的时间字符串
     * @param i 指定用于运算的时间天数 
     * @param format 自定义的时间格式
     * @return 返回指定的当前时间的前后那天的时间字符串，例如参数 i 为 3时 返回当前时间加3天后的时间字符串 为负数时减
     */
    public static String getDate(int i, String format)
    {

        Calendar now = Calendar.getInstance();

        now.roll(Calendar.DAY_OF_YEAR, i);
        SimpleDateFormat sf = new SimpleDateFormat(format);

        return sf.format(now.getTime());
    }

    /**
     * 根据参数选择SimpleDateFormat的自定义格式
     *
     * @param interval 2 SimpleDateFormat的格式为 yyyy/MM/dd<br> 3 yyyy/MM <br> 4 yyyy <br> 否则 HH:mm
     *
     * @return 带格式SimpleDateFormat
     */
    public static SimpleDateFormat getDateFormat(int interval)
    {

        switch (interval)
        {
            case 2:
                return new SimpleDateFormat("yyyy/MM/dd");
            case 3:
                return new SimpleDateFormat("yyyy/MM");
            case 4:
                return new SimpleDateFormat("yyyy");
            case 5:
                return new SimpleDateFormat("yyyy-MM-dd");
            default:
                return new SimpleDateFormat("HH:mm");
        }
    }


    /**
     * 获取上个小时的整点时间，格式 Wed Dec 02 23:00:00 CST 2009
     * @return 返回上个小时的整点时间，分钟和秒都为00
     */
    @SuppressWarnings("deprecation")
    public static Date getLastHour() {
        Date tmp = new Date(System.currentTimeMillis()-60*60*1000);
                
        Date lastHour = new Date(tmp.getYear(), tmp.getMonth(), tmp.getDate(), tmp.getHours(),0, 0);
               
        return lastHour;
    }
    /**
     * 获取上个小时的整点时间的字符串， 格式为：yyyyMMddHHmmss
     * @return 返回上个小时的整点时间，分钟和秒都为00 
     */
    public String getLastHourStr() {
        return formatDateYYYYMMDDHH24MMSS(getLastHour());
    }
    
    /**
     * 将指定时间按 yyyyMMddHHmmss 返回字符串
     * @param date 时间类型
     * @return 返回指定格式时间字符串 格式为 yyyyMMddHHmmss
     */
    public static String formatDateYYYYMMDDHH24MMSS(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = df.format(date);

        return dateStr;
    }
    
    /**
     * 将指定时间按 yyyyMMddHH 返回字符串
     * @param date 时间类型
     * @return 返回指定格式时间字符串 格式为 yyyyMMddHH
     */
    public static String formatDateYYYYMMDDHH24(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHH");
        String dateStr = df.format(date);

        return dateStr;
    }
    
    /**
     * 时间格式转换 yyyy-MM-dd HH:mm:ss
     * @return
     * @author cxj
     */
    public static String formatDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);

        return dateStr;
    }
    
    public static String convertDateToString(Date date,String format){
        String dateStr = "";
        if(date != null){
            dateStr = DateFormatUtils.format(date, format);
        }
        return dateStr;

    }
    /**
     * 返回当前整点时间的字符串 格式为：yyyyMMddHHmmss 例如：20091202230000
     * @return 返回当前整点时间的字符串，分钟和秒都为00
     */
    public static String getCurrentHourStr() {

        return formatDateYYYYMMDDHH24MMSS(getCurrentHour());
    }
    
    /**
     *  获取当前小时时间 eg:Wed Dec 02 23:00:00 CST 2009
     * @return 当前的整点时间 分钟和秒都为00
     */
    @SuppressWarnings("deprecation")
    public static Date getCurrentHour() {
        Date tmp = new Date(System.currentTimeMillis());
                
        Date currHour = new Date(tmp.getYear(), tmp.getMonth(), tmp.getDate(), tmp.getHours(), 0, 0);
        return currHour;
                
    }
    /**
     * 时间格式转换 yyyy-MM-ddTHH:mm:ss,适应JOBS.xml要求
     * @return
     * @param  时间
     */
    public static String getJobsDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        return  dateStr.replaceFirst("\\s", "T");
      
    }
    
    /**
     *
     * <pre>
     * 将时间字符串+8小时后，按指定格式返回.
     * </pre>
     * @param dateStr     指定时间字符串
     * @param dateFormat  指定时间格式
     * @return
     */
    public static String griCalendarToString(String dateStr,String dateFormat){
        try{
            DateFormat df = new SimpleDateFormat(dateFormat);
            Calendar cc = new GregorianCalendar();
            cc.setTime(df.parse(dateStr));
            cc.add(Calendar.HOUR ,8);
            return df.format(cc.getTime());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
  
/**
 * 获取当前时间
 * @param dateFormat
 * @return
 */ 
    public static java.util.Date getCurSysDate() {
        java.util.Date curDate = new Date();
        try {
            SimpleDateFormat sdf = TimeUtils.getDateFormat(5);
            String strCurDate = TimeUtils.getDateFormat(5).format(new java.util.Date());
            curDate = sdf.parse(strCurDate);
        } catch (Exception ex) {
            LOGGER.error("TimeUtils:getCurSysDate",ex);
        }

        return curDate;
    }
    
    public static String formatDate(Date date,String formate){
        DateFormat df = new SimpleDateFormat(formate);
        String dateStr = df.format(date);

        return dateStr;
    }
    
    public static Date convertDate(String strDate,String format) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            LOGGER.error("TimeUtils:convertDate",e);
        }

        return date;
    }
    
    /**
     * <pre>
     *   Oracle,HQL语句中Timestamp转换为Date
     * </pre>
     * @author ysw
     * @Date   2014-2-20
     */
    public static String frommatToDate(Timestamp time){
        return " to_date('"+TimeUtils.formatDate(new Date(time.getTime()),"yyyy-MM-dd HH:mm:ss")+"','yyyy-mm-dd :hh24:mi:ss')";
    }

    /**
     * <pre>
     *   方法描述：当前时间timestamp
     * </pre>
     * @author ZhangJianxiang
     * @Date   2014-3-15 上午11:08:12
     * @return
     */
    public static Timestamp getCurrentTimestamp(){
        return new Timestamp(new Date().getTime());
    }

    /**
     * <pre>
     *   方法描述：timestamp转字符串
     * </pre>
     * @author ZhangJianxiang
     * @Date   2014-3-17 下午8:05:21
     * @param date
     * @return
     */
    public static String getCurrentTimestampStr(Timestamp date){
        String dateStr = "";
        if(date!=null){
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateStr = df.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }
    /**
     * <pre>
     *   方法描述：timestamp转字符串
     * </pre>
     * @author
     * @Date   2014-3-17 下午8:05:21
     * @param date
     * @return
     */
    public static String getCurrentTimestampStr(Timestamp date,String format){
        String dateStr = "";
        if(date!=null){
            try {
                SimpleDateFormat df = new SimpleDateFormat(format);
                dateStr = df.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }


    /**
     * <pre>
     *  方法描述:根据传入条件获取时间date
     * </pre>
     * @author
     * Date 2014-4-7 下午12:57:44
     * @param date
     * @param fileds
     * @param interval
     * @return
     */
    public static Date getDate(Date date,int fileds,int  interval){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(fileds, interval);
        return calendar.getTime();
    }

    /**
     * <pre>
     *  方法描述:根据传入条件获取时间timestamp
     * </pre>
     * @author ZhangJianxiang
     * Date 2014-4-7 下午12:57:56
     * @param timestamp
     * @param fileds
     * @param interval
     * @return
     */
    public static Timestamp getDate(Timestamp timestamp, int fileds,int  interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(fileds, interval);
        return new Timestamp(cal.getTimeInMillis());
    }
    /**
     * <pre>
     *  方法描述:根据传入条件获取时间timestamp
     * </pre>
     * @author ysw
     * Date 2014-4-22 下午16:10:56
     * @param time
     * @return
     * @throws ParseException
     */
    public static Timestamp stringFormatTimestamp(String time) throws ParseException {
         DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
          format.setLenient(false);
          if(time!=null && !"".equals(time)){
              Timestamp ts = new Timestamp(format.parse(time+" 00:00:00").getTime());
              return ts;
          }else{
              return null;
          }
    }

    public static long getDateTime(Date d){
        long time = 0l;
        if(d !=null){
            time= d.getTime();
        }
        return time;
    }

    /**
     * <pre>
     *  方法描述：获取当天开始时间
     *  00:00:00
     * </pre>
     * @author
     * Date 2014-5-1 下午5:34:08
     * @param time
     * @return
     */
    public static long getStartOfToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();
        return date.getTime();
    }

    /**
     * <pre>
     *  方法描述：获取当天结束时间
     *  23:59:59
     * </pre>
     * @author
     * Date 2014-5-1 下午5:34:08
     * @param time
     * @return
     */
    public static long getEndOfToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date=calendar.getTime();
        return date.getTime();
    }

    /**
     * 获取某一天的开始时间
     * @param dateStr 格式为"yyyy-mm-dd"
     * @return long型时间
     * @throws ParseException
     */
    public static long getStartOfDay(String dateStr) throws ParseException{
        dateStr +=" 00:00:00";
        SimpleDateFormat formatter = new SimpleDateFormat(TimeUtils.FORMAT_YYYY_MM_DD);
        return formatter.parse(dateStr).getTime();
    }

    /**
     * 获取某一天的结束时间
     * @param dateStr 格式为"yyyy-mm-dd"
     * @return long型时间
     * @throws ParseException
     */
    public static long getEndOfDay(String dateStr)throws ParseException{
        dateStr +=" 23:59:59";
        SimpleDateFormat formatter = new SimpleDateFormat(TimeUtils.FORMAT_YYYY_MM_DD);
        return formatter.parse(dateStr).getTime();
    }
    /**
     * 根据int型时间获取日期+时间
     * @param time int类型
     * @return
     */
    public static String getDateTimeFromInt(Integer time) {
        SimpleDateFormat formatter = new SimpleDateFormat(TimeUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
        return formatter.format(new Date(time*1000L));
    }

    /**
     * 根据int型时间获取日期+时间
     * @param time int类型
     * @return
     */
    public static String getDateTimeFromInt(Integer time,String formater){
        SimpleDateFormat formatter = new SimpleDateFormat(formater);
        return formatter.format(new Date(time*1000L));
    }

      public static void main(String[] args){
          // Date date =  TimeUtils.convertDateFrom(, "yyyyMMdd HH:mm:ss");

//            System.out.println(convertDate("20110726 23:28:20","HH:mm:ss"));
           System.out.println(getBeforebeMonth(3));
        }

    /**
     * 获取指定时间的毫秒数
     * @param timeStart 形如：14:35
     * @return
     */
    public static long getTimeByHHMM(String timeStart) {
        long today = getStartOfToday();
        String[] time = timeStart.split(":");
        long hours = Integer.parseInt(time[0]) * 3600 * 1000;
        long minutes = Integer.parseInt(time[1]) * 60 * 1000;
        return today + hours + minutes;
    }

    public static String getMillisOfToday () {
        long today = getStartOfToday();
        long nineThirty = today + 9 * 3600 * 1000l + 30 * 60 * 1000l;
        long thirteen = today + 13 * 3600 * 1000l;
        //long[] millis = new long[240];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < 120; i++) {
            sb.append(nineThirty + i * 60 * 1000l + ",");
            //millis[i] = nineThirty + i * 60 * 1000l;
        }
        for (int j = 121; j < 240; j ++) {
            sb.append(thirteen + j * 60 * 1000l + "");
            if (j != 239) {
                sb.append(",");
            }
            //millis[j] = thirteen + j * 60 * 1000l;
        }
        sb.append("]");
        return sb.toString();
    }

     /**
     * 判断当前时间是不是在时间断中
     * @param value 形如：14:35 或"00:00-2:30;8:40-14:35;20:40-23:59"; 不同时间段用;隔开
     * @return
     */
    public static boolean isTimesBetween(String value){
        String[] stopTimeArray = value.split(";");
        long date = System.currentTimeMillis();
        for (int i = 0; i < stopTimeArray.length; i++) {
            String[] time = stopTimeArray[i].split("-");
            String timeStart = time[0];
            String timeEnd = time[1];
            long timeS = TimeUtils.getTimeByHHMM(timeStart);
            long timeE = TimeUtils.getTimeByHHMM(timeEnd);
            if (date >= timeS && date < timeE) {
                return true;
            }
        }
        return false;
    }
    /**
     * 本周开始时间
     * @return
     */
    public static long weekStartTime(){
        Date date = new Date();
        Calendar c  = Calendar.getInstance();
        int weekday = c.get(7)-2;
        c.add(5,-weekday);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        date = c.getTime();
        return c.getTime().getTime()/1000;
    }
    /**
     * 本月开始时间
     * @return
     */
    public static long monthStartTime(){
        Calendar c  = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime().getTime()/1000;
    }
    /**
     * 几个月前时间一天开始时间
     * @param month 之前几个月时间，如： month==3时，3个月前一天开始时间
     * @return
     */
    public static long getBeforebeMonth(int month){
        Calendar c  = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.add(Calendar.MONTH, -month);
        return  c.getTime().getTime()/1000;
    }
    
}
