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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JSeparator;

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

public class BinSummaryPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private BinSummaryProgress task;
    
    static JTextField test_bin_total_text;
    static JTextField test_bin_good_text;
    static JTextField test_bin_fail_text;
    static JTextField test_bin_bin1_text;
    static JTextField test_bin_bin2_text;
    static JTextField test_bin_bin3_text;
    static JTextField test_bin_bin4_text;
    static JTextField test_bin_bin5_text;
    static JTextField test_bin_bin6_text;
    static JTextField test_bin_bin7_text;
    static JTextField test_bin_bin8_text;
    static JTextField test_bin_bin9_text;
    static JTextField handler_bin_total_text;
    static JTextField handler_bin_good_text;
    static JTextField handler_bin_fail_text;
    public static JTextField handler_bin_bin1_text;
    static JTextField handler_bin_bin2_text;
    static JTextField handler_bin_bin3_text;
    static JTextField handler_bin_bin4_text;
    static JTextField handler_bin_bin5_text;
    static JTextField handler_bin_bin6_text;
    static JTextField handler_bin_bin7_text;
    static JTextField handler_bin_bin8_text;
    static JTextField handler_bin_bin9_text;
    static JTextField operator_bin_total_text;
    static JTextField operator_bin_good_text;
    static JTextField operator_bin_fail_text;
    static JTextField operator_bin_bin1_text;
    static JTextField operator_bin_bin2_text;
    static JTextField operator_bin_bin3_text;
    static JTextField operator_bin_bin4_text;
    static JTextField operator_bin_bin5_text;
    static JTextField operator_bin_bin6_text;
    static JTextField operator_bin_bin7_text;
    static JTextField operator_bin_bin8_text;
    static JTextField operator_bin_bin9_text;
    
    // PASS_BIN_SELECTION 변수 
    CommonUtil cu = new CommonUtil();
    
    String sblBin9Counter;
    String passBinSelection_h1;
    String passBinSelection_h2;
    
    JButton bin_report_handler_test_out_bt;
    
    int ok_count_h1 = 0;
    int ok_count_h2 = 0;
    private JSeparator separator;
    private JLabel bin_error_lb;
    /**
     * Create the dialog.
     */
    public BinSummaryPop(final Frame parent) {
        
        super(parent, true);
        
        if (MainDual.main_radio_st1.isSelected()) {
            passBinSelection_h1 = cu.FileReaderData(PathProperties.ftplocal, "pass_bin_selection_h1.dat", true).trim();
            sblBin9Counter      = cu.FileReaderData(PathProperties.ftplocal, "sbl_bin9_counter_h1.dat", true).trim();
        } else {
            passBinSelection_h2 = cu.FileReaderData(PathProperties.ftplocal, "pass_bin_selection_h2.dat", true).trim();
            sblBin9Counter      = cu.FileReaderData(PathProperties.ftplocal, "sbl_bin9_counter_h2.dat", true).trim();
        }
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setTitle("BIN REPORT");
        
        MainDual.finalTestEnd = false;
        
        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setBounds((screenSize.width - (frameSize.width / 2)) / 3, (screenSize.height - (frameSize.height / 2)) / 2, 837, 349);
        
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel test_in_manua_panel = new JPanel();
            test_in_manua_panel.setLayout(null);
            test_in_manua_panel.setBackground(new Color(20, 86, 148));
            test_in_manua_panel.setBounds(7, 12, 802, 89);
            test_in_auto__parent_panel.add(test_in_manua_panel);
            {
                JLabel test_in_manua_title_lb = new JLabel("HEAD A");
                test_in_manua_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_manua_title_lb.setBounds(355, 52, 76, 18);
                test_in_manua_panel.add(test_in_manua_title_lb);
            }
            {
                JLabel test_in_manual_header_lb = new JLabel("BIN REPORT");
                test_in_manual_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_manual_header_lb.setBounds(339, 17, 108, 18);
                test_in_manua_panel.add(test_in_manual_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 258, 802, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton bin_report_handler_ok_bt = new GradientButton("OK");
                bin_report_handler_ok_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
                bin_report_handler_ok_bt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        if ("".equals(operator_bin_bin1_text.getText())) {
                            operator_bin_bin1_text.requestFocus();
                            bin_error_lb.setText("! Bin1 Please enter.");
                            return;  
                        } else if ("".equals(operator_bin_bin2_text.getText())) {
                            operator_bin_bin2_text.requestFocus();
                            bin_error_lb.setText("! Bin2 Please enter.");
                            return;
                        } else if ("".equals(operator_bin_bin3_text.getText())) {
                            bin_error_lb.setText("! Bin3 Please enter.");
                            operator_bin_bin3_text.requestFocus();
                            return;
                        } else if ("".equals(operator_bin_bin4_text.getText())) {
                            bin_error_lb.setText("! Bin4 Please enter.");
                            operator_bin_bin4_text.requestFocus();
                            return;
                        } else if ("".equals(operator_bin_bin5_text.getText())) {
                            bin_error_lb.setText("! Bin5 Please enter.");
                            operator_bin_bin5_text.requestFocus();
                            return;
                        } else if ("".equals(operator_bin_bin6_text.getText())) {
                            bin_error_lb.setText("! Bin6 Please enter.");
                            operator_bin_bin6_text.requestFocus();
                            return;
                        } else if ("".equals(operator_bin_bin7_text.getText())) {
                            bin_error_lb.setText("! Bin7 Please enter.");
                            operator_bin_bin7_text.requestFocus();
                            return;
                        } else if ("".equals(operator_bin_bin8_text.getText())) {
                            bin_error_lb.setText("! Bin8 Please enter.");
                            operator_bin_bin8_text.requestFocus();
                            return;
                        }
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            MainDual.sblResultErrorCount_h1 = 0;
                        } else {
                            MainDual.sblResultErrorCount_h2 = 0;
                        }

                        int operatorBinTotal = Integer.parseInt(operator_bin_bin1_text.getText()) 
                                            + Integer.parseInt(operator_bin_bin2_text.getText()) 
                                            + Integer.parseInt(operator_bin_bin3_text.getText())
                                            + Integer.parseInt(operator_bin_bin4_text.getText()) 
                                            + Integer.parseInt(operator_bin_bin5_text.getText()) 
                                            + Integer.parseInt(operator_bin_bin6_text.getText())
                                            + Integer.parseInt(operator_bin_bin7_text.getText()) 
                                            + Integer.parseInt(operator_bin_bin8_text.getText());
                        
                        int operatorBinGood = 0;
                        int operatorBinFail = 0;
                        
                        System.out.println("passBinSelection_h1 ================> " + passBinSelection_h1);
                        System.out.println("passBinSelection_h2 ================> " + passBinSelection_h2);
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            /*
                             * ********************* operator Good/Fail_S 계산  ***************************************/
                            if (String.valueOf(passBinSelection_h1.charAt(0)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin1_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin1_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(1)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin2_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin2_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(2)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin3_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin3_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(3)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin4_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin4_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(4)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin5_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin5_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(5)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin6_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin6_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(6)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin7_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin7_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h1.charAt(7)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin8_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin8_text.getText());
                            }
                            /*
                             * ********************* operator Good/Fail_N 계산  ***************************************/
                        } else {
                            /*
                             * ********************* operator Good/Fail_S 계산  ***************************************/
                            if (String.valueOf(passBinSelection_h2.charAt(0)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin1_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin1_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(1)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin2_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin2_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(2)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin3_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin3_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(3)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin4_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin4_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(4)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin5_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin5_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(5)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin6_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin6_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(6)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin7_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin7_text.getText());
                            }

                            if (String.valueOf(passBinSelection_h2.charAt(7)).equals("Y")) {
                                operatorBinGood += Integer.parseInt(operator_bin_bin8_text.getText());
                            } else {
                                operatorBinFail += Integer.parseInt(operator_bin_bin8_text.getText());
                            }
                            /*
                             * ********************* operator Good/Fail_N 계산  ***************************************/
                        }
                        
                        System.out.println("operatorBinGood ============> " + operatorBinGood);
                        System.out.println("operatorBinFail ============> " + operatorBinFail);
                        
                        operator_bin_good_text.setText(String.valueOf(operatorBinGood));
                        operator_bin_fail_text.setText(String.valueOf(operatorBinFail));
                        
                        int operatorBinBin9 = operatorBinTotal - Integer.parseInt(handler_bin_total_text.getText());
                        System.out.println("operatorBinBin9 ====> " + operatorBinBin9);
                        
                        operator_bin_bin9_text.setText(String.valueOf(Math.abs(operatorBinBin9)));
                        
                        int operatorBinTotalText = operatorBinTotal + Math.abs(operatorBinBin9); 
                        
                        System.out.println("operatorBinTotalText ====> " + operatorBinTotalText);
                        
                        operator_bin_total_text.setText(String.valueOf(operatorBinTotalText));
                        
                        int sblYieldResult = Integer.parseInt(operator_bin_total_text.getText()) / operatorBinGood; 
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h1.dat", String.valueOf(sblYieldResult), false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h2.dat", String.valueOf(sblYieldResult), false);
                        }
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.SblBinResult(0, handler_bin_bin1_text.getText(), operator_bin_bin1_text.getText(), "sbl_bin1_result_h1.dat", cu);
                            cu.SblBinResult(1, handler_bin_bin2_text.getText(), operator_bin_bin2_text.getText(), "sbl_bin2_result_h1.dat", cu);
                            cu.SblBinResult(2, handler_bin_bin3_text.getText(), operator_bin_bin3_text.getText(), "sbl_bin3_result_h1.dat", cu);
                            cu.SblBinResult(3, handler_bin_bin4_text.getText(), operator_bin_bin4_text.getText(), "sbl_bin4_result_h1.dat", cu);
                            cu.SblBinResult(4, handler_bin_bin5_text.getText(), operator_bin_bin5_text.getText(), "sbl_bin5_result_h1.dat", cu);
                            cu.SblBinResult(5, handler_bin_bin6_text.getText(), operator_bin_bin6_text.getText(), "sbl_bin6_result_h1.dat", cu);
                            cu.SblBinResult(6, handler_bin_bin7_text.getText(), operator_bin_bin7_text.getText(), "sbl_bin7_result_h1.dat", cu);
                        } else {
                            cu.SblBinResult(0, handler_bin_bin1_text.getText(), operator_bin_bin1_text.getText(), "sbl_bin1_result_h2.dat", cu);
                            cu.SblBinResult(1, handler_bin_bin2_text.getText(), operator_bin_bin2_text.getText(), "sbl_bin2_result_h2.dat", cu);
                            cu.SblBinResult(2, handler_bin_bin3_text.getText(), operator_bin_bin3_text.getText(), "sbl_bin3_result_h2.dat", cu);
                            cu.SblBinResult(3, handler_bin_bin4_text.getText(), operator_bin_bin4_text.getText(), "sbl_bin4_result_h2.dat", cu);
                            cu.SblBinResult(4, handler_bin_bin5_text.getText(), operator_bin_bin5_text.getText(), "sbl_bin5_result_h2.dat", cu);
                            cu.SblBinResult(5, handler_bin_bin6_text.getText(), operator_bin_bin6_text.getText(), "sbl_bin6_result_h2.dat", cu);
                            cu.SblBinResult(6, handler_bin_bin7_text.getText(), operator_bin_bin7_text.getText(), "sbl_bin7_result_h2.dat", cu);
                        }
                        
                        // SBL_BIN8_LIMIT < OP_BIN8 수랑 나누기 OPBIN TOTAl 수량 = 0.0%
                        // BETS(PROGRAM REGISTER)로 부터 받은 BIN8 관리 PERCENT(%)
                        
                        String sblBin8Limit = "";
                        int operatorBin8Limit = 0;
                        
                        if (0 < Integer.parseInt(operator_bin_bin8_text.getText())) {
                            operatorBin8Limit =  Integer.parseInt(operator_bin_total_text.getText()) / Integer.parseInt(operator_bin_bin8_text.getText());
                        }
                        
                        System.out.println("operatorBin8Limit ==> " + operatorBin8Limit);
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            sblBin8Limit = cu.FileReaderData(PathProperties.ftplocal, "sbl_bin8_limit_h1.dat", true).trim();
                            
                            System.out.println("sblBin8Limit ==========> " + sblBin8Limit);
                            System.out.println("sblBin8Limit ===> " + Float.parseFloat(sblBin8Limit));
                            
                            if (Float.parseFloat(sblBin8Limit) <  operatorBin8Limit) {
                                System.out.println("@@@@@@@@@@@@@@@@@@sblBin8Limit) <  operatorBin8Limit@@@@@@@@@@@@@@@@@@@@");
                                cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h1.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h1++;
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h1.dat", "NULL", false);
                            }
                        } else {
                            sblBin8Limit = cu.FileReaderData(PathProperties.ftplocal, "sbl_bin8_limit_h2.dat", true).trim();
                            
                            if (Integer.parseInt(sblBin8Limit) < operatorBin8Limit) {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h2.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h2++;
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h2.dat", "NULL", false);
                            }
                        }
                        
                        if (Integer.parseInt(sblBin9Counter) <   Math.abs(operatorBinBin9)) {
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h1++;
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h2++;
                            }
                        } else {
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", "NULL", false);
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", "NULL", false);
                            }
                        }
                        
                        // OPERATOR 입력 BIN9 갯수 LIMIT 설정 값. 
                        if (Integer.parseInt(sblBin9Counter) <   Math.abs(operatorBinBin9)) {
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h1++;
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", "ERROR", false);
                                MainDual.sblResultErrorCount_h2++;
                            }
                        } else {
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", "NULL", false);
                            } else {
                                cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", "NULL", false);
                            }
                        }
                        
                        if (MainDual.sblResultErrorCount_h1 > 0) {
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h1.dat", "ERROR", false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h1.dat", "PASS", false);
                        }

                        if (MainDual.sblResultErrorCount_h2 > 0) {
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h2.dat", "ERROR", false);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "sbl_result_h2.dat", "PASS", false);
                        }
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            System.out.println("MainDual.sblResultErrorCount_h1 =============> " + MainDual.sblResultErrorCount_h1);
                            if (MainDual.sblResultErrorCount_h1 > 0) {
                                System.out.println("@@@@@@@@@@@@@ MainDual.sblResultErrorCount_h1 @@@@@@@@@@@@@@");
                                ok_count_h1++;
                                bin_error_lb.setText("! BIN COUNTER " + ok_count_h1 +" ERROR.");
                            }
                        } else {
                            if (MainDual.sblResultErrorCount_h2 > 0) {
                                ok_count_h2++;
                                bin_error_lb.setText("! BIN COUNTER " + ok_count_h2 + " ERROR.");
                            }
                        }
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            if (0 == MainDual.sblResultErrorCount_h1 || 3 <= ok_count_h1) {
                                bin_report_handler_test_out_bt.setEnabled(true);
                            }
                        } else {
                            if (0 == MainDual.sblResultErrorCount_h2 || 3 <= ok_count_h2) {
                                bin_report_handler_test_out_bt.setEnabled(true);
                            }
                        }
                    }
                });
                bin_report_handler_ok_bt.setBounds(529, 4, 68, 27);
                bin_report_handler_ok_bt.setActionCommand("OK");
                buttonPane.add(bin_report_handler_ok_bt);
                getRootPane().setDefaultButton(bin_report_handler_ok_bt);
            }
            
            bin_report_handler_test_out_bt = new GradientButton("TEST_OUT");
            bin_report_handler_test_out_bt.setFont(new Font("Tahoma", Font.BOLD, 15));
            bin_report_handler_test_out_bt.setEnabled(false);
            bin_report_handler_test_out_bt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    CommonUtil cu = new CommonUtil();
                    dispose();
                    task = new BinSummaryProgress();
                    task.go();
                }
            });
            bin_report_handler_test_out_bt.setActionCommand("OK");
            bin_report_handler_test_out_bt.setBounds(204, 4, 121, 27);
            buttonPane.add(bin_report_handler_test_out_bt);
        }
        
        JLabel bin_report_handler_bin_bin_lb = new JLabel("HANDLER BIN");
        bin_report_handler_bin_bin_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_handler_bin_bin_lb.setBounds(7, 167, 115, 18);
        test_in_auto__parent_panel.add(bin_report_handler_bin_bin_lb);
        
        JLabel bin_report_menu_lb = new JLabel("MENU");
        bin_report_menu_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_menu_lb.setBounds(7, 113, 115, 18);
        test_in_auto__parent_panel.add(bin_report_menu_lb);
        
        JLabel bin_report_total_lb = new JLabel("TOTAL");
        bin_report_total_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_total_lb.setHorizontalAlignment(SwingConstants.CENTER);
        bin_report_total_lb.setBounds(144, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_total_lb);
        
        JLabel bin_report_good_lb = new JLabel("GOOD");
        bin_report_good_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_good_lb.setHorizontalAlignment(SwingConstants.CENTER);
        bin_report_good_lb.setBounds(200, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_good_lb);
        
        JLabel bin_report_fail_lb = new JLabel("FAIL",SwingConstants.CENTER);
        bin_report_fail_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_fail_lb.setBounds(256, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_fail_lb);
        
        JLabel bin_report_bin1_lb = new JLabel("BIN1",SwingConstants.CENTER);
        bin_report_bin1_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin1_lb.setBounds(312, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin1_lb);
        
        JLabel bin_report_bin2_lb = new JLabel("BIN2",SwingConstants.CENTER);
        bin_report_bin2_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin2_lb.setBounds(368, 113, 45, 18);
        test_in_auto__parent_panel.add(bin_report_bin2_lb);
        
        JLabel bin_report_bin3_lb = new JLabel("BIN3",SwingConstants.CENTER);
        bin_report_bin3_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin3_lb.setBounds(427, 113, 46, 18);
        test_in_auto__parent_panel.add(bin_report_bin3_lb);
        
        JLabel bin_report_bin4_lb = new JLabel("BIN4",SwingConstants.CENTER);
        bin_report_bin4_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin4_lb.setBounds(480, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin4_lb);
        
        JLabel bin_report_bin5_lb = new JLabel("BIN5",SwingConstants.CENTER);
        bin_report_bin5_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin5_lb.setBounds(536, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin5_lb);
        
        JLabel bin_report_bin6_lb = new JLabel("BIN6",SwingConstants.CENTER);
        bin_report_bin6_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin6_lb.setBounds(592, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin6_lb);
        
        JLabel bin_report_bin7_lb = new JLabel("BIN7",SwingConstants.CENTER);
        bin_report_bin7_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin7_lb.setBounds(648, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin7_lb);
        
        JLabel bin_report_bin8_lb = new JLabel("BIN8",SwingConstants.CENTER);
        bin_report_bin8_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin8_lb.setBounds(704, 113, 50, 18);
        test_in_auto__parent_panel.add(bin_report_bin8_lb);
        
        JLabel bin_report_bin9_lb = new JLabel("BIN9",SwingConstants.CENTER);
        bin_report_bin9_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_bin9_lb.setBounds(760, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin9_lb);
        
        test_bin_total_text = new JTextField();
        test_bin_total_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_total_text.setEditable(false);
        test_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_total_text.setColumns(10);
        test_bin_total_text.setBounds(144, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_total_text);
        
        test_bin_good_text = new JTextField();
        test_bin_good_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_good_text.setEditable(false);
        test_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_good_text.setColumns(10);
        test_bin_good_text.setBounds(200, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_good_text);
        
        test_bin_fail_text = new JTextField();
        test_bin_fail_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_fail_text.setEditable(false);
        test_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_fail_text.setColumns(10);
        test_bin_fail_text.setBounds(256, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_fail_text);
        
        test_bin_bin1_text = new JTextField();
        test_bin_bin1_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin1_text.setText(String.valueOf(MainDual.testBin1));
        test_bin_bin1_text.setEditable(false);
        test_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin1_text.setColumns(10);
        test_bin_bin1_text.setBounds(312, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin1_text);
        
        test_bin_bin2_text = new JTextField(String.valueOf(MainDual.testBin2));
        test_bin_bin2_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin2_text.setEditable(false);
        test_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin2_text.setColumns(10);
        test_bin_bin2_text.setBounds(368, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin2_text);
        
        test_bin_bin3_text = new JTextField(String.valueOf(MainDual.testBin3));
        test_bin_bin3_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin3_text.setEditable(false);
        test_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin3_text.setColumns(10);
        test_bin_bin3_text.setBounds(424, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin3_text);
        
        test_bin_bin4_text = new JTextField(String.valueOf(MainDual.testBin4));
        test_bin_bin4_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin4_text.setEditable(false);
        test_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin4_text.setColumns(10);
        test_bin_bin4_text.setBounds(480, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin4_text);
        
        test_bin_bin5_text = new JTextField(String.valueOf(MainDual.testBin5));
        test_bin_bin5_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin5_text.setEditable(false);
        test_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin5_text.setColumns(10);
        test_bin_bin5_text.setBounds(536, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin5_text);
        
        test_bin_bin6_text = new JTextField(String.valueOf(MainDual.testBin6));
        test_bin_bin6_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin6_text.setEditable(false);
        test_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin6_text.setColumns(10);
        test_bin_bin6_text.setBounds(592, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin6_text);
        
        test_bin_bin7_text = new JTextField(String.valueOf(MainDual.testBin7));
        test_bin_bin7_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin7_text.setEditable(false);
        test_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin7_text.setColumns(10);
        test_bin_bin7_text.setBounds(648, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin7_text);
        
        test_bin_bin8_text = new JTextField(String.valueOf(MainDual.testBin8));
        test_bin_bin8_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin8_text.setEditable(false);
        test_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin8_text.setColumns(10);
        test_bin_bin8_text.setBounds(704, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin8_text);
        
        test_bin_bin9_text = new JTextField(String.valueOf(MainDual.testBin9));
        test_bin_bin9_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        test_bin_bin9_text.setEditable(false);
        test_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin9_text.setColumns(10);
        test_bin_bin9_text.setBounds(760, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin9_text);
        
        JLabel bin_report_test_bin_lb = new JLabel("TEST BIN");
        bin_report_test_bin_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_test_bin_lb.setBounds(7, 137, 115, 18);
        test_in_auto__parent_panel.add(bin_report_test_bin_lb);
        
        handler_bin_total_text = new JTextField();
        handler_bin_total_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_total_text.setEditable(false);
        handler_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_total_text.setColumns(10);
        handler_bin_total_text.setBounds(144, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_total_text);
        
        handler_bin_good_text = new JTextField();
        handler_bin_good_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_good_text.setEditable(false);
        handler_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_good_text.setColumns(10);
        handler_bin_good_text.setBounds(200, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_good_text);
        
        handler_bin_fail_text = new JTextField();
        handler_bin_fail_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_fail_text.setEditable(false);
        handler_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_fail_text.setColumns(10);
        handler_bin_fail_text.setBounds(256, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_fail_text);
        
        handler_bin_bin1_text = new JTextField(String.valueOf(MainDual.handlerBin1));
        handler_bin_bin1_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin1_text.setEditable(false);
        handler_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin1_text.setColumns(10);
        handler_bin_bin1_text.setBounds(312, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin1_text);
        
        handler_bin_bin2_text = new JTextField(String.valueOf(MainDual.handlerBin2));
        handler_bin_bin2_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin2_text.setEditable(false);
        handler_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin2_text.setColumns(10);
        handler_bin_bin2_text.setBounds(368, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin2_text);
        
        handler_bin_bin3_text = new JTextField(String.valueOf(MainDual.handlerBin3));
        handler_bin_bin3_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin3_text.setEditable(false);
        handler_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin3_text.setColumns(10);
        handler_bin_bin3_text.setBounds(424, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin3_text);
        
        handler_bin_bin4_text = new JTextField(String.valueOf(MainDual.handlerBin4));
        handler_bin_bin4_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin4_text.setEditable(false);
        handler_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin4_text.setColumns(10);
        handler_bin_bin4_text.setBounds(480, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin4_text);
        
        handler_bin_bin5_text = new JTextField(String.valueOf(MainDual.handlerBin5));
        handler_bin_bin5_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin5_text.setForeground(Color.RED);
        handler_bin_bin5_text.setEditable(false);
        handler_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin5_text.setColumns(10);
        handler_bin_bin5_text.setBounds(536, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin5_text);
        
        handler_bin_bin6_text = new JTextField(String.valueOf(MainDual.handlerBin6));
        handler_bin_bin6_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin6_text.setForeground(Color.RED);
        handler_bin_bin6_text.setEditable(false);
        handler_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin6_text.setColumns(10);
        handler_bin_bin6_text.setBounds(592, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin6_text);
        
        handler_bin_bin7_text = new JTextField(String.valueOf(MainDual.handlerBin7));
        handler_bin_bin7_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin7_text.setForeground(Color.RED);
        handler_bin_bin7_text.setEditable(false);
        handler_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin7_text.setColumns(10);
        handler_bin_bin7_text.setBounds(648, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin7_text);
        
        handler_bin_bin8_text = new JTextField(String.valueOf(MainDual.handlerBin8));
        handler_bin_bin8_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin8_text.setForeground(Color.RED);
        handler_bin_bin8_text.setEditable(false);
        handler_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin8_text.setColumns(10);
        handler_bin_bin8_text.setBounds(704, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin8_text);
        
        handler_bin_bin9_text = new JTextField(String.valueOf(MainDual.handlerBin9));
        handler_bin_bin9_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        handler_bin_bin9_text.setForeground(Color.RED);
        handler_bin_bin9_text.setEditable(false);
        handler_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin9_text.setColumns(10);
        handler_bin_bin9_text.setBounds(760, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin9_text);
        
        JLabel bin_report_handler_operator_bin_lb = new JLabel("OPERATOR BIN");
        bin_report_handler_operator_bin_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_report_handler_operator_bin_lb.setBounds(7, 197, 123, 18);
        test_in_auto__parent_panel.add(bin_report_handler_operator_bin_lb);
        
        operator_bin_total_text = new JTextField();
        operator_bin_total_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_total_text.setEditable(false);
        operator_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_total_text.setColumns(10);
        operator_bin_total_text.setBounds(144, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_total_text);
        
        operator_bin_good_text = new JTextField();
        operator_bin_good_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_good_text.setEditable(false);
        operator_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_good_text.setColumns(10);
        operator_bin_good_text.setBounds(200, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_good_text);
        
        operator_bin_fail_text = new JTextField();
        operator_bin_fail_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_fail_text.setEditable(false);
        operator_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_fail_text.setColumns(10);
        operator_bin_fail_text.setBounds(256, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_fail_text);
        
        operator_bin_bin1_text = new JTextField();
        operator_bin_bin1_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin1_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
                String bin1_number = operator_bin_bin1_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin1_text.setText(bin1_number);
            }
        });
        operator_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin1_text.setColumns(10);
        operator_bin_bin1_text.setBounds(312, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin1_text);
        
        operator_bin_bin2_text = new JTextField();
        operator_bin_bin2_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin2_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin2_number = operator_bin_bin2_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin2_text.setText(bin2_number);
            }
        });
        operator_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin2_text.setColumns(10);
        operator_bin_bin2_text.setBounds(368, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin2_text);
        
        operator_bin_bin3_text = new JTextField();
        operator_bin_bin3_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin3_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin3_number = operator_bin_bin3_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin3_text.setText(bin3_number);
            }
        });
        operator_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin3_text.setColumns(10);
        operator_bin_bin3_text.setBounds(424, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin3_text);
        
        operator_bin_bin4_text = new JTextField();
        operator_bin_bin4_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin4_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin4_number = operator_bin_bin4_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin4_text.setText(bin4_number);
            }
        });
        operator_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin4_text.setColumns(10);
        operator_bin_bin4_text.setBounds(480, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin4_text);
        
        operator_bin_bin5_text = new JTextField();
        operator_bin_bin5_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin5_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin5_number = operator_bin_bin5_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin5_text.setText(bin5_number);
            }
        });
        operator_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin5_text.setColumns(10);
        operator_bin_bin5_text.setBounds(536, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin5_text);
        
        operator_bin_bin6_text = new JTextField();
        operator_bin_bin6_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin6_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin6_number = operator_bin_bin6_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin6_text.setText(bin6_number);
            }
        });
        operator_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin6_text.setColumns(10);
        operator_bin_bin6_text.setBounds(592, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin6_text);
        
        operator_bin_bin7_text = new JTextField();
        operator_bin_bin7_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin7_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin7_number = operator_bin_bin7_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin7_text.setText(bin7_number);
            }
        });
        operator_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin7_text.setColumns(10);
        operator_bin_bin7_text.setBounds(648, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin7_text);
        
        operator_bin_bin8_text = new JTextField();
        operator_bin_bin8_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin8_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin8_number = operator_bin_bin8_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin8_text.setText(bin8_number);
            }
        });
        operator_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin8_text.setColumns(10);
        operator_bin_bin8_text.setBounds(704, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin8_text);
        
        operator_bin_bin9_text = new JTextField();
        operator_bin_bin9_text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        operator_bin_bin9_text.setEditable(false);
        operator_bin_bin9_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin9_number = operator_bin_bin9_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin9_text.setText(bin9_number);
            }
        });
        operator_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin9_text.setColumns(10);
        operator_bin_bin9_text.setBounds(760, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin9_text);
        
        
        /*
         * ******************************************상단 표시 레이블 색깔_S****************************************************************/
        if (MainDual.main_radio_st1.isSelected()) {
            bin_report_bin1_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin2_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin3_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin4_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin5_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin6_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin7_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin8_lb.setForeground(String.valueOf(passBinSelection_h1.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        } else {
            bin_report_bin1_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin2_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin3_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin4_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin5_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin6_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin7_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            bin_report_bin8_lb.setForeground(String.valueOf(passBinSelection_h2.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        }
        
        bin_report_bin9_lb.setForeground(Color.RED);
        /*
         * ******************************************상단 표시 레이블 색깔_N****************************************************************/
        
        /*
         * ******************************************test_bin 입력란 글자 색 변경_S****************************************************************/
        if (MainDual.main_radio_st1.isSelected()) {
            test_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h1.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h1.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h1.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h1.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h1.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h1.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h1.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h1.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        } else {
            test_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h2.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h2.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h2.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h2.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h2.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h2.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h2.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            test_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h2.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        }
        test_bin_bin9_text.setForeground(Color.RED);
        /*
         * ******************************************test_bin 입력란 글자 색 변경_N****************************************************************/
        
        /*
         * ****************************************** handler_bin 입력란 글자 색 변경_S ****************************************************************/
        if (MainDual.main_radio_st1.isSelected()) {
            handler_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h1.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h1.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h1.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h1.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h1.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h1.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h1.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h1.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        } else {
            handler_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h2.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h2.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h2.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h2.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h2.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h2.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h2.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            handler_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h2.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        }
        handler_bin_bin9_text.setForeground(Color.RED);
        /*
         * ****************************************** handler_bin 입력란 글자 색 변경_N ****************************************************************/
  
        /*
         * ****************************************** operator_bin 입력란 글자 색 변경_S ****************************************************************/
        if (MainDual.main_radio_st1.isSelected()) {
            operator_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h1.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h1.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h1.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h1.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h1.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h1.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h1.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h1.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        } else {
            operator_bin_bin1_text.setForeground(String.valueOf(passBinSelection_h2.charAt(0)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin2_text.setForeground(String.valueOf(passBinSelection_h2.charAt(1)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin3_text.setForeground(String.valueOf(passBinSelection_h2.charAt(2)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin4_text.setForeground(String.valueOf(passBinSelection_h2.charAt(3)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin5_text.setForeground(String.valueOf(passBinSelection_h2.charAt(4)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin6_text.setForeground(String.valueOf(passBinSelection_h2.charAt(5)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin7_text.setForeground(String.valueOf(passBinSelection_h2.charAt(6)).equals("Y") ? Color.BLACK : Color.RED);
            operator_bin_bin8_text.setForeground(String.valueOf(passBinSelection_h2.charAt(7)).equals("Y") ? Color.BLACK : Color.RED);
        }
        operator_bin_bin9_text.setForeground(Color.RED);
        /*
         * ****************************************** operator_bin 입력란 글자 색 변경_S ****************************************************************/
        
        /*테스트 빈 총합_s*/
        int testBinTotal = MainDual.testBin1 + MainDual.testBin2 + MainDual.testBin3 + 
                            MainDual.testBin4 + MainDual.testBin5 + MainDual.testBin6 + 
                            MainDual.testBin7 + MainDual.testBin8 + MainDual.testBin9; 
        test_bin_total_text.setText(String.valueOf(testBinTotal));
        /*테스트 빈 총합_n*/
        
        /*헨들러 빈 총합_s*/
        int handlerbinTotal = MainDual.handlerBin1 + MainDual.handlerBin2 + MainDual.handlerBin3 + 
                MainDual.handlerBin4 + MainDual.handlerBin5 + MainDual.handlerBin6 + 
                MainDual.handlerBin7 + MainDual.handlerBin8 + MainDual.handlerBin9; 
        handler_bin_total_text.setText(String.valueOf(handlerbinTotal));
        /*헨들러 빈 총합_s*/
        
        int testBinGood = 0;
        int testBinFail = 0;
        
        
        if (MainDual.main_radio_st1.isSelected()) {
            /*
             * ************* test Good/Fail 계산_S ******************************/
            if (String.valueOf(passBinSelection_h1.charAt(0)).equals("Y")) {
                testBinGood += MainDual.testBin1;
            } else {
                testBinFail += MainDual.testBin1;
            }

            if (String.valueOf(passBinSelection_h1.charAt(1)).equals("Y")) {
                testBinGood += MainDual.testBin2;
            } else {
                testBinFail += MainDual.testBin2;
            }

            if (String.valueOf(passBinSelection_h1.charAt(2)).equals("Y")) {
                testBinGood += MainDual.testBin3;
            } else {
                testBinFail += MainDual.testBin3;
            }

            if (String.valueOf(passBinSelection_h1.charAt(3)).equals("Y")) {
                testBinGood += MainDual.testBin4;
            } else {
                testBinFail += MainDual.testBin4;
            }

            if (String.valueOf(passBinSelection_h1.charAt(4)).equals("Y")) {
                testBinGood += MainDual.testBin5;
            } else {
                testBinFail += MainDual.testBin5;
            }

            if (String.valueOf(passBinSelection_h1.charAt(5)).equals("Y")) {
                testBinGood += MainDual.testBin6;
            } else {
                testBinFail += MainDual.testBin6;
            }

            if (String.valueOf(passBinSelection_h1.charAt(6)).equals("Y")) {
                testBinGood += MainDual.testBin7;
            } else {
                testBinFail += MainDual.testBin7;
            }

            if (String.valueOf(passBinSelection_h1.charAt(7)).equals("Y")) {
                testBinGood += MainDual.testBin8;
            } else {
                testBinFail += MainDual.testBin8;
            }
            /*
             * ************* test Good/Fail 계산_N ******************************/
        } else {
            /*
             * ************* test Good/Fail 계산_S ******************************/
            if (String.valueOf(passBinSelection_h2.charAt(0)).equals("Y")) {
                testBinGood += MainDual.testBin1;
            } else {
                testBinFail += MainDual.testBin1;
            }

            if (String.valueOf(passBinSelection_h2.charAt(1)).equals("Y")) {
                testBinGood += MainDual.testBin2;
            } else {
                testBinFail += MainDual.testBin2;
            }

            if (String.valueOf(passBinSelection_h2.charAt(2)).equals("Y")) {
                testBinGood += MainDual.testBin3;
            } else {
                testBinFail += MainDual.testBin3;
            }

            if (String.valueOf(passBinSelection_h2.charAt(3)).equals("Y")) {
                testBinGood += MainDual.testBin4;
            } else {
                testBinFail += MainDual.testBin4;
            }

            if (String.valueOf(passBinSelection_h2.charAt(4)).equals("Y")) {
                testBinGood += MainDual.testBin5;
            } else {
                testBinFail += MainDual.testBin5;
            }

            if (String.valueOf(passBinSelection_h2.charAt(5)).equals("Y")) {
                testBinGood += MainDual.testBin6;
            } else {
                testBinFail += MainDual.testBin6;
            }

            if (String.valueOf(passBinSelection_h2.charAt(6)).equals("Y")) {
                testBinGood += MainDual.testBin7;
            } else {
                testBinFail += MainDual.testBin7;
            }

            if (String.valueOf(passBinSelection_h2.charAt(7)).equals("Y")) {
                testBinGood += MainDual.testBin8;
            } else {
                testBinFail += MainDual.testBin8;
            }
            /*
             * ************* test Good/Fail 계산_N ******************************/
        }
        
        test_bin_good_text.setText(String.valueOf(testBinGood));
        test_bin_fail_text.setText(String.valueOf(testBinFail));
        
        int handlerBinGood = 0;
        int handlerBinFail = 0;
        
        if (MainDual.main_radio_st1.isSelected()) {
            /*
             * ************* handler Good/Fail 계산_S ******************************/
            if (String.valueOf(passBinSelection_h1.charAt(0)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin1;
            } else {
                handlerBinFail += MainDual.handlerBin1; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(1)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin2;
            } else {
                handlerBinFail += MainDual.handlerBin2; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(2)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin3;
            } else {
                handlerBinFail += MainDual.handlerBin3; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(3)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin4;
            } else {
                handlerBinFail += MainDual.handlerBin4; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(4)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin5;
            } else {
                handlerBinFail += MainDual.handlerBin5; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(5)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin6;
            } else {
                handlerBinFail += MainDual.handlerBin6; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(6)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin7;
            } else {
                handlerBinFail += MainDual.handlerBin7; 
            }
            
            if (String.valueOf(passBinSelection_h1.charAt(7)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin8;
            } else {
                handlerBinFail += MainDual.handlerBin8; 
            }
            /*
             * ************* handler Good/Fail 계산_N ******************************/
        } else {
            /*
             * ************* handler Good/Fail 계산_S ******************************/
            if (String.valueOf(passBinSelection_h2.charAt(0)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin1;
            } else {
                handlerBinFail += MainDual.handlerBin1; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(1)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin2;
            } else {
                handlerBinFail += MainDual.handlerBin2; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(2)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin3;
            } else {
                handlerBinFail += MainDual.handlerBin3; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(3)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin4;
            } else {
                handlerBinFail += MainDual.handlerBin4; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(4)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin5;
            } else {
                handlerBinFail += MainDual.handlerBin5; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(5)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin6;
            } else {
                handlerBinFail += MainDual.handlerBin6; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(6)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin7;
            } else {
                handlerBinFail += MainDual.handlerBin7; 
            }
            
            if (String.valueOf(passBinSelection_h2.charAt(7)).equals("Y")) {
                handlerBinGood += MainDual.handlerBin8;
            } else {
                handlerBinFail += MainDual.handlerBin8; 
            }
            /*
             * ************* handler Good/Fail 계산_N ******************************/
        }
        
        handler_bin_good_text.setText(String.valueOf(handlerBinGood));
        handler_bin_fail_text.setText(String.valueOf(handlerBinFail));
        
        separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 250, 802, 1);
        test_in_auto__parent_panel.add(separator);
        
        bin_error_lb = new JLabel("");
        bin_error_lb.setHorizontalAlignment(SwingConstants.LEFT);
        bin_error_lb.setForeground(Color.RED);
        bin_error_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        bin_error_lb.setBounds(7, 227, 802, 18);
        test_in_auto__parent_panel.add(bin_error_lb);
        
    }
}

class BinSummaryProgress {

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new BinSummaryTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class BinSummaryTask {
        Loading ld;

        BinSummaryTask() {

            try {
                
                CommonUtil cu = new CommonUtil();
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date bin_end_time_dt = new Date();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    // 작업자가 BIN 입력 완료 시점.(TEST OUT Buttom Push 시점) --->FINAL_END일경우만 표시,--->RE_TEST 경우는 0
                    cu.FileNew(PathProperties.local_Header, "bin_end_time_h1.dat", sdf.format(bin_end_time_dt), false);
                    cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", "99", false);
                } else {
                    // 작업자가 BIN 입력 완료 시점.(TEST OUT Buttom Push 시점) --->FINAL_END일경우만 표시,--->RE_TEST 경우는 0
                    cu.FileNew(PathProperties.local_Header, "bin_end_time_h2.dat", sdf.format(bin_end_time_dt), false);
                    cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", "99", false);
                }
                
                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                Date sbl_in_time_dt = new Date();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    // BETS 서버로 DATA 전송한 시점.
                    cu.FileNew(PathProperties.local_Header, "sbl_in_time_h1.dat", sdf.format(sbl_in_time_dt), false);
                } else {
                    // BETS 서버로 DATA 전송한 시점.
                    cu.FileNew(PathProperties.local_Header, "sbl_in_time_h2.dat", sdf.format(sbl_in_time_dt), false);
                }
                
                if (MainDual.main_radio_st1.isSelected()) {
                    // BETS 서버로 부터 이상 유무의 명령을 받은 시점.
                    cu.FileNew(PathProperties.local_Header, "sbl_end_time_h1.dat", "NULL", false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_result_h1.dat", "NULL", false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_result_h1.dat", "NULL", false);
                } else {
                    // BETS 서버로 부터 이상 유무의 명령을 받은 시점.
                    cu.FileNew(PathProperties.local_Header, "sbl_end_time_h2.dat", "NULL", false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_result_h2.dat", "NULL", false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_result_h2.dat", "NULL", false);
                }
                
                Date file_new_dt = new Date();
                MainDual.header_data = "";
                String main_bin_data = "";
                cu.Headerdata();
                String header_data = MainDual.header_data;
                main_bin_data += "<TEST_BIN>\n";
                main_bin_data += cu.getBinDataFormat(String.valueOf(MainDual.testBin1));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin2));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin3));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin4));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin5));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin6));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin7));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin8));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.testBin9)) + "\n";
                main_bin_data += "</TEST_BIN>\n";
                main_bin_data += "<HANDLER_BIN>\n";
                main_bin_data += cu.getBinDataFormat(String.valueOf(MainDual.handlerBin1));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin2));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin3));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin4));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin5));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin6));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin7));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin8));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(MainDual.handlerBin9)) + "\n";
                main_bin_data += "</HANDLER_BIN>\n";
                main_bin_data += "<OP_BIN>\n";
                main_bin_data += cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin1_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin2_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin3_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin4_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin5_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin6_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin7_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin8_text.getText()));
                main_bin_data += " " + cu.getBinDataFormat(String.valueOf(BinSummaryPop.operator_bin_bin9_text.getText())) + "\n";
                main_bin_data += "</HANDLER_BIN>";
                header_data += "\n" + main_bin_data;
                cu.FileNew(PathProperties.lc_main_bin, "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_MAINBIN_REPORT_"+sdf.format(file_new_dt)+".HEADA", header_data, false);
                ftpModule.fileCopy(PathProperties.lc_main_bin + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_MAINBIN_REPORT_"+sdf.format(file_new_dt)+".HEADA", PathProperties.ftppre + "HTA01_"+cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim()+"_MAINBIN_REPORT_"+sdf.format(file_new_dt)+".HEADA");
                
                ftpModule.re_test_end_exit = false;
                ftpModule.FtpServerSend(1);
                
                while (true) {
                    if (ftpModule.re_test_end_exit) {
                        break;
                    }
                }
                
                Date prelot_end_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "prelot_end_time_h1.dat", sdf.format(prelot_end_time_dt), false);
                } else {
                    cu.FileNew(PathProperties.local_Header, "prelot_end_time_h2.dat", sdf.format(prelot_end_time_dt), false);
                }
                
                
                ld.setVisible(false);
                ld.dispose();
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}