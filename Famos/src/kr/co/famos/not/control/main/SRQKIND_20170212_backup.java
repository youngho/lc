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
//            // true 走舛獣 督析税 奄糎 鎧遂拭 戚嬢辞 拙失
//            FileWriter fw = new FileWriter(file_binon, false);
//
//            System.out.println("====input_lot_no-binon_report-start_====> " + "input_lot_no-binon_report-start_" + MainDual.start_date);
//
//            // 督析照拭 庚切伸 床奄
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
//            // 社掴 搭重生稽 閤焼 神澗 葵
//            fw.write("ししししししししししし" + "\r\n");
//
//            fw.flush();
//            fw.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // 督析 差紫
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1");
//            } else {
//                // 督析 差紫
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // thead1.dat聖 head稽 紫遂馬壱 益購拭 hynix拭辞 鎧形層 Chip_ID舛左研 古 Shot 紺 data Append 獣鉄
//        try {
//            File file_chid;
//            if (MainDual.main_radio_st1.isSelected()) {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//            // true 走舛獣 督析税 奄糎 鎧遂拭 戚嬢辞 拙失
//            FileWriter fw_chid = new FileWriter(file_chid, false);
//
//            // 督析照拭 庚切伸 床奄
//
//            // 督析照拭 庚切伸 床奄
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
//            // 懲 汽戚斗澗 督析稽 稽鎮拭  恭嬢 闘形 層陥. 
//            // 汽戚斗 葵聖 閤焼辞 細戚檎 吉陥.
//
//            fw_chid.flush();
//            fw_chid.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // 督析 差紫
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                // 督析 差紫
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // RE_TEST_END
//        // 獣拙 劾切 曽戟 劾切 識情
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
//                // true 走舛獣 督析税 奄糎 鎧遂拭 戚嬢辞 拙失
//                FileWriter fw = new FileWriter(file, false);
//
//                // 督析照拭 庚切伸 床奄
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
//                // 社掴拭辞 汽戚斗 閤焼 神奄
//                fw.write("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "\r\n");
//
//                fw.flush();
//                fw.close();
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    // 督析 差紫
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // 督析 差紫
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
//        // 獣拙 劾切 曽戟 劾切 識情
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
//                // true 走舛獣 督析税 奄糎 鎧遂拭 戚嬢辞 拙失
//                FileWriter fw = new FileWriter(file, false);
//
//                // 督析照拭 庚切伸 床奄
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
//                    // 督析 差紫
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // 督析 差紫
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
