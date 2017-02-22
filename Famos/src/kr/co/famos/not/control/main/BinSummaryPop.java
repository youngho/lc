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
    static JTextField handler_bin_bin1_text;
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
    
    JButton bin_report_handler_test_out_bt;
    /**
     * Create the dialog.
     */
    public BinSummaryPop(final Frame parent) {

        super(parent, true);
        setTitle("BIN REPORT");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setBounds((screenSize.width - 815) / 2, (screenSize.height - 312) / 2, 815, 312);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel test_in_manua_panel = new JPanel();
            test_in_manua_panel.setLayout(null);
            test_in_manua_panel.setBackground(Color.LIGHT_GRAY);
            test_in_manua_panel.setBounds(7, 12, 787, 89);
            test_in_auto__parent_panel.add(test_in_manua_panel);
            {
                JLabel test_in_manua_title_lb = new JLabel("HEAD A");
                test_in_manua_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_manua_title_lb.setBounds(355, 52, 76, 18);
                test_in_manua_panel.add(test_in_manua_title_lb);
            }
            {
                JLabel test_in_manual_header_lb = new JLabel("BIN REPORT");
                test_in_manual_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_manual_header_lb.setBounds(339, 17, 108, 18);
                test_in_manua_panel.add(test_in_manual_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(7, 227, 787, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton bin_report_handler_ok_bt = new JButton("OK");
                bin_report_handler_ok_bt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                        if (operator_bin_bin1_text.getText().equals("")) {
                            operator_bin_bin1_text.setText("0");
                        }
                        
                        if (operator_bin_bin2_text.getText().equals("")) {
                            operator_bin_bin2_text.setText("0");
                        }
                        
                        if (operator_bin_bin3_text.getText().equals("")) {
                            operator_bin_bin3_text.setText("0");
                        }
                        
                        if (operator_bin_bin4_text.getText().equals("")) {
                            operator_bin_bin4_text.setText("0");
                        }
                        
                        if (operator_bin_bin5_text.getText().equals("")) {
                            operator_bin_bin5_text.setText("0");
                        }
                        
                        if (operator_bin_bin6_text.getText().equals("")) {
                            operator_bin_bin6_text.setText("0");
                        }
                        
                        if (operator_bin_bin7_text.getText().equals("")) {
                            operator_bin_bin7_text.setText("0");
                        }
                        
                        if (operator_bin_bin8_text.getText().equals("")) {
                            operator_bin_bin8_text.setText("0");
                        }
                        
                        if (operator_bin_bin9_text.getText().equals("")) {
                            operator_bin_bin9_text.setText("0");
                        }
                        
                        int total_op_bin_sum = Integer.parseInt(operator_bin_bin1_text.getText()) 
                                + Integer.parseInt(operator_bin_bin2_text.getText()) 
                                + Integer.parseInt(operator_bin_bin3_text.getText())
                                + Integer.parseInt(operator_bin_bin4_text.getText()) 
                                + Integer.parseInt(operator_bin_bin5_text.getText()) 
                                + Integer.parseInt(operator_bin_bin6_text.getText())
                                + Integer.parseInt(operator_bin_bin7_text.getText()) 
                                + Integer.parseInt(operator_bin_bin8_text.getText()) 
                                + Integer.parseInt(operator_bin_bin9_text.getText());

                        int total_op_fail_bin_sum = Integer.parseInt(operator_bin_bin5_text.getText()) 
                                + Integer.parseInt(operator_bin_bin6_text.getText()) 
                                + Integer.parseInt(operator_bin_bin7_text.getText())
                                + Integer.parseInt(operator_bin_bin8_text.getText()) 
                                + Integer.parseInt(operator_bin_bin9_text.getText());
                        
                        int total_op_good_bin_sum = total_op_bin_sum - total_op_fail_bin_sum; 
                        
                        operator_bin_total_text.setText(String.valueOf(total_op_bin_sum));
                        operator_bin_good_text.setText(String.valueOf(total_op_good_bin_sum));
                        operator_bin_fail_text.setText(String.valueOf(total_op_fail_bin_sum));
                        
                        bin_report_handler_test_out_bt.setEnabled(true);
                    }
                });
                bin_report_handler_ok_bt.setBounds(389, 4, 53, 27);
                bin_report_handler_ok_bt.setActionCommand("OK");
                buttonPane.add(bin_report_handler_ok_bt);
                getRootPane().setDefaultButton(bin_report_handler_ok_bt);
            }

            {
                JButton bin_report_handler_exit_bt = new JButton("EXIT");
                bin_report_handler_exit_bt.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                bin_report_handler_exit_bt.setBounds(576, 4, 77, 27);
                bin_report_handler_exit_bt.setActionCommand("Cancel");
                buttonPane.add(bin_report_handler_exit_bt);
            }
            
            bin_report_handler_test_out_bt = new JButton("TEST_OUT");
            bin_report_handler_test_out_bt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    
