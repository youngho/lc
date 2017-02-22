package kr.co.famos.not.control.ftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import kr.co.famos.not.control.main.Loading;

/**
 * <code>FtpConnect.java</code>
 * 
 * @company : FAMOS
 * @Description : FTP 컨넥션 모듈 
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.01.31
 * @version : 1.0
 */

public class FtpConnect {

    // 서버 연결 확인 유무
    public static boolean ConnectSuccess(FTPClient ftpClient, String sServer, int iPort) {
        int reply = 0;
        try {
            ftpClient.connect(sServer, iPort);
            // 연결 시도후, 성공했는지 응답 코드 확인
            reply = ftpClient.getReplyCode();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return (FTPReply.isPositiveCompletion(reply));
    }

    // 서버로 연결
    public static FTPClient Connect(String sServer, int iPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(sServer, iPort);
            int reply;

            // 연결 시도후, 성공했는지 응답 코드 확인
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                JOptionPane.showMessageDialog(null, "FTP 서버 연결 거부.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                // logger.info("****************************************************************");
                // logger.info(sServer+" = FTP 서버 연결");
            }

        } catch (IOException ioe) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException f) {
                    System.out.println(sServer + f.getMessage());
                }
            }
            System.out.println(sServer + ioe.getMessage());
        }
        return ftpClient;
    }

    // 계정과 패스워드로 로그인
    public static boolean Login(FTPClient ftpClient, String sServer, String sId, String sPwd, Loading ld) {

        try {
            // logger.info(sServer+" = FTP 서버 로그인");
            ftpClient.connect(sServer);
            return ftpClient.login(sId, sPwd);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "FTP connet login error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            ld.setVisible(false);
            ld.dispose();
        }
        return false;
    }

    // 서버로부터 로그아웃
    public static boolean Logout(FTPClient ftpClient, String sServer) {

        try {
            // logger.info(sServer+" = FTP 서버 로그아웃");
            ftpClient.connect(sServer);
            return ftpClient.logout();
        } catch (IOException ioe) {
            System.out.println(sServer + ioe.getMessage());
        }
        return false;
    }

    // 서버로부터 연결을 닫는다
    public static void disconnect(FTPClient ftpClient, String sServer) {

        try {
            ftpClient.disconnect();
            // logger.info(sServer+" = FTP 서버 연결 종료");
            // logger.info("****************************************************************
            // \n\n");
        } catch (IOException ioe) {
            System.out.println(sServer + ioe.getMessage());
            System.out.println("----------------------------------------------------------------------------------\n\n");
            // ioe.printStackTrace();
        }
    }
}
