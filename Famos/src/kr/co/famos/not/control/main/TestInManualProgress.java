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
                cu.FileNew(PathProperties.local_Header, "lot_id_h1.dat", TestInManualPop.test_in_manua_lot_number_text.getText(), false);
                cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "TEST_IN_MANUAL", false);
                // PRE_PGM_RESTORE READY 모드를 위해서 백업
                cu.FileNew(PathProperties.ftplocal, "lc_seq_h1.dat", "TEST_IN_MANUAL", false);
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h1.dat", "MANUAL", false);
                // 핸들러 A
                cu.FileNew(PathProperties.local_Header, "head.dat", "A", false);
                // 테스트모드
                cu.FileNew(PathProperties.local_Header, "test_mode_h1.dat", MainDual.main_testmode_text_st1.getText() , false);
            } else {
                cu.FileNew(PathProperties.local_Header, "lot_id_h1.dat", TestInManualPop.test_in_manua_lot_number_text.getText(), false);
                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "TEST_IN_MANUAL", false);
                // PRE_PGM_RESTORE READY 모드를 위해서 백업
                cu.FileNew(PathProperties.ftplocal, "lc_seq_h2.dat", "TEST_IN_MANUAL", false);
                // TEST 횟수 (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "0", false);
                // TEST INPUT 방식 (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h2.dat", "MANUAL", false);
                // 핸들러 B
                cu.FileNew(PathProperties.local_Header, "head.dat", "B", false);
                // 테스트모드
                cu.FileNew(PathProperties.local_Header, "test_mode_h2.dat", MainDual.main_testmode_text_st2.getText() , false);
            }

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

                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.lot_no_st1 = TestInManualPop.test_in_manua_lot_number_text.getText();
                } else {
                    MainDual.lot_no_st2 = TestInManualPop.test_in_manua_lot_number_text.getText();
                }

                // LC가 BETS한테 LOT 정보 주는 시점.
                Date bets_in_time_dt = new Date();
                String operator = ""; 
                String lotno = "";
                String testmode = "";
                
                if (MainDual.main_radio_st1.isSelected()) {  
                    // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h1.dat", sdf.format(bets_in_time_dt), false);
                    operator = cu.HederData(PathProperties.local_Header, "operator_id_h1.dat").trim();
                    lotno = cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim();
                    testmode = cu.HederData(PathProperties.local_Header, "test_mode_h1.dat").trim();
                } else {
                    // LC가 BETS에 LOT_IN 통신 시점에서,BETS 서버에 LOT_ID 전송한 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h2.dat", sdf.format(bets_in_time_dt), false);
                    operator = cu.HederData(PathProperties.local_Header, "operator_id_h2.dat").trim();
                    lotno = cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim();
                    testmode = cu.HederData(PathProperties.local_Header, "test_mode_h2.dat").trim();
                }
                
                System.out.println("##########################TestInAutoProgress.java########################################");
                System.out.println("operator ===========> " + operator);
                System.out.println("testmode ===========> " + testmode);
                System.out.println("TESTID.dat ===> " + cu.HederData(PathProperties.local_Header, "TESTID.dat").trim());
                System.out.println("head.dat ===========> " + cu.HederData(PathProperties.local_Header, "head.dat").trim());
                System.out.println("operator ===========> " + operator);
                System.out.println("##########################TestInAutoProgress.java########################################");
                
               pw.println("<MSG>\n"
                        + "<CMD>LOT_IN</CMD>\n"
                        + "<TEST_IN_MODE>MANUAL</TEST_IN_MODE>\n"
                        + "<OPERATOR_ID>"+ operator +"</OPERATOR_ID>\n"
                        + "<TEST_MODE>"+ testmode +"</TEST_MODE>\n"
                        + "<LOT_ID>"+ lotno +"</LOT_ID>\n"
                        + "<PART_NUMBER>"+bets_part_number+"</PART_NUMBER>\n"
                        + "<PROCESS_CODE>"+bets_process_code+"</PROCESS_CODE>\n"
                        + "<TEMPERATURE>"+bets_test_temp+"</TEMPERATURE>\n"
                        + "<FAB>"+bets_fab_code+"</FAB>\n"
                        + "<GRADE>"+bets_grade_code+"</GRADE>\n"
                        + "<QTY>"+bets_quantity+"</QTY>\n"
                        + "<TESTER_MODEL>"+cu.HederData(PathProperties.local_Header, "TESTID.dat").trim()+"</TESTER_MODEL>\n"
                        + "<HEAD>"+cu.HederData(PathProperties.local_Header, "head.dat").trim()+"</HEAD>\n"
                        + "</MSG>\n");
                
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
                
                // LC가 BETS로 부터 LOT 정보를 받는 시점.(PROGREAM,LOT 정보)   
                Date bets_end_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    //  LC가 BETS로 부터 LOT 정보를 받는 시점.(PROGREAM,LOT 정보)    
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h1.dat", sdf.format(bets_end_time_dt), false);
                } else {
                    //  LC가 BETS로 부터 LOT 정보를 받는 시점.(PROGREAM,LOT 정보)    
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h2.dat", sdf.format(bets_end_time_dt), false);
                }

                if (MainDual.main_radio_st1.isSelected()) {

                    MainDual.main_lotno_text_st1.setText(MainDual.lot_no_st1);
                    MainDual.main_partnumber_text_st1.setText((String) MainDual.info_map_st1.get("PART_NUMBER"));
                    MainDual.main_processcode_text_st1.setText((String) MainDual.info_map_st1.get("PROCESS_CODE"));
                    MainDual.main_fab_text_st1.setText((String) MainDual.info_map_st1.get("FAB"));
                    MainDual.main_grade_text_st1.setText((String) MainDual.info_map_st1.get("GRADE"));
                    MainDual.main_temp_text_st1.setText((String) MainDual.info_map_st1.get("TEMPERATURE"));

                    // MainDual.main_qty_text_st1.setText((String) MainDual.info_map_st1.get("QTY"));
                    MainDual.main_qty_text_st1.setText("1000");

                    cu.FileNew(PathProperties.local_Header, "part_number_h1.dat", (String) MainDual.info_map_st1.get("PART_NUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_code_h1.dat", (String) MainDual.info_map_st1.get("PROCESS_CODE"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h1.dat", (String) MainDual.info_map_st1.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h1.dat", (String) MainDual.info_map_st1.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temperature_h1.dat", (String) MainDual.info_map_st1.get("TEMPERATURE"), false);
                    cu.FileNew(PathProperties.local_Header, "function_key_h1.dat", (String) MainDual.info_map_st1.get("FUNCTION_KEY"), false);

                    /* 나중에 BETS에서 받아서 처리 */
                    MainDual.info_map_st1.put("PASS_BIN_SELECTION", "YYYYNNNN");
                    cu.FileNew(PathProperties.local_Header, "pass_bin_selection_h1.dat", (String) MainDual.info_map_st1.get("PASS_BIN_SELECTION"), false);
                    /* 나중에 BETS에서 받아서 처리 */

                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h1.dat", (String) MainDual.info_map_st1.get("SBL_BIN9_COUNTER"), false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h1.dat", (String) MainDual.info_map_st1.get("SBL_BIN8_LIMIT"), false);

                    // MES에서 수량 정보를 받아 와야 되는데  개발 환경에서는 MES랑 통신이 안되기 때문에 일단 하드 코딩
                    //  cu.FileNew(PathProperties.local_Header, "quantity_h1.dat", (String) MainDual.info_map_st1.get("QTY"), false);
                    cu.FileNew(PathProperties.local_Header, "quantity_h1.dat", "1000", false);
                    cu.FileNew(PathProperties.local_Header, "pgm_name_h1.dat", (String) MainDual.info_map_st1.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "firmware_name_h1.dat", (String) MainDual.info_map_st1.get("FW_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "firmware_version_h1.dat", (String) MainDual.info_map_st1.get("FW_VERSION"), false);
                    cu.FileNew(PathProperties.local_Header, "main_program_name_h1.dat", (String) MainDual.info_map_st1.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "para_h1.dat", (String) MainDual.info_map_st1.get("PARA"), false);
                    cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "NULL", false);

                    // MES에서 정보를 BETS에 보내 주어야 함.
                    // 오퍼레이터가 not이 돌아 가고 있는데, 중간에 다른 핸들러에 가서 다시 not 정보를 입력해서 장비를 기동할때 애러를 출력하기 위한 플레그 일단 대기.....
                    cu.FileNew(PathProperties.local_Header, "rework_flag_h1.dat", (String) MainDual.info_map_st1.get("REWORK_FLAG"), false);

                    // PRE_PGM_RESTORE READY 모드를 위해서 백업
                    cu.FileNew(PathProperties.ftplocal, "part_number_h1.dat", (String) MainDual.info_map_st1.get("PART_NUMBER"), false);
                    cu.FileNew(PathProperties.ftplocal, "process_code_h1.dat", (String) MainDual.info_map_st1.get("PROCESS_CODE"), false);
                    cu.FileNew(PathProperties.ftplocal, "fab_h1.dat", (String) MainDual.info_map_st1.get("FAB"), false);
                    cu.FileNew(PathProperties.ftplocal, "grade_h1.dat", (String) MainDual.info_map_st1.get("GRADE"), false);
                    cu.FileNew(PathProperties.ftplocal, "temperature_h1.dat", (String) MainDual.info_map_st1.get("TEMPERATURE"), false);
                    cu.FileNew(PathProperties.ftplocal, "function_key_h1.dat", (String) MainDual.info_map_st1.get("FUNCTION_KEY"), false);
                    // cu.FileNew(PathProperties.ftplocal, "pass_bin_selection_h1.dat", (String) MainDual.info_map_st1.get("PASS_BIN_SELECTION"), false);
                    cu.FileNew(PathProperties.ftplocal, "pass_bin_selection_h1.dat", "YYYYNNNN", false);
                    cu.FileNew(PathProperties.ftplocal, "sbl_bin9_counter_h1.dat", (String) MainDual.info_map_st1.get("SBL_BIN9_COUNTER"), false);

                    // cu.FileNew(PathProperties.ftplocal, "quantity_h1.dat", (String) MainDual.info_map_st1.get("QUANTITY"), false);
                    cu.FileNew(PathProperties.ftplocal, "quantity_h1.dat", "1000", false);
                    cu.FileNew(PathProperties.ftplocal, "pgm_name_h1.dat", (String) MainDual.info_map_st1.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "firmware_name_h1.dat", (String) MainDual.info_map_st1.get("FW_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "firmware_version_h1.dat", (String) MainDual.info_map_st1.get("FW_VERSION"), false);
                    cu.FileNew(PathProperties.ftplocal, "function_key_h1.dat", (String) MainDual.info_map_st1.get("FUNCTION_KEY"), false);
                    cu.FileNew(PathProperties.ftplocal, "main_program_name_h1.dat", (String) MainDual.info_map_st1.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "para_h1.dat", (String) MainDual.info_map_st1.get("PARA"), false);
                    cu.FileNew(PathProperties.ftplocal, "test_flow_h1.dat", "NULL", false);

                    // MES에서 정보를 BETS에 보내 주어야 함.
                    // 오퍼레이터가 not이 돌아 가고 있는데, 중간에 다른 핸들러에 가서 다시 not 정보를 입력해서 장비를 기동할때 애러를 출력하기 위한 플레그 일단 대기.....
                    cu.FileNew(PathProperties.ftplocal, "rework_flag_h1.dat", (String) MainDual.info_map_st1.get("REWORK_FLAG"), false);

                } else {

                    MainDual.main_lotno_text_st2.setText(MainDual.lot_no_st2);
                    MainDual.main_partnumber_text_st2.setText((String) MainDual.info_map_st2.get("PART_NUMBER"));
                    MainDual.main_processcode_text_st2.setText((String) MainDual.info_map_st2.get("PROCESS_CODE"));
                    MainDual.main_fab_text_st2.setText((String) MainDual.info_map_st2.get("FAB"));
                    MainDual.main_grade_text_st2.setText((String) MainDual.info_map_st2.get("GRADE"));
                    MainDual.main_temp_text_st2.setText((String) MainDual.info_map_st2.get("TEMPERATURE"));

                    // MainDual.main_qty_text_st1.setText((String) MainDual.info_map_st1.get("QTY"));
                    MainDual.main_qty_text_st1.setText("1000");

                    cu.FileNew(PathProperties.local_Header, "part_number_h2.dat", (String) MainDual.info_map_st2.get("PART_NUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_code_h2.dat", (String) MainDual.info_map_st2.get("PROCESS_CODE"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h2.dat", (String) MainDual.info_map_st2.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h2.dat", (String) MainDual.info_map_st2.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temperature_h2.dat", (String) MainDual.info_map_st2.get("TEMPERATURE"), false);
                    cu.FileNew(PathProperties.local_Header, "function_key_h2.dat", (String) MainDual.info_map_st2.get("FUNCTION_KEY"), false);

                    /* 나중에 BETS에서 받아서 처리 */
                    MainDual.info_map_st2.put("PASS_BIN_SELECTION", "YYYYNNNN");
                    cu.FileNew(PathProperties.local_Header, "pass_bin_selection_h1.dat", (String) MainDual.info_map_st1.get("PASS_BIN_SELECTION"), false);
                    /* 나중에 BETS에서 받아서 처리 */

                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h2.dat", (String) MainDual.info_map_st2.get("SBL_BIN9_COUNTER"), false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h2.dat", (String) MainDual.info_map_st2.get("SBL_BIN8_LIMIT"), false);

                    // MES에서 수량 정보를 받아 와야 되는데  개발 환경에서는 MES랑 통신이 안되기 때문에 일단 하드 코딩
                    //  cu.FileNew(PathProperties.local_Header, "quantity_h1.dat", (String) MainDual.info_map_st1.get("QTY"), false);
                    cu.FileNew(PathProperties.local_Header, "quantity_h2.dat", "1000", false);
                    cu.FileNew(PathProperties.local_Header, "pgm_name_h2.dat", (String) MainDual.info_map_st2.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "firmware_name_h2.dat", (String) MainDual.info_map_st2.get("FW_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "firmware_version_h2.dat", (String) MainDual.info_map_st2.get("FW_VERSION"), false);
                    cu.FileNew(PathProperties.local_Header, "main_program_name_h2.dat", (String) MainDual.info_map_st2.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.local_Header, "para_h2.dat", (String) MainDual.info_map_st2.get("PARA"), false);
                    cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "NULL", false);

                    // MES에서 정보를 BETS에 보내 주어야 함.
                    // 오퍼레이터가 not이 돌아 가고 있는데, 중간에 다른 핸들러에 가서 다시 not 정보를 입력해서 장비를 기동할때 애러를 출력하기 위한 플레그 일단 대기.....
                    cu.FileNew(PathProperties.local_Header, "rework_flag_h2.dat", (String) MainDual.info_map_st2.get("REWORK_FLAG"), false);

                    // PRE_PGM_RESTORE READY 모드를 위해서 백업
                    cu.FileNew(PathProperties.ftplocal, "part_number_h2.dat", (String) MainDual.info_map_st2.get("PART_NUMBER"), false);
                    cu.FileNew(PathProperties.ftplocal, "process_code_h2.dat", (String) MainDual.info_map_st2.get("PROCESS_CODE"), false);
                    cu.FileNew(PathProperties.ftplocal, "fab_h2.dat", (String) MainDual.info_map_st2.get("FAB"), false);
                    cu.FileNew(PathProperties.ftplocal, "grade_h2.dat", (String) MainDual.info_map_st2.get("GRADE"), false);
                    cu.FileNew(PathProperties.ftplocal, "temperature_h2.dat", (String) MainDual.info_map_st2.get("TEMPERATURE"), false);
                    cu.FileNew(PathProperties.ftplocal, "function_key_h2.dat", (String) MainDual.info_map_st2.get("FUNCTION_KEY"), false);
                    // cu.FileNew(PathProperties.ftplocal, "pass_bin_selection_h2.dat", (String) MainDual.info_map_st2.get("PASS_BIN_SELECTION"), false);
                    cu.FileNew(PathProperties.ftplocal, "pass_bin_selection_h2.dat", "YYYYNNNN", false);
                    cu.FileNew(PathProperties.ftplocal, "sbl_bin9_counter_h2.dat", (String) MainDual.info_map_st2.get("SBL_BIN9_COUNTER"), false);

                    // cu.FileNew(PathProperties.ftplocal, "quantity_h2.dat", (String) MainDual.info_map_st2.get("QUANTITY"), false);
                    cu.FileNew(PathProperties.ftplocal, "quantity_h2.dat", "1000", false);
                    cu.FileNew(PathProperties.ftplocal, "pgm_name_h2.dat", (String) MainDual.info_map_st2.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "firmware_name_h2.dat", (String) MainDual.info_map_st2.get("FW_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "firmware_version_h2.dat", (String) MainDual.info_map_st2.get("FW_VERSION"), false);
                    cu.FileNew(PathProperties.ftplocal, "function_key_h2.dat", (String) MainDual.info_map_st2.get("FUNCTION_KEY"), false);
                    cu.FileNew(PathProperties.ftplocal, "main_program_name_h2.dat", (String) MainDual.info_map_st2.get("PGM_NAME"), false);
                    cu.FileNew(PathProperties.ftplocal, "para_h2.dat", (String) MainDual.info_map_st2.get("PARA"), false);
                    cu.FileNew(PathProperties.ftplocal, "test_flow_h2.dat", "NULL", false);

                    // MES에서 정보를 BETS에 보내 주어야 함.
                    // 오퍼레이터가 not이 돌아 가고 있는데, 중간에 다른 핸들러에 가서 다시 not 정보를 입력해서 장비를 기동할때 애러를 출력하기 위한 플레그 일단 대기.....
                    cu.FileNew(PathProperties.ftplocal, "rework_flag_h2.dat", (String) MainDual.info_map_st2.get("REWORK_FLAG"), false);

                }

                if (connet && connet_login) {
                    MainDual.appendToPane(MainDual.main_log_textPane, "Start downloading FTP files...\n", Color.BLACK);

                    // 1. ftpClient 접속 생성자 2. FTP파일 주소 3. 로컬 저장 경로 4. 저장할 파일 이름

                    if (MainDual.main_radio_st1.isSelected()) {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st1.get("TAR_NAME"), PathProperties.ftplocal, (String) MainDual.info_map_st1.get("TAR_NAME"));
                    } else {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st2.get("TAR_NAME"), PathProperties.ftplocal, (String) MainDual.info_map_st2.get("TAR_NAME"));
                    }

                    FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
                    FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // 서버로 부터 접속을 끝는다.
                    MainDual.appendToPane(MainDual.main_log_textPane, "FTP file download complete...\n", Color.BLACK);

                    // FTP에서 파일 다운 받고 삭제
                    ftpModule.delete();
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
                //                cu.execExecution("tar xvf xxxx.tar");

                // ==> TEST PROGRAM 폴더 경로 설정 : exec "fscd

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
                    // soc.dat를 soc로 변환
                    // cu.execExecution("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat\n", Color.BLACK);

                    // soc.dat 파일 내용을 읽어서….. 
                    //                     String red_soc = cu.FileReaderData("1 /home/fsdiag/HTM", "soc.dat", true);

                    // ==> TEST SOCKET PROGRAM UP-LOAD :
                    // exec
                    // "fsconf --station 1 T5503256.soc"
                    // cu.execExecution("fsconf --station 1" + red_soc);
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // cu.execExecution("cp /home/fsdiag/SBCAL/*.cal .");
                    // 2.BETS로 부터 전달 받은 Test Program을 T5503 지정된 폴드에 Back-Up실시
                    //                     cu.execExecution("cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "cp  -f *.pro *.mpa *.scr *.soc *.lib *.sub  /home/fsdiag/EMCP/BACKUP/PGM \n", Color.BLACK);

                    String stationOnOff = (String) MainDual.info_map_st1.get("FUNCTION_KEY");

                    if (!"-".equals(stationOnOff)) {
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

                    Date lot_in_end_time_dt = new Date();
                    //  LC가 BETS로 부터 가져온 TEST PROGRAM을 T5503/MAGNUM5 설비 Memory에 Up-loading완료 시점
                    cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h1.dat", sdf.format(lot_in_end_time_dt), false);

                } else {

                    // String board_id = cu.executeRuntime("BOARD_ID");
                    cu.FileNew(PathProperties.local_Header, "board_id_h2.dat", "0", false);

                    // cu.execExecution("fstmode --station 2 --auto");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fstmode --station 2 --auto . \n", Color.BLACK);

                    // cu.execExecution("fscd --station 2 /home/fsdiag/HTM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fscd --station 2 /home/fsdiag/HTM \n", Color.BLACK);

                    // cu.execExecution("fsproset --station 1 HT8GTSTO.pro");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsproset --station 1 HT8GTSTO.pro \n", Color.BLACK);

                    // cu.execExecution("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat \n", Color.BLACK);

                    // cu.execExecution("fsconf --station 1 T5503256.soc");
                    // textArea.setText("exec fsconf --station 1 T5503256.soc" + "\r\n");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // cu.execExecution("cp /home/fsdiag/SBCAL/*.cal .");
                    // textArea.setText("exec cp /home/fsdiag/SBCAL/*.cal ." + "\r\n");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec cp /home/fsdiag/SBCAL/*.cal . \n", Color.BLACK);

                    String stationOnOff = (String) MainDual.info_map_st2.get("FUNCTION_KEY");

                    for (int i = 0; i < stationOnOff.length(); i++) {

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

                    Date lot_in_end_time_dt = new Date();
                    //  LC가 BETS로 부터 가져온 TEST PROGRAM을 T5503/MAGNUM5 설비 Memory에 Up-loading완료 시점
                    cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h2.dat", sdf.format(lot_in_end_time_dt), false);
                }

                if ("OFF".equals(MainDual.srqkind)) {
                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);

                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);

                    // if (MainDual.main_radio_st1.isSelected()) {
                    //     MainDual.station_step1 = "step_in_auto";
                    // } else {
                    //     MainDual.station_step2 = "step_in_auto";
                    // }

                }

                //                cu.executeRuntime("exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat");
                // Handler Temp Check (Interlock 기능)
                // temp1.c  Library를 구동 시킨후 결과값을 H1TEMP.dat 파일로 담기
                // temp1.c는 별도 개발 예정.(GPIB  SRQ)

                //                String temp_dat = cu.FileReaderData("파일 있는 위치", "H1TEMP.dat", true);

                String gpib_handler_temp = "-32";
                String gpib_handler_map = "DUT_MAP_128PARA";

                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);

                // ** 일단 주석 처리 : 온도 측정 하는 부분..     
                // BETS 에서 내려온 정보 BETS_TEST_TEMP가 기준 정보
                if (MainDual.main_radio_st1.isSelected()) {

                    /*
                     * int temp_difference =
                     * (Integer.parseInt(gpib_handler_temp) -
                     * ((Integer.parseInt((String)
                     * MainDual.info_map_st1.get("TEMPERATURE")))));
                     */

                    // if (((Integer.parseInt((String) MainDual.info_map_st1.get("TEMPERATURE_LIMIT"))) < Math.abs(temp_difference))) {
                    //     MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                    //     JOptionPane.showMessageDialog(null, "Handler TEMP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    //     ld.setVisible(false);
                    //     ld.dispose();
                    //     return;
                    // }
                    // 
                    // if (!gpib_handler_map.equals((String) MainDual.info_map_st1.get("DUT_MAP"))) {
                    //     MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                    //     JOptionPane.showMessageDialog(null, "Handler DUT_MAP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    //     ld.setVisible(false);
                    //     ld.dispose();
                    //     return;
                    // }

                } else {
                    int temp_difference = (Integer.parseInt(gpib_handler_temp) - ((Integer.parseInt((String) MainDual.info_map_st2.get("TEMPERATURE")))));

                    // if (((Integer.parseInt((String) MainDual.info_map_st2.get("TEMPERATURE_LIMIT"))) < Math.abs(temp_difference))) {
                    //     MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
                    //     JOptionPane.showMessageDialog(null, "Handler TEMP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    //     ld.setVisible(false);
                    //     ld.dispose();
                    //     return;
                    // }
                    // 
                    // if (!gpib_handler_map.equals((String) MainDual.info_map_st2.get("DUT_MAP"))) {
                    //     MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
                    //     JOptionPane.showMessageDialog(null, "Handler DUT_MAP ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    //     ld.setVisible(false);
                    //     ld.dispose();
                    //     return;
                    // }
                }

                if (MainDual.srqkind.equals("ON")) {

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

                    System.out.println("@@@@@@@@@@@@@@@BinSummaryPop 호출 전@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

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
