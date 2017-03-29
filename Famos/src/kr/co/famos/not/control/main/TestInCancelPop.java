package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JSeparator;

/**
 * <code>TestInCancelPop.java</code>
 * 
 * @company : FAMOS
 * @Description : TEST_IN_CANCEL 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.04
 * @version : 1.0
 */

public class TestInCancelPop extends JDialog {

    private final JPanel test_in_cancel_parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private TestInCancelProgress task;

    /**
     * Create the dialog.
     */
    public TestInCancelPop(final Frame parent) {

        super(parent, true);
        setTitle("TEST_IN_CANCEL");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 416, 215);
        getContentPane().setLayout(new BorderLayout());
        test_in_cancel_parent_panel.setBackground(Color.WHITE);
        test_in_cancel_parent_panel.setForeground(Color.RED);
        test_in_cancel_parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_cancel_parent_panel, BorderLayout.CENTER);
        test_in_cancel_parent_panel.setLayout(null);
        {
            JPanel test_in_cancel_panel = new JPanel();
            test_in_cancel_panel.setLayout(null);
            test_in_cancel_panel.setBackground(new Color(20, 86, 148));
            test_in_cancel_panel.setBounds(7, 12, 384, 89);
            test_in_cancel_parent_panel.add(test_in_cancel_panel);
            {
                JLabel test_in_cancel_title_lb = new JLabel("HEAD A");
                test_in_cancel_title_lb.setForeground(Color.WHITE);
                test_in_cancel_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_cancel_title_lb.setBounds(157, 52, 70, 18);
                test_in_cancel_panel.add(test_in_cancel_title_lb);
            }
            {
                JLabel test_in_cancel_header_lb = new JLabel("TEST_IN_CANCEL");
                test_in_cancel_header_lb.setForeground(Color.WHITE);
                test_in_cancel_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_cancel_header_lb.setBounds(116, 17, 152, 18);
                test_in_cancel_panel.add(test_in_cancel_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(17, 123, 367, 35);
            test_in_cancel_parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_in_cancel_bt_ok = new GradientButton("OK");
                test_in_cancel_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 15));
                test_in_cancel_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        task = new TestInCancelProgress();
                        task.go();
                    }
                });
                test_in_cancel_bt_ok.setBounds(89, 5, 63, 27);
                test_in_cancel_bt_ok.setActionCommand("OK");
                buttonPane.add(test_in_cancel_bt_ok);
                getRootPane().setDefaultButton(test_in_cancel_bt_ok);
            }

            {
                JButton test_in_cancel_bt_exit = new GradientButton("EXIT");
                test_in_cancel_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
                test_in_cancel_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_in_cancel_bt_exit.setBounds(231, 5, 77, 27);
                test_in_cancel_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_in_cancel_bt_exit);
            }
        }
        {
            JSeparator separator = new JSeparator();
            separator.setForeground(Color.LIGHT_GRAY);
            separator.setBackground(Color.WHITE);
            separator.setBounds(7, 113, 384, 1);
            test_in_cancel_parent_panel.add(separator);
        }
    }
}

