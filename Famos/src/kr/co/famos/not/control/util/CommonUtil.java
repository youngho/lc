package kr.co.famos.not.control.util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.main.MainDual;

/**
 * <code>CommonUtil.java</code>
 * 
 * @company : FAMOS 
 * @Description : �������� ó��
 * @author : ������ ����
 * @modify :
 * @since : 2017.02.09
 * @version : 1.0
 */

public class CommonUtil {
    public static void main(String[] args) {
        
        CommonUtil cu = new CommonUtil();

        String sub_bin_data = cu.FileReaderData("E:\\EMCP\\BACKUP\\LOCAL_08\\", "input_lotid123456_subbin_report-20170630135555.HEADA", true);
        cu.SubBinData(sub_bin_data);
        
    } 
    
    
    public static void data_reset() {
        CommonUtil cu = new CommonUtil();
        List li = cu.getDirFileList(PathProperties.local_Header);

        if (MainDual.main_radio_st1.isSelected()) {

            // ��� ���� �ʱ�ȭ
            MainDual.main_operator_text_st1.setText("");
            MainDual.main_fab_text_st1.setText("");
            MainDual.main_partnumber_text_st1.setText("");
            MainDual.main_lotno_text_st1.setText("");
            MainDual.main_grade_text_st1.setText("");
            MainDual.main_qty_text_st1.setText("");
            MainDual.main_temp_text_st1.setText("");
            MainDual.info_map_st1.clear();
            MainDual.lot_no_st1 = "";
            MainDual.main_processcode_text_st1.setText("");
            MainDual.main_testmode_text_st1.setText("PRODUCT MODE");
            MainDual.test_in_manual_mode_h1 = "PRODUCT MODE";

            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).toString().endsWith("h1.dat")) {
                    cu.fileDelete(li.get(i).toString());
                }
            }

            ButtonConditionA();
        } else {

            MainDual.main_operator_text_st2.setText("");
            MainDual.main_fab_text_st2.setText("");
            MainDual.main_partnumber_text_st2.setText("");
            MainDual.main_lotno_text_st2.setText("");
            MainDual.main_grade_text_st2.setText("");
            MainDual.main_qty_text_st2.setText("");
            MainDual.main_temp_text_st2.setText("");
            MainDual.info_map_st2.clear();
            MainDual.lot_no_st2 = "";
            MainDual.main_processcode_text_st1.setText("");
            MainDual.main_testmode_text_st2.setText("PRODUCT MODE");
            MainDual.test_in_manual_mode_h2 = "PRODUCT MODE";

            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).toString().endsWith("h2.dat")) {
                    cu.fileDelete(li.get(i).toString());
                }
            }

            ButtonConditionB();
        }

        // ���� �ð� 
        MainDual.start_date = "";
        // ���� �ð�
        MainDual.end_date = "";

        MainDual.main_log_textPane.setText("");

    }
    
    public void Headerdata() {
        CommonUtil cu = new CommonUtil();
        if (MainDual.main_radio_st1.isSelected()) {
            MainDual.header_data += "TESTER=HTMAT1"+"\n";
            MainDual.header_data += "SYSTEM=T5503"+"\n";
            MainDual.header_data += "HANDLER=TW350HT"+"\n";
            MainDual.header_data += "LOT_ID=" + MainDual.main_lotno_text_st1.getText()+"\n";
            MainDual.header_data += "PROCESS_CODE= " + (String) MainDual.info_map_st1.get("PROCESS")+"\n";
            MainDual.header_data += "PART_NUMBER=" + (String) MainDual.info_map_st1.get("PARTNUMBER")+"\n";
            MainDual.header_data += "PROGRAM_NAME=" + (String) MainDual.info_map_st1.get("MAIN_PGM")+"\n";
            MainDual.header_data += "GRADE=" + (String) MainDual.info_map_st1.get("GRADE")+"\n";
            MainDual.header_data += "FAB=M1" + (String) MainDual.info_map_st1.get("FAB")+"\n";
            MainDual.header_data += "FW_PATH=0"+"\n";
            MainDual.header_data += "FW_NAME=0"+"\n";
            MainDual.header_data += "TEMP=" + (String) MainDual.info_map_st1.get("TEMP")+"\n";
            MainDual.header_data += "OPERATOR_ID=" + cu.HederData(PathProperties.local_Header, "input_operator_id_h1.dat");
            MainDual.header_data += "QUANTITY=" + (String) MainDual.info_map_st1.get("QTY")+"\n";
            MainDual.header_data += "FUNCTION_KEY=YYNNNNNNNNNNNNNN"+"\n";
            MainDual.header_data += "TEST_COUNTER=" + cu.HederData(PathProperties.local_Header, "test_counter_h1.dat");
            MainDual.header_data += "TEST_INPUT=" + cu.HederData(PathProperties.local_Header, "test_input_h1.dat");
            MainDual.header_data += "TEST_FLOW=" + cu.HederData(PathProperties.local_Header, "test_flow_h1.dat");
            MainDual.header_data += "BOARD_ID=0"+"\n";
            MainDual.header_data += "PRELOT_END TIME=00000000000000"+"\n";
            MainDual.header_data += "LOT_IN_TIME=" + cu.HederData(PathProperties.local_Header, "lot_in_time_h1.dat");
            MainDual.header_data += "MES_IN_TIME=0"+"\n";
            MainDual.header_data += "MES_END_TIME=0"+"\n";
            MainDual.header_data += "BETS_IN_TIME=" + cu.HederData(PathProperties.local_Header, "bets_in_time_h1.dat");
            MainDual.header_data += "BETS_END_TIME=" + cu.HederData(PathProperties.local_Header, "bets_end_time_h1.dat");
            MainDual.header_data += "LOT_IN_END_TIME=" + cu.HederData(PathProperties.local_Header, "lot_in_end_time_h1.dat");
            MainDual.header_data += "PGM_UPLOAD_TIME=" + cu.HederData(PathProperties.local_Header, "pgm_upload_time_h1.dat");
            MainDual.header_data += "END_TIME=" + cu.HederData(PathProperties.local_Header, "end_time_h1.dat");
            MainDual.header_data += "BIN_IN_TIME=" + cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat");
            MainDual.header_data += "BIN_END_TIME=" + cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat");
            MainDual.header_data += "SBL_IN_TIME=" + cu.HederData(PathProperties.local_Header, "sbl_in_time_h1.dat");
            MainDual.header_data += "SBL_END_TIME=" + cu.HederData(PathProperties.local_Header, "sbl_end_time_h1.dat");
            MainDual.header_data += "SIMAX END_IN_TIME=0"+"\n";
            MainDual.header_data += "SIMAX_END_END_TIME=0"+"\n";
            MainDual.header_data += "FINAL_END_TIME=" + cu.HederData(PathProperties.local_Header, "final_end_time_h1.dat");
        } else {                                                    
            MainDual.header_data += "TESTER=HTMAT2"+"\n";
            MainDual.header_data += "SYSTEM=T5503"+"\n";
            MainDual.header_data += "HANDLER=TW350HT"+"\n";
            MainDual.header_data += "LOT_ID=" + MainDual.main_lotno_text_st2.getText()+"\n";
            MainDual.header_data += "PROCESS_CODE= " + (String) MainDual.info_map_st2.get("PROCESS")+"\n";
            MainDual.header_data += "PART_NUMBER=" + (String) MainDual.info_map_st2.get("PARTNUMBER")+"\n";
            MainDual.header_data += "PROGRAM_NAME=" + (String) MainDual.info_map_st2.get("MAIN_PGM")+"\n";
            MainDual.header_data += "GRADE=" + (String) MainDual.info_map_st2.get("GRADE")+"\n";
            MainDual.header_data += "FAB=M1" + (String) MainDual.info_map_st2.get("FAB")+"\n";
            MainDual.header_data += "FW_PATH=0"+"\n";
            MainDual.header_data += "FW_NAME=0"+"\n";
            MainDual.header_data += "TEMP=" + (String) MainDual.info_map_st2.get("TEMP")+"\n";
            MainDual.header_data += "OPERATOR_ID=" + cu.HederData(PathProperties.local_Header, "input_operator_id_h2.dat");
            MainDual.header_data += "QUANTITY=" + (String) MainDual.info_map_st2.get("QTY")+"\n";
            MainDual.header_data += "FUNCTION_KEY=YYNNNNNNNNNNNNNN"+"\n";
            MainDual.header_data += "TEST_COUNTER=" + cu.HederData(PathProperties.local_Header, "test_counter_h2.dat");
            MainDual.header_data += "TEST_INPUT=" + cu.HederData(PathProperties.local_Header, "test_input_h2.dat");
            MainDual.header_data += "TEST_FLOW=" + cu.HederData(PathProperties.local_Header, "test_flow_h2.dat");
            MainDual.header_data += "BOARD_ID=0"+"\n";
            MainDual.header_data += "PRELOT_END TIME=00000000000000"+"\n";
            MainDual.header_data += "LOT_IN_TIME=" + cu.HederData(PathProperties.local_Header, "lot_in_time_h2.dat");
            MainDual.header_data += "MES_IN_TIME=0"+"\n";
            MainDual.header_data += "MES_END_TIME=0"+"\n";
            MainDual.header_data += "BETS_IN_TIME=" + cu.HederData(PathProperties.local_Header, "bets_in_time_h2.dat");
            MainDual.header_data += "BETS_END_TIME=" + cu.HederData(PathProperties.local_Header, "bets_end_time_h2.dat");
            MainDual.header_data += "LOT_IN_END_TIME=" + cu.HederData(PathProperties.local_Header, "lot_in_end_time_h2.dat");
            MainDual.header_data += "PGM_UPLOAD_TIME=" + cu.HederData(PathProperties.local_Header, "pgm_upload_time_h2.dat");
            MainDual.header_data += "END_TIME=" + cu.HederData(PathProperties.local_Header, "end_time_h2.dat");
            MainDual.header_data += "BIN_IN_TIME=" + cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat");
            MainDual.header_data += "BIN_END_TIME=" + cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat");
            MainDual.header_data += "SBL_IN_TIME=" + cu.HederData(PathProperties.local_Header, "sbl_in_time_h2.dat");
            MainDual.header_data += "SBL_END_TIME=" + cu.HederData(PathProperties.local_Header, "sbl_end_time_h2.dat");
            MainDual.header_data += "SIMAX END_IN_TIME=0"+"\n";
            MainDual.header_data += "SIMAX_END_END_TIME=0"+"\n";
            MainDual.header_data += "FINAL_END_TIME=" + cu.HederData(PathProperties.local_Header, "final_end_time_h2.dat");
        }
    }
    
    // ���� ���¿� ���� ��ū ���� HEAD A
    public static void ButtonConditionA() {
        CommonUtil cu = new CommonUtil();
        
        if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("OPERATOR_ID")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(true);
            MainDual.main_test_in_manual_bt.setEnabled(true);
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("TEST_IN_AUTO")) {
            
            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("TEST_IN_MANUAL")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("RE_TEST")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("FINAL_END")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);
        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("PRE_PGM_RESTORE")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(false);
            
        } else {
            
            MainDual.main_operatorId_bt.setEnabled(true);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(false);
            MainDual.main_pre_pgm_restore_bt.setEnabled(false);
        }

    }
    
    // ���� ���¿� ���� ��ū ���� HEAD B
    public static void ButtonConditionB() {
        CommonUtil cu = new CommonUtil();
        
        if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("OPERATOR_ID")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(true);
            MainDual.main_test_in_manual_bt.setEnabled(true);
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("TEST_IN_AUTO")) {
            
            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("TEST_IN_MANUAL")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("RE_TEST")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("FINAL_END")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);
        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("PRE_PGM_RESTORE")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(true);
            MainDual.main_final_test_end_bt.setEnabled(true);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(false);
            
        } else {
            
            MainDual.main_operatorId_bt.setEnabled(true);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(false);
            MainDual.main_pre_pgm_restore_bt.setEnabled(false);
        }
    }
    
    
    // Ŀ�ǵ� ��� ��� ���� �����͸� �о ���Ϸ� �����.
