package kr.co.famos.not.control.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.PathProperties;

public class SRQKIND_20170212_backup {
//    public void SRQ_AUTO_ReTest() {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date dt = new Date();
//        MainDual.start_date = sdf.format(dt);
//
//        try {
//            File file_binon;
//            if (MainDual.main_radio_st1.isSelected()) {
//                file_binon = new File(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                file_binon = new File(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//            // true 지정시 파일의 기존 내용에 이어서 작성
//            FileWriter fw = new FileWriter(file_binon, false);
//
//            System.out.println("====input_lot_no-binon_report-start_====> " + "input_lot_no-binon_report-start_" + MainDual.start_date);
//
//            // 파일안에 문자열 쓰기
//            if (MainDual.main_radio_st1.isSelected()) {
//                fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st1) + "\r\n");
//                fw.write((String) MainDual.info_map_st1.get("BETS_STEP_ID") + "\r\n");
//                fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                fw.write((String) MainDual.info_map_st1.get("BETS_QUANTITY") + "\r\n");
//                fw.write(MainDual.main_operator_text_st1.getText() + "\r\n");
//            } else {
//                fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st2) + "\r\n");
//                fw.write((String) MainDual.info_map_st2.get("BETS_STEP_ID") + "\r\n");
//                fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                fw.write((String) MainDual.info_map_st2.get("BETS_QUANTITY") + "\r\n");
//                fw.write(MainDual.main_operator_text_st2.getText() + "\r\n");
//            }
//
//            // 소켓 통신으로 받아 오는 값
//            fw.write("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" + "\r\n");
//
//            fw.flush();
//            fw.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // 파일 복사
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1");
//            } else {
//                // 파일 복사
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // thead1.dat을 head로 사용하고 그밑에 hynix에서 내려준 Chip_ID정보를 매 Shot 별 data Append 시킴
//        try {
//            File file_chid;
//            if (MainDual.main_radio_st1.isSelected()) {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//            // true 지정시 파일의 기존 내용에 이어서 작성
//            FileWriter fw_chid = new FileWriter(file_chid, false);
//
//            // 파일안에 문자열 쓰기
//
//            // 파일안에 문자열 쓰기
//            if (MainDual.main_radio_st1.isSelected()) {
//                fw_chid.write(MainDual.main_title_lb.getText() + "\r\n");
//                fw_chid.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                fw_chid.write(Integer.toString(TestInAutoPop.test_in_auto_count_st1) + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st1.get("BETS_STEP_ID") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st1.get("BETS_QUANTITY") + "\r\n");
//                fw_chid.write(MainDual.main_operator_text_st1.getText() + "\r\n");
//            } else {
//                fw_chid.write(MainDual.main_title_lb.getText() + "\r\n");
//                fw_chid.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                fw_chid.write(Integer.toString(TestInAutoPop.test_in_auto_count_st2) + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st2.get("BETS_STEP_ID") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                fw_chid.write((String) MainDual.info_map_st2.get("BETS_QUANTITY") + "\r\n");
//                fw_chid.write(MainDual.main_operator_text_st2.getText() + "\r\n");
//            }
//
//            // 칩 데이터는 파일로 로컬에  떨어 트려 준다. 
//            // 데이터 값을 받아서 붙이면 된다.
//
//            fw_chid.flush();
//            fw_chid.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // 파일 복사
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                // 파일 복사
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // RE_TEST_END
//        // 시작 날자 종료 날자 선언
//        SimpleDateFormat sdf_end = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date dt_end = new Date();
//        MainDual.end_date = sdf_end.format(dt_end);
//
//        try {
//
//            try {
//
//                File file;
//                if (MainDual.main_radio_st1.isSelected()) {
//                    file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1");
//                } else {
//                    file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2");
//                }
//                // true 지정시 파일의 기존 내용에 이어서 작성
//                FileWriter fw = new FileWriter(file, false);
//
//                // 파일안에 문자열 쓰기
//                if (MainDual.main_radio_st1.isSelected()) {
//                    fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                    fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                    fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st1) + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_STEP_ID") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_QUANTITY") + "\r\n");
//                    fw.write(MainDual.main_operator_text_st1.getText() + "\r\n");
//                } else {
//                    fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                    fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                    fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st2) + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_STEP_ID") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_QUANTITY") + "\r\n");
//                    fw.write(MainDual.main_operator_text_st2.getText() + "\r\n");
//                }
//
//                fw.write(MainDual.start_date + "\r\n");
//                fw.write(MainDual.end_date + "\r\n");
//
//                // 소켓에서 데이터 받아 오기
//                fw.write("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "\r\n");
//
//                fw.flush();
//                fw.close();
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    // 파일 복사
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // 파일 복사
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
//                }
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    ReTestEndPop.re_test_end_count_st1++;
//                } else {
//                    ReTestEndPop.re_test_end_count_st2++;
//                }
//
//                ftpModule.FtpServerSend(0);
//
//                while (true) {
//                    if (ftpModule.re_test_end_exit) {
//                        break;
//                    }
//                }
//
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//            }
//
//        } catch (NumberFormatException e1) {
//            e1.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public void SRQ_AUTO_Final() {
//
//        if (MainDual.main_radio_st1.isSelected()) {
//            FinalTestEndPop.final_test_end_count_st1++;
//        } else {
//            FinalTestEndPop.final_test_end_count_st2++;
//        }
//
//        // 시작 날자 종료 날자 선언
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date dt = new Date();
//        MainDual.end_date = sdf.format(dt);
//
//        try {
//
//            try {
//
//                File file;
//                if (MainDual.main_radio_st1.isSelected()) {
//                    file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1");
//                } else {
//                    file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2");
//                }
//                // true 지정시 파일의 기존 내용에 이어서 작성
//                FileWriter fw = new FileWriter(file, false);
//
//                // 파일안에 문자열 쓰기
//                if (MainDual.main_radio_st1.isSelected()) {
//                    fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                    fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                    fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st1) + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_STEP_ID") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st1.get("BETS_QUANTITY") + "\r\n");
//                    fw.write(MainDual.main_operator_text_st1.getText() + "\r\n");
//                } else {
//                    fw.write(MainDual.main_title_lb.getText() + "\r\n");
//                    fw.write(TestInAutoPop.test_in_auto_text.getText() + "\r\n");
//                    fw.write(Integer.toString(TestInAutoPop.test_in_auto_count_st2) + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_STEP_ID") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_PART_NUMBER") + "\r\n");
//                    fw.write((String) MainDual.info_map_st2.get("BETS_QUANTITY") + "\r\n");
//                    fw.write(MainDual.main_operator_text_st2.getText() + "\r\n");
//                }
//
//                Process final_test_end_data = Runtime.getRuntime().exec("java");
//                BufferedReader final_test_end_data_read = new BufferedReader(new InputStreamReader(final_test_end_data.getInputStream()));
//
//                while (true) {
//                    String final_test_end_line = final_test_end_data_read.readLine();
//                    if (final_test_end_line == null) {
//                        break;
//                    } else {
//                        MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + final_test_end_line + "\n", Color.BLACK);
//                        fw.write(final_test_end_line + "\r\n");
//                    }
//                }
//
//                fw.flush();
//                fw.close();
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    // 파일 복사
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // 파일 복사
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
//                }
//
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//            }
//
//        } catch (NumberFormatException e1) {
//            e1.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }

}
