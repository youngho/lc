package kr.co.famos.not.control.socket;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class ServerHelper implements Runnable{
    
    ServerSocket myServerSocket;
    boolean ServerOn = true;
    
    public void run() {

        try {
            myServerSocket = new ServerSocket(10001);
        } catch (IOException ioe) {
            System.exit(-1);
        }

        while (ServerOn) {
            try {
                Socket clientSocket = myServerSocket.accept();
                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start();
            } catch (IOException ioe) {
                System.out.println("Exception found on accept. Ignoring. Stack Trace :");
                ioe.printStackTrace();
            }
        }

        try {
            myServerSocket.close();
            System.out.println("Server Stopped");
        } catch (Exception ioe) {
            System.out.println("Error Found stopping server socket");
            System.exit(-1);
        }

    }
    
//    public static void main(String[] args) {
//        new MultiThreadedSocketServer();
//    }

    class ClientServiceThread extends Thread {
        Socket myClientSocket;
        boolean m_bRunThread = true;

        public ClientServiceThread() {
            super();
        }

        ClientServiceThread(Socket s) {
            myClientSocket = s;
        }

        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            
            boolean srq_02 = false;
            boolean srq_08 = false;
            boolean srq_04 = false;
            boolean srq_10 = false;
            boolean srq_re = false;
            
            CommonUtil cu = new CommonUtil();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date bin_back_dt = null;
            try {
                in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream()));

                while (m_bRunThread) {
                    String recvStr = in.readLine();
                    MainDual.appendToPane(MainDual.main_log_textPane, "gpib ==>" + recvStr + "\n", Color.BLACK);
                    //SRQKIND = #02 Lot Start
                    //SRQKIND = #08 Lot End 
                    //SRQKIND = #04 Retest Start    
                    //SRQKIND = #10 Final Lot End   
                    //BINON       
                    
                    if (recvStr.indexOf("#02") > -1) {
                        Date start_time_dt = new Date();
                        bin_back_dt = new Date();

                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "lot_start_time_h1.dat", sdf.format(start_time_dt), false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "lot_start_time_h2.dat", sdf.format(start_time_dt), false);
                        }
                        
                        srq_02 = true;
                        
                        srq_08 = false;
                        srq_04 = false;
                        srq_10 = false;
                        srq_re = false;
                    }
                    
                    if (recvStr.indexOf("#08") > -1) {
                        srq_08 = true;
                        
                        srq_02 = false;
                        srq_04 = false;
                        srq_10 = false;
                        srq_re = false;
                    }

                    if (recvStr.indexOf("#04") > -1) {
                        bin_back_dt = new Date();
                        
                        srq_04 = true;
                        
                        srq_02 = false;
                        srq_08 = false;
                        srq_10 = false;
                        srq_re = false;
                    }

                    if (recvStr.indexOf("#10") > -1) {
                        srq_10 = true;
                        
                        srq_02 = false;
                        srq_04 = false;
                        srq_08 = false;
                        srq_re = false;
                    }
                    
                    
                    if (srq_02) {
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.ftpcasi, "HTA01_" + cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim() + "_CASI_REPORT_" + sdf.format(bin_back_dt) + ".HEADA", recvStr + "\n", true);
                        } else {
                            cu.FileNew(PathProperties.ftpcasi, "HTA01_" + cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim() + "_CASI_REPORT_" + sdf.format(bin_back_dt) + ".HEADB", recvStr + "\n", true);
                        }
                    }
                    
                    
                    if (srq_08) {
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            String prelot_end_time = cu.HederData(PathProperties.local_Header, "prelot_end_time_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "prelot_end_time_h1.dat").trim();
                            String sbl_result = cu.HederData(PathProperties.local_Header, "sbl_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_result_h1.dat").trim();
                            String sbl_yield_limit = cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h1.dat").trim();
                            String sbl_sub_bina_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat").trim();
                            String sbl_sub_bina_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat").trim();
                            String sbl_sub_binb_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat").trim();
                            String sbl_sub_binb_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat").trim();
                            String sbl_bin9_counter = cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h1.dat").trim();
                            String sbl_bin9_limit = cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h1.dat").trim();
                            String bin_in_time = cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat").trim();
                            String bin_end_time = cu.HederData(PathProperties.local_Header, "bin_end_time_h1.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_end_time_h1.dat").trim();
                            String sbl_bin8_limit = cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h1.dat").trim();
                            String sbl_yield_result = cu.HederData(PathProperties.local_Header, "sbl_yield_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_result_h1.dat").trim();
                            String sbl_bin1_result = cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h1.dat").trim();
                            String sbl_bin2_result = cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h1.dat").trim();
                            String sbl_bin3_result = cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h1.dat").trim();
                            String sbl_bin4_result = cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h1.dat").trim();
                            String sbl_bin5_result = cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h1.dat").trim();
                            String sbl_bin6_result = cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h1.dat").trim();
                            String sbl_bin7_result = cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h1.dat").trim();
                            String sbl_bin8_result = cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h1.dat").trim();
                            String sbl_bin9_result = cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h1.dat").trim();
                            
                            cu.FileNew(PathProperties.local_Header, "bin_in_time_h1.dat", bin_in_time, false);
                            cu.FileNew(PathProperties.local_Header, "bin_end_time_h1.dat", bin_end_time, false);
                            cu.FileNew(PathProperties.local_Header, "prelot_end_time_h1.dat", prelot_end_time, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h1.dat", sbl_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_limit_h1.dat", sbl_yield_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat", sbl_sub_bina_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat", sbl_sub_bina_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat", sbl_sub_binb_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat", sbl_sub_binb_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h1.dat", sbl_bin9_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_limit_h1.dat", sbl_bin9_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h1.dat", sbl_bin8_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h1.dat", sbl_yield_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin1_result_h1.dat", sbl_bin1_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin2_result_h1.dat", sbl_bin2_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin3_result_h1.dat", sbl_bin3_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin4_result_h1.dat", sbl_bin4_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin5_result_h1.dat", sbl_bin5_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin6_result_h1.dat", sbl_bin6_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin7_result_h1.dat", sbl_bin7_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h1.dat", sbl_bin8_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", sbl_bin9_result, false);
                                    
                        } else {
                            String prelot_end_time = cu.HederData(PathProperties.local_Header, "prelot_end_time_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "prelot_end_time_h2.dat").trim();
                            String sbl_result = cu.HederData(PathProperties.local_Header, "sbl_result_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "sbl_result_h2.dat").trim();
                            String sbl_yield_limit = cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h2.dat").trim();
                            String sbl_sub_bina_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat").trim();
                            String sbl_sub_bina_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat").trim();
                            String sbl_sub_binb_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat").trim();
                            String sbl_sub_binb_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat").trim();
                            String sbl_bin9_counter = cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h2.dat").trim();
                            String sbl_bin9_limit = cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h2.dat").trim();
                            String bin_in_time = cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat").trim();
                            String bin_end_time = cu.HederData(PathProperties.local_Header, "bin_end_time_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_end_time_h2.dat").trim();
                            String sbl_bin8_limit = cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h2.dat").trim();
                            String sbl_yield_result = cu.HederData(PathProperties.local_Header, "sbl_yield_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_result_h2.dat").trim();
                            String sbl_bin1_result = cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h2.dat").trim();
                            String sbl_bin2_result = cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h2.dat").trim();
                            String sbl_bin3_result = cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h2.dat").trim();
                            String sbl_bin4_result = cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h2.dat").trim();
                            String sbl_bin5_result = cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h2.dat").trim();
                            String sbl_bin6_result = cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h2.dat").trim();
                            String sbl_bin7_result = cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h2.dat").trim();
                            String sbl_bin8_result = cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h2.dat").trim();
                            String sbl_bin9_result = cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h2.dat").trim();
                            
                            cu.FileNew(PathProperties.local_Header, "bin_in_time_h2.dat", bin_in_time, false);
                            cu.FileNew(PathProperties.local_Header, "bin_end_time_h2.dat", bin_end_time, false);
                            cu.FileNew(PathProperties.local_Header, "prelot_end_time_h2.dat", prelot_end_time, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h2.dat", sbl_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_limit_h2.dat", sbl_yield_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat", sbl_sub_bina_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat", sbl_sub_bina_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat", sbl_sub_binb_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat", sbl_sub_binb_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h2.dat", sbl_bin9_counter, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_limit_h2.dat", sbl_bin9_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h2.dat", sbl_bin8_limit, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h2.dat", sbl_yield_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin1_result_h2.dat", sbl_bin1_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin2_result_h2.dat", sbl_bin2_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin3_result_h2.dat", sbl_bin3_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin4_result_h2.dat", sbl_bin4_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin5_result_h2.dat", sbl_bin5_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin6_result_h2.dat", sbl_bin6_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin7_result_h2.dat", sbl_bin7_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h2.dat", sbl_bin8_result, false);
                            cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", sbl_bin9_result, false);
                        }
                        
                        cu.Sequence(bin_back_dt, "0");

                        ftpModule.re_test_end_exit = false;
                        ftpModule.FtpServerSend(0);

                        while (true) {
                            if (ftpModule.re_test_end_exit) {
                                break;
                            }
                        }
                    }
                    
                    // 리테스트
                    if (srq_04) {
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "RE_TEST", false);
                            CommonUtil.ButtonConditionA();
                        } else {
                            cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "RE_TEST", false);
                            CommonUtil.ButtonConditionB();
                        }
                        
                        Date re_start_time_dt = new Date();
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "RETEST", false);
                            // SRQKIND#08 or SRQKIND#10 받은 시점.
                            cu.FileNew(PathProperties.local_Header, "lot_start_time_h1.dat", sdf.format(re_start_time_dt), false);
                            String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h1.dat", true);
                            int count = Integer.parseInt(test_counter.trim()) + 1;
                            cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", String.valueOf(count), false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "RETEST", false);
                            cu.FileNew(PathProperties.local_Header, "lot_start_time_h2.dat", sdf.format(re_start_time_dt), false);
                            String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h2.dat", true);
                            int count = Integer.parseInt(test_counter.trim()) + 1;
                            cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", String.valueOf(count), false);
                        }
                        
                        srq_04 = false;
                        srq_re = true;
                    }
                    
                    if (srq_re) {
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.ftpcasi, "HTA01_" + cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim() + "_CASI_REPORT_" + sdf.format(bin_back_dt) + ".HEADA", recvStr + "\n", true);
                        } else {
                            cu.FileNew(PathProperties.ftpcasi, "HTA01_" + cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim() + "_CASI_REPORT_" + sdf.format(bin_back_dt) + ".HEADB", recvStr + "\n", true);
                        }
                    }

                    if (srq_10) {
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "FINAL_END", false);
                            CommonUtil.ButtonConditionA();
                        } else {
                            cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "FINAL_END", false);
                            CommonUtil.ButtonConditionB();
                        }
                        
                        // cu.execExecution("/home/fsdiag/NLcommand_FS/LCommand.sh");
                        // 테스트
                        String handlerBinData = cu.FileReaderData(PathProperties.local, "SUM2.DAT", true);
                        // 삭제 :  cu.SubBinData(sub_bin_data);
                        
                        cu.HandlerbinCalculator(handlerBinData);
                        
                        Date end_time_dt = new Date();
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "FINAL", false);
                            cu.FileNew(PathProperties.local_Header, "end_time_h1.dat", sdf.format(end_time_dt), false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "FINAL", false);
                            cu.FileNew(PathProperties.local_Header, "end_time_h2.dat", sdf.format(end_time_dt), false);
                        }
                        
                        // cu.Sequence(bin_back_dt, "0");

                        ftpModule.re_test_end_exit = false;
                        ftpModule.FtpServerSend(0);

                        while (true) {
                            if (ftpModule.re_test_end_exit) {
                                break;
                            }
                        }

                        MainDual.while_break = true;
                    }

                    if (!ServerOn) {
                        System.out.print("Server has already stopped");
                        out.println("Server has already stopped");
                        out.flush();
                        m_bRunThread = false;
                    }

                    if (recvStr.equalsIgnoreCase("quit")) {
                        m_bRunThread = false;
                        System.out.print("Stopping client thread for client : ");
                    } else if (recvStr.equalsIgnoreCase("end")) {
                        m_bRunThread = false;
                        System.out.print("Stopping client thread for client : ");
                        ServerOn = false;
                    } else {
                        out.println(recvStr);
                        out.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    myClientSocket.close();
                    System.out.println("...Stopped");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}