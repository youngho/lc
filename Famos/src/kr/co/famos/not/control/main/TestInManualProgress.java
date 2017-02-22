package kr.co.famos.not.control.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import kr.co.famos.not.control.ftp.FtpConnect;
import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.main.TestInManualProgress.TestInManualTask;
import kr.co.famos.not.control.socket.ServerHelper;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

public class TestInManualProgress {
    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new TestInManualTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class TestInManualTask {
        Loading ld;

        TestInManualTask() {

            CommonUtil cu = new CommonUtil();

            // 시작 날자 종료 날자 선언
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dt = new Date();

            if (MainDual.main_radio_st1.isSelected()) {
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h1.dat", "MANUAL", false);
                // ----> 현재 LOT LOT_IN 시간.(LC에서 LOT_ID 입력후 OK button을 push한 시점)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(dt), false);
                // LC 현제 진행 상태
                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "TEST_IN_MANUAL", false);
            } else {
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h2.dat", "MANUAL", false);
                // ----> 현재 LOT LOT_IN 시간.(LC에서 LOT_ID 입력후 OK button을 push한 시점)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(dt), false);
                // LC 현제 진행 상태
                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "TEST_IN_MANUAL", false);
            }

            String input_lot_no = TestInManualPop.test_in_manua_lot_number_text.getText();
            String bets_part_number = TestInManualPop.test_in_manua_part_number_text.getText();
            String bets_process_code = TestInManualPop.test_in_manua_process_code_text.getText();
            String bets_grade_code = TestInManualPop.test_in_manua_grade_text.getText();
            String bets_fab_code = TestInManualPop.test_in_manua_fab_text.getText();
            String bets_test_temp = TestInManualPop.test_in_manua_temp_text.getText();
            String bets_quantity = TestInManualPop.test_in_manua_qty_text.getText();

            // 소켓통신
            Socket sock = null;
            BufferedReader br = null;
            PrintWriter pw = null;

