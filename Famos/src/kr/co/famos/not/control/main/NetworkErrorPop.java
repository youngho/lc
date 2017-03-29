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
 * <code>NetworkErrorPop.java</code>
 * 
 * @company : FAMOS
 * @Description : NetworkErrorPop 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.03.27
 * @version : 1.0
 */

public class NetworkErrorPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    /**
     * Create the dialog.
     */
    public NetworkErrorPop(final Frame parent) {

        super(parent, true);
        setTitle("ERROR");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 289, 198);
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
            re_test_end_panel.setBounds(7, 12, 255, 89);
            test_in_auto__parent_panel.add(re_test_end_panel);
            {
                JLabel bin_counter_errorpop_title_lb = new JLabel("HEAD A");
                bin_counter_errorpop_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                bin_counter_errorpop_title_lb.setBounds(92, 52, 71, 18);
                re_test_end_panel.add(bin_counter_errorpop_title_lb);
            }
            {
                JLabel bin_counter_errorpop_lb = new JLabel("Network Down !!!!");
                bin_counter_errorpop_lb.setForeground(Color.RED);
                bin_counter_errorpop_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                bin_counter_errorpop_lb.setBounds(45, 17, 164, 18);
                re_test_end_panel.add(bin_counter_errorpop_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 113, 255, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton network_error_bt_ok = new GradientButton("OK");
                network_error_bt_ok.setText("OK");
                network_error_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                network_error_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                network_error_bt_ok.setBounds(82, 4, 91, 27);
                network_error_bt_ok.setActionCommand("OK");
                buttonPane.add(network_error_bt_ok);
                getRootPane().setDefaultButton(network_error_bt_ok);
            }
        }
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 109, 255, 2);
        test_in_auto__parent_panel.add(separator);
    }
}
