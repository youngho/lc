package kr.co.famos.not.control.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <code>PathProperties.java</code>
 * 
 * @company : FAMOS
 * @Description : Properties 작업 변수 선언
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.01
 * @version : 1.0
 */

public class PathProperties {
    
    /* 설정 프로퍼티 파일 변수 */
    public static String ftpurl;
    public static String ftpid;
    public static String ftppw;
    public static String ftpdir;
    
    public static String ftppre;
    public static String ftpport;
    public static String ftplocal;
    
    public static String lc_ng_bin;
    public static String lc_main_bin;
    public static String lc_casi_bin;
    public static String lc_cancel_bin;
    public static String lc_chid;
    
    public static String ftpcasi;
    public static String local;
    
    public static String local_Header;
    
    public static String lc_log;
    
    /* 프로퍼티 설정값 가져 오는 함수 */
    public void PropertiesSetting() {

        Properties props = new Properties();
        try {

            props.load(new FileInputStream("setting.properties"));
            // BETS FTP 정보
            ftpurl          = props.getProperty("ftpurl");
            ftpid           = props.getProperty("ftpid");
            ftppw           = props.getProperty("ftppw");
            ftpdir          = props.getProperty("ftpdir");
            ftpport         = props.getProperty("ftpport");
            ftplocal        = props.getProperty("ftplocal");
            // 리포트 백업 
            lc_ng_bin       = props.getProperty("lc_ng_bin");
            lc_main_bin     = props.getProperty("lc_main_bin");
            lc_casi_bin     = props.getProperty("lc_casi_bin");
            lc_cancel_bin   = props.getProperty("lc_cancel_bin");
            lc_chid         = props.getProperty("lc_chid");
            // 칩데이터 사용할수도 있고, 안할수도 있고
            lc_chid         = props.getProperty("lc_chid");
            // GPIB 통신에서  가시데이터 저장
            ftpcasi         = props.getProperty("ftpcasi");
            // 공통 해더 정보
            local_Header    = props.getProperty("local_Header");
            // 로컬 저장 경로
            local           = props.getProperty("local");
            
            ftppre          = props.getProperty("ftppre");
            
            lc_log          = props.getProperty("lc_log");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