//                    boolean flag = false;
//
//                    if (Integer.parseInt(test_bin_bin1_text.getText()) != Integer.parseInt(operator_bin_bin1_text.getText())) {
//                        flag = true;
//                    }
//
//                    if (Integer.parseInt(test_bin_bin2_text.getText()) != Integer.parseInt(operator_bin_bin2_text.getText())) {
//                        flag = true;
//                    }
//
//                    if (Integer.parseInt(test_bin_bin3_text.getText()) != Integer.parseInt(operator_bin_bin3_text.getText())) {
//                        flag = true;
//                    }
//
//                    if (Integer.parseInt(test_bin_bin4_text.getText()) != Integer.parseInt(operator_bin_bin4_text.getText())) {
//                        flag = true;
//                    }
//
//                    if (flag) {
//                        BinCounTerErrorPop BinCounTerErrorPop = new BinCounTerErrorPop(MainDual.main_frm_d);
//                        BinCounTerErrorPop.setVisible(true);
//                    } else {
//                        dispose();
//
//                        task = new BinSummaryProgress();
//                        task.go();
//                    }
                    
                    dispose();

                    task = new BinSummaryProgress();
                    task.go();
                }
            });
            bin_report_handler_test_out_bt.setActionCommand("OK");
            bin_report_handler_test_out_bt.setBounds(134, 4, 121, 27);
            buttonPane.add(bin_report_handler_test_out_bt);
        }
        
        JLabel bin_report_handler_bin_bin_lb = new JLabel("HANDLER BIN");
        bin_report_handler_bin_bin_lb.setBounds(7, 167, 115, 18);
        test_in_auto__parent_panel.add(bin_report_handler_bin_bin_lb);
        
        JLabel bin_report_menu_lb = new JLabel("MENU");
        bin_report_menu_lb.setBounds(7, 113, 115, 18);
        test_in_auto__parent_panel.add(bin_report_menu_lb);
        
        JLabel bin_report_total_lb = new JLabel("TOTAL");
        bin_report_total_lb.setHorizontalAlignment(SwingConstants.CENTER);
        bin_report_total_lb.setBounds(129, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_total_lb);
        
        JLabel bin_report_good_lb = new JLabel("GOOD");
        bin_report_good_lb.setHorizontalAlignment(SwingConstants.CENTER);
        bin_report_good_lb.setBounds(185, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_good_lb);
        
        JLabel bin_report_fail_lb = new JLabel("FAIL",SwingConstants.CENTER);
        bin_report_fail_lb.setBounds(241, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_fail_lb);
        
        JLabel bin_report_bin1_lb = new JLabel("BIN1",SwingConstants.CENTER);
        bin_report_bin1_lb.setBounds(297, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin1_lb);
        
        JLabel bin_report_bin2_lb = new JLabel("BIN2",SwingConstants.CENTER);
        bin_report_bin2_lb.setBounds(353, 113, 45, 18);
        test_in_auto__parent_panel.add(bin_report_bin2_lb);
        
        JLabel bin_report_bin3_lb = new JLabel("BIN3",SwingConstants.CENTER);
        bin_report_bin3_lb.setBounds(412, 113, 46, 18);
        test_in_auto__parent_panel.add(bin_report_bin3_lb);
        
        JLabel bin_report_bin4_lb = new JLabel("BIN4",SwingConstants.CENTER);
        bin_report_bin4_lb.setBounds(465, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin4_lb);
        
        JLabel bin_report_bin5_lb = new JLabel("BIN5",SwingConstants.CENTER);
        bin_report_bin5_lb.setForeground(Color.RED);
        bin_report_bin5_lb.setBounds(521, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin5_lb);
        
        JLabel bin_report_bin6_lb = new JLabel("BIN6",SwingConstants.CENTER);
        bin_report_bin6_lb.setForeground(Color.RED);
        bin_report_bin6_lb.setBounds(577, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin6_lb);
        
        JLabel bin_report_bin7_lb = new JLabel("BIN7",SwingConstants.CENTER);
        bin_report_bin7_lb.setForeground(Color.RED);
        bin_report_bin7_lb.setBounds(633, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin7_lb);
        
        JLabel bin_report_bin8_lb = new JLabel("BIN8",SwingConstants.CENTER);
        bin_report_bin8_lb.setForeground(Color.RED);
        bin_report_bin8_lb.setBounds(689, 113, 50, 18);
        test_in_auto__parent_panel.add(bin_report_bin8_lb);
        
        JLabel bin_report_bin9_lb = new JLabel("BIN9",SwingConstants.CENTER);
        bin_report_bin9_lb.setForeground(Color.RED);
        bin_report_bin9_lb.setBounds(745, 113, 49, 18);
        test_in_auto__parent_panel.add(bin_report_bin9_lb);
        
        // 메인빈 합치기
        int total_test_bin_sum = Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_0"))
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_1")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_2")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_3")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_4")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_5")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_6")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_7")) 
         + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_8"));
        
        int total_fail_bin_sum = Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_4")) 
                + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_5")) 
                + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_6")) 
                + Integer.parseInt((String)MainDual.sub_bin_map_st1.get("bin_7"));
        
        test_bin_total_text = new JTextField(String.valueOf(total_test_bin_sum));
        test_bin_total_text.setEditable(false);
        test_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_total_text.setColumns(10);
        test_bin_total_text.setBounds(129, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_total_text);
        
        test_bin_good_text = new JTextField((String)MainDual.sub_bin_map_st1.get("bin_0"));
        test_bin_good_text.setEditable(false);
        test_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_good_text.setColumns(10);
        test_bin_good_text.setBounds(185, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_good_text);
        
        test_bin_fail_text = new JTextField(String.valueOf(total_fail_bin_sum));
        test_bin_fail_text.setEditable(false);
        test_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_fail_text.setColumns(10);
        test_bin_fail_text.setBounds(241, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_fail_text);
        
        test_bin_bin1_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_0").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_0"));
        test_bin_bin1_text.setEditable(false);
        test_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin1_text.setColumns(10);
        test_bin_bin1_text.setBounds(297, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin1_text);
        
        test_bin_bin2_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_1").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_1"));
        test_bin_bin2_text.setEditable(false);
        test_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin2_text.setColumns(10);
        test_bin_bin2_text.setBounds(353, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin2_text);
        
        test_bin_bin3_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_2").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_2"));
        test_bin_bin3_text.setEditable(false);
        test_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin3_text.setColumns(10);
        test_bin_bin3_text.setBounds(409, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin3_text);
        
        test_bin_bin4_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_3").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_3"));
        test_bin_bin4_text.setEditable(false);
        test_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin4_text.setColumns(10);
        test_bin_bin4_text.setBounds(465, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin4_text);
        
        test_bin_bin5_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_4").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_4"));
        test_bin_bin5_text.setForeground(Color.RED);
        test_bin_bin5_text.setEditable(false);
        test_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin5_text.setColumns(10);
        test_bin_bin5_text.setBounds(521, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin5_text);
        
        test_bin_bin6_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_5").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_5"));
        test_bin_bin6_text.setForeground(Color.RED);
        test_bin_bin6_text.setEditable(false);
        test_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin6_text.setColumns(10);
        test_bin_bin6_text.setBounds(577, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin6_text);
        
        test_bin_bin7_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_6").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_6"));
        test_bin_bin7_text.setForeground(Color.RED);
        test_bin_bin7_text.setEditable(false);
        test_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin7_text.setColumns(10);
        test_bin_bin7_text.setBounds(633, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin7_text);
        
        test_bin_bin8_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_7").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_7"));
        test_bin_bin8_text.setForeground(Color.RED);
        test_bin_bin8_text.setEditable(false);
        test_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin8_text.setColumns(10);
        test_bin_bin8_text.setBounds(689, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin8_text);
        
        test_bin_bin9_text = new JTextField((boolean) MainDual.sub_bin_map_st1.get("bin_8").equals("0000") ? "0" : (String) MainDual.sub_bin_map_st1.get("bin_8"));
        test_bin_bin9_text.setForeground(Color.RED);
        test_bin_bin9_text.setEditable(false);
        test_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        test_bin_bin9_text.setColumns(10);
        test_bin_bin9_text.setBounds(745, 137, 49, 24);
        test_in_auto__parent_panel.add(test_bin_bin9_text);
        
        JLabel bin_report_test_bin_lb = new JLabel("TEST BIN");
        bin_report_test_bin_lb.setBounds(7, 137, 115, 18);
        test_in_auto__parent_panel.add(bin_report_test_bin_lb);
        
        handler_bin_total_text = new JTextField();
        handler_bin_total_text.setEditable(false);
        handler_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_total_text.setColumns(10);
        handler_bin_total_text.setBounds(129, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_total_text);
        
        handler_bin_good_text = new JTextField();
        handler_bin_good_text.setEditable(false);
        handler_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_good_text.setColumns(10);
        handler_bin_good_text.setBounds(185, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_good_text);
        
        handler_bin_fail_text = new JTextField();
        handler_bin_fail_text.setEditable(false);
        handler_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_fail_text.setColumns(10);
        handler_bin_fail_text.setBounds(241, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_fail_text);
        
        handler_bin_bin1_text = new JTextField();
        handler_bin_bin1_text.setEditable(false);
        handler_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin1_text.setColumns(10);
        handler_bin_bin1_text.setBounds(297, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin1_text);
        
        handler_bin_bin2_text = new JTextField();
        handler_bin_bin2_text.setEditable(false);
        handler_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin2_text.setColumns(10);
        handler_bin_bin2_text.setBounds(353, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin2_text);
        
        handler_bin_bin3_text = new JTextField();
        handler_bin_bin3_text.setEditable(false);
        handler_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin3_text.setColumns(10);
        handler_bin_bin3_text.setBounds(409, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin3_text);
        
        handler_bin_bin4_text = new JTextField();
        handler_bin_bin4_text.setEditable(false);
        handler_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin4_text.setColumns(10);
        handler_bin_bin4_text.setBounds(465, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin4_text);
        
        handler_bin_bin5_text = new JTextField();
        handler_bin_bin5_text.setForeground(Color.RED);
        handler_bin_bin5_text.setEditable(false);
        handler_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin5_text.setColumns(10);
        handler_bin_bin5_text.setBounds(521, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin5_text);
        
        handler_bin_bin6_text = new JTextField();
        handler_bin_bin6_text.setForeground(Color.RED);
        handler_bin_bin6_text.setEditable(false);
        handler_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin6_text.setColumns(10);
        handler_bin_bin6_text.setBounds(577, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin6_text);
        
        handler_bin_bin7_text = new JTextField();
        handler_bin_bin7_text.setForeground(Color.RED);
        handler_bin_bin7_text.setEditable(false);
        handler_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin7_text.setColumns(10);
        handler_bin_bin7_text.setBounds(633, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin7_text);
        
        handler_bin_bin8_text = new JTextField();
        handler_bin_bin8_text.setForeground(Color.RED);
        handler_bin_bin8_text.setEditable(false);
        handler_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin8_text.setColumns(10);
        handler_bin_bin8_text.setBounds(689, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin8_text);
        
        handler_bin_bin9_text = new JTextField();
        handler_bin_bin9_text.setForeground(Color.RED);
        handler_bin_bin9_text.setEditable(false);
        handler_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        handler_bin_bin9_text.setColumns(10);
        handler_bin_bin9_text.setBounds(745, 167, 49, 24);
        test_in_auto__parent_panel.add(handler_bin_bin9_text);
        
        JLabel bin_report_handler_operator_bin_lb = new JLabel("OPERATOR BIN");
        bin_report_handler_operator_bin_lb.setBounds(7, 197, 115, 18);
        test_in_auto__parent_panel.add(bin_report_handler_operator_bin_lb);
        
        operator_bin_total_text = new JTextField();
        operator_bin_total_text.setEditable(false);
        operator_bin_total_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_total_text.setColumns(10);
        operator_bin_total_text.setBounds(129, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_total_text);
        
        operator_bin_good_text = new JTextField();
        operator_bin_good_text.setEditable(false);
        operator_bin_good_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_good_text.setColumns(10);
        operator_bin_good_text.setBounds(185, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_good_text);
        
        operator_bin_fail_text = new JTextField();
        operator_bin_fail_text.setEditable(false);
        operator_bin_fail_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_fail_text.setColumns(10);
        operator_bin_fail_text.setBounds(241, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_fail_text);
        
        operator_bin_bin1_text = new JTextField();
        operator_bin_bin1_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
                String bin1_number = operator_bin_bin1_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin1_text.setText(bin1_number);
            }
        });
        operator_bin_bin1_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin1_text.setColumns(10);
        operator_bin_bin1_text.setBounds(297, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin1_text);
        
        operator_bin_bin2_text = new JTextField();
        operator_bin_bin2_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin2_number = operator_bin_bin2_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin2_text.setText(bin2_number);
            }
        });
        operator_bin_bin2_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin2_text.setColumns(10);
        operator_bin_bin2_text.setBounds(353, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin2_text);
        
        operator_bin_bin3_text = new JTextField();
        operator_bin_bin3_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin3_number = operator_bin_bin3_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin3_text.setText(bin3_number);
            }
        });
        operator_bin_bin3_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin3_text.setColumns(10);
        operator_bin_bin3_text.setBounds(409, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin3_text);
        
        operator_bin_bin4_text = new JTextField();
        operator_bin_bin4_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin4_number = operator_bin_bin4_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin4_text.setText(bin4_number);
            }
        });
        operator_bin_bin4_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin4_text.setColumns(10);
        operator_bin_bin4_text.setBounds(465, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin4_text);
        
        operator_bin_bin5_text = new JTextField();
        operator_bin_bin5_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin5_number = operator_bin_bin5_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin5_text.setText(bin5_number);
            }
        });
        operator_bin_bin5_text.setForeground(Color.RED);
        operator_bin_bin5_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin5_text.setColumns(10);
        operator_bin_bin5_text.setBounds(521, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin5_text);
        
        operator_bin_bin6_text = new JTextField();
        operator_bin_bin6_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin6_number = operator_bin_bin6_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin6_text.setText(bin6_number);
            }
        });
        operator_bin_bin6_text.setForeground(Color.RED);
        operator_bin_bin6_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin6_text.setColumns(10);
        operator_bin_bin6_text.setBounds(577, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin6_text);
        
        operator_bin_bin7_text = new JTextField();
        operator_bin_bin7_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin7_number = operator_bin_bin7_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin7_text.setText(bin7_number);
            }
        });
        operator_bin_bin7_text.setForeground(Color.RED);
        operator_bin_bin7_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin7_text.setColumns(10);
        operator_bin_bin7_text.setBounds(633, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin7_text);
        
        operator_bin_bin8_text = new JTextField();
        operator_bin_bin8_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin8_number = operator_bin_bin8_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin8_text.setText(bin8_number);
            }
        });
        operator_bin_bin8_text.setForeground(Color.RED);
        operator_bin_bin8_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin8_text.setColumns(10);
        operator_bin_bin8_text.setBounds(689, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin8_text);
        
        operator_bin_bin9_text = new JTextField();
        operator_bin_bin9_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String bin9_number = operator_bin_bin9_text.getText().replaceAll("[^0-9]", "");
                operator_bin_bin9_text.setText(bin9_number);
            }
        });
        operator_bin_bin9_text.setForeground(Color.RED);
        operator_bin_bin9_text.setHorizontalAlignment(JTextField.RIGHT);
        operator_bin_bin9_text.setColumns(10);
        operator_bin_bin9_text.setBounds(745, 197, 49, 24);
        test_in_auto__parent_panel.add(operator_bin_bin9_text);
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
                } else {
                    // 작업자가 BIN 입력 완료 시점.(TEST OUT Buttom Push 시점) --->FINAL_END일경우만 표시,--->RE_TEST 경우는 0
                    cu.FileNew(PathProperties.local_Header, "bin_end_time_h2.dat", sdf.format(bin_end_time_dt), false);
                }
                
                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                String main_bin_data = "";
                
                // TEST BIN
                main_bin_data += (BinSummaryPop.test_bin_bin1_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin1_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin2_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin2_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin3_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin3_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin4_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin4_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin5_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin5_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin6_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin6_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin7_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin7_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin8_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin8_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.test_bin_bin9_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.test_bin_bin9_text.getText());
                main_bin_data += "\n";
                // HANDLER BIN
                main_bin_data += (BinSummaryPop.handler_bin_bin1_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin1_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin2_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin2_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin3_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin3_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin4_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin4_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin5_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin5_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin6_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin6_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin7_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin7_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin8_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin8_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.handler_bin_bin9_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.handler_bin_bin9_text.getText());
                main_bin_data += "\n";
                // OPERATOR BIN
                main_bin_data += (BinSummaryPop.operator_bin_bin1_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin1_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin2_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin2_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin3_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin3_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin4_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin4_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin5_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin5_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin6_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin6_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin7_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin7_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin8_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin8_text.getText());
                main_bin_data += " ";
                main_bin_data += (BinSummaryPop.operator_bin_bin9_text.getText().equals(""))? "0000" : cu.getBinDataFormat(BinSummaryPop.operator_bin_bin9_text.getText());
                
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
                    cu.FileNew(PathProperties.local_Header, "sbl_end_time_h1.dat", "", false);
                } else {
                    // BETS 서버로 부터 이상 유무의 명령을 받은 시점.
                    cu.FileNew(PathProperties.local_Header, "sbl_end_time_h2.dat", "", false);
                }
                
                MainDual.header_data = "";
                cu.Headerdata();
                String header_data = MainDual.header_data;
                
                header_data += "\n" + main_bin_data;

                cu.FileNew(PathProperties.ftp_main_bin, "input_lotid123456_mainbin_report-20170630135555.HEADA", header_data, false);
                ftpModule.fileCopy(PathProperties.ftp_main_bin + "input_lotid123456_mainbin_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_mainbin_report-20170630135555.HEADA");
                
                
                ftpModule.re_test_end_exit = false;
                ftpModule.FtpServerSend(0);
                
                while (true) {
                    if (ftpModule.re_test_end_exit) {
                        break;
                    }
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