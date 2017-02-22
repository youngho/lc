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

public class TestInAutoProgress {
    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new TestInAutoTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class TestInAutoTask {
        Loading ld;

        TestInAutoTask() {

            CommonUtil cu = new CommonUtil();

            // ���� ���� ���� ���� ����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dt = new Date();

            if (MainDual.main_radio_st1.isSelected()) {
                // TEST Ƚ�� (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "0", false);
                // TEST INPUT ��� (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h1.dat", "AUTO", false);
                // ----> ���� LOT LOT_IN �ð�.(LC���� LOT_ID �Է��� OK button�� push�� ����)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(dt), false);

            } else {
                // TEST Ƚ�� (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "0", false);
                // TEST INPUT ��� (AUTO or MANUAL)
                cu.FileNew(PathProperties.local_Header, "test_input_h2.dat", "AUTO", false);
                // ----> ���� LOT LOT_IN �ð�.(LC���� LOT_ID �Է��� OK button�� push�� ����)
                cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(dt), false);
            }

            // �������
            Socket sock = null;
            BufferedReader br = null;
            PrintWriter pw = null;

            try {

                MainDual.loading_flg = false;

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                // ���� ����
                sock = new Socket(MainDual.socket_ip, Integer.parseInt(MainDual.socket_port));
                pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                // FTP ���� Ȯ��
                FTPClient ftpClient = new FTPClient();

                // ���� ���� ����
                boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
                if (!connet) {
                    JOptionPane.showMessageDialog(null, "FTP connection error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                // �α��� ���� ����
                boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, ld);
                if (!connet_login) {
                    JOptionPane.showMessageDialog(null, "FTP connet login error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.lot_no_st1 = TestInAutoPop.test_in_auto_text.getText();
                } else {
                    MainDual.lot_no_st2 = TestInAutoPop.test_in_auto_text.getText();
                }

                // ������ �Է¹��� id ����
                //                pw.println(
                //                  "<MSG>\n"
                //                + "<SYSTEM>OPIRUS</SYSTEM>\n"                   
                //                + "<CMD>PROGRAM_LOAD</CMD>\n"                   
                //                + "<LOTID>"+TestInAutoPop.test_in_auto_text.getText() +"</LOTID>\n"                    
                //                + "<PARTNUMBER></PARTNUMBER>\n"  
                //                + "<PROCESS></PROCESS>\n"                
                //                + "<MODEL>T5503</MODEL>\n"                      
                //                + "<HANDLER>TW350HT</HANDLER>\n"                
                //                + "<TESTER></TESTER>\n"                     
                //                + "<HEAD></HEAD>\n"                            
                //                + "<TYPE></TYPE>\n"                           
                //                + "<TMODE></TMODE>\n"                        
                //                + "<BOARD></BOARD>\n"                           
                //                + "<PARA></PARA>\n"                             
                //                + "<PRE_PGM></PRE_PGM>\n"                
                //                + "<PRE_REV></PRE_REV>\n"                      
                //                + "<TAR_FILE_CHK></TAR_FILE_CHK>\n"           
                //                + "<NCF_CODE></NCF_CODE>\n"                  
                //                + "<HMODE></HMODE>\n"                   
                //                + "<FSST_USING></FSST_USING>\n"               
                //                + "<SCKCTRL></SCKCTRL>\n"                     
                //                + "<PURPOSE_TYPE></PURPOSE_TYPE>\n"            
                //                + "<FAB></FAB>\n"                              
                //                + "<GRADE></GRADE>\n"                          
                //                + "<OPERATOR></OPERATOR>\n"                    
                //                + "<QTY></QTY>\n"
                //                + "<AUTO>AUTO</AUTO>\n"                              
                //                + "</MSG>\n"
                //               );

                SimpleDateFormat bets_in_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date bets_in_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    // LC�� BETS�� LOT_IN ��� ��������,BETS ������ LOT_ID ������ ������ ����.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h1.dat", bets_in_time_sdf.format(bets_in_time_dt), false);
                } else {
                    // LC�� BETS�� LOT_IN ��� ��������,BETS ������ LOT_ID ������ ������ ����.
                    cu.FileNew(PathProperties.local_Header, "bets_in_time_h2.dat", bets_in_time_sdf.format(bets_in_time_dt), false);
                }

                pw.println("<MSG>\n" 
                        + "<SYSTEM>OPIRUS</SYSTEM>\n" 
                        + "<CMD>PROGRAM_LOAD</CMD>\n" 
                        + "<LOTID>KGDTEST</LOTID>\n" 
                        + "<PARTNUMBER>H5TQXXXXXXX-XXX</PARTNUMBER>\n" 
                        + "<PROCESS>T092RWK</PROCESS>\n" 
                        + "<MODEL>T5503</MODEL>\n"
                        + "<HANDLER>STH5600</HANDLER>\n" 
                        + "<TESTER>kd00</TESTER>\n" 
                        + "<HEAD>A</HEAD>\n" 
                        + "<TYPE>PP</TYPE>\n" 
                        + "<TMODE>NOR</TMODE>\n" 
                        + "<BOARD></BOARD>\n" 
                        + "<PARA></PARA>\n" 
                        + "<PRE_PGM></PRE_PGM>\n"
                        + "<PRE_REV>0</PRE_REV>\n" 
                        + "<TAR_FILE_CHK>NO</TAR_FILE_CHK>\n" 
                        + "<NCF_CODE>---</NCF_CODE>\n" 
                        + "<HMODE>CONTINUE</HMODE>\n" 
                        + "<FSST_USING>NO</FSST_USING>\n" 
                        + "<SCKCTRL>NO</SCKCTRL>\n"
                        + "<PURPOSE_TYPE>-</PURPOSE_TYPE>\n" 
                        + "<FAB>-</FAB>\n" 
                        + "<GRADE>-</GRADE>\n" 
                        + "<OPERATOR>-</OPERATOR>\n" 
                        + "<QTY>-</QTY>\n" 
                        + "</MSG>");

                pw.flush();

                // �����κ��� �Է¹��� ���ڿ� ����ϴ� ������ ����
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
                    //  LC�� BETS�� ���� TEST PROGRAM�� ���� ��������,LC�� BETS�� LOT ���� ��� �Ϸ������  ����
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h1.dat", bets_end_time_sdf.format(bets_end_time_dt), false);
                } else {
                    //  LC�� BETS�� ���� TEST PROGRAM�� ���� ��������,LC�� BETS�� LOT ���� ��� �Ϸ������  ����
                    cu.FileNew(PathProperties.local_Header, "bets_end_time_h2.dat", bets_end_time_sdf.format(bets_end_time_dt), false);
                }

                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.main_lotno_text_st1.setText(MainDual.lot_no_st1);
                    MainDual.main_partnumber_text_st1.setText((String) MainDual.info_map_st1.get("PARTNUMBER"));
                    MainDual.main_processcode_text_st1.setText((String) MainDual.info_map_st1.get("PROCESS"));
                    MainDual.main_fab_text_st1.setText((String) MainDual.info_map_st1.get("FAB"));
                    MainDual.main_grade_text_st1.setText((String) MainDual.info_map_st1.get("GRADE"));
                    MainDual.main_temp_text_st1.setText((String) MainDual.info_map_st1.get("TEMP"));
                    MainDual.main_qty_text_st1.setText((String) MainDual.info_map_st1.get("QTY"));

                    cu.FileNew(PathProperties.local_Header, "partnumber_h1.dat", (String) MainDual.info_map_st1.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_h1.dat", (String) MainDual.info_map_st1.get("PROCESS"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h1.dat", (String) MainDual.info_map_st1.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h1.dat", (String) MainDual.info_map_st1.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temp_h1.dat", (String) MainDual.info_map_st1.get("TEMP"), false);
                    cu.FileNew(PathProperties.local_Header, "qty_h1.dat", (String) MainDual.info_map_st1.get("QTY"), false);
                    
                    // PRE_PGM_RESTORE READY ��带 ���ؼ� ���
                    cu.FileNew(PathProperties.ftplocal, "partnumber_h1.dat", (String) MainDual.info_map_st1.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.ftplocal, "process_h1.dat", (String) MainDual.info_map_st1.get("PROCESS"), false);
                    cu.FileNew(PathProperties.ftplocal, "fab_h1.dat", (String) MainDual.info_map_st1.get("FAB"), false);
                    cu.FileNew(PathProperties.ftplocal, "grade_h1.dat", (String) MainDual.info_map_st1.get("GRADE"), false);
                    cu.FileNew(PathProperties.ftplocal, "temp_h1.dat", (String) MainDual.info_map_st1.get("TEMP"), false);
                    cu.FileNew(PathProperties.ftplocal, "qty_h1.dat", (String) MainDual.info_map_st1.get("QTY"), false);
                    cu.FileNew(PathProperties.ftplocal, "tar_pgm_h1.dat", (String) MainDual.info_map_st1.get("TAR_PGM"), false);
                    
                } else {
                    MainDual.main_lotno_text_st2.setText(MainDual.lot_no_st2);
                    MainDual.main_partnumber_text_st2.setText((String) MainDual.info_map_st2.get("PARTNUMBER"));
                    MainDual.main_processcode_text_st2.setText((String) MainDual.info_map_st2.get("PROCESS"));
                    MainDual.main_fab_text_st2.setText((String) MainDual.info_map_st2.get("FAB"));
                    MainDual.main_grade_text_st2.setText((String) MainDual.info_map_st2.get("GRADE"));
                    MainDual.main_temp_text_st2.setText((String) MainDual.info_map_st2.get("TEMP"));
                    MainDual.main_qty_text_st2.setText((String) MainDual.info_map_st2.get("QTY"));
                    
                    cu.FileNew(PathProperties.local_Header, "partnumber_h2.dat", (String) MainDual.info_map_st2.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.local_Header, "process_h2.dat", (String) MainDual.info_map_st2.get("PROCESS"), false);
                    cu.FileNew(PathProperties.local_Header, "fab_h2.dat", (String) MainDual.info_map_st2.get("FAB"), false);
                    cu.FileNew(PathProperties.local_Header, "grade_h2.dat", (String) MainDual.info_map_st2.get("GRADE"), false);
                    cu.FileNew(PathProperties.local_Header, "temp_h2.dat", (String) MainDual.info_map_st2.get("TEMP"), false);
                    cu.FileNew(PathProperties.local_Header, "qty_h2.dat", (String) MainDual.info_map_st2.get("QTY"), false);
                    
                    // PRE_PGM_RESTORE READY ��带 ���ؼ� ���
                    cu.FileNew(PathProperties.ftplocal, "partnumber_h2.dat", (String) MainDual.info_map_st2.get("PARTNUMBER"), false);
                    cu.FileNew(PathProperties.ftplocal, "process_h2.dat", (String) MainDual.info_map_st2.get("PROCESS"), false);
                    cu.FileNew(PathProperties.ftplocal, "fab_h2.dat", (String) MainDual.info_map_st2.get("FAB"), false);
                    cu.FileNew(PathProperties.ftplocal, "grade_h2.dat", (String) MainDual.info_map_st2.get("GRADE"), false);
                    cu.FileNew(PathProperties.ftplocal, "temp_h2.dat", (String) MainDual.info_map_st2.get("TEMP"), false);
                    cu.FileNew(PathProperties.ftplocal, "qty_h2.dat", (String) MainDual.info_map_st2.get("QTY"), false);
                    cu.FileNew(PathProperties.ftplocal, "tar_pgm_h2.dat", (String) MainDual.info_map_st2.get("TAR_PGM"), false);
                }

                if (connet && connet_login) {

                    MainDual.appendToPane(MainDual.main_log_textPane, "Start downloading FTP files...\n", Color.BLACK);
                    /*
                     * 1. ftpClient ���� ������ 2. FTP���� �ּ� 3. ���� ���� ��� 4. ������ ���� �̸�
                     */
                    if (MainDual.main_radio_st1.isSelected()) {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st1.get("TAR_PGM"), PathProperties.ftplocal, (String) MainDual.info_map_st1.get("TAR_PGM"));
                    } else {
                        ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st2.get("TAR_PGM"), PathProperties.ftplocal, (String) MainDual.info_map_st2.get("TAR_PGM"));
                    }

