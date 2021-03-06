package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.GradientButton;
import javax.swing.JSeparator;

/**
 * <code>BinCounTerErrorPop.java</code>
 * 
 * @company : FAMOS
 * @Description : BIN_COUNTER_ERROR 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.17
 * @version : 1.0
 */

public class BinCounTerErrorPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // 테스트 카운터 횟수
    static int re_test_end_count_st1 = 0;
    static int re_test_end_count_st2 = 0;

    /**
     * Create the dialog.
     */
    public BinCounTerErrorPop(final Frame parent) {

        super(parent, true);
        setTitle("ERROR");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = test_in_auto__parent_panel.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width) / 3, (screenSize.height - frameSize.height) / 3, 268, 195);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel re_test_end_panel = new JPanel();
            re_test_end_panel.setLayout(null);
            re_test_end_panel.setBackground(new Color(20, 86, 148));
            re_test_end_panel.setBounds(7, 12, 238, 89);
            test_in_auto__parent_panel.add(re_test_end_panel);
            {
                JLabel bin_counter_errorpop_title_lb = new JLabel("HEAD A");
                bin_counter_errorpop_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                bin_counter_errorpop_title_lb.setBounds(83, 59, 71, 18);
                re_test_end_panel.add(bin_counter_errorpop_title_lb);
            }
            {
                JLabel bin_counter_errorpop_lb = new JLabel("BIN COUNTER ERROR!!!");
                bin_counter_errorpop_lb.setForeground(Color.RED);
                bin_counter_errorpop_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                bin_counter_errorpop_lb.setBounds(14, 29, 210, 18);
                re_test_end_panel.add(bin_counter_errorpop_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 113, 238, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton bin_counter_errorpop_bt_ok = new GradientButton("RETRY");
                bin_counter_errorpop_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                bin_counter_errorpop_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                bin_counter_errorpop_bt_ok.setBounds(73, 4, 91, 27);
                bin_counter_errorpop_bt_ok.setActionCommand("OK");
                buttonPane.add(bin_counter_errorpop_bt_ok);
                getRootPane().setDefaultButton(bin_counter_errorpop_bt_ok);
            }
        }
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 107, 238, 2);
        test_in_auto__parent_panel.add(separator);
    }
}
