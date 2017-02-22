package kr.co.famos.not.control.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

import kr.co.famos.not.control.ftp.FtpConnect;
import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.socket.ServerHelper;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

public class PrePgmRestoreProgress {
    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new PrePgmRestoreTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class PrePgmRestoreTask {
        Loading ld;

        PrePgmRestoreTask() {

            CommonUtil cu = new CommonUtil();

            // 시작 날자 종료 날자 선언
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dt = new Date();

            if (MainDual.main_radio_st1.isSelected()) {
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h1.dat", "PRE", false);
                // ----> 현재 LOT LOT_IN 시간.(LC에서 LOT_ID 입력후 OK button을 push한 시점)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(dt), false);
                // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                cu.FileNew(PathProperties.local_Header, "bets_in_time_h1.dat", "0" , false);
                //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점에서,LC가 BETS에 LOT 정보 통신 완료시점을  뺀값
                cu.FileNew(PathProperties.local_Header, "bets_end_time_h1.dat", "0", false);
            } else {
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h2.dat", "PRE", false);
                // ----> 현재 LOT LOT_IN 시간.(LC에서 LOT_ID 입력후 OK button을 push한 시점)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(dt), false);
                // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                cu.FileNew(PathProperties.local_Header, "bets_in_time_h2.dat", "0" , false);
                //  LC가 BETS로 부터 TEST PROGRAM을 받은 시점에서,LC가 BETS에 LOT 정보 통신 완료시점을  뺀값
                cu.FileNew(PathProperties.local_Header, "bets_end_time_h2.dat", "0", false);
            }

            try {

                MainDual.loading_flg = false;

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.lot_no_st1 = PrePgmRestore.pre_pgm_restore_text.getText();
                } else {
                    MainDual.lot_no_st2 = PrePgmRestore.pre_pgm_restore_text.getText();
                }

                if (MainDual.main_radio_st1.isSelected()) {

                    MainDual.main_lotno_text_st1.setText(MainDual.lot_no_st1);
                    MainDual.main_partnumber_text_st1.setText(cu.HederData(PathProperties.ftplocal, "partnumber_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "partnumber_h1.dat").trim());
                    MainDual.main_processcode_text_st1.setText(cu.HederData(PathProperties.ftplocal, "process_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_h1.dat").trim());
                    MainDual.main_fab_text_st1.setText(cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim());
                    MainDual.main_grade_text_st1.setText(cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim());
                    MainDual.main_temp_text_st1.setText(cu.HederData(PathProperties.ftplocal, "temp_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temp_h1.dat").trim());
                    MainDual.main_qty_text_st1.setText(cu.HederData(PathProperties.ftplocal, "qty_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "qty_h1.dat"));

                } else {
                    MainDual.main_lotno_text_st2.setText(MainDual.lot_no_st2);
                    MainDual.main_partnumber_text_st2.setText(cu.HederData(PathProperties.ftplocal, "partnumber_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "partnumber_h2.dat").trim());
                    MainDual.main_processcode_text_st2.setText(cu.HederData(PathProperties.ftplocal, "process_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_h2.dat").trim());
                    MainDual.main_fab_text_st2.setText(cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim());
                    MainDual.main_grade_text_st2.setText(cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim());
                    MainDual.main_temp_text_st2.setText(cu.HederData(PathProperties.ftplocal, "temp_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temp_h2.dat").trim());
                    MainDual.main_qty_text_st2.setText(cu.HederData(PathProperties.ftplocal, "qty_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "qty_h2.dat"));

                }

                // SEQ4 1.BETS로 부터 전달 받은 Test Program을 T5503환경에 Up-Load실시
                // ==> 압축 풀기 : exec "tar xvf xxxx.tar"
                MainDual.appendToPane(MainDual.main_log_textPane, "exec tar xvf xxxx.tar \n", Color.BLACK);
                // Runtime.getRuntime().exec("tar xvf xxxx.tar");

                // ==> TEST PROGRAM 폴더 경로 설정 : exec "fscd

                if (MainDual.main_radio_st1.isSelected()) {

                    // cu.execExecution("fstmode --station 1 --auto");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fstmode --station 1 --auto\n", Color.BLACK);

                    // cu.execExecution("fscd --station1/home/fsdiag/HTM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fscd --station1/home/fsdiag/HTM\n", Color.BLACK);

                    // ==> TEST MAIN PROGRAM UP-LOAD : exec
                    // "fsproset --station 1 HT8GTSTO.pro"
                    // cu.execExecution("fsproset --station 1 HT8GTSTO.pro");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsproset --station 1 HT8GTSTO.pro\n", Color.BLACK);

                    // ==> exec "ls *.soc > soc.dat" ==>
                    // T5503256.soc
                    // soc.dat를 soc로 변환
                    // Runtime.getRuntime().exec("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat\n", Color.BLACK);

                    // soc.dat 파일 내용을 읽어서….. 
                    // String red_soc = cu.FileReader("1 /home/fsdiag/HTM", "soc.dat");

                    // ==> TEST SOCKET PROGRAM UP-LOAD :
                    // exec
                    // "fsconf --station 1 T5503256.soc"
                    // cu.execExecution("fsconf --station 1" + red_soc);
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // cu.execExecution("cp /home/fsdiag/SBCAL/*.cal .");
                    // 2.BETS로 부터 전달 받은 Test Program을 T5503 지정된 폴드에 Back-Up실시
                    // cu.execExecution("cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM \n", Color.BLACK);

                    String stationOnOff = (String) MainDual.info_map_st1.get("FUNCTION KEY");

                    if (stationOnOff != null) {
                        for (int i = 0; i < 16; i++) {
                            if (stationOnOff.charAt(i) == 'Y') {
                                // cu.execExecution("fsfk --station 1 --on " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 1 --on" + (i + 1) + " \n", Color.BLACK);
                            } else {
                                // cu.execExecution("fsfk --station 2 --off " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 2 --on" + (i + 1) + " \n", Color.BLACK);
                            }
                        }
                    }

                    SimpleDateFormat pgm_upload_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date pgm_upload_time_dt = new Date();
                    //  LC가 BETS로 부터 가져온 TEST PROGRAM을 T5503/MAGNUM5 설비 Memory에 Up-loading완료 시점
                    cu.FileNew(PathProperties.local_Header, "pgm_upload_time_h1.dat", pgm_upload_time_sdf.format(pgm_upload_time_dt), false);

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

                    SimpleDateFormat pgm_upload_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date pgm_upload_time_dt = new Date();
                    //  LC가 BETS로 부터 가져온 TEST PROGRAM을 T5503/MAGNUM5 설비 Memory에 Up-loading완료 시점
                    cu.FileNew(PathProperties.local_Header, "pgm_upload_time_h2.dat", pgm_upload_time_sdf.format(pgm_upload_time_dt), false);
                }

                if ("off".equals(MainDual.srqkind)) {

                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);
                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);
                    MainDual.main_pre_pgm_restore_bt.setEnabled(false);

                }

                // Handler Temp Check (Interlock 기능)
                // temp1.c  Library를 구동 시킨후 결과값을 H1TEMP.dat 파일로 담기
                // temp1.c는 별도 개발 예정.(GPIB  SRQ)

                //                String srq_handler_temp;
                //                String srq_handler_map;
                //                String srq_handler_para;
                //                
                //                
                //String temp_dat = cu.execExecutionFile("/home/fsdiag/GPIB/temp1", "파일 있는 위치", "H1TEMP.dat");
                //
                //                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);
                //
                //                srq_handler_temp = "90";
                //                // DUT MAP
                //                srq_handler_map = "ASDF";
                //                // PARA 
                //                srq_handler_para = "256";

                // ** 일단 주석 처리 : 온도 측정 하는 부분..     
                // BETS 에서 내려온 정보 BETS_TEST_TEMP가 기준 정보
                //                if (MainDual.main_radio_st1.isSelected()) {
                //
                //                    if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
                //                            && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
                //                                    && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //                    if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
                //                            && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
                //                                    && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //                    if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
                //                            && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
                //                                    && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //
                //                } else {
                //                    if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
                //                            && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
                //                                    && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //                    if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
                //                            && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
                //                                    && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //                    if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
                //                            && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) + 3)))
                //                            || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
                //                                    && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) - 3)))) {
                //                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
                //                        JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                //                        ld.setVisible(false);
                //                        ld.dispose();
                //                        return;
                //                    }
                //                }

                if (MainDual.srqkind.equals("on")) {
                    MainDual.while_break = false;
                    while (true) {
                        if (MainDual.while_break) {
                            break;
                        }
                    }

                    ld.setVisible(false);
                    ld.dispose();

                    BinSummaryPop BinSummaryPop = new BinSummaryPop(MainDual.main_frm_d);
                    BinSummaryPop.setVisible(true);
                } else {
                    MainDual.while_break = false;
                    while (true) {
                        if (MainDual.while_break) {
                            break;
                        }
                    }

                    ld.setVisible(false);
                    ld.dispose();
                }

                MainDual.loading_flg = true;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
