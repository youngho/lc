package kr.co.famos.not.control.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import kr.co.famos.not.control.ftp.FtpConnect;
import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.socket.ServerHelper;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import kr.co.famos.not.control.util.WriteLogger;

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
    
    public static JFrame main_frm_d;
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
    public static JRadioButton main_radio_st2;
    
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
    
    // 시작 시간 
    public static String start_date;
    // 종료 시간
    public static String end_date;
    
    // SRQKIND
    public static String srqkind = "ON";
    
    // TESTER MODEL
    public static String tester_model = "T5503";
    
    // 소켓 통신 
    static String socket_ip = "121.185.32.49";
    static String socket_port = "7074";
    private JMenu mnTool;
    
    // 로딩 창 실행시 버튼 클릭 방지 플래그
    public static boolean loading_flg = true;
    
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
    private static JLabel lblNewLabel;
    private static JLabel main_model_text_name;
    private static JLabel lblNewLabel_2;
    private static JLabel main_version_text;
    
    // bin 입력창 데이터 변수 : main_bin 데이터 결과 값
    public static Map test_bin;
    
    public static int testBin1 = 0;
    public static int testBin2 = 0;
    public static int testBin3 = 0;
    public static int testBin4 = 0;
    public static int testBin5 = 0;
    public static int testBin6 = 0;
    public static int testBin7 = 0;
    public static int testBin8 = 0;
    public static int testBin9 = 0;
    
    // HANDLER BIN 입력창 데이터 변수
    public static Map handler_bin;
    
    public static int handlerBin1 = 0;
    public static int handlerBin2 = 0;
    public static int handlerBin3 = 0;
    public static int handlerBin4 = 0;
    public static int handlerBin5 = 0;
    public static int handlerBin6 = 0;
    public static int handlerBin7 = 0;
    public static int handlerBin8 = 0;
    public static int handlerBin9 = 0;
    
    public static int sblResultErrorCount_h1 = 0;
    public static int sblResultErrorCount_h2 = 0;
    
    public static int operatorBinInputOutCount_h1 = 0;
    public static int operatorBinInputOutCount_h2 = 0;
    
    // FinalTestEnd Seq
    public static boolean finalTestEnd = true;
    
    // SRQ ON_OFF 이미지
    static JLabel on_off_img;
    static ImageIcon srqImageIcon;
    
    
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
                
                // 프로퍼티 설정
                PathProperties pts = new PathProperties();
                pts.PropertiesSetting();
               
                // 로그 설정
                Logger logger = WriteLogger.getInstance("MainDual.java");
                try {
                    MainDual window = new MainDual();
                    window.main_frm_d.setVisible(true);
                    CommonUtil cu = new CommonUtil();
                    
                    /* 프레임 화면 가운데 */
                    Dimension frameSize = window.main_frm_d.getSize();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    window.main_frm_d.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                    
                    // 소켓 통신에서 받아온 값을 담는 변수
                    info_map_st1 = new HashMap();
                    info_map_st2 = new HashMap();
                    
                    sub_bin_map_st1 = new HashMap();
                    sub_bin_map_st2 = new HashMap();
                    
                    // FTP 연결 확인
                    FTPClient ftpClient = new FTPClient();
                    
                    // 접속 상태 오류
                    // ftpurl  : 121.185.32.49
                    // ftpport : 21
                    boolean connet = FtpConnect.ConnectSuccess(ftpClient, PathProperties.ftpurl, Integer.parseInt(PathProperties.ftpport));
                    
                    if (connet) {
                        ftpModule.FtpServerSend(0);
                        while (true) {
                            if (ftpModule.re_test_end_exit) {
                                break;
                            }
                        }
                    } else {
                        logger.error("FTP connection error !!!!");
                        JOptionPane.showMessageDialog(null, "FTP connection error !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // SRQKIND
                    MainDual.srqkind = cu.HederData(PathProperties.local_Header, "srqkind.dat").trim().equals("0") ? MainDual.srqkind : cu.HederData(PathProperties.local_Header, "srqkind.dat").trim();
                    
                    ImageIcon srqImageIcon;
                    if ("ON".equals(MainDual.srqkind)) {
                        // 아이콘 크기 수정
                        ImageIcon onImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/on.png"));
                        Image onImage = onImageIcon.getImage(); 
                        Image onImageNew = onImage.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH);  
                        srqImageIcon = new ImageIcon(onImageNew);
                    } else {
                        ImageIcon offImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/off.png"));
                        Image offImage = offImageIcon.getImage(); 
                        Image offImageNew = offImage.getScaledInstance(100, 30,  java.awt.Image.SCALE_SMOOTH);  
                        srqImageIcon = new ImageIcon(offImageNew);
                    }
                    
                    String head = cu.HederData(PathProperties.local_Header, "head.dat").trim().equals("0") ? "A" : cu.HederData(PathProperties.local_Header, "head.dat").trim();
                            
                    if ("A".equals(head)) {
                        main_radio_st1.setSelected(true);
                    } else {
                        main_radio_st2.setSelected(true);
                    }
                    
                    // 화면창 X(창 닫기) 버튼을 누르면  닫기 확인 창이 뜬다.
                    test_in_manual_mode_h1 = cu.HederData(PathProperties.local_Header, "test_mode_h1.dat").trim().equals("0") ? "PRODUCT MODE" : cu.HederData(PathProperties.local_Header, "test_mode_h1.dat").trim();
                    main_testmode_text_st1.setText(cu.HederData(PathProperties.local_Header, "test_mode_h1.dat").trim().equals("0") ? "PRODUCT MODE" : cu.HederData(PathProperties.local_Header, "test_mode_h1.dat").trim());
                    main_operator_text_st1.setText(cu.HederData(PathProperties.local_Header, "operator_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "operator_id_h1.dat").trim());
                    main_lotno_text_st1.setText(cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim());
                    main_partnumber_text_st1.setText(cu.HederData(PathProperties.local_Header, "part_number_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "part_number_h1.dat").trim());
                    main_processcode_text_st1.setText(cu.HederData(PathProperties.local_Header, "process_code_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "process_code_h1.dat").trim());
                    main_fab_text_st1.setText(cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim());
                    main_grade_text_st1.setText(cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim());
                    main_temp_text_st1.setText(cu.HederData(PathProperties.local_Header, "temperature_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "temperature_h1.dat").trim());
                    main_qty_text_st1.setText(cu.HederData(PathProperties.local_Header, "quantity_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "quantity_h1.dat").trim());

                    // 핸들러 2 상태
                    test_in_manual_mode_h2 = cu.HederData(PathProperties.local_Header, "test_mode_h2.dat").trim().equals("0") ? "PRODUCT MODE" : cu.HederData(PathProperties.local_Header, "test_mode_h2.dat").trim();
                    main_testmode_text_st2.setText(cu.HederData(PathProperties.local_Header, "test_mode_h2.dat").trim().equals("0") ? "PRODUCT MODE" : cu.HederData(PathProperties.local_Header, "test_mode_h2.dat").trim());
                    main_operator_text_st2.setText(cu.HederData(PathProperties.local_Header, "operator_id_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "operator_id_h2.dat").trim());
                    main_lotno_text_st2.setText(cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "lot_id_h2.dat").trim());
                    main_partnumber_text_st2.setText(cu.HederData(PathProperties.local_Header, "part_number_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "part_number_h2.dat").trim());
                    main_processcode_text_st2.setText(cu.HederData(PathProperties.local_Header, "process_code_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "process_code_h2.dat").trim());
                    main_fab_text_st2.setText(cu.HederData(PathProperties.local_Header, "fab_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "fab_h2.dat").trim());
                    main_grade_text_st2.setText(cu.HederData(PathProperties.local_Header, "grade_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "grade_h2.dat").trim());
                    main_temp_text_st2.setText(cu.HederData(PathProperties.local_Header, "temperature_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "temperature_h2.dat").trim());
                    main_qty_text_st2.setText(cu.HederData(PathProperties.local_Header, "quantity_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "quantity_h2.dat").trim());
                    
                    // 아이콘 크기 수정
                    ImageIcon imageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/main.png"));
                    Image image = imageIcon.getImage(); 
                    Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);  
                    imageIcon = new ImageIcon(newimg);
                    
                    lblNewLabel = new JLabel("");
                    lblNewLabel.setIcon(imageIcon);
                    lblNewLabel.setBounds(10, 49, 60, 61);
                    main_frm_d.getContentPane().add(lblNewLabel);
                    
                    main_model_text_name = new JLabel("T5503");
                    main_model_text_name.setFont(new Font("Tahoma", Font.BOLD, 15));
                    main_model_text_name.setForeground(Color.RED);
                    main_model_text_name.setBounds(81, 60, 56, 18);
                    main_frm_d.getContentPane().add(main_model_text_name);
                    
                    lblNewLabel_2 = new JLabel("LOT CONTROLLER");
                    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
                    lblNewLabel_2.setForeground(Color.BLACK);
                    lblNewLabel_2.setBounds(132, 60, 157, 18);
                    main_frm_d.getContentPane().add(lblNewLabel_2);
                    
                    main_version_text = new JLabel("version 1.0.0");
                    main_version_text.setForeground(Color.BLACK);
                    main_version_text.setFont(new Font("Tahoma", Font.BOLD, 15));
                    main_version_text.setBounds(80, 83, 209, 18);
                    main_frm_d.getContentPane().add(main_version_text);
                    
                    
                    on_off_img = new JLabel("");
                    on_off_img.setBounds(542, 80, 100, 30);
                    on_off_img.setIcon(srqImageIcon);
                    main_frm_d.getContentPane().add(on_off_img);
                    
                    // 아이콘 크기 수정
                    ImageIcon srqImageLbIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/srq.jpg"));
                    Image srqLbImage = srqImageLbIcon.getImage();
                    Image srqLbImageNew = srqLbImage.getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon srqLbImageIcon = new ImageIcon(srqLbImageNew);

                    JLabel srq_img_lb = new JLabel("New label");
                    srq_img_lb.setBounds(542, 42, 100, 37);
                    srq_img_lb.setIcon(srqLbImageIcon);
                    main_frm_d.getContentPane().add(srq_img_lb);
                    
                    
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
                    
                    // String tester_number = cu.executeRuntime("java");
                    
                    // cu.FileNew(PathProperties.local_Header, "tester_number.dat", "HTMAT1", false);
                    cu.FileNew(PathProperties.local_Header, "TESTID.dat"        , "HTMAT1"  , false);
                    cu.FileNew(PathProperties.local_Header, "tester_model.dat"  , "T5503"   , false);
                    cu.FileNew(PathProperties.local_Header, "handler_model.dat" , "TW350HT" , false);
                    
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
        
        // 창크기 불변
        main_frm_d.setResizable(false); 
        main_frm_d.getContentPane().setBackground(Color.WHITE);
        main_frm_d.setTitle("HTM T5503 LOT CONTROLLER");
        main_frm_d.setBounds(100, 100, 662, 720);
        main_frm_d.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main_frm_d.getContentPane().setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(20,84,148));
        menuBar.setBounds(0, 0, 650, 37);
        main_frm_d.getContentPane().add(menuBar);
        
        // 아이콘 크기 수정
        ImageIcon adminImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/admin.png"));
        Image adminImage = adminImageIcon.getImage(); 
        Image adminNewimg = adminImage.getScaledInstance(30, 25,  java.awt.Image.SCALE_SMOOTH);  
        adminImageIcon = new ImageIcon(adminNewimg);

        JMenu mnNewMenu = new JMenu("Admin");
        mnNewMenu.setForeground(Color.WHITE);
        mnNewMenu.setBackground(new Color(20,84,148));
        mnNewMenu.setIcon(adminImageIcon);
        mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Setting");
        mntmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 15));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SettingPassWord SettingPassWord = new SettingPassWord(main_frm_d);
                SettingPassWord.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);
        
        // 아이콘 크기 수정
        ImageIcon toolImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/tool.png"));
        Image toolImage = toolImageIcon.getImage(); 
        Image toolNewimg = toolImage.getScaledInstance(30, 25,  java.awt.Image.SCALE_SMOOTH);
        
        toolImageIcon = new ImageIcon(toolNewimg);
        mnTool = new JMenu("Tool");
        mnTool.setForeground(Color.WHITE);
        mnTool.setBackground(new Color(20,84,148));
        mnTool.setIcon(toolImageIcon);
        mnTool.setFont(new Font("Tahoma", Font.BOLD, 18));
        menuBar.add(mnTool);
        
        JMenuItem mntmSrq = new JMenuItem("SRQKIND");
        mntmSrq.setFont(new Font("Tahoma", Font.BOLD, 15));
        mntmSrq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SRQKINDPassWord SRQKINDPassWord = new SRQKINDPassWord(main_frm_d);
                SRQKINDPassWord.setVisible(true);
            }
        });
        mnTool.add(mntmSrq);
        
        JMenuItem mntmTesterModel = new JMenuItem("TESTER MODEL");
        mntmTesterModel.setFont(new Font("Tahoma", Font.BOLD, 15));
        mntmTesterModel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                TesterModelPassWord TesterModelPassWord = new TesterModelPassWord(main_frm_d);
                TesterModelPassWord.setVisible(true);
            }
        });
        mnTool.add(mntmTesterModel);
        
        JPanel main_panel_1 = new JPanel();
        main_panel_1.setBackground(Color.WHITE);
        main_panel_1.setLayout(null);
        main_panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_1.setBounds(0, 147, 233, 300);
        main_frm_d.getContentPane().add(main_panel_1);
        
        main_radio_st2 = new JRadioButton("HEAD B");
        main_radio_st2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CommonUtil.ButtonConditionB();
            }
        });
        
        main_operatorId_bt = new GradientButton("OPERATOR ID");
        main_operatorId_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_operatorId_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (loading_flg) {
                    OperatorIdPop OperatorId = new OperatorIdPop(main_frm_d);
                    OperatorId.setVisible(true);
                }
            }
        });
        main_operatorId_bt.setBounds(19, 6, 195, 35);
        main_panel_1.add(main_operatorId_bt);
        buttonGroup.add(main_radio_st2);
        main_radio_st2.setBounds(128, 11, 84, 27);
        main_panel_1.add(main_radio_st2);
        
        main_radio_st1 = new JRadioButton("HEAD A");
        main_radio_st1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CommonUtil.ButtonConditionA();
            }
        });
        buttonGroup.add(main_radio_st1);
        main_radio_st1.setBounds(19, 11, 98, 27);
        main_panel_1.add(main_radio_st1);
        
        JButton button = new JButton("MANUAL");
        button.setBounds(0, 0, 0, 0);
        main_panel_1.add(button);
        
        main_test_in_auto_bt = new GradientButton("TEST_IN_AUTO");
        main_test_in_auto_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_test_in_auto_bt.setEnabled(false);
        main_test_in_auto_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (loading_flg) {
                    thread_stop = false;
                    TestInAutoPop TestInAutoPop = new TestInAutoPop(main_frm_d);
                    TestInAutoPop.setVisible(true);
                }
            }
        });
        main_test_in_auto_bt.setBounds(19, 47, 195, 35);
        main_panel_1.add(main_test_in_auto_bt);
        
        main_test_in_manual_bt = new GradientButton("TEST_IN_MANUAL");
        main_test_in_manual_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        
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
        main_test_in_manual_bt.setBounds(19, 88, 195, 35);
        main_panel_1.add(main_test_in_manual_bt);
        
        main_re_test_end_bt = new GradientButton("RE-TEST_END");
        main_re_test_end_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_re_test_end_bt.setEnabled(false);
        main_re_test_end_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    ReTestEndPop ReTestEndPop = new ReTestEndPop(main_frm_d);
                    ReTestEndPop.setVisible(true);
                }
            }
        });
        main_re_test_end_bt.setBounds(19, 129, 195, 35);
        main_panel_1.add(main_re_test_end_bt);
        
        main_final_test_end_bt = new GradientButton("FINAL_TEST_END");
        main_final_test_end_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_final_test_end_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    FinalTestEndPop FinalTestEndPop = new FinalTestEndPop(main_frm_d);
                    FinalTestEndPop.setVisible(true);
                }
            }
        });
        main_final_test_end_bt.setEnabled(false);
        main_final_test_end_bt.setBounds(19, 170, 195, 35);
        main_panel_1.add(main_final_test_end_bt);
        
        main_test_in_cancel_bt = new GradientButton("TEST_IN_CANCEL");
        main_test_in_cancel_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_test_in_cancel_bt.setEnabled(false);
        main_test_in_cancel_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    TestInCancelPop TestInCancelPop = new TestInCancelPop(main_frm_d);
                    TestInCancelPop.setVisible(true);
                }
            }
        });
        main_test_in_cancel_bt.setBounds(19, 211, 195, 35);
        main_panel_1.add(main_test_in_cancel_bt);
        
        main_pre_pgm_restore_bt = new GradientButton("PRE_PGM_RESTORE");
        main_pre_pgm_restore_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_pre_pgm_restore_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loading_flg) {
                    PrePgmRestore PrePgmRestore = new PrePgmRestore(main_frm_d); 
                    PrePgmRestore.setVisible(true);
                }
            }
        });
        main_pre_pgm_restore_bt.setEnabled(false);
        main_pre_pgm_restore_bt.setBounds(19, 252, 195, 35);
        main_panel_1.add(main_pre_pgm_restore_bt);
        
        JPanel main_panel_2 = new JPanel();
        main_panel_2.setBackground(Color.WHITE);
        main_panel_2.setLayout(null);
        main_panel_2.setForeground(Color.WHITE);
        main_panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_2.setBounds(238, 147, 412, 300);
        main_frm_d.getContentPane().add(main_panel_2);
        
        JLabel main_operator_lb_st1 = new JLabel("OPERATOR ID");
        main_operator_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_operator_lb_st1.setBounds(14, 42, 125, 24);
        main_panel_2.add(main_operator_lb_st1);
        
        JLabel main_fab_lb_st1 = new JLabel("FAB");
        main_fab_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_fab_lb_st1.setBounds(14, 168, 125, 24);
        main_panel_2.add(main_fab_lb_st1);
        
        JLabel main_partnumber_lb_st1 = new JLabel("PART NUMBER");
        main_partnumber_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_partnumber_lb_st1.setBounds(14, 108, 125, 24);
        main_panel_2.add(main_partnumber_lb_st1);
        
        JLabel main_lotno_lb_st1 = new JLabel("LOT ID");
        main_lotno_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_lotno_lb_st1.setBounds(14, 75, 125, 24);
        main_panel_2.add(main_lotno_lb_st1);
        
        JLabel main_grade_lb_st1 = new JLabel("GRADE");
        main_grade_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_grade_lb_st1.setBounds(14, 201, 125, 24);
        main_panel_2.add(main_grade_lb_st1);
        
        JLabel main_qty_lb_st1 = new JLabel("Q'TY");
        main_qty_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_qty_lb_st1.setBounds(14, 267, 125, 24);
        main_panel_2.add(main_qty_lb_st1);
        
        JLabel main_temp_lb_st1 = new JLabel("TEMP");
        main_temp_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_temp_lb_st1.setBounds(14, 234, 125, 24);
        main_panel_2.add(main_temp_lb_st1);
        
        main_operator_text_st1 = new JTextField();
        main_operator_text_st1.setBackground(Color.WHITE);
        main_operator_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_operator_text_st1.setForeground(Color.BLACK);
        main_operator_text_st1.setEditable(false);
        main_operator_text_st1.setColumns(10);
        main_operator_text_st1.setBounds(153, 40, 241, 24);
        main_panel_2.add(main_operator_text_st1);
        
        main_fab_text_st1 = new JTextField();
        main_fab_text_st1.setBackground(Color.WHITE);
        main_fab_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_fab_text_st1.setEditable(false);
        main_fab_text_st1.setColumns(10);
        main_fab_text_st1.setBounds(153, 168, 241, 24);
        main_panel_2.add(main_fab_text_st1);
        
        main_partnumber_text_st1 = new JTextField();
        main_partnumber_text_st1.setBackground(Color.WHITE);
        main_partnumber_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_partnumber_text_st1.setEditable(false);
        main_partnumber_text_st1.setColumns(10);
        main_partnumber_text_st1.setBounds(153, 104, 241, 24);
        main_panel_2.add(main_partnumber_text_st1);
        
        main_lotno_text_st1 = new JTextField();
        main_lotno_text_st1.setBackground(Color.WHITE);
        main_lotno_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_lotno_text_st1.setEditable(false);
        main_lotno_text_st1.setColumns(10);
        main_lotno_text_st1.setBounds(153, 72, 241, 24);
        main_panel_2.add(main_lotno_text_st1);
        
        main_grade_text_st1 = new JTextField();
        main_grade_text_st1.setBackground(Color.WHITE);
        main_grade_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_grade_text_st1.setEditable(false);
        main_grade_text_st1.setColumns(10);
        main_grade_text_st1.setBounds(153, 200, 241, 24);
        main_panel_2.add(main_grade_text_st1);
        
        main_qty_text_st1 = new JTextField();
        main_qty_text_st1.setBackground(Color.WHITE);
        main_qty_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_qty_text_st1.setEditable(false);
        main_qty_text_st1.setColumns(10);
        main_qty_text_st1.setBounds(153, 264, 241, 24);
        main_panel_2.add(main_qty_text_st1);
        
        main_temp_text_st1 = new JTextField();
        main_temp_text_st1.setBackground(Color.WHITE);
        main_temp_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_temp_text_st1.setEditable(false);
        main_temp_text_st1.setColumns(10);
        main_temp_text_st1.setBounds(153, 232, 241, 24);
        main_panel_2.add(main_temp_text_st1);
        
        main_processcode_text_st1 = new JTextField();
        main_processcode_text_st1.setBackground(Color.WHITE);
        main_processcode_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_processcode_text_st1.setEditable(false);
        main_processcode_text_st1.setColumns(10);
        main_processcode_text_st1.setBounds(153, 136, 241, 24);
        main_panel_2.add(main_processcode_text_st1);
        
        main_processcode_lb_st1 = new JLabel("PROCESS CODE");
        main_processcode_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_processcode_lb_st1.setBounds(14, 141, 125, 18);
        main_panel_2.add(main_processcode_lb_st1);
        
        JLabel main_testmode_lb_st1 = new JLabel("TEST MODE");
        main_testmode_lb_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_testmode_lb_st1.setBounds(14, 9, 125, 24);
        main_panel_2.add(main_testmode_lb_st1);
        
        main_testmode_text_st1 = new JTextField();
        main_testmode_text_st1.setBackground(Color.WHITE);
        main_testmode_text_st1.setFont(new Font("Tahoma", Font.BOLD, 15));
        main_testmode_text_st1.setText(test_in_manual_mode_h1);
        main_testmode_text_st1.setForeground(Color.BLACK);
        main_testmode_text_st1.setEditable(false);
        main_testmode_text_st1.setColumns(10);
        main_testmode_text_st1.setBounds(153, 8, 241, 24);
        main_panel_2.add(main_testmode_text_st1);
        
        JPanel main_panel_3 = new JPanel();
        main_panel_3.setLayout(null);
        main_panel_3.setForeground(Color.WHITE);
        main_panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "HEAD B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        main_panel_3.setBounds(655, 197, 408, 328);
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
        main_title_lb.setFont(new Font("Tahoma", Font.BOLD, 30));
        main_title_lb.setBounds(204, 95, 240, 53);
        main_frm_d.getContentPane().add(main_title_lb);
        
        
        main_log_textPane = new JTextPane();
        main_log_textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
        main_log_textPane.setForeground(Color.WHITE);
        main_log_textPane.setEnabled(false);
        
        JScrollPane main_log_scrollPane = new JScrollPane();
        main_log_scrollPane.setViewportView(main_log_textPane);
        main_log_scrollPane.setBounds(4, 459, 646, 218);
        main_frm_d.getContentPane().add(main_log_scrollPane);
        
        URL iconURL = getClass().getResource("/kr/co/famos/not/control/ftp/img/main.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        main_frm_d.setIconImage(icon.getImage());
     
    }
}
