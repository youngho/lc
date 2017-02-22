package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.net.ftp.FTPClient;

import kr.co.famos.not.control.ftp.FtpConnect;
import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>TestInManualPop.java</code>
 * 
 * @company : FAMOS 
 * @Description : TEST_IN_MANUAL 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.03
 * @version : 1.0
 */

public class TestInManualPop2_20170213_backup extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    static JTextField test_in_manua_lot_number_text;
    static JTextField test_in_manua_part_number_text;
    static JTextField test_in_manua_process_code_text;
    static JTextField test_in_manua_grade_text;
    static JTextField test_in_manua_fab_text;
    static JTextField test_in_manua_temp_text;
    static JTextField test_in_manua_qty_text;
    
    // 에러 메시지 표시
    private JLabel test_in_manua_error_message;

    // 백그라운드 뒤단 처리
    private TestInManualProgress task;
    
    // 테스트 카운터 횟수
    static int test_in_manual_count_st1 = -1;
    static int test_in_manual_count_st2 = -1;
    
    /**
     * Create the dialog.
     */
    public TestInManualPop2_20170213_backup(final Frame parent) {

        super(parent, true);
        setTitle("TEST_IN_MANUAL");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 416, 415);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel test_in_manua_panel = new JPanel();
            test_in_manua_panel.setLayout(null);
            test_in_manua_panel.setBackground(Color.LIGHT_GRAY);
            test_in_manua_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(test_in_manua_panel);
            {
                JLabel test_in_manua_title_lb = new JLabel("HEAD A");
                test_in_manua_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_manua_title_lb.setBounds(154, 52, 76, 18);
                test_in_manua_panel.add(test_in_manua_title_lb);
            }
            {
                JLabel test_in_manual_header_lb = new JLabel("TEST_IN_MANUAL");
                test_in_manual_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_manual_header_lb.setBounds(114, 17, 156, 18);
                test_in_manua_panel.add(test_in_manual_header_lb);
            }
        }

        JLabel test_in_manua_lot_number_lb = new JLabel("LOT ID");
        test_in_manua_lot_number_lb.setBounds(17, 109, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_lot_number_lb);

        test_in_manua_lot_number_text = new JTextField();
        test_in_manua_lot_number_text.setText("KGDTEST");
        test_in_manua_lot_number_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String lot_no_check = test_in_manua_lot_number_text.getText();
                if (lot_no_check.length() >= 30) {
                    test_in_manua_lot_number_text.setText(test_in_manua_lot_number_text.getText().substring(0, 30));
                }
            }
        });
        test_in_manua_lot_number_text.setColumns(10);
        test_in_manua_lot_number_text.setBounds(153, 109, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_lot_number_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(17, 326, 367, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_in_manua_bt_ok = new JButton("OK");
                test_in_manua_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
//                        dispose();
//                        task = new TestInManualProgress();
//                        task.go();
                    }
                });
                test_in_manua_bt_ok.setBounds(89, 5, 53, 27);
                test_in_manua_bt_ok.setActionCommand("OK");
                buttonPane.add(test_in_manua_bt_ok);
                getRootPane().setDefaultButton(test_in_manua_bt_ok);
            }

            {
                JButton test_in_manua_bt_exit = new JButton("EXIT");
                test_in_manua_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_in_manua_bt_exit.setBounds(231, 5, 77, 27);
                test_in_manua_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_in_manua_bt_exit);
            }
        }

        test_in_manua_error_message = new JLabel("");
        test_in_manua_error_message.setForeground(Color.RED);
        test_in_manua_error_message.setBounds(17, 370, 367, 18);
        test_in_auto__parent_panel.add(test_in_manua_error_message);
        
        JLabel test_in_manua_part_number_lb = new JLabel("PART NUMBER");
        test_in_manua_part_number_lb.setBounds(17, 142, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_part_number_lb);
        
        test_in_manua_part_number_text = new JTextField();
        test_in_manua_part_number_text.setText("H5TQXXXXXXX-XXX");
        test_in_manua_part_number_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
            }
        });
        test_in_manua_part_number_text.setColumns(10);
        test_in_manua_part_number_text.setBounds(153, 142, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_part_number_text);
        
        JLabel test_in_manua_process_code_lb = new JLabel("PROCESS CODE");
        test_in_manua_process_code_lb.setBounds(17, 175, 122, 18);
        test_in_auto__parent_panel.add(test_in_manua_process_code_lb);
        
        test_in_manua_process_code_text = new JTextField();
        test_in_manua_process_code_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_process_code_text.setColumns(10);
        test_in_manua_process_code_text.setBounds(153, 175, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_process_code_text);
        
        JLabel test_in_manua_grade_lb = new JLabel("GRADE");
        test_in_manua_grade_lb.setBounds(17, 235, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_grade_lb);
        
        test_in_manua_grade_text = new JTextField();
        test_in_manua_grade_text.setText("HQ");
        test_in_manua_grade_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_grade_text.setColumns(10);
        test_in_manua_grade_text.setBounds(153, 235, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_grade_text);
        
        JLabel test_in_manua_fab_lb = new JLabel("FAB");
        test_in_manua_fab_lb.setBounds(17, 205, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_fab_lb);
        
        test_in_manua_fab_text = new JTextField();
        test_in_manua_fab_text.setText("M10");
        test_in_manua_fab_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_fab_text.setColumns(10);
        test_in_manua_fab_text.setBounds(153, 205, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_fab_text);
        
        JLabel test_in_manua_temp_lb = new JLabel("TEMP");
        test_in_manua_temp_lb.setBounds(17, 266, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_temp_lb);
        
        test_in_manua_temp_text = new JTextField();
        test_in_manua_temp_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_temp_text.setColumns(10);
        test_in_manua_temp_text.setBounds(153, 266, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_temp_text);
        
        JLabel test_in_manua_qty_lb = new JLabel("QTY");
        test_in_manua_qty_lb.setBounds(17, 296, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_qty_lb);
        
        test_in_manua_qty_text = new JTextField();
        test_in_manua_qty_text.setText("1000");
        test_in_manua_qty_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_qty_text.setColumns(10);
        test_in_manua_qty_text.setBounds(153, 296, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_qty_text);
    }
}

