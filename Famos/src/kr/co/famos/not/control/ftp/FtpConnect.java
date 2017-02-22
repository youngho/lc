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
 * @Description : FTP ���ؼ� ��� 
 * @author : ������ ����
 * @modify :
 * @since : 2017.01.31
 * @version : 1.0
 */

public class FtpConnect {

    // ���� ���� Ȯ�� ����
    public static boolean ConnectSuccess(FTPClient ftpClient, String sServer, int iPort) {
        int reply = 0;
        try {
            ftpClient.connect(sServer, iPort);
            // ���� �õ���, �����ߴ��� ���� �ڵ� Ȯ��
            reply = ftpClient.getReplyCode();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return (FTPReply.isPositiveCompletion(reply));
    }

    // ������ ����
    public static FTPClient Connect(String sServer, int iPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(sServer, iPort);
            int reply;

            // ���� �õ���, �����ߴ��� ���� �ڵ� Ȯ��
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                JOptionPane.showMessageDialog(null, "FTP ���� ���� �ź�.", "����", JOptionPane.ERROR_MESSAGE);
            } else {
                // logger.info("****************************************************************");
                // logger.info(sServer+" = FTP ���� ����");
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

    // ������ �н������ �α���
    public static boolean Login(FTPClient ftpClient, String sServer, String sId, String sPwd, Loading ld) {

        try {
            // logger.info(sServer+" = FTP ���� �α���");
            ftpClient.connect(sServer);
            return ftpClient.login(sId, sPwd);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "FTP connet login error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            ld.setVisible(false);
            ld.dispose();
        }
        return false;
    }

    // �����κ��� �α׾ƿ�
    public static boolean Logout(FTPClient ftpClient, String sServer) {

        try {
            // logger.info(sServer+" = FTP ���� �α׾ƿ�");
            ftpClient.connect(sServer);
            return ftpClient.logout();
        } catch (IOException ioe) {
            System.out.println(sServer + ioe.getMessage());
        }
        return false;
    }

    // �����κ��� ������ �ݴ´�
    public static void disconnect(FTPClient ftpClient, String sServer) {

        try {
            ftpClient.disconnect();
            // logger.info(sServer+" = FTP ���� ���� ����");
            // logger.info("****************************************************************
            // \n\n");
        } catch (IOException ioe) {
            System.out.println(sServer + ioe.getMessage());
            System.out.println("----------------------------------------------------------------------------------\n\n");
            // ioe.printStackTrace();
        }
    }
}
