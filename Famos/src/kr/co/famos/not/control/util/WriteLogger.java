package kr.co.famos.not.control.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class WriteLogger {

    static Logger logger;

    public static Logger getInstance(String logClass) {

        String fileName = "lc_log";

        if (logger == null) {
            logger = Logger.getLogger(logClass);
            String pattern = "[%d{yyyy/MM/dd HH:mm:ss}][%-5p](%F:%L) - %m%n";
            PatternLayout layout = new PatternLayout(pattern);

            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("yyyyMMdd");
            String strDate = dateformat.format(new Date());

            fileName = PathProperties.lc_log + fileName + "_" + strDate + ".log";

            RollingFileAppender appender = null;
            try {
                appender = new RollingFileAppender(layout, fileName);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            logger.addAppender(appender);
            return logger;
        } else {
            return logger;
        }
    }

    //LOGfile delete
    public static void LogFileDelete(long lDay) throws ParseException {
        String now_Date = null;
        File[] files = null;

        Date sourceDate1 = new Date();
        Date sourceDate2 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowDate = new Date();

        //        now_Date = formatter.format(nowDate.getTime());
        //        sourceDate1 = formatter.parse(now_Date);

        //        File dir = new File(PathProperties.lc_log);
        //        files = dir.listFiles();
        //        if (files != null) {
        //            for (int i = 0; i < files.length; i++) {
        //                long fileModified = files[i].lastModified();
        //                String fileDate = formatter.format(fileModified);
        //                sourceDate2 = formatter.parse(fileDate);
        //                long fileDiff = (sourceDate1.getTime() - sourceDate2.getTime()) / (1000 * 60 * 60 * 24);
        //
        //                //설정된 기간보다 날짜가 크면삭제
        //                if (lDay <= fileDiff) {
        //                    files[i].delete();
        //                }
        //            }
        //        }
    }
}
