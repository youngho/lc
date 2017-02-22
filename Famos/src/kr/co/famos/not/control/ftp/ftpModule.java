package kr.co.famos.not.control.ftp;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import kr.co.famos.not.control.main.MainDual;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>ftpModule.java</code>
 * 
 * @company : FAMOS
 * @Description : FTP �۾� ���� ���
 * @author : ������ ����
 * @modify :
 * @since : 2017.01.31
 * @version : 1.0
 */

public class ftpModule {
    public static void main(String[] args) {
        ftpModule fm = new ftpModule();
        
        try {
            fm.delete();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
    
    
    // ������ �����ϴ� �޼ҵ� 20170124
    public static void fileCopy(String inFileName, String outFileName) {
        try {
            FileInputStream fis = new FileInputStream(inFileName);
            FileOutputStream fos = new FileOutputStream(outFileName);

            int data = 0;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }
            fis.close();
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // ���� ���ε� �Ѵ�
    public static boolean put(FTPClient ftpClient, String remoteDir, String localFiles, String fileName) {
        boolean isSuccess = false;

        try {
            
            //ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            FileInputStream fis = null;

            //�ѱ����ϸ��� Upload�ϱ����ؼ� ����
            String tempFileName = new String(fileName.getBytes("euc-kr"), "iso_8859_1");
            File put_file = new File(localFiles + fileName);
            fis = new FileInputStream(put_file);
            isSuccess = ftpClient.storeFile(remoteDir + tempFileName, fis);

            if (!isSuccess) {
                MainDual.appendToPane(MainDual.main_log_textPane, "���� ���ε� ����\n", Color.RED);
                JOptionPane.showMessageDialog(null, "FTP ���� ���ε� ����", "����", JOptionPane.ERROR_MESSAGE);
                if (fis != null) {
                    fis.close();
                }
                return false;
            }

            if (fis != null) {
                fis.close();
                put_file.delete();
            }
        } catch (FileNotFoundException e) {
            MainDual.appendToPane(MainDual.main_log_textPane, e.getMessage() + "\n", Color.RED);
            return isSuccess;
        } catch (IOException ie) {
            MainDual.appendToPane(MainDual.main_log_textPane, ie.getMessage() + "\n", Color.RED);
            return isSuccess;
        } catch (Exception ie) {
            MainDual.appendToPane(MainDual.main_log_textPane, ie.getMessage() + "\n", Color.RED);
            return isSuccess;
        }

        return isSuccess;

    }
    
    // ���� �ٿ�ε�
    public static boolean ftpDown(FTPClient ftpClient, String source, String target, String name) {

        boolean flag = false;

        OutputStream output = null;
        try {
            // �޴� ���� ���� �� ��ġ�� �� �̸����� ���� �����ȴ�
            File local = new File(target, name);
            output = new FileOutputStream(local);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "�ٿ�ε��� ���丮 ����", "����", JOptionPane.ERROR_MESSAGE);
            return flag;
        }

        File file = new File(source);
        try {
            if (ftpClient.retrieveFile(source, output)) {
                flag = true;
            }
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "���� �ٿ�ε� ����", "����", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
    
    // ���� ���� ����Ʈ
    public static List subDirList(String folder) {

        List listFTP = new ArrayList();

        File dir = new File(folder);
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            if (file.isFile()) {
                listFTP.add(file.getName());
            }
        }
        return listFTP;
    }
    
    // ���� ���� FTP ���� ������ ����
    public static boolean re_test_end_exit = false;

    public static void FtpServerSend(int re_set) {

        List ll = subDirList(PathProperties.ftppre);
        FTPClient ftpClient = new FTPClient();

        boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
        boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, null);
        
        if (connet && connet_login) {
            
            Iterator iterator = ll.iterator();
            while (iterator.hasNext()) {
                String element = (String) iterator.next();
                ftpModule.put(ftpClient, PathProperties.ftpdir, PathProperties.ftppre, element);
            }
            
            FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
            FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // ������ ���� ������ ���´�.

            if (re_set == 1) {
                CommonUtil.data_reset();
            }

            re_test_end_exit = true;
        } else {
            String[] buttons = { "RE_TRY", "EXIT" };
            int choice = JOptionPane.showOptionDialog(null, "Network Down !!!!", "ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, buttons, "RE_TRY");

            // interpret the user's choice
            if (choice == JOptionPane.YES_OPTION) {
                FtpServerSend(re_set);
            }

            if (choice == JOptionPane.NO_OPTION) {
                re_test_end_exit = true;
            }
        }
    }
    
    // FTP ���� ����
    public static void delete() throws Exception {
        FTPClient ftpClient = new FTPClient();

        boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
        boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, null);

        if (connet && connet_login) {

            FTPFile[] ftpfiles = ftpClient.listFiles("/BETS/"); // public ������ ��� ������ list �մϴ�
            if (ftpfiles != null) {
                for (int i = 0; i < ftpfiles.length; i++) {
                    FTPFile file = ftpfiles[i];
                    ftpClient.deleteFile(PathProperties.ftpdir + file.getName());
                }
            }

            FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
            FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // ������ ���� ������ ���´�.
        }
    }

        
    private static void Logout(FTPClient ftpClient, String ftpurl) {
        // TODO Auto-generated method stub
        
    }
    
    
}
