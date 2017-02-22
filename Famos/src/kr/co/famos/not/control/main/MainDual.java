package kr.co.famos.not.control.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import kr.co.famos.not.control.socket.ServerHelper;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>MainDual.java</code>
 * 
 * @company : FAMOS 
 * @Description : LOT CONTROL 듀얼 메인 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.01.31
 * @version : 1.0
 */

public class MainDual {
    
    static JFrame main_frm_d;
    public static JTextField main_operator_text_st1;
    public static JTextField main_fab_text_st1;
    public static JTextField main_partnumber_text_st1;
    public static JTextField main_lotno_text_st1;
    public static JTextField main_grade_text_st1;
    public static JTextField main_qty_text_st1;
    public static JTextField main_temp_text_st1;
    public static JTextField main_processcode_text_st1;
    public static JTextField main_operator_text_st2;
    public static JTextField main_fab_text_st2;
    public static JTextField main_partnumber_text_st2;
    public static JTextField main_lotno_text_st2;
    public static JTextField main_grade_text_st2;
    public static JTextField main_qty_text_st2;
    public static JTextField main_temp_text_st2;
    public static JTextField main_processcode_text_st2;
    public static JTextField main_testmode_text_st1;
    public static JTextField main_testmode_text_st2;
    
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    // 스테이션 선택 라이오 버튼 전역 번수 선언
    public static JRadioButton main_radio_st1;
    static JRadioButton main_radio_st2;
    
    // 소켓통신(베츠에서 얻어온값) 담기
    public static Map info_map_st1;
    public static Map info_map_st2;
    
    // 서브 빈에서 bin데이터 값 생성
    public static Map sub_bin_map_st1;
    public static Map sub_bin_map_st2;
    
    // 소켓 통신후 베츠에서 정보 다 받아 올때 까지 기다리는 변수...
    static boolean thread_stop;
    
    // 로고 출력해 주는 패널
    public static JTextPane main_log_textPane;
    
    // 버튼 전역 변수 선언
    public static JButton main_operatorId_bt;
    public static JButton main_test_in_auto_bt;
    public static JButton main_test_in_manual_bt;
    public static JButton main_re_test_end_bt;
    public static JButton main_final_test_end_bt;
    public static JButton main_test_in_cancel_bt;
    public static JButton main_pre_pgm_restore_bt;
    
    // TEST_IN_AUTO 입력창 
    public static String lot_no_st1;
    public static String lot_no_st2;
    
    // 메인 레이블 (장비 명칭)
    public static JLabel main_title_lb;
    
    // 라디오 버튼 현재 상태 
//    public static String station_step1 = "step_op_id";
//    public static String station_step2 = "step_op_id";
    
    // 시작 시간 
    public static String start_date;
    // 종료 시간
    public static String end_date;
    
    // SRQKIND
    static String srqkind = "on";
    
    // 소켓 통신 
    static String socket_ip = "121.185.32.49";
    static String socket_port = "7074";
    private JMenu mnTool;
    
    // 로딩 창 실행시 버튼 클릭 방지 플래그
    static boolean loading_flg = true;
    
    // 소켓 메시지
    public static String soket_message = "";
    
    public static boolean while_break = false;
    private JLabel main_processcode_lb_st1;
    private JLabel main_processcode_lb_st2;
    
    // 헤더 데이터 공통
    public static String header_data = "";
    
    // TEST_IN_MANUAL MODE
    public static String test_in_manual_mode_h1 = "PRODUCT MODE";
    public static String test_in_manual_mode_h2 = "PRODUCT MODE";
    
    /*텍스트 textArea 창에 로그 뿌리기*/
    public static void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        // aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                PropertyConfigurator.configure("log4j.properties");
                Logger logger = Logger.getLogger(getClass());
                