class TestInCancelProgress {

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new TestInCancelTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class TestInCancelTask {
        Loading ld;

        TestInCancelTask() {

            try {
                
                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);
                
                CommonUtil cu = new CommonUtil();
                Date final_end_time_dt = new Date();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    String prelot_end_time = cu.HederData(PathProperties.local_Header, "prelot_end_time_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "prelot_end_time_h1.dat").trim();
                    String sbl_result = cu.HederData(PathProperties.local_Header, "sbl_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_result_h1.dat").trim();
                    String sbl_yield_limit = cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h1.dat").trim();
                    String sbl_sub_bina_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat").trim();
                    String sbl_sub_bina_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat").trim();
                    String sbl_sub_binb_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat").trim();
                    String sbl_sub_binb_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat").trim();
                    String sbl_bin9_counter = cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h1.dat").trim();
                    String sbl_bin9_limit = cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h1.dat").trim();
                    String bin_in_time = cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_in_time_h1.dat").trim();
                    String bin_end_time = cu.HederData(PathProperties.local_Header, "bin_end_time_h1.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_end_time_h1.dat").trim();
                    String sbl_bin8_limit = cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h1.dat").trim();
                    String sbl_yield_result = cu.HederData(PathProperties.local_Header, "sbl_yield_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_result_h1.dat").trim();
                    String sbl_bin1_result = cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h1.dat").trim();
                    String sbl_bin2_result = cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h1.dat").trim();
                    String sbl_bin3_result = cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h1.dat").trim();
                    String sbl_bin4_result = cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h1.dat").trim();
                    String sbl_bin5_result = cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h1.dat").trim();
                    String sbl_bin6_result = cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h1.dat").trim();
                    String sbl_bin7_result = cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h1.dat").trim();
                    String sbl_bin8_result = cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h1.dat").trim();
                    String sbl_bin9_result = cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h1.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h1.dat").trim();
                    
                    cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "CANCEL", false);
                    
                    cu.FileNew(PathProperties.local_Header, "bin_in_time_h1.dat", bin_in_time, false);
                    cu.FileNew(PathProperties.local_Header, "bin_end_time_h1.dat", bin_end_time, false);
                    cu.FileNew(PathProperties.local_Header, "prelot_end_time_h1.dat", prelot_end_time, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_result_h1.dat", sbl_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_yield_limit_h1.dat", sbl_yield_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_counter_h1.dat", sbl_sub_bina_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_limit_h1.dat", sbl_sub_bina_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_counter_h1.dat", sbl_sub_binb_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_limit_h1.dat", sbl_sub_binb_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h1.dat", sbl_bin9_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_limit_h1.dat", sbl_bin9_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h1.dat", sbl_bin8_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h1.dat", sbl_yield_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin1_result_h1.dat", sbl_bin1_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin2_result_h1.dat", sbl_bin2_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin3_result_h1.dat", sbl_bin3_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin4_result_h1.dat", sbl_bin4_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin5_result_h1.dat", sbl_bin5_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin6_result_h1.dat", sbl_bin6_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin7_result_h1.dat", sbl_bin7_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h1.dat", sbl_bin8_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h1.dat", sbl_bin9_result, false);
                            
                } else {
                    String prelot_end_time = cu.HederData(PathProperties.local_Header, "prelot_end_time_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "prelot_end_time_h2.dat").trim();
                    String sbl_result = cu.HederData(PathProperties.local_Header, "sbl_result_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "sbl_result_h2.dat").trim();
                    String sbl_yield_limit = cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_limit_h2.dat").trim();
                    String sbl_sub_bina_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat").trim();
                    String sbl_sub_bina_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat").trim();
                    String sbl_sub_binb_counter = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat").trim();
                    String sbl_sub_binb_limit = cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat").trim();
                    String sbl_bin9_counter = cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_counter_h2.dat").trim();
                    String sbl_bin9_limit = cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_limit_h2.dat").trim();
                    String bin_in_time = cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_in_time_h2.dat").trim();
                    String bin_end_time = cu.HederData(PathProperties.local_Header, "bin_end_time_h2.dat").trim().equals("0") ? "0" : cu.HederData(PathProperties.local_Header, "bin_end_time_h2.dat").trim();
                    String sbl_bin8_limit = cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_limit_h2.dat").trim();
                    String sbl_yield_result = cu.HederData(PathProperties.local_Header, "sbl_yield_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_yield_result_h2.dat").trim();
                    String sbl_bin1_result = cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin1_result_h2.dat").trim();
                    String sbl_bin2_result = cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin2_result_h2.dat").trim();
                    String sbl_bin3_result = cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin3_result_h2.dat").trim();
                    String sbl_bin4_result = cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin4_result_h2.dat").trim();
                    String sbl_bin5_result = cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin5_result_h2.dat").trim();
                    String sbl_bin6_result = cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin6_result_h2.dat").trim();
                    String sbl_bin7_result = cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin7_result_h2.dat").trim();
                    String sbl_bin8_result = cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin8_result_h2.dat").trim();
                    String sbl_bin9_result = cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h2.dat").trim().equals("0") ? "NULL" : cu.HederData(PathProperties.local_Header, "sbl_bin9_result_h2.dat").trim();
                    
                    cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "CANCEL", false);
                    
                    cu.FileNew(PathProperties.local_Header, "bin_in_time_h2.dat", bin_in_time, false);
                    cu.FileNew(PathProperties.local_Header, "bin_end_time_h2.dat", bin_end_time, false);
                    cu.FileNew(PathProperties.local_Header, "prelot_end_time_h2.dat", prelot_end_time, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_result_h2.dat", sbl_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_yield_limit_h2.dat", sbl_yield_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_counter_h2.dat", sbl_sub_bina_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_bina_limit_h2.dat", sbl_sub_bina_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_counter_h2.dat", sbl_sub_binb_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_sub_binb_limit_h2.dat", sbl_sub_binb_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_counter_h2.dat", sbl_bin9_counter, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_limit_h2.dat", sbl_bin9_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_limit_h2.dat", sbl_bin8_limit, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_yield_result_h2.dat", sbl_yield_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin1_result_h2.dat", sbl_bin1_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin2_result_h2.dat", sbl_bin2_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin3_result_h2.dat", sbl_bin3_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin4_result_h2.dat", sbl_bin4_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin5_result_h2.dat", sbl_bin5_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin6_result_h2.dat", sbl_bin6_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin7_result_h2.dat", sbl_bin7_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin8_result_h2.dat", sbl_bin8_result, false);
                    cu.FileNew(PathProperties.local_Header, "sbl_bin9_result_h2.dat", sbl_bin9_result, false);
                }
                
                cu.Sequence(final_end_time_dt, "CANCEL");

                ftpModule.re_test_end_exit = false;
                ftpModule.FtpServerSend(1);

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
