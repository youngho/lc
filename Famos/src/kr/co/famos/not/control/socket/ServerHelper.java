package kr.co.famos.not.control.socket;

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

            boolean srq_08 = false;
            boolean srq_04 = false;
            boolean srq_10 = false;

            CommonUtil cu = new CommonUtil();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date bin_back_dt = null;
            try {
                in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream()));

                while (m_bRunThread) {
                    String recvStr = in.readLine();

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
                    }

                    if (recvStr.indexOf("#10") > -1) {
                        srq_10 = true;

                        srq_04 = false;
                        srq_08 = false;
                    }

                    if (srq_08) {
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_08, "input_lotid" + MainDual.main_lotno_text_st1.getText() + "_casi_report-" + sdf.format(bin_back_dt) + ".HEADA", recvStr + "\n", true);
                        } else {
                            cu.FileNew(PathProperties.local_08, "input_lotid" + MainDual.main_lotno_text_st2.getText() + "_casi_report-" + sdf.format(bin_back_dt) + ".HEADB", recvStr + "\n", true);
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

                            // SRQKIND#08 or SRQKIND#10 받은 시점.
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