                try {
                    MainDual window = new MainDual();
                    window.main_frm_d.setVisible(true);
                    CommonUtil cu = new CommonUtil();
                    
                    /* 프레임 화면 가운데 */
                    Dimension frameSize = window.main_frm_d.getSize();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    window.main_frm_d.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                    
                    // 프로퍼티 설
                    PathProperties pts = new PathProperties();
                    pts.PropertiesSetting();
                    
                    // 소켓 통신에서 받아온 값을 담는 변수
                    info_map_st1 = new HashMap();
                    info_map_st2 = new HashMap();
                    
                    sub_bin_map_st1 = new HashMap();
                    sub_bin_map_st2 = new HashMap();
                    
                    // FTP 연결 확인
//                    FTPClient ftpClient = new FTPClient();
                    
                    // 접속 상태 오류
//                    boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
                    
//                    if (connet) {
//                        ftpModule.FtpServerSend(0);
//
//                        while (true) {
//                            if (ftpModule.re_test_end_exit) {
//                                break;
//                            }
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "FTP connection error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                    }
                    
                    // 화면창 X(창 닫기) 버튼을 누르면  닫기 확인 창이 뜬다.
                    // 핸들러 1 상태
                    main_operator_text_st1.setText(cu.HederData(PathProperties.local_Header, "input_operator_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "input_operator_id_h1.dat").trim());
                    main_lotno_text_st1.setText(cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim());
                    main_partnumber_text_st1.setText(cu.HederData(PathProperties.local_Header, "partnumber_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "partnumber_h1.dat").trim());
                    main_processcode_text_st1.setText(cu.HederData(PathProperties.local_Header, "process_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "process_h1.dat").trim());
                    main_fab_text_st1.setText(cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim());
                    main_grade_text_st1.setText(cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim());
                    main_temp_text_st1.setText(cu.HederData(PathProperties.local_Header, "temp_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "temp_h1.dat").trim());
                    main_qty_text_st1.setText(cu.HederData(PathProperties.local_Header, "qty_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "qty_h1.dat"));

                    // 핸들러 2 상태
                    main_operator_text_st2.setText(cu.HederData(PathProperties.local_Header, "input_operator_id_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "input_operator_id_h2.dat").trim());
                    main_lotno_text_st2.setText(cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim());
                    main_partnumber_text_st2.setText(cu.HederData(PathProperties.local_Header, "partnumber_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "partnumber_h2.dat").trim());
                    main_processcode_text_st2.setText(cu.HederData(PathProperties.local_Header, "process_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "process_h2.dat").trim());
                    main_fab_text_st2.setText(cu.HederData(PathProperties.local_Header, "fab_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "fab_h2.dat").trim());
                    main_grade_text_st2.setText(cu.HederData(PathProperties.local_Header, "grade_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "grade_h2.dat").trim());
                    main_temp_text_st2.setText(cu.HederData(PathProperties.local_Header, "temp_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "temp_h2.dat").trim());
                    main_qty_text_st2.setText(cu.HederData(PathProperties.local_Header, "qty_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "qty_h.dat"));
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        // 로컬에 파일 데이터를 읽어와서 현재 버튼의 상태를 컨트롤 한다.
                        CommonUtil.ButtonConditionA();
                    } else {
                        // 로컬에 파일 데이터를 읽어와서 현재 버튼의 상태를 컨트롤 한다.
                        CommonUtil.ButtonConditionB();
                    }
                    
                    window.main_frm_d.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent e) {

                            if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                                ClosePassWord ClosePassWord = new ClosePassWord(main_frm_d);
                                ClosePassWord.setVisible(true);
                            }
                        }
                    });
                    
                    // CommonUtil cu = new CommonUtil();
                    // MainDual.tester_h = cu.execExecution("java");
                    
                    Thread shT = new Thread(new ServerHelper());
                    shT.start();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainDual() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        main_frm_d = new JFrame();
        main_frm_d.setTitle("HTM T5503 LOT CONTROLLER");
        main_frm_d.setBounds(100, 100, 1087, 690);
        main_frm_d.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main_frm_d.getContentPane().setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1076, 26);
        main_frm_d.getContentPane().add(menuBar);
        
        JMenu mnNewMenu = new JMenu("Admin");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Setting");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SettingPassWord SettingPassWord = new SettingPassWord(main_frm_d);
                SettingPassWord.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);
        
        mnTool = new JMenu("Tool");
        menuBar.add(mnTool);
        
        JMenuItem mntmSrq = new JMenuItem("SRQKIND");
        mntmSrq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SRQKINDPassWord SRQKINDPassWord = new SRQKINDPassWord(main_frm_d);
                SRQKINDPassWord.setVisible(true);
            }
        });
        mnTool.add(mntmSrq);
        
        JPanel main_panel_1 = new JPanel();
        main_panel_1.setLayout(null);
        main_panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_1.setBounds(5, 77, 233, 328);
        main_frm_d.getContentPane().add(main_panel_1);
        
        main_radio_st2 = new JRadioButton("HEAD B");
        main_radio_st2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                CommonUtil.ButtonConditionB();
                /*if (station_step2.equals("step_op_id")) {
                    main_test_in_auto_bt.setEnabled(true);
                    main_test_in_manual_bt.setEnabled(true);

                    main_re_test_end_bt.setEnabled(false);
                    main_final_test_end_bt.setEnabled(false);
                } else if (station_step2.equals("step_in_auto")) {
                    main_test_in_auto_bt.setEnabled(false);
                    main_test_in_manual_bt.setEnabled(false);

                    main_re_test_end_bt.setEnabled(true);
                    main_final_test_end_bt.setEnabled(true);
                } else if (station_step2.equals("step_in_manual")) {
                    main_test_in_auto_bt.setEnabled(false);
                    main_test_in_manual_bt.setEnabled(false);

                    main_re_test_end_bt.setEnabled(true);
                    main_final_test_end_bt.setEnabled(true);
                } else if (station_step2.equals("step_re")) {

                } else if (station_step2.equals("step_final")) {

                } else {

                }*/
                
                
            }
        });
        buttonGroup.add(main_radio_st2);
        main_radio_st2.setBounds(132, 22, 84, 27);
        main_panel_1.add(main_radio_st2);
        
        main_radio_st1 = new JRadioButton("HEAD A");
        main_radio_st1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                CommonUtil.ButtonConditionA();
                
                /*if (station_step1.equals("step_op_id")) {
                    main_test_in_auto_bt.setEnabled(true);
                    main_test_in_manual_bt.setEnabled(true);

                    main_re_test_end_bt.setEnabled(false);
                    main_final_test_end_bt.setEnabled(false);
                } else if (station_step1.equals("step_in_auto")) {
                    main_test_in_auto_bt.setEnabled(false);
                    main_test_in_manual_bt.setEnabled(false);

                    main_re_test_end_bt.setEnabled(true);
                    main_final_test_end_bt.setEnabled(true);
                } else if (station_step1.equals("step_in_manual")) {
                    main_test_in_auto_bt.setEnabled(false);
                    main_test_in_manual_bt.setEnabled(false);

                    main_re_test_end_bt.setEnabled(true);
                    main_final_test_end_bt.setEnabled(true);
                } else if (station_step1.equals("step_re")) {

                } else if (station_step1.equals("step_final")) {

                } else {

                }*/
                
                
            }
        });
        buttonGroup.add(main_radio_st1);
        main_radio_st1.setSelected(true);
        main_radio_st1.setBounds(17, 22, 98, 27);
        main_panel_1.add(main_radio_st1);
        
        JButton button = new JButton("MANUAL");
        button.setBounds(0, 0, 0, 0);
        main_panel_1.add(button);
        
        main_operatorId_bt = new JButton("OPERATOR ID");
        main_operatorId_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (loading_flg) {
                    OperatorIdPop OperatorId = new OperatorIdPop(main_frm_d);
                    OperatorId.setVisible(true);
                }
            }
        });
        main_operatorId_bt.setBounds(31, 62, 185, 27);
        main_panel_1.add(main_operatorId_bt);
        
        main_test_in_auto_bt = new JButton("TEST_IN_AUTO");
        main_test_in_auto_bt.setEnabled(false);
        main_test_in_auto_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (loading_flg) {
                    if (main_radio_st1.isSelected()) {
                        if ("".equals(main_operator_text_st1.getText())) {
                            JOptionPane.showMessageDialog(null, "STATION #1 OPERATOR 입력 하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        if ("".equals(main_operator_text_st2.getText())) {
                            JOptionPane.showMessageDialog(null, "STATION #2 OPERATOR 입력 하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    thread_stop = false;
                    TestInAutoPop TestInAutoPop = new TestInAutoPop(main_frm_d);
                    TestInAutoPop.setVisible(true);
                }
                
            }
        });
        main_test_in_auto_bt.setBounds(31, 98, 185, 27);
        main_panel_1.add(main_test_in_auto_bt);
        
        main_test_in_manual_bt = new JButton("TEST_IN_MANUAL");
        
        main_test_in_manual_bt.setEnabled(false);
        main_test_in_manual_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    if (main_radio_st1.isSelected()) {
                        if ("".equals(main_operator_text_st1.getText())) {
                            JOptionPane.showMessageDialog(null, "STATION #1 OPERATOR 입력 하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else {
                        if ("".equals(main_operator_text_st2.getText())) {
                            JOptionPane.showMessageDialog(null, "STATION #2 OPERATOR 입력 하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    thread_stop = false;
                    
                    TestInManualMode TestInManualMode = new TestInManualMode(main_frm_d);
                    TestInManualMode.setVisible(true);
                    
                }
            }
        });
        main_test_in_manual_bt.setBounds(31, 134, 185, 27);
        main_panel_1.add(main_test_in_manual_bt);
        
        main_re_test_end_bt = new JButton("RE-TEST_END");
        main_re_test_end_bt.setEnabled(false);
        main_re_test_end_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    ReTestEndPop ReTestEndPop = new ReTestEndPop(main_frm_d);
                    ReTestEndPop.setVisible(true);
                }
            }
        });
        main_re_test_end_bt.setBounds(31, 170, 185, 27);
        main_panel_1.add(main_re_test_end_bt);
        
        main_final_test_end_bt = new JButton("FINAL_TEST_END");
        main_final_test_end_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    FinalTestEndPop FinalTestEndPop = new FinalTestEndPop(main_frm_d);
                    FinalTestEndPop.setVisible(true);
                }
            }
        });
        main_final_test_end_bt.setEnabled(false);
        main_final_test_end_bt.setBounds(31, 206, 185, 27);
        main_panel_1.add(main_final_test_end_bt);
        