class TestInManualProgress1 {

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
            
            String input_lot_no         = TestInManualPop2_20170213_backup.test_in_manua_lot_number_text.getText();
            String bets_part_number     = TestInManualPop2_20170213_backup.test_in_manua_part_number_text.getText();
            String bets_process_code    = TestInManualPop2_20170213_backup.test_in_manua_process_code_text.getText();
            String bets_grade_code      = TestInManualPop2_20170213_backup.test_in_manua_grade_text.getText();
            String bets_fab_code        = TestInManualPop2_20170213_backup.test_in_manua_fab_text.getText();
            String bets_test_temp       = TestInManualPop2_20170213_backup.test_in_manua_temp_text.getText();
            String bets_quantity        = TestInManualPop2_20170213_backup.test_in_manua_qty_text.getText();
            
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
                boolean connet_login = FtpConnect.Login(ftpClient, PathProperties.ftpurl, PathProperties.ftpid, PathProperties.ftppw, ld);

                if (!connet && !connet_login) {
                    JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MainDual.main_radio_st1.isSelected()) {
//                    MainDual.main_fab_text_st1.setText(bets_fab_code);        
//                    MainDual.main_partnumber_text_st1.setText(bets_part_number); 
//                    MainDual.main_lotno_text_st1.setText(input_lot_no);      
//                    MainDual.main_grade_text_st1.setText(bets_grade_code);      
//                    MainDual.main_qty_text_st1.setText(bets_quantity);        
//                    MainDual.main_temp_text_st1.setText(bets_test_temp);    
                    
                    MainDual.main_lotno_text_st1.setText(MainDual.lot_no_st1);
                    MainDual.main_partnumber_text_st1.setText((String) MainDual.info_map_st1.get("PARTNUMBER"));
                    MainDual.main_processcode_text_st1.setText((String) MainDual.info_map_st1.get("PROCESS"));
                    MainDual.main_fab_text_st1.setText((String) MainDual.info_map_st1.get("FAB"));
                    MainDual.main_grade_text_st1.setText((String) MainDual.info_map_st1.get("GRADE"));
                    MainDual.main_temp_text_st1.setText((String) MainDual.info_map_st1.get("TEMP"));
                    MainDual.main_qty_text_st1.setText((String) MainDual.info_map_st1.get("QTY"));
                    
                    
                } else {
//                    MainDual.main_fab_text_st2.setText(bets_fab_code);        
//                    MainDual.main_partnumber_text_st2.setText(bets_part_number); 
//                    MainDual.main_lotno_text_st2.setText(input_lot_no);      
//                    MainDual.main_grade_text_st2.setText(bets_grade_code);      
//                    MainDual.main_qty_text_st2.setText(bets_quantity);        
//                    MainDual.main_temp_text_st2.setText(bets_test_temp);   
                }

