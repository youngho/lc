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
 * @Description : ���� ��� (����)
 * @author : ������ ����
 * @modify :
 * @since : 2017.02.07
 * @version : 1.0
 */

public class ServerHelper_20170213_backup2 implements Runnable {
    
    public void run() {
        ServerSocket serverSocket = null; //������ ����
        int port = 10001; //��Ʈ ��ȣ
        
        Socket socket = null;
        
        PrintWriter pw = null;
        BufferedReader br = null;
        
        try {
            serverSocket = new ServerSocket(port); //��Ʈ�� ���������� ���δ�(Bind)
            socket = serverSocket.accept(); // Ŭ���̾�Ʈ�� ������ �㰡�Ѵ�.(Accept)

            OutputStream out = socket.getOutputStream(); //Ŭ���̾�Ʈ ������ ����Ʈ ��Ʈ���� �����´�.
            InputStream in = socket.getInputStream(); //Ŭ���̾�Ʈ ������ ����Ʈ ��Ʈ���� �Է��Ѵ�.

            pw = new PrintWriter(new OutputStreamWriter(out));
            br = new BufferedReader(new InputStreamReader(in));

            String recvStr = null; //���� ���ڿ�
            
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
                        // SRQKIND#08 or SRQKIND#10 ���� ����.
                        cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(end_time_dt), false);
                    } else {
                        // SRQKIND#08 or SRQKIND#10 ���� ����.
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

                    cu.Sequence(bin_back_dt);

                    ftpModule.re_test_end_exit = false;
                    ftpModule.FtpServerSend(0);

                    while (true) {
                        if (ftpModule.re_test_end_exit) {
                            break;
                        }
                    }

                    if (MainDual.main_radio_st1.isSelected()) {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "RETEST", false);

                        // SRQKIND#08 or SRQKIND#10 ���� ����.
                        String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h1.dat", true);
                        int count = Integer.parseInt(test_counter.trim()) + 1;
                        cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", String.valueOf(count), false);
                    } else {
                        cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "RETEST", false);
                        String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h2.dat", true);
                        int count = Integer.parseInt(test_counter.trim()) + 1;
                        cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", String.valueOf(count), false);
                    }

                }
                
                if (srq_10) {

                    cu.Sequence(bin_back_dt);

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

                    MainDual.while_break = true;
                }
                
                pw.println(recvStr);    //�޽����� Ŭ���̾�Ʈ���� ����
                pw.flush(); //���۸� ���
            }
            
            pw.close();     //��Ʈ�� �ݱ�
            br.close();     //���� �ݱ�
            socket.close(); //���� �ݱ�
            System.out.println("@@@@@@@@@@@LC  ���� ����@@@@@@@@@@@@@");
        } catch (Exception e) {
            System.out.println("���� ���� :: " + e);
            e.printStackTrace(); //���� ó��
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
