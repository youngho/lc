package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.GradientButton;
import java.awt.Font;
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

public class Setting extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    static JTextField setting_ip_text;
    static JTextField setting_port_text;

    /**
     * Create the dialog.
     */
    public Setting(final Frame parent) {

        super(parent, true);
        setTitle("Setting");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 265, 178);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);

        JLabel setting_ip_lb = new JLabel("IP");
        setting_ip_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        setting_ip_lb.setBounds(17, 12, 55, 18);
        test_in_auto__parent_panel.add(setting_ip_lb);

        setting_ip_text = new JTextField("121.185.32.49");
        setting_ip_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        setting_ip_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                
            }
        });
        setting_ip_text.setColumns(10);
        setting_ip_text.setBounds(86, 9, 149, 24);
        test_in_auto__parent_panel.add(setting_ip_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(14, 91, 221, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton setting_bt_ok = new GradientButton("OK");
                setting_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                setting_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        MainDual.socket_ip = setting_ip_text.getText();
                        MainDual.socket_port = setting_port_text.getText();
                        dispose();
                    }
                });
                setting_bt_ok.setBounds(30, 5, 53, 27);
                setting_bt_ok.setActionCommand("OK");
                buttonPane.add(setting_bt_ok);
                getRootPane().setDefaultButton(setting_bt_ok);
            }

            {
                JButton setting_bt_exit = new GradientButton("EXIT");
                setting_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                setting_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                setting_bt_exit.setBounds(113, 5, 77, 27);
                setting_bt_exit.setActionCommand("Cancel");
                buttonPane.add(setting_bt_exit);
            }
        }
        
        JLabel setting_port_lb = new JLabel("PORT");
        setting_port_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        setting_port_lb.setBounds(17, 45, 55, 18);
        test_in_auto__parent_panel.add(setting_port_lb);
        
        setting_port_text = new JTextField("7074");
        setting_port_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        setting_port_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
            }
        });
        setting_port_text.setColumns(10);
        setting_port_text.setBounds(86, 45, 149, 24);
        test_in_auto__parent_panel.add(setting_port_text);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(14, 81, 219, 2);
        test_in_auto__parent_panel.add(separator);
    }
}