            try {
                MainDual.loading_flg = false;

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                // 소켓 연결
                sock = new Socket(MainDual.socket_ip, Integer.parseInt(MainDual.socket_port));
                pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                // FTP 연결 확인
                FTPClient ftpClient = new FTPClient();

                boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
                if (!connet) {
                    JOptionPane.showMessageDialog(null, "FTP connection error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, ld);
                if (!connet_login) {
                    JOptionPane.showMessageDialog(null, "FTP connet login error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                // 서버로 입력받은 id 전송
                //                 pw.println(
                //                   "<MSG>\n"
                //                     + "<SYSTEM>OPIRUS</SYSTEM>\n"                   
                //                     + "<CMD>PROGRAM_LOAD</CMD>\n"                   
                //                     + "<LOTID>"+TestInAutoPop.test_in_auto_text.getText() +"</LOTID>\n"                    
                //                     + "<PARTNUMBER>" +bets_part_number + "</PARTNUMBER>\n"  
                //                     + "<PROCESS>" +bets_process_code+ "</PROCESS>\n"                
                //                     + "<MODEL>T5503</MODEL>\n"                      
                //                     + "<HANDLER>TW350HT</HANDLER>\n"                
                //                     + "<TESTER></TESTER>\n"                     
                //                     + "<HEAD></HEAD>\n"                            
                //                     + "<TYPE></TYPE>\n"                           
                //                     + "<TMODE></TMODE>\n"                        
                //                     + "<BOARD></BOARD>\n"                           
                //                     + "<PARA></PARA>\n"                             
                //                     + "<PRE_PGM></PRE_PGM>\n"                
                //                     + "<PRE_REV></PRE_REV>\n"                      
                //                     + "<TAR_FILE_CHK></TAR_FILE_CHK>\n"           
                //                     + "<NCF_CODE></NCF_CODE>\n"                  
                //                     + "<HMODE></HMODE>\n"                   
                //                     + "<FSST_USING></FSST_USING>\n"               
                //                     + "<SCKCTRL></SCKCTRL>\n"                     
                //                     + "<PURPOSE_TYPE></PURPOSE_TYPE>\n"            
                //                     + "<FAB>" + bets_fab_code + "</FAB>\n"                              
                //                     + "<GRADE>"+bets_grade_code+"</GRADE>\n"                          
                //                     + "<OPERATOR></OPERATOR>\n"                    
                //                     + "<QTY>" + bets_quantity + "</QTY>\n"
                //                     + "<MANUAL>MANUAL</MANUAL>\n"                              
                //                     + "</MSG>\n"
                //                );

                SimpleDateFormat bets_in_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date bets_in_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h1.dat", bets_in_time_sdf.format(bets_in_time_dt), false);
                } else {
                    // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h2.dat", bets_in_time_sdf.format(bets_in_time_dt), false);
                }

                pw.println("<MSG>\n" + "<SYSTEM>OPIRUS</SYSTEM>\n" + "<CMD>PROGRAM_LOAD</CMD>\n" + "<LOTID>KGDTEST</LOTID>\n" + "<PARTNUMBER>H5TQXXXXXXX-XXX</PARTNUMBER>\n" + "<PROCESS>T092RWK</PROCESS>\n" + "<MODEL>T5503</MODEL>\n"
                        + "<HANDLER>STH5600</HANDLER>\n" + "<TESTER>kd00</TESTER>\n" + "<HEAD>A</HEAD>\n" + "<TYPE>PP</TYPE>\n" + "<TMODE>NOR</TMODE>\n" + "<BOARD></BOARD>\n" + "<PARA></PARA>\n" + "<PRE_PGM>000.016</PRE_PGM>\n"
                        + "<PRE_REV>0</PRE_REV>\n" + "<TAR_FILE_CHK>NO</TAR_FILE_CHK>\n" + "<NCF_CODE>---</NCF_CODE>\n" + "<HMODE>CONTINUE</HMODE>\n" + "<FSST_USING>NO</FSST_USING>\n" + "<SCKCTRL>NO</SCKCTRL>\n"
                        + "<PURPOSE_TYPE>-</PURPOSE_TYPE>\n" + "<FAB>-</FAB>\n" + "<GRADE>-</GRADE>\n" + "<OPERATOR>-</OPERATOR>\n" + "<QTY>-</QTY>\n" + "</MSG>");

                pw.flush();

                // 서버로부터 입력받은 문자열 출력하는 스레드 생성
                BetsThread btd = new BetsThread(sock, br);
                btd.start();

                while (true) {
                    if (MainDual.thread_stop) {
                        break;
                    }
                }

                sock.close();

                SimpleDateFormat bets_end_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date bets_end_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점에서,LC가 BETS에 LOT 정보 통신 완료시점을  뺀값
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h1.dat", bets_end_time_sdf.format(bets_end_time_dt), false);
                } else {
                    //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점에서,LC가 BETS에 LOT 정보 통신 완료시점을  뺀값
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h2.dat", bets_end_time_sdf.format(bets_end_time_dt), false);
                }

                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.main_lotno_text_st1.setText(input_lot_no);
                    MainDual.main_partnumber_text_st1.setText(bets_part_number);
                    MainDual.main_processcode_text_st1.setText(bets_process_code);
                    MainDual.main_fab_text_st1.setText(bets_fab_code);
                    MainDual.main_grade_text_st1.setText(bets_grade_code);
                    MainDual.main_temp_text_st1.setText(bets_test_temp);
                    MainDual.main_qty_text_st1.setText(bets_quantity);
                    
                    cu.FileNew(PathProperties.local_Header, "partnumber_h1.dat" , (String) MainDual.info_map_st1.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_h1.dat"    , (String) MainDual.info_map_st1.get("PROCESS"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h1.dat"        , (String) MainDual.info_map_st1.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h1.dat"      , (String) MainDual.info_map_st1.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temp_h1.dat"       , (String) MainDual.info_map_st1.get("TEMP"), false);
                    cu.FileNew(PathProperties.local_Header, "qty_h1.dat"        , (String) MainDual.info_map_st1.get("QTY"), false);
                } else {
                    MainDual.main_lotno_text_st2.setText(input_lot_no);
                    MainDual.main_partnumber_text_st2.setText(bets_part_number);
                    MainDual.main_processcode_text_st2.setText(bets_process_code);
                    MainDual.main_fab_text_st2.setText(bets_fab_code);
                    MainDual.main_grade_text_st2.setText(bets_grade_code);
                    MainDual.main_temp_text_st2.setText(bets_test_temp);
                    MainDual.main_qty_text_st2.setText(bets_quantity);
                    
                    cu.FileNew(PathProperties.local_Header, "partnumber_h2.dat" , (String) MainDual.info_map_st2.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_h2.dat"    , (String) MainDual.info_map_st2.get("PROCESS"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h2.dat"        , (String) MainDual.info_map_st2.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h2.dat"      , (String) MainDual.info_map_st2.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temp_h2.dat"       , (String) MainDual.info_map_st2.get("TEMP"), false);
                    cu.FileNew(PathProperties.local_Header, "qty_h2.dat"        , (String) MainDual.info_map_st2.get("QTY"), false);
                }

                if (connet && connet_login) {

                    MainDual.appendToPane(MainDual.main_log_textPane, "Start downloading FTP files...\n", Color.BLACK);
                    /*
                     * 1. ftpClient 접속 생성자 2. FTP파일 주소 3. 로컬 저장 경로 4. 저장할 파일 이름
                     */
                    if (MainDual.main_radio_st1.isSelected()) {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st1.get("TAR_PGM"), PathProperties.ftplocal, (String) MainDual.info_map_st1.get("TAR_PGM"));
                    } else {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st2.get("TAR_PGM"), PathProperties.ftplocal, (String) MainDual.info_map_st2.get("TAR_PGM"));
                    }

                    FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
                    FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // 서버로 부터 접속을 끝는다.
                    MainDual.appendToPane(MainDual.main_log_textPane, "FTP file download complete...\n", Color.BLACK);

                    SimpleDateFormat lot_in_end_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date lot_in_end_time_dt = new Date();
                    if (MainDual.main_radio_st1.isSelected()) {
                        //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점
                        cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h1.dat", lot_in_end_time_sdf.format(lot_in_end_time_dt), false);
                    } else {
                        //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점
                        cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h2.dat", lot_in_end_time_sdf.format(lot_in_end_time_dt), false);
                    }

                } else {
                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : FTP connection error!!\n", Color.RED);
                    JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                // SEQ4 1.BETS로 부터 전달 받은 Test Program을 T5503환경에 Up-Load실시
                // ==> 압축 풀기 : exec "tar xvf xxxx.tar"
                MainDual.appendToPane(MainDual.main_log_textPane, "exec tar xvf xxxx.tar \n", Color.BLACK);
                // Runtime.getRuntime().exec("tar xvf xxxx.tar");

                // ==> TEST PROGRAM 폴더 경로 설정 : exec "fscd
                if (MainDual.main_radio_st1.isSelected()) {

                    //Runtime.getRuntime().exec("fstmode --station 1 --auto");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fstmode --station 1 --auto\n", Color.BLACK);

                    // Runtime.getRuntime().exec("fscd --station1/home/fsdiag/HTM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fscd --station1/home/fsdiag/HTM\n", Color.BLACK);
                    // ==> TEST MAIN PROGRAM UP-LOAD : exec
                    // "fsproset --station 1 HT8GTSTO.pro"
                    // Runtime.getRuntime().exec("fsproset --station 1 HT8GTSTO.pro");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsproset --station 1 HT8GTSTO.pro\n", Color.BLACK);

                    // ==> exec "ls *.soc > soc.dat" ==>
                    // T5503256.soc
                    // soc.data를 soc로 변환
                    // Runtime.getRuntime().exec("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat\n", Color.BLACK);

                    // ==> TEST SOCKET PROGRAM UP-LOAD :
                    // exec
                    // "fsconf --station 1 T5503256.soc"
                    // Runtime.getRuntime().exec("fsconf --station 1 T5503256.soc");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // Runtime.getRuntime().exec("cp /home/fsdiag/SBCAL/*.cal .");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec cp /home/fsdiag/SBCAL/*.cal . \n", Color.BLACK);

                    String stationOnOff = (String) MainDual.info_map_st1.get("FUNCTION KEY");

                    if (stationOnOff != null) {
                        for (int i = 0; i < 16; i++) {
                            if (stationOnOff.charAt(i) == 'Y') {
                                // Runtime.getRuntime().exec("fsfk --station 1 --on " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 1 --on" + (i + 1) + " \n", Color.BLACK);
                            } else {
                                // Runtime.getRuntime().exec("fsfk --station 2 --off " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 2 --on" + (i + 1) + " \n", Color.BLACK);
                            }
                        }
                    }

                } else {

                    // Runtime.getRuntime().exec("fstmode --station 2 --auto");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fstmode --station 2 --auto . \n", Color.BLACK);

                    // Runtime.getRuntime().exec("fscd --station 2 /home/fsdiag/HTM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fscd --station 2 /home/fsdiag/HTM \n", Color.BLACK);

                    // Runtime.getRuntime().exec("fsproset --station 1 HT8GTSTO.pro");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsproset --station 1 HT8GTSTO.pro \n", Color.BLACK);

                    // Runtime.getRuntime().exec("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat \n", Color.BLACK);

                    // Runtime.getRuntime().exec("fsconf --station 1 T5503256.soc");
                    // textArea.setText("exec fsconf --station 1 T5503256.soc" + "\r\n");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // Runtime.getRuntime().exec("cp /home/fsdiag/SBCAL/*.cal .");
                    // textArea.setText("exec cp /home/fsdiag/SBCAL/*.cal ." + "\r\n");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec cp /home/fsdiag/SBCAL/*.cal . \n", Color.BLACK);

                    for (int i = 0; i < 16; i++) {
                        String stationOnOff = (String) MainDual.info_map_st2.get("FUNCTION KEY");

                        System.out.println();

                        if (stationOnOff != null) {
                            if (stationOnOff.charAt(i) == 'Y') {
                                // Runtime.getRuntime().exec("fsfk --station 1 --on " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "fsfk --station 1 --on " + (i + 1) + " \n", Color.BLACK);
                            } else {
                                // Runtime.getRuntime().exec("fsfk --station 1 --off " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "fsfk --station 1 --off " + (i + 1) + " \n", Color.BLACK);
                            }
                        }

                    }
                }

                // 2.BETS로 부터 전달 받은 Test Program을 T5503 지정된 폴드에 Back-Up실시
                if ("off".equals(MainDual.srqkind)) {
                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);

                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);
                    
//                    if (MainDual.main_radio_st1.isSelected()) {
//                        MainDual.station_step1 = "step_in_manual";
//                    } else {
//                        MainDual.station_step2 = "step_in_manual";
//                    }
                }

                // Handler Temp Check (Interlock 기능)
                // temp1.c  Library를 구동 시킨후 결과값을 H1TEMP.dat 파일로 담기
                // temp1.c는 별도 개발 예정.(GPIB  SRQ)

                //                String srq_handler_temp;
                //                String srq_handler_map;
                //                String srq_handler_para;

                //                Process temp_dat = Runtime.getRuntime().exec("/home/fsdiag/GPIB/temp1 > H1TEMP.dat");
                //
                //                BufferedReader in_temp_dat = new BufferedReader(new InputStreamReader(temp_dat.getInputStream()));
                //
                //                while (true) {
                //                    String out_temp_dat = in_temp_dat.readLine();
                //                    if (out_temp_dat == null) {
                //                        break;
                //                    } else {
                //                        srq_handler_temp = out_temp_dat;
                //                        System.out.println(out_temp_dat);
                //                    }
                //                }

                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);

                //                srq_handler_temp = "90";
                // DUT MAP
                //                srq_handler_map = "ASDF";
                // PARA 
                //                srq_handler_para = "256";

                //              ** 일단 주석 처리 : 온도 측정 하는 부분..     
                // BETS 에서 내려온 정보 BETS_TEST_TEMP가 기준 정보
                //              if (MainDual.main_radio_st1.isSelected()) {
                //
                //                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
                //                          && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
                //                                  && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
                //                          && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
                //                                  && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
                //                          && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
                //                                  && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //
                //              } else {
                //                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
                //                          && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
                //                                  && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
                //                          && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
                //                                  && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
                //                          && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) + 3)))
                //                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
                //                                  && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) - 3)))) {
                //                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
                //                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                      ld.setVisible(false);
                //                      ld.dispose();
                //                      return;
                //                  }
                //              }

                if (MainDual.srqkind.equals("on")) {
                    // Tester/Handler Start
//                    Thread shT = new Thread(new ServerHelper());
//                    shT.start();

                    MainDual.while_break = false;
                    while (true) {
                        if (MainDual.while_break)
                            break;
                    }

                    ld.setVisible(false);
                    ld.dispose();

                    BinSummaryPop BinSummaryPop = new BinSummaryPop(MainDual.main_frm_d);
                    BinSummaryPop.setVisible(true);
                } else {
                    // Tester/Handler Start
//                    Thread shT = new Thread(new ServerHelper());
//                    shT.start();

                    MainDual.while_break = false;
                    while (true) {
                        if (MainDual.while_break)
                            break;
                    }

                    ld.setVisible(false);
                    ld.dispose();
                }

                MainDual.loading_flg = true;

            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
