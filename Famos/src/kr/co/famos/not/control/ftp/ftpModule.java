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
 * @Description : FTP 작업 공통 모듈
 * @author : 김이주 차장
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
    
    
    // 파일을 복사하는 메소드 20170124
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

    // 파일 업로드 한다
    public static boolean put(FTPClient ftpClient, String remoteDir, String localFiles, String fileName) {
        boolean isSuccess = false;

        try {
            
            //ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            FileInputStream fis = null;

            //한글파일명을 Upload하기위해서 변경
            String tempFileName = new String(fileName.getBytes("euc-kr"), "iso_8859_1");
            File put_file = new File(localFiles + fileName);
            fis = new FileInputStream(put_file);
            isSuccess = ftpClient.storeFile(remoteDir + tempFileName, fis);

            if (!isSuccess) {
                MainDual.appendToPane(MainDual.main_log_textPane, "파일 업로드 실패\n", Color.RED);
                JOptionPane.showMessageDialog(null, "FTP 파일 업로드 실패", "오류", JOptionPane.ERROR_MESSAGE);
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
    
    // 파일 다운로드
    public static boolean ftpDown(FTPClient ftpClient, String source, String target, String name) {

        boolean flag = false;

        OutputStream output = null;
        try {
            // 받는 파일 생성 이 위치에 이 이름으로 파일 생성된다
            File local = new File(target, name);
            output = new FileOutputStream(local);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "다운로드할 디렉토리 없음", "오류", JOptionPane.ERROR_MESSAGE);
            return flag;
        }

        File file = new File(source);
        try {
            if (ftpClient.retrieveFile(source, output)) {
                flag = true;
            }
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "파일 다운로드 실패", "오류", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }
    
    // 로컬 파일 리스트
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
    
    // 로컬 서버 FTP 파일 서버로 전송
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
            FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // 서버로 부터 접속을 끝는다.

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
    
    // FTP 파일 삭제
    public static void delete() throws Exception {
        FTPClient ftpClient = new FTPClient();

        boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
        boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, null);

        if (connet && connet_login) {

            FTPFile[] ftpfiles = ftpClient.listFiles("/BETS/"); // public 폴더의 모든 파일을 list 합니다
            if (ftpfiles != null) {
                for (int i = 0; i < ftpfiles.length; i++) {
                    FTPFile file = ftpfiles[i];
                    ftpClient.deleteFile(PathProperties.ftpdir + file.getName());
                }
            }

            FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
            FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // 서버로 부터 접속을 끝는다.
        }
    }

        
    private static void Logout(FTPClient ftpClient, String ftpurl) {
        // TODO Auto-generated method stub
        
    }
    
    
}