//    public String execExecutionFile(String cmd, String path, String file_name) {
//        try {
//            Process exec_cmd = Runtime.getRuntime().exec(cmd);
//            BufferedReader err = new BufferedReader(new InputStreamReader(exec_cmd.getErrorStream()));
//
//            while (true) {
//                String errLine = err.readLine();
//                if (errLine == null) {
//                    break;
//                } else {
//                    System.out.println(errLine);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return FileReaderData(path, file_name, true);
//    }
    
    public String execExecutionFile(String cmd, String path, String file_name) {

        Runtime run = Runtime.getRuntime();
        Process p = null;

        try {

            p = run.exec(cmd);
            StreamGobbler gb1 = new StreamGobbler(p.getInputStream());
            StreamGobbler gb2 = new StreamGobbler(p.getErrorStream());
            gb1.start();
            gb2.start();

            while (true) {
                if (!gb1.isAlive() && !gb2.isAlive()) { //�ΰ��� �����尡 �����Ҹ� ���μ��� ���ᶧ���� ��ٸ���.
                    p.waitFor();
                    break;
                }
            }

            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while (true) {
                String errLine = err.readLine();
                if (errLine == null) {
                    break;
                } else {
                    System.out.println(errLine);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (p != null)
                p.destroy();
        }
        return FileReaderData(path, file_name, true);
    }
    
    
    // Ŀ�ǵ� ��� ��� ���� �����͸� �о ���Ϸ� �����.
//    public String execExecutionInfo(String cmd) {
//        String cmd_name = null;
//        try {
//            Process exec_cmd = Runtime.getRuntime().exec(cmd);
//
//            BufferedReader exec_data = new BufferedReader(new InputStreamReader(exec_cmd.getInputStream()));
//
//            while (true) {
//                String exec_data_line = exec_data.readLine();
//                if (exec_data_line == null) {
//                    break;
//                } else {
//                    cmd_name += exec_data_line + "\n";
//                    MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + exec_data_line + "\n", Color.BLACK);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return cmd_name;
//    }
    
    // Ŀ�ǵ� ��� ��� ���� �����͸� �о ���Ϸ� �����.
    public String execExecutionInfo(String cmd) {
        String cmd_name = null;
        Runtime run = Runtime.getRuntime();
        Process p = null;
        
        try {

            p = run.exec(cmd);
            StreamGobbler gb1 = new StreamGobbler(p.getInputStream());
            StreamGobbler gb2 = new StreamGobbler(p.getErrorStream());
            gb1.start();
            gb2.start();

            while (true) {
                if (!gb1.isAlive() && !gb2.isAlive()) { //�ΰ��� �����尡 �����Ҹ� ���μ��� ���ᶧ���� ��ٸ���.
                    p.waitFor();
                    break;
                }
            }
              
            BufferedReader exec_data = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while (true) {
                String exec_data_line = exec_data.readLine();
                if (exec_data_line == null) {
                    break;
                } else {
                    cmd_name += exec_data_line + "\n";
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + exec_data_line + "\n", Color.BLACK);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(p != null) p.destroy();
        } 
        return cmd_name;
    }
    
    
    // exec ����
    public void execExecution(String cmd) {
        try {
            Process exec_cmd = Runtime.getRuntime().exec(cmd);
            BufferedReader err = new BufferedReader(new InputStreamReader(exec_cmd.getErrorStream()));

            while (true) {
                String errLine = err.readLine();
                if (errLine == null) {
                    break;
                } else {
                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR :" + cmd +"\n", Color.RED);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : IOException" +"\n", Color.RED);
        }
    }
    
    public void FileNew(String path, String file_name, String write, boolean overwrite) {
        // ���� ��ü ����
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            // true ������ ������ ���� ���뿡 �̾ �ۼ�
            BufferedWriter fw = new BufferedWriter(new FileWriter(path + file_name, overwrite));
            // ���Ͼȿ� ���ڿ� ����
            fw.write(write);
            fw.flush();

            // ��ü �ݱ�
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    // ���� text ������ �о� ����
    public String FileReaderData(String path, String file_name, boolean first_next) {

        String return_data="";

        try {
            
            BufferedReader in = new BufferedReader(new FileReader(path + file_name));
            String data_read;
            
            while ((data_read = in.readLine()) != null) {
                if (first_next) {
                    return_data += data_read+"\n";
                }
                first_next = true;
            }
            in.close();
        } catch (IOException e) {
            MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : IOException \n", Color.RED);
        }
        return return_data;
    }
    
    // ���� text ������ �о� ����
    public String HederData(String path, String file_name) {
        
        File file = new File(path + file_name);
        
        String return_value = "";
        
        // ���� �ִ��� ��ȸ
        if (file.exists()) {
            return_value = FileReaderData(path, file_name, true);
        } else {
            // true ������ ������ ���� ���뿡 �̾ �ۼ�
            BufferedWriter fw;
            try {
                fw = new BufferedWriter(new FileWriter(path + file_name, false));
                // ���Ͼȿ� ���ڿ� ����
                fw.write("0");
                fw.flush();
                fw.close();
                
                return_value = FileReaderData(path, file_name, true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return return_value;
    }
    
    //���丮�� ���� ����Ʈ�� �д� �޼ҵ�
    public List getDirFileList(String dirPath) {
        // ���丮 ���� ����Ʈ
        List dirFileList = null;
        
        // ���� ����� ��û�� ���丮�� ������ ���� ��ü�� ������
        File dir = new File(dirPath);

        // ���丮�� �����Ѵٸ�
        if (dir.exists()) {
            // ���� ����� ����
            File[] files = dir.listFiles();

            // ���� �迭�� ���� ����Ʈ�� ��ȭ�� 
            dirFileList = Arrays.asList(files);
        }
        return dirFileList;
    }
    
    //������ �����ϴ� �޼ҵ�
    public void fileDelete(String deleteFileName) {

        File fd = new File(deleteFileName);
        fd.delete();
    }
    
    public void Sequence (Date bin_back_dt) {
        
        CommonUtil cu = new CommonUtil();
        MainDual.header_data = "";
        // ��� ���� �ְ�
        cu.Headerdata();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        
        // ���õ����� �����ͼ� �ֱ�
        if (MainDual.main_radio_st1.isSelected()) {
            String header_data = MainDual.header_data;
            String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", false);
            header_data += "\n"+bin_data; 
            cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
            ftpModule.fileCopy(PathProperties.ftpcasi+ "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + MainDual.main_lotno_text_st1.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADA");
        } else {
            String header_data = MainDual.header_data;
            String bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", false);
            header_data += "\n"+bin_data;
            cu.FileNew(PathProperties.ftpcasi , "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", header_data, true);
            ftpModule.fileCopy(PathProperties.ftpcasi + "input_lotid"+MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB", PathProperties.ftppre + MainDual.main_lotno_text_st2.getText()+"_casi_report-"+sdf.format(bin_back_dt)+".HEADB");
        }
        
        // Ĩ������ ����
        if (MainDual.main_radio_st1.isSelected()) {
            Date chipid_dt = new Date();
            // Ĩ������ ����
            String header_data = MainDual.header_data;
            
            // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "���� ���", "SUM1.DAT");
            // �׽�Ʈ
            String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADA", true);
            header_data += "\n" + chipid_data;
            cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADA", header_data, true);
            ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADA");
        } else {
            Date chipid_dt = new Date();
            // Ĩ������ ����
            String header_data = MainDual.header_data;
            // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "���� ���", "SUM1.DAT");
            // �׽�Ʈ
            String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADB", true);
            header_data += "\n" + chipid_data;
            cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADB", header_data, true);
            ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADB");
        }
        
        // �����
        if (MainDual.main_radio_st1.isSelected()) {
            String header_data = MainDual.header_data;
            Date sub_bin_dt = new Date();
            // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "���� ���", "SUM1.DAT");
            // �׽�Ʈ
            String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADA", true);
            cu.SubBinData(sub_bin_data);
            
            header_data += "\n" + sub_bin_data;
            cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADA", header_data, true);
            ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADA");
        
        } else {
            Date sub_bin_dt = new Date();
            String header_data = MainDual.header_data;
            // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "���� ���", "SUM1.DAT");
            // �׽�Ʈ
            String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADB", true);
            cu.SubBinData(sub_bin_data);
            
            header_data += "\n" + sub_bin_data;
            cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADB", header_data, true);
            ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADB");
        }
    }
    
    public void SubBinData(String sub_bin_data) {
        String[] bin = sub_bin_data.split("\n");
        String[] bin_data = null;

        for (int i = 0; i < 1; i++) {
            bin_data = bin[i].split(" ");
        }

        for (int x = 0; x < bin_data.length; x++) {
            if (MainDual.main_radio_st1.isSelected()) {
                MainDual.sub_bin_map_st1.put("bin_" + x, bin_data[x]);
            } else {
                MainDual.sub_bin_map_st2.put("bin_" + x, bin_data[x]);
            }

            for (int z = 0; z < bin_data[x].length(); z++) {
                if (bin_data[x].charAt(z) != '0') {
                    if (MainDual.main_radio_st1.isSelected()) {
                        MainDual.sub_bin_map_st1.put("bin_" + x, bin_data[x].substring(z));
                    } else {
                        MainDual.sub_bin_map_st2.put("bin_" + x, bin_data[x].substring(z));
                    }

                    break;
                }
            }
        }
    }


    // ������ ���� ���� �Ѵ�. 
    public String getBinDataFormat(String binNum) {

        int bin_lg = binNum.length();

        String zero = "";

        for (int i = 0; i < (4 - bin_lg); i++) {
            zero += "0";
        }

        zero += binNum;
        return zero;
    }
    
}

class StreamGobbler extends Thread
{
    InputStream is;
    public StreamGobbler(InputStream is) {
        this.is = is;
    }
    public void run()
    {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ( (line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException ioe) {
          System.out.println(ioe);
        }
    }
}

