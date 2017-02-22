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
    public static String ftpcancel;
    public static String ftppre;
    public static String ftpport;
    public static String ftplocal;
    public static String ftp_sub_bin;
    public static String ftpcasi;
    public static String ftp_main_bin;
    public static String ftpchid;
    
    public static String op_id_dir;
    public static String local_08;
    public static String local_Header;
    
    /* 프로퍼티 설정값 가져 오는 함수 */
    public void PropertiesSetting() {

        Properties props = new Properties();
        try {

            props.load(new FileInputStream("setting.properties"));

            ftpurl       = props.getProperty("ftpurl");
            ftpid        = props.getProperty("ftpid");
            ftppw        = props.getProperty("ftppw");
            ftpdir       = props.getProperty("ftpdir");
            ftpport      = props.getProperty("ftpport");
            ftpcancel    = props.getProperty("ftpcancel");
            ftppre       = props.getProperty("ftppre");
            ftplocal     = props.getProperty("ftplocal");
            ftp_sub_bin  = props.getProperty("ftp_sub_bin");
            ftpcasi      = props.getProperty("ftpcasi");
            ftp_main_bin = props.getProperty("ftp_main_bin");
            ftpchid      = props.getProperty("ftpchid");
            local_08     = props.getProperty("local_08");
            local_Header = props.getProperty("local_Header");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