        main_test_in_cancel_bt = new JButton("TEST_IN_CANCEL");
        main_test_in_cancel_bt.setEnabled(false);
        main_test_in_cancel_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    TestInCancelPop TestInCancelPop = new TestInCancelPop(main_frm_d);
                    TestInCancelPop.setVisible(true);
                }
            }
        });
        main_test_in_cancel_bt.setBounds(31, 242, 185, 27);
        main_panel_1.add(main_test_in_cancel_bt);
        
        main_pre_pgm_restore_bt = new JButton("PRE_PGM_RESTORE");
        main_pre_pgm_restore_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    PrePgmRestore PrePgmRestore = new PrePgmRestore(main_frm_d); 
                    PrePgmRestore.setVisible(true);
                }
            }
        });
        main_pre_pgm_restore_bt.setEnabled(false);
        main_pre_pgm_restore_bt.setBounds(31, 278, 185, 27);
        main_panel_1.add(main_pre_pgm_restore_bt);
        
        JPanel main_panel_2 = new JPanel();
        main_panel_2.setLayout(null);
        main_panel_2.setForeground(Color.WHITE);
        main_panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "HEAD A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_2.setBounds(243, 77, 412, 328);
        main_frm_d.getContentPane().add(main_panel_2);
        
        JLabel main_operator_lb_st1 = new JLabel("OPERATOR ID");
        main_operator_lb_st1.setBounds(14, 70, 125, 18);
        main_panel_2.add(main_operator_lb_st1);
        
        JLabel main_fab_lb_st1 = new JLabel("FAB");
        main_fab_lb_st1.setBounds(14, 198, 125, 18);
        main_panel_2.add(main_fab_lb_st1);
        
        JLabel main_partnumber_lb_st1 = new JLabel("PART NUMBER");
        main_partnumber_lb_st1.setBounds(14, 134, 125, 18);
        main_panel_2.add(main_partnumber_lb_st1);
        
        JLabel main_lotno_lb_st1 = new JLabel("LOT ID");
        main_lotno_lb_st1.setBounds(14, 102, 125, 18);
        main_panel_2.add(main_lotno_lb_st1);
        
        JLabel main_grade_lb_st1 = new JLabel("GRADE");
        main_grade_lb_st1.setBounds(14, 228, 125, 18);
        main_panel_2.add(main_grade_lb_st1);
        
        JLabel main_qty_lb_st1 = new JLabel("Q'TY");
        main_qty_lb_st1.setBounds(14, 292, 125, 18);
        main_panel_2.add(main_qty_lb_st1);
        
        JLabel main_temp_lb_st1 = new JLabel("TEMP");
        main_temp_lb_st1.setBounds(14, 260, 125, 18);
        main_panel_2.add(main_temp_lb_st1);
        
        main_operator_text_st1 = new JTextField();
        main_operator_text_st1.setForeground(Color.BLACK);
        main_operator_text_st1.setEditable(false);
        main_operator_text_st1.setColumns(10);
        main_operator_text_st1.setBounds(153, 70, 241, 24);
        main_panel_2.add(main_operator_text_st1);
        
        main_fab_text_st1 = new JTextField();
        main_fab_text_st1.setEditable(false);
        main_fab_text_st1.setColumns(10);
        main_fab_text_st1.setBounds(153, 198, 241, 24);
        main_panel_2.add(main_fab_text_st1);
        
        main_partnumber_text_st1 = new JTextField();
        main_partnumber_text_st1.setEditable(false);
        main_partnumber_text_st1.setColumns(10);
        main_partnumber_text_st1.setBounds(153, 134, 241, 24);
        main_panel_2.add(main_partnumber_text_st1);
        
        main_lotno_text_st1 = new JTextField();
        main_lotno_text_st1.setEditable(false);
        main_lotno_text_st1.setColumns(10);
        main_lotno_text_st1.setBounds(153, 102, 241, 24);
        main_panel_2.add(main_lotno_text_st1);
        
        main_grade_text_st1 = new JTextField();
        main_grade_text_st1.setEditable(false);
        main_grade_text_st1.setColumns(10);
        main_grade_text_st1.setBounds(153, 228, 241, 24);
        main_panel_2.add(main_grade_text_st1);
        
        main_qty_text_st1 = new JTextField();
        main_qty_text_st1.setEditable(false);
        main_qty_text_st1.setColumns(10);
        main_qty_text_st1.setBounds(153, 292, 241, 24);
        main_panel_2.add(main_qty_text_st1);
        
        main_temp_text_st1 = new JTextField();
        main_temp_text_st1.setEditable(false);
        main_temp_text_st1.setColumns(10);
        main_temp_text_st1.setBounds(153, 260, 241, 24);
        main_panel_2.add(main_temp_text_st1);
        
        main_processcode_text_st1 = new JTextField();
        main_processcode_text_st1.setEditable(false);
        main_processcode_text_st1.setColumns(10);
        main_processcode_text_st1.setBounds(153, 166, 241, 24);
        main_panel_2.add(main_processcode_text_st1);
        
        main_processcode_lb_st1 = new JLabel("PROCESS CODE");
        main_processcode_lb_st1.setBounds(14, 166, 125, 18);
        main_panel_2.add(main_processcode_lb_st1);
        
        JLabel main_testmode_lb_st1 = new JLabel("TEST MODE");
        main_testmode_lb_st1.setBounds(14, 34, 125, 18);
        main_panel_2.add(main_testmode_lb_st1);
        
        main_testmode_text_st1 = new JTextField();
        main_testmode_text_st1.setText(test_in_manual_mode_h1);
        main_testmode_text_st1.setForeground(Color.BLACK);
        main_testmode_text_st1.setEditable(false);
        main_testmode_text_st1.setColumns(10);
        main_testmode_text_st1.setBounds(153, 34, 241, 24);
        main_panel_2.add(main_testmode_text_st1);
        
        JPanel main_panel_3 = new JPanel();
        main_panel_3.setLayout(null);
        main_panel_3.setForeground(Color.WHITE);
        main_panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "HEAD B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_3.setBounds(660, 77, 408, 328);
        main_frm_d.getContentPane().add(main_panel_3);
        
        JLabel main_operator_lb_st2 = new JLabel("OPERATOR");
        main_operator_lb_st2.setBounds(14, 70, 125, 18);
        main_panel_3.add(main_operator_lb_st2);
        
        JLabel main_fab_lb_st2 = new JLabel("FAB");
        main_fab_lb_st2.setBounds(14, 198, 125, 18);
        main_panel_3.add(main_fab_lb_st2);
        
        JLabel main_partnumber_lb_st2 = new JLabel("PART NUMBER");
        main_partnumber_lb_st2.setBounds(14, 134, 125, 18);
        main_panel_3.add(main_partnumber_lb_st2);
        
        JLabel main_lotno_lb_st2 = new JLabel("LOT NO");
        main_lotno_lb_st2.setBounds(14, 102, 125, 18);
        main_panel_3.add(main_lotno_lb_st2);
        
        JLabel main_grade_lb_st2 = new JLabel("GRADE");
        main_grade_lb_st2.setBounds(14, 228, 125, 18);
        main_panel_3.add(main_grade_lb_st2);
        
        JLabel main_qty_lb_st2 = new JLabel("Q'TY");
        main_qty_lb_st2.setBounds(14, 292, 125, 18);
        main_panel_3.add(main_qty_lb_st2);
        
        JLabel main_temp_lb_st2 = new JLabel("TEMP");
        main_temp_lb_st2.setBounds(14, 260, 125, 18);
        main_panel_3.add(main_temp_lb_st2);
        
        main_operator_text_st2 = new JTextField();
        main_operator_text_st2.setForeground(Color.BLACK);
        main_operator_text_st2.setEditable(false);
        main_operator_text_st2.setColumns(10);
        main_operator_text_st2.setBounds(153, 70, 241, 24);
        main_panel_3.add(main_operator_text_st2);
        
        main_fab_text_st2 = new JTextField();
        main_fab_text_st2.setEditable(false);
        main_fab_text_st2.setColumns(10);
        main_fab_text_st2.setBounds(153, 198, 241, 24);
        main_panel_3.add(main_fab_text_st2);
        
        main_partnumber_text_st2 = new JTextField();
        main_partnumber_text_st2.setEditable(false);
        main_partnumber_text_st2.setColumns(10);
        main_partnumber_text_st2.setBounds(153, 134, 241, 24);
        main_panel_3.add(main_partnumber_text_st2);
        
        main_lotno_text_st2 = new JTextField();
        main_lotno_text_st2.setEditable(false);
        main_lotno_text_st2.setColumns(10);
        main_lotno_text_st2.setBounds(153, 102, 241, 24);
        main_panel_3.add(main_lotno_text_st2);
        
        main_grade_text_st2 = new JTextField();
        main_grade_text_st2.setEditable(false);
        main_grade_text_st2.setColumns(10);
        main_grade_text_st2.setBounds(153, 228, 241, 24);
        main_panel_3.add(main_grade_text_st2);
        
        main_qty_text_st2 = new JTextField();
        main_qty_text_st2.setEditable(false);
        main_qty_text_st2.setColumns(10);
        main_qty_text_st2.setBounds(153, 292, 241, 24);
        main_panel_3.add(main_qty_text_st2);
        
        main_temp_text_st2 = new JTextField();
        main_temp_text_st2.setEditable(false);
        main_temp_text_st2.setColumns(10);
        main_temp_text_st2.setBounds(153, 260, 241, 24);
        main_panel_3.add(main_temp_text_st2);
        
        main_processcode_lb_st2 = new JLabel("PROCESS CODE");
        main_processcode_lb_st2.setBounds(14, 166, 125, 18);
        main_panel_3.add(main_processcode_lb_st2);
        
        main_processcode_text_st2 = new JTextField();
        main_processcode_text_st2.setEditable(false);
        main_processcode_text_st2.setColumns(10);
        main_processcode_text_st2.setBounds(153, 166, 241, 24);
        main_panel_3.add(main_processcode_text_st2);
        
        JLabel main_testmode_lb_st2 = new JLabel("TEST MODE");
        main_testmode_lb_st2.setBounds(14, 34, 125, 18);
        main_panel_3.add(main_testmode_lb_st2);
        
        main_testmode_text_st2 = new JTextField();
        main_testmode_text_st2.setText(test_in_manual_mode_h2);
        main_testmode_text_st2.setForeground(Color.BLACK);
        main_testmode_text_st2.setEditable(false);
        main_testmode_text_st2.setColumns(10);
        main_testmode_text_st2.setBounds(153, 34, 241, 24);
        main_panel_3.add(main_testmode_text_st2);
        
        main_title_lb = new JLabel("HTM T5503 #1");
        main_title_lb.setFont(new Font("굴림", Font.BOLD, 25));
        main_title_lb.setBounds(449, 27, 178, 59);
        main_frm_d.getContentPane().add(main_title_lb);
        
        
        main_log_textPane = new JTextPane();
        main_log_textPane.setForeground(Color.CYAN);
        main_log_textPane.setEnabled(false);
        
        JScrollPane main_log_scrollPane = new JScrollPane();
        main_log_scrollPane.setViewportView(main_log_textPane);
        main_log_scrollPane.setBounds(15, 411, 1040, 218);
        main_frm_d.getContentPane().add(main_log_scrollPane);
        
        URL iconURL = getClass().getResource("/kr/co/famos/not/control/ftp/img/main.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        main_frm_d.setIconImage(icon.getImage());
     
    }
}
