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
//            // true ������ ������ ���� ���뿡 �̾ �ۼ�
//            FileWriter fw = new FileWriter(file_binon, false);
//
//            System.out.println("====input_lot_no-binon_report-start_====> " + "input_lot_no-binon_report-start_" + MainDual.start_date);
//
//            // ���Ͼȿ� ���ڿ� ����
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
//            // ���� ������� �޾� ���� ��
//            fw.write("����������������������" + "\r\n");
//
//            fw.flush();
//            fw.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // ���� ����
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1");
//            } else {
//                // ���� ����
//                ftpModule.fileCopy(PathProperties.ftpbinon + "input_lot_no-binon_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // thead1.dat�� head�� ����ϰ� �׹ؿ� hynix���� ������ Chip_ID������ �� Shot �� data Append ��Ŵ
//        try {
//            File file_chid;
//            if (MainDual.main_radio_st1.isSelected()) {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//            // true ������ ������ ���� ���뿡 �̾ �ۼ�
//            FileWriter fw_chid = new FileWriter(file_chid, false);
//
//            // ���Ͼȿ� ���ڿ� ����
//
//            // ���Ͼȿ� ���ڿ� ����
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
//            // Ĩ �����ʹ� ���Ϸ� ���ÿ�  ���� Ʈ�� �ش�. 
//            // ������ ���� �޾Ƽ� ���̸� �ȴ�.
//
//            fw_chid.flush();
//            fw_chid.close();
//
//            if (MainDual.main_radio_st1.isSelected()) {
//                // ���� ����
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
//            } else {
//                // ���� ����
//                ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//        }
//
//        // RE_TEST_END
//        // ���� ���� ���� ���� ����
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
//                // true ������ ������ ���� ���뿡 �̾ �ۼ�
//                FileWriter fw = new FileWriter(file, false);
//
//                // ���Ͼȿ� ���ڿ� ����
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
//                // ���Ͽ��� ������ �޾� ����
//                fw.write("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "\r\n");
//
//                fw.flush();
//                fw.close();
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    // ���� ����
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // ���� ����
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
//        // ���� ���� ���� ���� ����
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
//                // true ������ ������ ���� ���뿡 �̾ �ۼ�
//                FileWriter fw = new FileWriter(file, false);
//
//                // ���Ͼȿ� ���ڿ� ����
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
//                    // ���� ����
//                    ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                } else {
//                    // ���� ����
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
