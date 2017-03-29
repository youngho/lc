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
import java.util.List;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.main.BinSummaryPop;
import kr.co.famos.not.control.main.MainDual;

/**
 * <code>CommonUtil.java</code>
 * 
 * @company : FAMOS 
 * @Description : 공통으로 처리
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.09
 * @version : 1.0
 */

public class CommonUtil {
    public static void data_reset() {
        CommonUtil cu = new CommonUtil();
        List li     = cu.getDirFileList(PathProperties.local_Header);
        List casiLi = cu.getDirFileList(PathProperties.ftpcasi);
        
        if (MainDual.main_radio_st1.isSelected()) {
            // 모든 정보 초기화
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
                System.out.println("li.get(i).toString() ===> " + li.get(i).toString());
                if (li.get(i).toString().endsWith("prelot_end_time_h1.dat")) {
                    System.out.println("li.get(i).toString() ===> " + li.get(i).toString());
                    continue;
                } else if (li.get(i).toString().endsWith("h1.dat")) {
                    cu.fileDelete(li.get(i).toString());
                } else if (li.get(i).toString().endsWith("head.dat")) {
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
                if (li.get(i).toString().startsWith("prelot_end_time_h2.dat")) {
                    continue;
                } else if (li.get(i).toString().endsWith("h2.dat")) {
                    cu.fileDelete(li.get(i).toString());
                } else if (li.get(i).toString().endsWith("head.dat")) {
                    cu.fileDelete(li.get(i).toString());
                }
            }
            ButtonConditionB();
        }
        
        for (int i = 0; i < casiLi.size(); i++) {
            cu.fileDelete(casiLi.get(i).toString());
        }

        // 시작 시간 
        MainDual.start_date = "";
        // 종료 시간
        MainDual.end_date = "";

        MainDual.main_log_textPane.setText("");

    }
    