                // 서버로 입력받은 id 전송
//                 pw.println(
//                   "<MSG>\n"
//                     + "<SYSTEM>OPIRUS</SYSTEM>\n"                   
//                     + "<CMD>PROGRAM_LOAD</CMD>\n"                   
//                     + "<LOTID>"+TestInAutoPop.test_in_auto_text.getText() +"</LOTID>\n"                    
//                     + "<PARTNUMBER>" +bets_part_number + "</PARTNUMBER>\n"  
//                     + "<PROCESS>" +bets_process_code+ "</PROCESS>\n"                
//                     + "<MODEL>T5503</MODEL>\n"                      
//                     + "<HANDLER>TW350HT</HANDLER>\n"                
//                     + "<TESTER></TESTER>\n"                     
//                     + "<HEAD></HEAD>\n"                            
//                     + "<TYPE></TYPE>\n"                           
//                     + "<TMODE></TMODE>\n"                        
//                     + "<BOARD></BOARD>\n"                           
//                     + "<PARA></PARA>\n"                             
//                     + "<PRE_PGM></PRE_PGM>\n"                
//                     + "<PRE_REV></PRE_REV>\n"                      
//                     + "<TAR_FILE_CHK></TAR_FILE_CHK>\n"           
//                     + "<NCF_CODE></NCF_CODE>\n"                  
//                     + "<HMODE></HMODE>\n"                   
//                     + "<FSST_USING></FSST_USING>\n"               
//                     + "<SCKCTRL></SCKCTRL>\n"                     
//                     + "<PURPOSE_TYPE></PURPOSE_TYPE>\n"            
//                     + "<FAB>" + bets_fab_code + "</FAB>\n"                              
//                     + "<GRADE>"+bets_grade_code+"</GRADE>\n"                          
//                     + "<OPERATOR></OPERATOR>\n"                    
//                     + "<QTY>" + bets_quantity + "</QTY>\n"
//                     + "<MANUAL>MANUAL</MANUAL>\n"                              
//                     + "</MSG>\n"
//                );
                
                
                pw.println(
                        "<MSG>\n"
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
                       + "<PRE_PGM>000.016</PRE_PGM>\n"
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
                       + "</MSG>"
                       );
                 
                 
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
                
                if (MainDual.main_radio_st1.isSelected()) {
                    MainDual.main_lotno_text_st1.setText(MainDual.lot_no_st1);
                    MainDual.main_partnumber_text_st1.setText((String) MainDual.info_map_st1.get("BETS_PART_NUMBER"));
                    MainDual.main_qty_text_st1.setText((String) MainDual.info_map_st1.get("BETS_QUANTITY"));
                    MainDual.main_temp_text_st1.setText((String) MainDual.info_map_st1.get("BETS_TEST_TEMP"));
                } else {
//                    MainDual.main_lotno_text_st2.setText(MainDual.lot_no_st2);
//                    MainDual.main_partnumber_text_st2.setText((String) MainDual.info_map_st2.get("BETS_PART_NUMBER"));
//                    MainDual.main_qty_text_st2.setText((String) MainDual.info_map_st2.get("BETS_QUANTITY"));
//                    MainDual.main_temp_text_st2.setText((String) MainDual.info_map_st2.get("BETS_TEST_TEMP"));
                }

