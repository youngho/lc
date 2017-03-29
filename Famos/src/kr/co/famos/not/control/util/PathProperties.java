package kr.co.famos.not.control.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <code>PathProperties.java</code>
 * 
 * @company : FAMOS
 * @Description : Properties �۾� ���� ����
 * @author : ������ ����
 * @modify :
 * @since : 2017.02.01
 * @version : 1.0
 */

public class PathProperties {
    
    /* ���� ������Ƽ ���� ���� */
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
    
    /* ������Ƽ ������ ���� ���� �Լ� */
    public void PropertiesSetting() {

        Properties props = new Properties();
        try {

            props.load(new FileInputStream("setting.properties"));
            // BETS FTP ����
            ftpurl          = props.getProperty("ftpurl");
            ftpid           = props.getProperty("ftpid");
            ftppw           = props.getProperty("ftppw");
            ftpdir          = props.getProperty("ftpdir");
            ftpport         = props.getProperty("ftpport");
            ftplocal        = props.getProperty("ftplocal");
            // ����Ʈ ��� 
            lc_ng_bin       = props.getProperty("lc_ng_bin");
            lc_main_bin     = props.getProperty("lc_main_bin");
            lc_casi_bin     = props.getProperty("lc_casi_bin");
            lc_cancel_bin   = props.getProperty("lc_cancel_bin");
            lc_chid         = props.getProperty("lc_chid");
            // Ĩ������ ����Ҽ��� �ְ�, ���Ҽ��� �ְ�
            lc_chid         = props.getProperty("lc_chid");
            // GPIB ��ſ���  ���õ����� ����
            ftpcasi         = props.getProperty("ftpcasi");
            // ���� �ش� ����
            local_Header    = props.getProperty("local_Header");
            // ���� ���� ���
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