    public void Headerdata() {
        CommonUtil cu = new CommonUtil();
        if (MainDual.main_radio_st1.isSelected()) {
            MainDual.header_data += "TESTER_NUMBER="+ cu.HederData(PathProperties.local_Header, "tester_number.dat");
            MainDual.header_data += "TESTER_MODEL="+ cu.HederData(PathProperties.local_Header, "TESTID.dat");
            MainDual.header_data += "HANDLER_MODEL="+ cu.HederData(PathProperties.local_Header, "handler_model.dat");
            MainDual.header_data += "HEAD="+ cu.HederData(PathProperties.local_Header, "head.dat");
            MainDual.header_data += "PARA="+ cu.HederData(PathProperties.local_Header, "para_h1.dat");
            MainDual.header_data += "LOT_ID="+ cu.HederData(PathProperties.local_Header, "lot_id_h1.dat");
            MainDual.header_data += "PROCESS_CODE="+ cu.HederData(PathProperties.local_Header, "process_code_h1.dat");
            MainDual.header_data += "PART_NUMBER="+ cu.HederData(PathProperties.local_Header, "part_number_h1.dat");
            MainDual.header_data += "MAIN_PROGRAM_NAME="+ cu.HederData(PathProperties.local_Header, "main_program_name_h1.dat");
            MainDual.header_data += "GRADE="+ cu.HederData(PathProperties.local_Header, "grade_h1.dat");
            MainDual.header_data += "FAB="+ cu.HederData(PathProperties.local_Header, "fab_h1.dat");
            MainDual.header_data += "FIRMWARE_NAME="+ cu.HederData(PathProperties.local_Header, "firmware_name_h1.dat");
            MainDual.header_data += "FIRMWARE_VERSION="+ cu.HederData(PathProperties.local_Header, "firmware_version_h1.dat");
            MainDual.header_data += "TEMPERATURE="+ cu.HederData(PathProperties.local_Header, "temperature_h1.dat");
            MainDual.header_data += "OPERATOR_ID="+ cu.HederData(PathProperties.local_Header, "operator_id_h1.dat");
            MainDual.header_data += "QUANTITY="+ cu.HederData(PathProperties.local_Header, "quantity_h1.dat");
            MainDual.header_data += "FUNCTION_KEY="+ cu.HederData(PathProperties.local_Header, "function_key_h1.dat");
            MainDual.header_data += "PASS_BIN_SELECTION="+ cu.HederData(PathProperties.local_Header, "pass_bin_selection_h1.dat");
            MainDual.header_data += "TEST_COUNTER="+ cu.HederData(PathProperties.local_Header, "test_counter_h1.dat");
            MainDual.header_data += "TEST_INPUT="+ cu.HederData(PathProperties.local_Header, "test_input_h1.dat");
            MainDual.header_data += "TEST_FLOW="+ cu.HederData(PathProperties.local_Header, "test_flow_h1.dat");
            MainDual.header_data += "REWORK_FLAG="+ cu.HederData(PathProperties.local_Header, "rework_flag_h1.dat");
            MainDual.header_data += "TEST_MODE="+ cu.HederData(PathProperties.local_Header, "test_mode_h1.dat");
            MainDual.header_data += "BOARD_ID="+ cu.HederData(PathProperties.local_Header, "board_id_h1.dat");
            MainDual.header_data += "PRELOT_END_TIME="+ cu.HederData(PathProperties.local_Header, "prelot_end_time_h1.dat");
            MainDual.header_data += "LOT_IN_TIME="+ cu.HederData(PathProperties.local_Header, "lot_in_time_h1.dat");
            MainDual.header_data += "BETS_IN_TIME="+ cu.HederData(PathProperties.local_Header, "bets_in_time_h1.dat");
            MainDual.header_data += "BETS_END_TIME="+ cu.HederData(PathProperties.local_Header, "bets_end_time_h1.dat");
            MainDual.header_data += "LOT_IN_END_TIME="+ cu.HederData(PathProperties.local_Header, "lot_in_end_time_h1.dat");
            MainDual.header_data += "LOT_START_TIME="+ cu.HederData(PathProperties.local_Header, "lot_start_time_h1.dat");
            MainDual.header_data += "END_TIME="+ cu.HederData(PathProperties.local_Header, "end_time_h1.dat");
            MainDual.header_data += "BIN_IN_TIME="+ cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat");
            MainDual.header_data += "BIN_END_TIME="+ cu.HederData(PathProperties.local_Header, "bin_end_time_h1.dat");
            MainDual.header_data += "SBL_IN_TIME="+ cu.HederData(PathProperties.local_Header, "sbl_in_time_h1.dat");
            MainDual.header_data += "SBL_END_TIME="+ cu.HederData(PathProperties.local_Header, "sbl_end_time_h1.dat");
            MainDual.header_data += "FINAL_END_TIME="+ cu.HederData(PathProperties.local_Header, "final_end_time_h1.dat");
            MainDual.header_data += "SBL_YIELD_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h1.dat");
            MainDual.header_data += "SBL_SUB_BINA_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat");
            MainDual.header_data += "SBL_SUB_BINA_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat");
            MainDual.header_data += "SBL_SUB_BINB_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat");
            MainDual.header_data += "SBL_SUB_BINB_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat");
            MainDual.header_data += "SBL_BIN9_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h1.dat");
            MainDual.header_data += "SBL_BIN9_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h1.dat");
            MainDual.header_data += "SBL_BIN8_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h1.dat");
            MainDual.header_data += "SBL_YIELD_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_yield_result_h1.dat");
            MainDual.header_data += "SBL_BIN1_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h1.dat");
            MainDual.header_data += "SBL_BIN2_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h1.dat");
            MainDual.header_data += "SBL_BIN3_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h1.dat");
            MainDual.header_data += "SBL_BIN4_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h1.dat");
            MainDual.header_data += "SBL_BIN5_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h1.dat");
            MainDual.header_data += "SBL_BIN6_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h1.dat");
            MainDual.header_data += "SBL_BIN7_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h1.dat");
            MainDual.header_data += "SBL_BIN8_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h1.dat");
            MainDual.header_data += "SBL_BIN9_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h1.dat");
            MainDual.header_data += "SBL_SUB_BINA_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_result_h1.dat");
            MainDual.header_data += "SBL_SUB_BINB_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_result_h1.dat");
            MainDual.header_data += "SBL_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_result_h1.dat");
        } else {                                                    
            MainDual.header_data += "TESTER_NUMBER="+ cu.HederData(PathProperties.local_Header, "tester_number.dat");
            MainDual.header_data += "TESTER_MODEL="+ cu.HederData(PathProperties.local_Header, "TESTID.dat");
            MainDual.header_data += "HANDLER_MODEL="+ cu.HederData(PathProperties.local_Header, "handler_model.dat");
            MainDual.header_data += "HEAD="+ cu.HederData(PathProperties.local_Header, "head.dat");
            MainDual.header_data += "PARA="+ cu.HederData(PathProperties.local_Header, "para_h2.dat");
            MainDual.header_data += "LOT_ID="+ cu.HederData(PathProperties.local_Header, "lot_id_h2.dat");
            MainDual.header_data += "PROCESS_CODE="+ cu.HederData(PathProperties.local_Header, "process_code_h2.dat");
            MainDual.header_data += "PART_NUMBER="+ cu.HederData(PathProperties.local_Header, "part_number_h2.dat");
            MainDual.header_data += "MAIN_PROGRAM_NAME="+ cu.HederData(PathProperties.local_Header, "main_program_name_h2.dat");
            MainDual.header_data += "GRADE="+ cu.HederData(PathProperties.local_Header, "grade_h2.dat");
            MainDual.header_data += "FAB="+ cu.HederData(PathProperties.local_Header, "fab_h2.dat");
            MainDual.header_data += "FIRMWARE_NAME="+ cu.HederData(PathProperties.local_Header, "firmware_name_h2.dat");
            MainDual.header_data += "FIRMWARE_VERSION"+ cu.HederData(PathProperties.local_Header, "firmware_version_h2.dat");
            MainDual.header_data += "TEMPERATURE="+ cu.HederData(PathProperties.local_Header, "temperature_h2.dat");
            MainDual.header_data += "OPERATOR_ID="+ cu.HederData(PathProperties.local_Header, "operator_id_h2.dat");
            MainDual.header_data += "QUANTITY="+ cu.HederData(PathProperties.local_Header, "quantity_h2.dat");
            MainDual.header_data += "FUNCTION_KEY="+ cu.HederData(PathProperties.local_Header, "function_key_h2.dat");
            MainDual.header_data += "PASS_BIN_SELECTION="+ cu.HederData(PathProperties.local_Header, "pass_bin_selection_h2.dat");
            MainDual.header_data += "TEST_COUNTER="+ cu.HederData(PathProperties.local_Header, "test_counter_h2.dat");
            MainDual.header_data += "TEST_INPUT="+ cu.HederData(PathProperties.local_Header, "test_input_h2.dat");
            MainDual.header_data += "TEST_FLOW="+ cu.HederData(PathProperties.local_Header, "test_flow_h2.dat");
            MainDual.header_data += "REWORK_FLAG="+ cu.HederData(PathProperties.local_Header, "rework_flag_h2.dat");
            MainDual.header_data += "TEST_MODE="+ cu.HederData(PathProperties.local_Header, "test_mode_h2.dat");
            MainDual.header_data += "BOARD_ID="+ cu.HederData(PathProperties.local_Header, "board_id_h2.dat");
            MainDual.header_data += "PRELOT_END_TIME="+ cu.HederData(PathProperties.local_Header, "prelot_end_time_h2.dat");
            MainDual.header_data += "LOT_IN_TIME="+ cu.HederData(PathProperties.local_Header, "lot_in_time_h2.dat");
            MainDual.header_data += "BETS_IN_TIME="+ cu.HederData(PathProperties.local_Header, "bets_in_time_h2.dat");
            MainDual.header_data += "BETS_END_TIME="+ cu.HederData(PathProperties.local_Header, "bets_end_time_h2.dat");
            MainDual.header_data += "LOT_IN_END_TIME="+ cu.HederData(PathProperties.local_Header, "lot_in_end_time_h2.dat");
            MainDual.header_data += "LOT_START_TIME="+ cu.HederData(PathProperties.local_Header, "lot_start_time_h2.dat");
            MainDual.header_data += "END_TIME="+ cu.HederData(PathProperties.local_Header, "end_time_h2.dat");
            MainDual.header_data += "BIN_IN_TIME="+ cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat");
            MainDual.header_data += "BIN_END_TIME="+ cu.HederData(PathProperties.local_Header, "bin_end_time_h2.dat");
            MainDual.header_data += "SBL_IN_TIME="+ cu.HederData(PathProperties.local_Header, "sbl_in_time_h2.dat");
            MainDual.header_data += "SBL_END_TIME="+ cu.HederData(PathProperties.local_Header, "sbl_end_time_h2.dat");
            MainDual.header_data += "FINAL_END_TIME="+ cu.HederData(PathProperties.local_Header, "final_end_time_h2.dat");
            MainDual.header_data += "SBL_YIELD_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h2.dat");
            MainDual.header_data += "SBL_SUB_BINA_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat");
            MainDual.header_data += "SBL_SUB_BINA_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat");
            MainDual.header_data += "SBL_SUB_BINB_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat");
            MainDual.header_data += "SBL_SUB_BINB_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat");
            MainDual.header_data += "SBL_BIN9_COUNTER="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h2.dat");
            MainDual.header_data += "SBL_BIN9_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h2.dat");
            MainDual.header_data += "SBL_BIN8_LIMIT="+ cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h2.dat");
            MainDual.header_data += "SBL_YIELD_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_yield_result_h2.dat");
            MainDual.header_data += "SBL_BIN1_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h2.dat");
            MainDual.header_data += "SBL_BIN2_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h2.dat");
            MainDual.header_data += "SBL_BIN3_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h2.dat");
            MainDual.header_data += "SBL_BIN4_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h2.dat");
            MainDual.header_data += "SBL_BIN5_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h2.dat");
            MainDual.header_data += "SBL_BIN6_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h2.dat");
            MainDual.header_data += "SBL_BIN7_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h2.dat");
            MainDual.header_data += "SBL_BIN8_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h2.dat");
            MainDual.header_data += "SBL_BIN9_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h2.dat");
            MainDual.header_data += "SBL_SUB_BINA_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_bina_result_h2.dat");
            MainDual.header_data += "SBL_SUB_BINB_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_sub_binb_result_h2.dat");
            MainDual.header_data += "SBL_RESULT="+ cu.HederData(PathProperties.local_Header, "sbl_result_h2.dat");
        }
    }
    
    // 현제 상태에 따른 버큰 상태 HEAD A
    public static void ButtonConditionA() {
        CommonUtil cu = new CommonUtil();
        
        if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("OPERATOR_ID")) {
            MainDual.main_operatorId_bt.setEnabled(false);
            
            if (MainDual.srqkind.equals("ON") ) {
                MainDual.main_test_in_auto_bt.setEnabled(true);
                MainDual.main_test_in_manual_bt.setEnabled(false);
            } else {
                MainDual.main_test_in_auto_bt.setEnabled(false);
                MainDual.main_test_in_manual_bt.setEnabled(true);
            }
            
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);
            MainDual.main_radio_st1.setSelected(true);
            
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
            if (MainDual.srqkind.equals("ON") ) {
                MainDual.main_re_test_end_bt.setEnabled(false);
                MainDual.main_final_test_end_bt.setEnabled(false);
            } else {
                MainDual.main_re_test_end_bt.setEnabled(true);
                MainDual.main_final_test_end_bt.setEnabled(true);
            }
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);

        } else if (cu.HederData(PathProperties.local_Header, "lc_seq_h1.dat").trim().equals("FINAL_END")) {
            MainDual.main_operatorId_bt.setEnabled(false);
            MainDual.main_test_in_auto_bt.setEnabled(false);
            MainDual.main_test_in_manual_bt.setEnabled(false);
            if (MainDual.srqkind.equals("ON") ) {
                MainDual.main_re_test_end_bt.setEnabled(false);
                MainDual.main_final_test_end_bt.setEnabled(false);
            } else {
                MainDual.main_re_test_end_bt.setEnabled(true);
                MainDual.main_final_test_end_bt.setEnabled(true);
            }
            
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
    
    // 현제 상태에 따른 버큰 상태 HEAD B
    public static void ButtonConditionB() {
        CommonUtil cu = new CommonUtil();
        
        if (cu.HederData(PathProperties.local_Header, "lc_seq_h2.dat").trim().equals("OPERATOR_ID")) {

            MainDual.main_operatorId_bt.setEnabled(false);
            if (MainDual.srqkind.equals("ON") ) {
                MainDual.main_test_in_auto_bt.setEnabled(true);
                MainDual.main_test_in_manual_bt.setEnabled(false);
            } else {
                MainDual.main_test_in_auto_bt.setEnabled(false);
                MainDual.main_test_in_manual_bt.setEnabled(true);
            }
            MainDual.main_re_test_end_bt.setEnabled(false);
            MainDual.main_final_test_end_bt.setEnabled(false);
            MainDual.main_test_in_cancel_bt.setEnabled(true);
            MainDual.main_pre_pgm_restore_bt.setEnabled(true);
            MainDual.main_radio_st2.setSelected(true);

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
    
    // exec 실행
    public void execExecution(String cmd) {
        Runtime run = Runtime.getRuntime();
        Process p = null;

        try {

            p = run.exec(cmd);
            StreamGobbler gb1 = new StreamGobbler(p.getInputStream());
            StreamGobbler gb2 = new StreamGobbler(p.getErrorStream());
            gb1.start();
            gb2.start();

            while (true) {
                if (!gb1.isAlive() && !gb2.isAlive()) { //두개의 스레드가 정지할면 프로세스 종료때까지 기다린다.
                    p.waitFor();
                    break;
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
    }
    
    /* exec 명령을 사용해서 데이터를 읽어 온다.*/
    public String executeRuntime(String command) {
        String return_data = null;
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                return_data += line + "\n";
            }

            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while (true) {
                String line = err.readLine();
                if (line == null)
                    break;
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_data;
    }
    
    public void FileNew(String path, String file_name, String write, boolean overwrite) {
        // 파일 객체 생성
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            // true 지정시 파일의 기존 내용에 이어서 작성
            BufferedWriter fw = new BufferedWriter(new FileWriter(path + file_name, overwrite));
            // 파일안에 문자열 쓰기
            fw.write(write);
            fw.flush();

            // 객체 닫기
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    // 파일 text 데이터 읽어 오기
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
    
    // 파일 text 데이터 읽어 오기
    public String HederData(String path, String file_name) {

        File fileDir = new File(path);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File file = new File(path + file_name);

        String return_value = "";

        // 파일 있는지 조회
        if (file.exists()) {
            return_value = FileReaderData(path, file_name, true);
        } else {
            // true 지정시 파일의 기존 내용에 이어서 작성

            BufferedWriter fw;
            try {
                fw = new BufferedWriter(new FileWriter(path + file_name, false));
                // 파일안에 문자열 쓰기
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
    
    //디렉토리의 파일 리스트를 읽는 메소드
    public List getDirFileList(String dirPath) {
        // 디렉토리 파일 리스트
        List dirFileList = null;
        
        // 파일 목록을 요청한 디렉토리를 가지고 파일 객체를 생성함
        File dir = new File(dirPath);

        // 디렉토리가 존재한다면
        if (dir.exists()) {
            // 파일 목록을 구함
            File[] files = dir.listFiles();

            // 파일 배열을 파일 리스트로 변화함 
            dirFileList = Arrays.asList(files);
        }
        return dirFileList;
    }
    
    //파일을 삭제하는 메소드
    public void fileDelete(String deleteFileName) {

        File fd = new File(deleteFileName);
        fd.delete();
    }
    
    public void Sequence (Date bin_back_dt, String actionBt) {
        
        CommonUtil cu = new CommonUtil();
        MainDual.header_data = "";
        // 헤더 정보 넣고
        cu.Headerdata();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        
        // 가시데이터 가져와서 넣기
        if (MainDual.main_radio_st1.isSelected()) {
            if ("CANCEL".equals(actionBt)) {
                if (!(this.casiFileName().equals(""))) {
                    String header_data = MainDual.header_data;
                    String bin_data = "<CASI>\n";
                    bin_data += cu.FileReaderData(PathProperties.ftpcasi, this.casiFileName(), false);
                    bin_data += "</CASI>\n";
                    header_data += "\n"+bin_data; 
                    cu.FileNew(PathProperties.lc_cancel_bin , "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
                    ftpModule.fileCopy(PathProperties.lc_cancel_bin+ "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADA");
                }
            } else {
                String header_data = MainDual.header_data;
                String bin_data = "<CASI>\n";
                bin_data += cu.FileReaderData(PathProperties.ftpcasi, "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADA", false);
                bin_data += "</CASI>\n";
                header_data += "\n"+bin_data; 
                cu.FileNew(PathProperties.lc_casi_bin , "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
                ftpModule.fileCopy(PathProperties.lc_casi_bin+ "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADA");
            }
        } else {
            
            if ("CANCEL".equals(actionBt)) {
                if (!(this.casiFileName().equals(""))) {
                    String header_data = MainDual.header_data;
                    String bin_data = "<CASI>\n"; 
                    bin_data += cu.FileReaderData(PathProperties.ftpcasi, this.casiFileName(), false);
                    bin_data += "</CASI>\n";
                    header_data += "\n"+bin_data;
                    cu.FileNew(PathProperties.lc_cancel_bin , "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADB", header_data, true);
                    ftpModule.fileCopy(PathProperties.lc_cancel_bin + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADB", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CANCEL_REPORT_"+sdf.format(bin_back_dt)+".HEADB");
                }
            } else {
                String header_data = MainDual.header_data;
                String bin_data = "<CASI>\n"; 
                bin_data += cu.FileReaderData(PathProperties.ftpcasi, "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADB", false);
                bin_data += "</CASI>\n";
                header_data += "\n"+bin_data;
                cu.FileNew(PathProperties.lc_casi_bin , "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADB", header_data, true);
                ftpModule.fileCopy(PathProperties.lc_casi_bin + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADB", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_CASI_REPORT_"+sdf.format(bin_back_dt)+".HEADB");
            }
        }
        
        // 칩데이터 생성
        if (MainDual.main_radio_st1.isSelected()) {
            Date chipid_dt = new Date();
            // 칩데이터 생성
            String header_data = MainDual.header_data;
            
            // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
            // 테스트
            // String chipid_data = "";
            // chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADA", true);
            // header_data += "\n" + chipid_data;
            // cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADA", header_data, true);
            // ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADA");
        } else {
            Date chipid_dt = new Date();
            // 칩데이터 생성
            String header_data = MainDual.header_data;
            // String chipid_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
            // 테스트
            // String chipid_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_chipid_report-20170630135555.HEADB", true);
            // header_data += "\n" + chipid_data;
            // cu.FileNew(PathProperties.ftpchid, "input_lotid123456_chipid_report-20170630135555.HEADB", header_data, true);
            // ftpModule.fileCopy(PathProperties.ftpchid + "input_lotid123456_chipid_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_chipid_report-20170630135555.HEADB");
        }
        
        // NG bin
        if (MainDual.main_radio_st1.isSelected()) {
            String header_data = MainDual.header_data;
            Date sub_bin_dt = new Date();
            
            // cu.execExecution("/home/fsdiag/NLcommand_FS/LCommand.sh");
            // 테스트
            String sub_bin_data = cu.FileReaderData(PathProperties.local, "SUM1.DAT", true);
            // 삭제 :  cu.SubBinData(sub_bin_data);
            
            // test_bin 데이터 계산
            cu.BinCalculator(sub_bin_data);
            
            header_data += "\n" + sub_bin_data;
            cu.FileNew(PathProperties.lc_ng_bin, "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_NGBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
            ftpModule.fileCopy(PathProperties.lc_ng_bin + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_NGBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_NGBBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA");
        } else {
            Date sub_bin_dt = new Date();
            String header_data = MainDual.header_data;
            // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
            // 테스트
            String sub_bin_data = cu.FileReaderData(PathProperties.local, "SUM1.DAT", true);
            // 삭제 : cu.SubBinData(sub_bin_data);
            
            header_data += "\n" + sub_bin_data;
            cu.FileNew(PathProperties.lc_ng_bin, "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_NGBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA", header_data, true);
            ftpModule.fileCopy(PathProperties.lc_ng_bin + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_NGBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim()+"_NGBBIN_REPORT_"+sdf.format(bin_back_dt)+".HEADA");
        }
    }
    
    // 빈데이터 값을 변경 한다. 
    public String getBinDataFormat(String binNum) {

        int bin_lg = binNum.length();

        String zero = "";

        for (int i = 0; i < (4 - bin_lg); i++) {
            zero += "0";
        }

        zero += binNum;
        return zero;
    }
    
    
    public void BinCalculator(String sub_bin_data) {

        int startIndex = sub_bin_data.indexOf(">");
        int endIndex = sub_bin_data.indexOf("</MAIN_BIN>");
        String binNumber = sub_bin_data.substring((startIndex + 1), endIndex).trim();

        String[] values = binNumber.split("\\s+");

        MainDual.test_bin = new HashMap();

        for (int x = 0; x < values.length; x++) {
            MainDual.test_bin.put("testBin_" + (x + 1), values[x].trim());
        }

        MainDual.testBin1 += Integer.parseInt(MainDual.test_bin.get("testBin_1").toString());
        MainDual.testBin2 += Integer.parseInt(MainDual.test_bin.get("testBin_2").toString());
        MainDual.testBin3 += Integer.parseInt(MainDual.test_bin.get("testBin_3").toString());
        MainDual.testBin4 += Integer.parseInt(MainDual.test_bin.get("testBin_4").toString());
        MainDual.testBin5 += Integer.parseInt(MainDual.test_bin.get("testBin_5").toString());
        MainDual.testBin6 += Integer.parseInt(MainDual.test_bin.get("testBin_6").toString());
        MainDual.testBin7 += Integer.parseInt(MainDual.test_bin.get("testBin_7").toString());
        MainDual.testBin8 += Integer.parseInt(MainDual.test_bin.get("testBin_8").toString());
        MainDual.testBin9 += Integer.parseInt(MainDual.test_bin.get("testBin_9").toString());
    }
    
    public void HandlerbinCalculator(String handler_bin_data) {

        int startIndex = handler_bin_data.indexOf(">");
        int endIndex = handler_bin_data.indexOf("</MAIN_BIN>");
        String binNumber = handler_bin_data.substring((startIndex + 1), endIndex).trim();

        String[] values = binNumber.split("\\s+");

        MainDual.handler_bin = new HashMap();

        for (int x = 0; x < values.length; x++) {
            MainDual.handler_bin.put("handlerBin_" + (x + 1), values[x].trim());
        }
        
        MainDual.handlerBin1 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_1").toString());
        MainDual.handlerBin2 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_2").toString());
        MainDual.handlerBin3 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_3").toString());
        MainDual.handlerBin4 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_4").toString());
        MainDual.handlerBin5 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_5").toString());
        MainDual.handlerBin6 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_6").toString());
        MainDual.handlerBin7 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_7").toString());
        MainDual.handlerBin8 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_8").toString());
        MainDual.handlerBin9 += Integer.parseInt(MainDual.handler_bin.get("handlerBin_9").toString());
    }
    
    
    // SBL 판정 결과 값 헤터 파일 생성
    public void SblBinResult(int positionNum, String handlerBin, String operatorBin, String fileDataName, CommonUtil cu) {
        
        String passBinSelection = "";
        if (MainDual.main_radio_st1.isSelected()) {
            passBinSelection = cu.FileReaderData(PathProperties.ftplocal, "pass_bin_selection_h1.dat", true).trim();
            
            if (String.valueOf(passBinSelection.charAt(positionNum)).equals("Y")) {
                if (handlerBin.equals(operatorBin)) {
                    cu.FileNew(PathProperties.local_Header, fileDataName, "NULL", false);
                } else {
                    cu.FileNew(PathProperties.local_Header, fileDataName, "ERROR", false);
                    MainDual.sblResultErrorCount_h1++;
                }
            } else {
                cu.FileNew(PathProperties.local_Header, fileDataName, "NULL", false);
            }
        } else {
            passBinSelection = cu.FileReaderData(PathProperties.ftplocal, "pass_bin_selection_h2.dat", true).trim();
            
            if (String.valueOf(passBinSelection.charAt(positionNum)).equals("Y")) {
                if (handlerBin.equals(operatorBin)) {
                    cu.FileNew(PathProperties.local_Header, fileDataName, "NULL", false);
                } else {
                    cu.FileNew(PathProperties.local_Header, fileDataName, "ERROR", false);
                    MainDual.sblResultErrorCount_h2++;
                }
            } else {
                cu.FileNew(PathProperties.local_Header, fileDataName, "NULL", false);
            }
        }
    }
    
    public String casiFileName() {

        CommonUtil cu = new CommonUtil();
        List casiLi = cu.getDirFileList(PathProperties.ftpcasi);

        String casiName = "";

        for (int i = 0; i < casiLi.size(); i++) {
            casiName = casiLi.get(i).toString();
        }

        int casiFileLastIndex = casiName.lastIndexOf("\\");

        return casiName.substring((casiFileLastIndex + 1));
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