                if (connet && connet_login) {

                    MainDual.appendToPane(MainDual.main_log_textPane, "Start downloading FTP files...\n", Color.BLACK);
                    /*
                     * 1. ftpClient 접속 생성자 2. FTP파일 주소 3. 로컬 저장 경로 4. 저장할 파일 이름
                     */
                    ftpModule.ftpDown(ftpClient, "/BETS/" + (String) MainDual.info_map_st1.get("TAR_PGM"), PathProperties.ftplocal, (String) MainDual.info_map_st1.get("TAR_PGM"));
                    FtpConnect.Logout(ftpClient, PathProperties.ftpurl); // LOGOUT
                    FtpConnect.disconnect(ftpClient, PathProperties.ftpurl); // 서버로 부터 접속을 끝는다.
                    MainDual.appendToPane(MainDual.main_log_textPane, "FTP file download complete...\n", Color.BLACK);
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
                // Runtime.getRuntime().exec("tar xvf xxxx.tar");

                // ==> TEST PROGRAM 폴더 경로 설정 : exec "fscd
                if (MainDual.main_radio_st1.isSelected()) {

                    //Runtime.getRuntime().exec("fstmode --station 1 --auto");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fstmode --station 1 --auto\n", Color.BLACK);

                    // Runtime.getRuntime().exec("fscd --station1/home/fsdiag/HTM");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fscd --station1/home/fsdiag/HTM\n", Color.BLACK);
                    // ==> TEST MAIN PROGRAM UP-LOAD : exec
                    // "fsproset --station 1 HT8GTSTO.pro"
                    // Runtime.getRuntime().exec("fsproset --station 1 HT8GTSTO.pro");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsproset --station 1 HT8GTSTO.pro\n", Color.BLACK);

                    // ==> exec "ls *.soc > soc.dat" ==>
                    // T5503256.soc
                    // soc.data를 soc로 변환
                    // Runtime.getRuntime().exec("ls *.soc > soc.dat");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec ls *.soc > soc.dat\n", Color.BLACK);

                    // ==> TEST SOCKET PROGRAM UP-LOAD :
                    // exec
                    // "fsconf --station 1 T5503256.soc"
                    // Runtime.getRuntime().exec("fsconf --station 1 T5503256.soc");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec fsconf --station 1 T5503256.soc \n", Color.BLACK);

                    // Runtime.getRuntime().exec("cp /home/fsdiag/SBCAL/*.cal .");
                    MainDual.appendToPane(MainDual.main_log_textPane, "exec cp /home/fsdiag/SBCAL/*.cal . \n", Color.BLACK);

                    String stationOnOff = (String) MainDual.info_map_st1.get("FUNCTION KEY");

                    if (stationOnOff != null) {
                        for (int i = 0; i < 16; i++) {
                            if (stationOnOff.charAt(i) == 'Y') {
                                // Runtime.getRuntime().exec("fsfk --station 1 --on " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 1 --on" + (i + 1) + " \n", Color.BLACK);
                            } else {
                                // Runtime.getRuntime().exec("fsfk --station 2 --off " + (i+1));
                                MainDual.appendToPane(MainDual.main_log_textPane, "exec fsfk --station 2 --on" + (i + 1) + " \n", Color.BLACK);
                            }
                        }
                    }

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
                }

                // 2.BETS로 부터 전달 받은 Test Program을 T5503 지정된 폴드에 Back-Up실시
                if ("off".equals(MainDual.srqkind)) {
                    MainDual.main_test_in_auto_bt.setEnabled(false);
                    MainDual.main_test_in_manual_bt.setEnabled(false);

                    MainDual.main_re_test_end_bt.setEnabled(true);
                    MainDual.main_final_test_end_bt.setEnabled(true);
                } 
                
                // Handler Temp Check (Interlock 기능)
                // temp1.c  Library를 구동 시킨후 결과값을 H1TEMP.dat 파일로 담기
                // temp1.c는 별도 개발 예정.(GPIB  SRQ)

                String srq_handler_temp;
                String srq_handler_map;
                String srq_handler_para;

                //                Process temp_dat = Runtime.getRuntime().exec("/home/fsdiag/GPIB/temp1 > H1TEMP.dat");
                //
                //                BufferedReader in_temp_dat = new BufferedReader(new InputStreamReader(temp_dat.getInputStream()));
                //
                //                while (true) {
                //                    String out_temp_dat = in_temp_dat.readLine();
                //                    if (out_temp_dat == null) {
                //                        break;
                //                    } else {
                //                        srq_handler_temp = out_temp_dat;
                //                        System.out.println(out_temp_dat);
                //                    }
                //                }

                MainDual.appendToPane(MainDual.main_log_textPane, "exec /home/fsdiag/GPIB/temp1 > H1TEMP.dat \n", Color.BLACK);

                srq_handler_temp = "90";
                // DUT MAP
                srq_handler_map = "ASDF";
                // PARA 
                srq_handler_para = "256";

//              ** 일단 주석 처리 : 온도 측정 하는 부분..     
              // BETS 에서 내려온 정보 BETS_TEST_TEMP가 기준 정보
//              if (MainDual.main_radio_st1.isSelected()) {
//
//                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
//                          && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
//                                  && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
//                          && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
//                                  && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_DUT_MAP")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//                  if ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
//                          && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
//                                  && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_PARA")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//
//              } else {
//                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) <= Integer.parseInt(srq_handler_temp)
//                          && Integer.parseInt(srq_handler_temp) <= ((Integer.parseInt((String) MainDual.info_map_st1.get("BETS_TEST_TEMP")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) >= Integer.parseInt(srq_handler_temp)
//                                  && Integer.parseInt(srq_handler_temp) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_TEST_TEMP")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_temp Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) <= Integer.parseInt(srq_handler_map)
//                          && Integer.parseInt(srq_handler_map) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) >= Integer.parseInt(srq_handler_map)
//                                  && Integer.parseInt(srq_handler_map) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_DUT_MAP")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_map Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//                  if ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) <= Integer.parseInt(srq_handler_para)
//                          && Integer.parseInt(srq_handler_para) <= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) + 3)))
//                          || (Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) >= Integer.parseInt(srq_handler_para)
//                                  && Integer.parseInt(srq_handler_para) >= ((Integer.parseInt((String) MainDual.info_map_st2.get("BETS_PARA")) - 3)))) {
//                      MainDual.appendToPane(MainDual.main_log_textPane, "handler_para Inconsistency \n", Color.RED);
//                      JOptionPane.showMessageDialog(null, "Handler ERROR !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                      ld.setVisible(false);
//                      ld.dispose();
//                      return;
//                  }
//              }
                
                /*********************************************************************************************************
                 * SRQ 가 어떻게 들어 오는지 여기서 계속 첵크를 해야 됨..
                 * 04 - 리테스트
                 * 01- 파이널 엔드 테스트
                 * *******************************************************************************************************/
                if ("on".equals(MainDual.srqkind)) {
                    
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    
                    int srq_reTest = 3;
                    int srq_final = 1;
                    
                    int srq_reTest_count = 0;
                    int srq_final_count = 0;
                    
                    SRQKIND_20170212_backup srqkind = new SRQKIND_20170212_backup();
                    
                    while (true) {
                        if (srq_reTest_count == srq_reTest) break;
//                        srqkind.SRQ_AUTO_ReTest();
                        
                        // 프로세스 정상 적으로 성공 하면 manual 횟수 증가 시킨다.
                        if (MainDual.main_radio_st1.isSelected()) {
                            TestInManualPop2_20170213_backup.test_in_manual_count_st1++;
                        } else {
                            TestInManualPop2_20170213_backup.test_in_manual_count_st2++;
                        }
                        
                        srq_reTest_count++;
                    }
                    
                    while (true) {
                        if (srq_final_count == srq_final) break;
//                        srqkind.SRQ_AUTO_Final();
                        srq_final_count++;
                    }
                    
                    MainDual.loading_flg = true;
                    ld.setVisible(false);
                    ld.dispose();
                    
                    BinSummaryPop_20170216 BinSummaryPop = new BinSummaryPop_20170216(MainDual.main_frm_d);
                    BinSummaryPop.setVisible(true);
                    return;
                    
                } else {
                    // 파일이름을 input_lot_no-binon_report-start_date.ST1
                    // 파일이름을 input_lot_no-binon_report-start_date.ST1
                    
                    // 시작 날자 종료 날자 선언
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date dt = new Date();
                    MainDual.start_date = sdf.format(dt);
                    
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
                        test_counter = Integer.toString(TestInManualPop2_20170213_backup.test_in_manual_count_st1);
                        bets_step_id = (String) MainDual.info_map_st1.get("BETS_STEP_ID");
                        betsPartNumber = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                        bets_function_key = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                        bets_qty = (String) MainDual.info_map_st1.get("BETS_QUANTITY");
                        input_operator_id = MainDual.main_operator_text_st1.getText();
                    } else {
//                        sys_number = MainDual.main_title_lb.getText();
//                        inputLotNo = MainDual.main_lotno_text_st2.getText();
//                        test_counter = Integer.toString(TestInManualPop.test_in_manual_count_st2);
//                        bets_step_id = (String) MainDual.info_map_st2.get("BETS_STEP_ID");
//                        betsPartNumber = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
//                        bets_function_key = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
//                        bets_qty = (String) MainDual.info_map_st2.get("BETS_QUANTITY");
//                        input_operator_id = MainDual.main_operator_text_st2.getText();
                    }

                    try {
                        File file;
                        if (MainDual.main_radio_st1.isSelected()) {
                            // 파일 복사
                            file = new File(PathProperties.ftpcasi + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1");
                        } else {
                            file = new File(PathProperties.ftpcasi + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2");
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

                        Process test_in_auto_data = Runtime.getRuntime().exec("java");

                        BufferedReader test_in_auto_read = new BufferedReader(new InputStreamReader(test_in_auto_data.getInputStream()));

                        while (true) {
                            String test_in_auto_line = test_in_auto_read.readLine();
                            if (test_in_auto_line == null) {
                                break;
                            } else {
                                MainDual.appendToPane(MainDual.main_log_textPane, test_in_auto_line + "\n", Color.BLACK);
                                fw.write(test_in_auto_line + "\r\n");
                            }
                        }

                        fw.flush();
                        fw.close();

                        if (MainDual.main_radio_st1.isSelected()) {
                            // 파일 복사
                            ftpModule.fileCopy(PathProperties.ftpcasi + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST1");
                        } else {
                            // 파일 복사
                            ftpModule.fileCopy(PathProperties.ftpcasi + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-binon_report-" + MainDual.start_date + ".ST2");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
                    }

                    // thead1.dat을 head로 사용하고 그밑에 hynix에서 내려준 Chip_ID정보를 매 Shot 별 data Append 시킴
                    try {
                        File file_chid;
                        if (MainDual.main_radio_st1.isSelected()) {
                            file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
                        } else {
                            file_chid = new File(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
                        }

                        // true 지정시 파일의 기존 내용에 이어서 작성
                        FileWriter fw_chid = new FileWriter(file_chid, false);

                        // 파일안에 문자열 쓰기
                        fw_chid.write(sys_number + "\r\n");
                        fw_chid.write(input_lot_no + "\r\n");
                        fw_chid.write(test_counter + "\r\n");
                        fw_chid.write(bets_step_id + "\r\n");
                        fw_chid.write(bets_part_number + "\r\n");
                        fw_chid.write(bets_function_key + "\r\n");
                        fw_chid.write(bets_qty + "\r\n");
                        fw_chid.write(input_operator_id + "\r\n");

                        Process test_in_auto_data_chid = Runtime.getRuntime().exec("java");

                        BufferedReader test_in_auto_read_chid = new BufferedReader(new InputStreamReader(test_in_auto_data_chid.getInputStream()));

                        while (true) {
                            String test_in_auto_line_chid = test_in_auto_read_chid.readLine();
                            if (test_in_auto_line_chid == null) {
                                break;
                            } else {
                                MainDual.appendToPane(MainDual.main_log_textPane, test_in_auto_line_chid + "\n", Color.BLACK);
                                fw_chid.write(test_in_auto_line_chid + "\r\n");
                            }
                        }

                        fw_chid.flush();
                        fw_chid.close();

                        if (MainDual.main_radio_st1.isSelected()) {
                            // 파일 복사
                            ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST1");
                        } else {
                            // 파일 복사
                            ftpModule.fileCopy(PathProperties.ftpchid + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2", PathProperties.ftppre + "input_lot_no-chid_report-start_" + MainDual.start_date + ".ST2");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
                    }
                    
                    MainDual.loading_flg = true;
                    
                    ld.setVisible(false);
                    ld.dispose();
                }
                
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
