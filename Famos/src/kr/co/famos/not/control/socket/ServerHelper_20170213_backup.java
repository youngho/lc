package kr.co.famos.not.control.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.main.MainDual;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>ServerHelper.java</code>
 * 
 * @company : FAMOS
 * @Description : 소켓 통신 (서버)
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.07
 * @version : 1.0
 */

public class ServerHelper_20170213_backup implements Runnable {
    
    public void run() {
        ServerSocket serverSocket = null; //서버의 소켓
        int port = 10001; //포트 번호
        
        Socket socket = null;
        
        PrintWriter pw = null;
        BufferedReader br = null;
        
        try {
            serverSocket = new ServerSocket(port); //포트에 서버소켓을 붙인다(Bind)
            socket = serverSocket.accept(); // 클라이언트의 접속을 허가한다.(Accept)

            OutputStream out = socket.getOutputStream(); //클라이언트 소켓의 바이트 스트림을 가져온다.
            InputStream in = socket.getInputStream(); //클라이언트 소켓의 바이트 스트림을 입력한다.

            pw = new PrintWriter(new OutputStreamWriter(out));
            br = new BufferedReader(new InputStreamReader(in));

            String recvStr = null; //받은 문자열
            
            boolean srq_08 = false;
            boolean srq_04 = false;
            boolean srq_10 = false;
            
            CommonUtil cu = new CommonUtil();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date bin_back_dt = null;
            
            
            while ((recvStr = br.readLine()) != null) {
                
                //SRQKIND = #08   Lot End 
                //SRQKIND = #04   Retest Start    
                //SRQKIND = #10   Final Lot End   
                //BINON       
                
                if (recvStr.indexOf("#08") > -1) {
                    
                    Date end_time_dt = new Date();
                    bin_back_dt = new Date();
                    

                    if (MainDual.main_radio_st1.isSelected()) {
                        // SRQKIND#08 or SRQKIND#10 받은 시점.
                        cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(end_time_dt), false);
                    } else {
                        // SRQKIND#08 or SRQKIND#10 받은 시점.
                        cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(end_time_dt), false);
                    }
                    srq_08 = true;
                    
                    srq_04 = false;
                    srq_10 = false;
                }
                
                if (recvStr.indexOf("#04") > -1) {
                    srq_04 = true;
                    
                    srq_08 = false;
                    srq_10 = false;
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        // SRQKIND#08 or SRQKIND#10 받은 시점.
                        String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h1.dat", true);
                        int count = Integer.parseInt(test_counter.trim()) + 1;
                        cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", String.valueOf(count), false);
                    } else {
                        // SRQKIND#08 or SRQKIND#10 받은 시점.
                        String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h2.dat", true);
                        int count = Integer.parseInt(test_counter.trim()) + 1;
                        cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", String.valueOf(count), false);
                    }
                }
                
                if (recvStr.indexOf("#10") > -1) {
                    srq_10 = true;
                    
                    srq_04 = false;
                    srq_08 = false;
                }
                
                if (srq_08) {
                    if (MainDual.main_radio_st1.isSelected()) {
                        cu.FileNew(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA" , recvStr+"\n", true);
                    } else {
                        cu.FileNew(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB" , recvStr+"\n", true);
                    }
                }
                
                if (srq_04) {
                    
                    MainDual.header_data = "";
                    // 헤더 정보 넣고
                    cu.Headerdata();
                    
                    // 가시데이터 가져와서 넣기
                    if (MainDual.main_radio_st1.isSelected()) {
                        String header_data = MainDual.header_data;
                        String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", false);
                        header_data += "\n"+bin_data; 
                        cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpcasi+ "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA");
                    } else {
                        String header_data = MainDual.header_data;
                        String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", false);
                        header_data += "\n"+bin_data;
                        cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpcasi + "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", PathProperties.ftppre + MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB");
                    }
                    
                    // 칩데이터 생성
                    if (MainDual.main_radio_st1.isSelected()) {
                        // 칩데이터 생성
                        String header_data = MainDual.header_data;
                        
                        // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADA", true);
                        header_data += "\n" + chipid_data;
                        cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADA");
                    } else {
                        // 칩데이터 생성
                        
                        String header_data = MainDual.header_data;
                        // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADB", true);
                        header_data += "\n" + chipid_data;
                        cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADB");
                    }
                    
                    // 서브빈
                    if (MainDual.main_radio_st1.isSelected()) {
                        String header_data = MainDual.header_data;
                        
                        // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADA", true);
                        header_data += "\n" + sub_bin_data;
                        cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADA");
                    } else {
                        String header_data = MainDual.header_data;
                        // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADB", true);
                        header_data += "\n" + sub_bin_data;
                        cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADB");
                    }
                    
                    ftpModule.re_test_end_exit = false;
                    ftpModule.FtpServerSend(0);
                    
                    while (true) {
                        if (ftpModule.re_test_end_exit) {
                            break;
                        }
                    }
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "RETEST", false);
                    } else {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "RETEST", false);
                    }
                    
                }
                
                if (srq_10) {
                    // 헤더 정보 넣고
                    MainDual.header_data = "";
                    
                    cu.Headerdata();
                    
                    // 빈정보 가져와서 넣기
                    if (MainDual.main_radio_st1.isSelected()) {
                        String header_data = MainDual.header_data;
                        String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", false);
                        header_data += "\n"+bin_data; 
                        cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpcasi+ "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA");
                    } else {
                        String header_data = MainDual.header_data;
                        String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", false);
                        header_data += "\n"+bin_data;
                        cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpcasi + "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", PathProperties.ftppre + MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB");
                    }
                    
                    // 칩데이터 생성
                    if (MainDual.main_radio_st1.isSelected()) {
                        // 칩데이터 생성
                        String header_data = MainDual.header_data;
                        
                        // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADA", true);
                        header_data += "\n" + chipid_data;
                        cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADA");
                    } else {
                        // 칩데이터 생성
                        
                        String header_data = MainDual.header_data;
                        // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADB", true);
                        header_data += "\n" + chipid_data;
                        cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADB");
                    }
                    
                    // 서브빈
                    if (MainDual.main_radio_st1.isSelected()) {
                        String header_data = MainDual.header_data;
                        
                        // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADA", true);
                        header_data += "\n" + sub_bin_data;
                        cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADA", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADA");
                    } else {
                        
                        String header_data = MainDual.header_data;
                        // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                        // 테스트
                        String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADB", true);
                        header_data += "\n" + sub_bin_data;
                        cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADB", header_data, true);
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADB");
                    }
                    
                    ftpModule.re_test_end_exit = false;
                    ftpModule.FtpServerSend(0);
                    
                    while (true) {
                        if (ftpModule.re_test_end_exit) {
                            break;
                        }
                    }
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "FINAL", false);
                    } else {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "FINAL", false);
                    }
                    
                    break;
                }
                
                pw.println(recvStr);    //메시지를 클라이언트에게 전송
                pw.flush(); //버퍼를 비움
            }
            
            pw.close(); //스트림 닫기
            br.close(); //버퍼 닫기
            socket.close(); //소켓 닫기
            MainDual.while_break = true;
        } catch (Exception e) {
            System.out.println("소켓 종료 :: " + e);
            e.printStackTrace(); //예외 처리
        }  finally {
            try { if (pw != null) pw.close();
                } catch (Exception ex) {}
            try { if (br != null) br.close();
                } catch (Exception ex) {}
            try { if (socket != null) socket.close();
                } catch (Exception ex) {}
        }  
    }
}
