package kr.co.famos.not.control.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

public class ReTestEndProgress_20170212_BACKUP {
    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new ReTestEndTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class ReTestEndTask {
        Loading ld;

        ReTestEndTask() {

            try {

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);
                
                
//                CommonUtil cu = new CommonUtil();
//                cu.ExecFileNewBin(PathProperties.ftpcat, PathProperties.ftppre, "input_lotid123456_subbin_report-", date, test_counter);
                
               
                
                
                
                
                
                
                
                
                
                
                
                
                
                String sys_number = "";
                String inputLotNo = "";
                String test_counter = "";
                String bets_step_id = "";
                String betsPartNumber = "";
                String bets_function_key = "";
                String bets_qty = "";
                String input_operator_id = "";

                if (MainDual.main_radio_st1.isSelected()) {
                    sys_number = MainDual.main_title_lb.getText();
                    inputLotNo = MainDual.main_lotno_text_st1.getText();
                    test_counter = Integer.toString(ReTestEndPop.re_test_end_count_st1);
                    bets_step_id = (String) MainDual.info_map_st1.get("BETS_STEP_ID");
                    betsPartNumber = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                    bets_function_key = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                    bets_qty = (String) MainDual.info_map_st1.get("BETS_QUANTITY");
                    input_operator_id = MainDual.main_operator_text_st1.getText();
                } else {
                    sys_number = MainDual.main_title_lb.getText();
                    inputLotNo = MainDual.main_lotno_text_st2.getText();
                    test_counter = Integer.toString(ReTestEndPop.re_test_end_count_st2);
                    bets_step_id = (String) MainDual.info_map_st2.get("BETS_STEP_ID");
                    betsPartNumber = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
                    bets_function_key = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
                    bets_qty = (String) MainDual.info_map_st2.get("BETS_QUANTITY");
                    input_operator_id = MainDual.main_operator_text_st2.getText();
                }

                try {
                    
                    File file;
                    if (MainDual.main_radio_st1.isSelected()) {
                        file = new File(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
                    } else {
                        file = new File(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
                    }
                    // true 지정시 파일의 기존 내용에 이어서 작성
                    FileWriter fw = new FileWriter(file, false);

                    // 파일안에 문자열 쓰기
                    fw.write(sys_number + "\r\n");
                    fw.write(inputLotNo + "\r\n");
                    fw.write(test_counter + "\r\n");
                    fw.write(bets_step_id + "\r\n");
                    fw.write(betsPartNumber + "\r\n");
                    fw.write(bets_function_key + "\r\n");
                    fw.write(bets_qty + "\r\n");
                    fw.write(input_operator_id + "\r\n");
                    fw.write(MainDual.start_date + "\r\n");
                    fw.write(MainDual.end_date + "\r\n");
                    
                    Process re_test_end_data = Runtime.getRuntime().exec("java");
                    BufferedReader re_test_end_data_read = new BufferedReader(new InputStreamReader(re_test_end_data.getInputStream()));

                    while (true) {
                        String re_test_end_line = re_test_end_data_read.readLine();
                        if (re_test_end_line == null) {
                            break;
                        } else {
                            MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + re_test_end_line + "\n", Color.BLACK);
                            fw.write(re_test_end_line + "\r\n");
                        }
                    }

                    fw.flush();
                    fw.close();
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        // 파일 복사
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
                        MainDual.station_step1 = "step_re"; 
                    } else {
                        // 파일 복사
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
                        MainDual.station_step2 = "step_re";
                    }
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        ReTestEndPop.re_test_end_count_st1++;
                    } else {
                        ReTestEndPop.re_test_end_count_st2++;
                    }
                    
                    ftpModule.FtpServerSend(0);
                   
                    while (true) {
                        if (ftpModule.re_test_end_exit) {
                            ld.setVisible(false);
                            ld.dispose();
                            //SRQ 분기를 빠져 나온다.
                            MainDual.while_break = false;
                            break;
                        }
                    }
                   
                } catch (Exception e1) {
                    e1.printStackTrace();
                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
                }

            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