                    FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
                    FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // ������ ���� ������ ���´�.
                    MainDual.appendToPane(MainDual.main_log_textPane, "FTP file download complete...\n", Color.BLACK);
                    
                    // FTP���� ���� �ٿ� �ް� ����
                    ftpModule.delete();
                    
                    SimpleDateFormat lot_in_end_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date lot_in_end_time_dt = new Date();
                    if (MainDual.main_radio_st1.isSelected()) {
                        //  LC�� BETS�� ���� TEST PROGRAM�� ���� ����
                        cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h1.dat", lot_in_end_time_sdf.format(lot_in_end_time_dt), false);
                    } else {
                        //  LC�� BETS�� ���� TEST PROGRAM�� ���� ����
                        cu.FileNew(PathProperties.local_Header, "lot_in_end_time_h2.dat", lot_in_end_time_sdf.format(lot_in_end_time_dt), false);
                    }

                } else {
                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : FTP connection error!!\n", Color.RED);
                    JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ld.setVisible(false);
                    ld.dispose();
                    return;
                }

                // SEQ4 1.BETS�� ���� ���� ���� Test Program�� T5503ȯ�濡 Up-Load�ǽ�
                // ==> ���� Ǯ�� : exec "tar xvf xxxx.tar"
                MainDual.appendToPane(MainDual.main_log_textPane, "exec tar xvf xxxx.tar \n", Color.BLACK);
                // Runtime.getRuntime().exec("tar xvf xxxx.tar");

                //                CommonUtil cu = new CommonUtil();

                // ==> TEST PROGRAM ���� ��� ���� : exec "fscd

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
                    //  LC�� BETS�� ���� ������ TEST PROGRAM�� T5503/MAGNUM5 ���� Memory�� Up-loading�Ϸ� ����
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
                    //  LC�� BETS�� ���� ������ TEST PROGRAM�� T5503/MAGNUM5 ���� Memory�� Up-loading�Ϸ� ����
                    cu.FileNew(PathProperties.local_Header, "pgm_upload_time_h2.dat", pgm_upload_time_sdf.format(pgm_upload_time_dt), false);
                }

                if ("off".equals(MainDual.srqkind)) {
                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);

                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);

