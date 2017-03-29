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

            // ���� ���� ���� ���� ����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dt = new Date();

            if (MainDual.main_radio_st1.isSelected()) {
                // TEST Ƚ�� (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "0", false);
                // TEST INPUT ��� (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h1.dat", "PRE", false);
                // ----> ���� LOT LOT_IN �ð�.(LC���� LOT_ID �Է��� OK button�� push�� ����)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(dt), false);
                // �ڵ鷯 A
                cu.FileNew(PathProperties.local_Header, "head.dat", "A", false);
                // �׽�Ʈ���
                cu.FileNew(PathProperties.local_Header, "test_mode_h1.dat", MainDual.main_testmode_text_st1.getText() , false);
                // LC�� BETS�� LOT_IN ��� ��������,BETS ������ LOT_ID ������ ������ ����.
                cu.FileNew(PathProperties.local_Header, "bets_in_time_h1.dat", "0" , false);
                //  LC�� BETS�� ���� TEST PROGRAM�� ���� ��������,LC�� BETS�� LOT ���� ��� �Ϸ������  ����
                cu.FileNew(PathProperties.local_Header, "bets_end_time_h1.dat", "0", false);
            } else {
                // TEST Ƚ�� (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "0", false);
                // TEST INPUT ��� (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h2.dat", "PRE", false);
                // ----> ���� LOT LOT_IN �ð�.(LC���� LOT_ID �Է��� OK button�� push�� ����)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(dt), false);
                // �ڵ鷯 B
                cu.FileNew(PathProperties.local_Header, "head.dat", "B", false);
                // �׽�Ʈ���
                cu.FileNew(PathProperties.local_Header, "test_mode_h2.dat", MainDual.main_testmode_text_st2.getText(), false);
                // LC�� BETS�� LOT_IN ��� ��������,BETS ������ LOT_ID ������ ������ ����.
                cu.FileNew(PathProperties.local_Header, "bets_in_time_h2.dat", "0" , false);
                //  LC�� BETS�� ���� TEST PROGRAM�� ���� ��������,LC�� BETS�� LOT ���� ��� �Ϸ������  ����
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
                    MainDual.main_partnumber_text_st1.setText(cu.HederData(PathProperties.ftplocal, "part_number_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "part_number_h1.dat").trim());
                    MainDual.main_processcode_text_st1.setText(cu.HederData(PathProperties.ftplocal, "process_code_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_code_h1.dat").trim());
                    MainDual.main_fab_text_st1.setText(cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim());
                    MainDual.main_grade_text_st1.setText(cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim());
                    MainDual.main_temp_text_st1.setText(cu.HederData(PathProperties.ftplocal, "temperature_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temperature_h1.dat").trim());
                    MainDual.main_qty_text_st1.setText(cu.HederData(PathProperties.ftplocal, "quantity_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "quantity_h1.dat"));
                } else {
                    MainDual.main_lotno_text_st2.setText(MainDual.lot_no_st2);
                    MainDual.main_partnumber_text_st2.setText(cu.HederData(PathProperties.ftplocal, "part_number_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "part_number_h2.dat").trim());
                    MainDual.main_processcode_text_st2.setText(cu.HederData(PathProperties.ftplocal, "process_code_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_code_h2.dat").trim());
                    MainDual.main_fab_text_st2.setText(cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim());
                    MainDual.main_grade_text_st2.setText(cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim());
                    MainDual.main_temp_text_st2.setText(cu.HederData(PathProperties.ftplocal, "temperature_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temperature_h2.dat").trim());
                    MainDual.main_qty_text_st2.setText(cu.HederData(PathProperties.ftplocal, "quantity_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "quantity_h2.dat"));
                }

                // SEQ4 1.BETS�� ���� ���� ���� Test Program�� T5503ȯ�濡 Up-Load�ǽ�
                // ==> ���� Ǯ�� : exec "tar xvf xxxx.tar"
                MainDual.appendToPane(MainDual.main_log_textPane, "exec tar xvf xxxx.tar \n", Color.BLACK);
                // Runtime.getRuntime().exec("tar xvf xxxx.tar");

                // ==> TEST PROGRAM ���� ��� ���� : exec "fscd

                if (MainDual.main_radio_st1.isSelected()) {
                    
                    // String board_id = cu.executeRuntime("BOARD_ID");
                    cu.FileNew(PathProperties.local_Header, "board_id_h1.dat", "0", false);
                    
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
                    // soc.dat�� soc�� ��ȯ
                    // Runtime.getRuntime().exec("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat\n", Color.BLACK);

                    // soc.dat ���� ������ �о��.. 
                    // String red_soc = cu.FileReader("1 /home/fsdiag/HTM", "soc.dat");

                    // ==> TEST SOCKET PROGRAM UP-LOAD :
                    // exec
                    // "fsconf --station 1 T5503256.soc"
                    // cu.execExecution("fsconf --station 1" + red_soc);
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // cu.execExecution("cp /home/fsdiag/SBCAL/*.cal .");
                    // 2.BETS�� ���� ���� ���� Test Program�� T5503 ������ ���忡 Back-Up�ǽ�
                    // cu.execExecution("cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM \n", Color.BLACK);
                    
                    System.out.println("function_key_h1.dat =====> " + cu.HederData(PathProperties.ftplocal, "function_key_h1.dat"));
                    String stationOnOff = cu.HederData(PathProperties.ftplocal, "function_key_h1.dat");

                    if (stationOnOff != null) {
                        for (int i = 0; i < stationOnOff.length(); i++) {
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
                    //  LC�� BETS�� ���� ������ TEST PROGRAM�� T5503/MAGNUM5 ���� Memory�� Up-loading�Ϸ� ����
                    cu.FileNew(PathProperties.local_Header, "pgm_upload_time_h1.dat", pgm_upload_time_sdf.format(pgm_upload_time_dt), false);

                } else {
                    
                    // String board_id = cu.executeRuntime("BOARD_ID");
                    cu.FileNew(PathProperties.local_Header, "board_id_h2.dat", "0", false);
                    
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

                    System.out.println("function_key_h2.dat =====> " + cu.HederData(PathProperties.ftplocal, "function_key_h2.dat"));
                    String stationOnOff = cu.HederData(PathProperties.ftplocal, "function_key_h2.dat");

                    if (stationOnOff != null) {
                        for (int i = 0; i < stationOnOff.length(); i++) {
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
                    //  LC�� BETS�� ���� ������ TEST PROGRAM�� T5503/MAGNUM5 ���� Memory�� Up-loading�Ϸ� ����
                    cu.FileNew(PathProperties.local_Header, "pgm_upload_time_h2.dat", pgm_upload_time_sdf.format(pgm_upload_time_dt), false);
                }

                if ("OFF".equals(MainDual.srqkind)) {

                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);
                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);
                    MainDual.main_pre_pgm_restore_bt.setEnabled(false);
                }

//                cu.executeRuntime("exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat");
                // Handler Temp Check (Interlock ���)
                // temp1.c  Library�� ���� ��Ų�� ������� H1TEMP.dat ���Ϸ� ���
                // temp1.c�� ���� ���� ����.(GPIB  SRQ)

//                String temp_dat = cu.FileReaderData("���� �ִ� ��ġ", "H1TEMP.dat", true);
                
                String gpib_handler_temp = "-32";
                String gpib_handler_map = "DUT_MAP_128PARA";
                
                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);
                
                // ** �ϴ� �ּ� ó�� : �µ� ���� �ϴ� �κ�..     
                // BETS ���� ������ ���� BETS_TEST_TEMP�� ���� ����
                if (MainDual.main_radio_st1.isSelected()) {
                    
                    System.out.println("temperature_h1.dat ===> " + Integer.parseInt(cu.HederData(PathProperties.ftplocal, "temperature_h1.dat").trim()));
                    
                    int temp_difference_h1 = (Integer.parseInt(gpib_handler_temp) - ((Integer.parseInt(cu.HederData(PathProperties.ftplocal, "temperature_h1.dat").trim()))));
                    System.out.println("temp_difference_h1 ==> " + temp_difference_h1);
                    
//                    if (((Integer.parseInt((String) MainDual.info_map_st1.get("TEMPERATURE_LIMIT"))) < Math.abs(temp_difference))) {
//                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
//                        JOptionPane.showMessageDialog(null, "Handler TEMP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                        ld.setVisible(false);
//                        ld.dispose();
//                        return;
//                    }
//                    
//                    if (!gpib_handler_map.equals((String) MainDual.info_map_st1.get("DUT_MAP"))) {
//                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
//                        JOptionPane.showMessageDialog(null, "Handler DUT_MAP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                        ld.setVisible(false);
//                        ld.dispose();
//                        return;
//                    }

                } else {
                    int temp_difference_h2 = (Integer.parseInt(gpib_handler_temp) - ((Integer.parseInt(cu.HederData(PathProperties.ftplocal, "temperature_h2.dat").trim()))));
                    System.out.println("temp_difference_h2 ==> " + temp_difference_h2);
//                    if (((Integer.parseInt((String) MainDual.info_map_st2.get("TEMPERATURE_LIMIT"))) < Math.abs(temp_difference))) {
//                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
//                        JOptionPane.showMessageDialog(null, "Handler TEMP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                        ld.setVisible(false);
//                        ld.dispose();
//                        return;
//                    }
//
//                    if (!gpib_handler_map.equals((String) MainDual.info_map_st2.get("DUT_MAP"))) {
//                        MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
//                        JOptionPane.showMessageDialog(null, "Handler DUT_MAP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                        ld.setVisible(false);
//                        ld.dispose();
//                        return;
//                    }
                }

                if (MainDual.srqkind.equals("ON")) {
                    
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    
                    MainDual.while_break = false;
                    while (true) {
                        if (MainDual.while_break) {
                            break;
                        }
                    }

                    ld.setVisible(false);
                    ld.dispose();
                    
                    
                    Date bin_in_time_dt = new Date();
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        cu.FileNew(PathProperties.local_Header, "bin_in_time_h1.dat", sdf.format(bin_in_time_dt), false);
                    } else {
                        cu.FileNew(PathProperties.local_Header, "bin_in_time_h2.dat", sdf.format(bin_in_time_dt), false);
                    }
                    
                    System.out.println("@@@@@@@@@@@@@@@BinSummaryPop ȣ�� ��@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    
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