//                    if (MainDual.main_radio_st1.isSelected()) {
//                        MainDual.station_step1 = "step_in_auto";
//                    } else {
//                        MainDual.station_step2 = "step_in_auto";
//                    }

                }

                // Handler Temp Check (Interlock ���)
                // temp1.c  Library�� ���� ��Ų�� ������� H1TEMP.dat ���Ϸ� ���
                // temp1.c�� ���� ���� ����.(GPIB  SRQ)

                //                String srq_handler_temp;
                //                String srq_handler_map;
                //                String srq_handler_para;
                //                
                //                
                //String temp_dat = cu.execExecutionFile("/home/fsdiag/GPIB/temp1", "���� �ִ� ��ġ", "H1TEMP.dat");
                //
                //                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);
                //
                //                srq_handler_temp = "90";
                //                // DUT MAP
                //                srq_handler_map = "ASDF";
                //                // PARA 
                //                srq_handler_para = "256";

                // ** �ϴ� �ּ� ó�� : �µ� ���� �ϴ� �κ�..     
                // BETS ���� ������ ���� BETS_TEST_TEMP�� ���� ����
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
                    // Tester/Handler Start
                    //                    Thread shT = new Thread(new ServerHelper());
                    //                    shT.start();

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
                    // Tester/Handler Start
                    //                    Thread shT = new Thread(new ServerHelper());
                    //                    shT.start();

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
