package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.GradientButton;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * <code>SettingPassWord.java</code>
 * 
 * @company : FAMOS 
 * @Description : 셋팅 관리자 패스워드 팝업
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.03
 * @version : 1.0
 */

public class SRQKINDPassWord extends JDialog {

    private final JPanel setting_password_panel = new JPanel();
    private JPasswordField srqkind_password_text;
    JLabel srqkind_pass_error_lb;
    /**
     * Create the dialog.
     */
    public SRQKINDPassWord(final Frame parent) {

        super(parent, true);
        setTitle("SRQKIND");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 286, 153);
        getContentPane().setLayout(new BorderLayout());
        setting_password_panel.setBackground(Color.WHITE);
        setting_password_panel.setForeground(Color.RED);
        setting_password_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(setting_password_panel, BorderLayout.CENTER);
        setting_password_panel.setLayout(null);

        JLabel srqkind_password_lb = new JLabel("password");
        srqkind_password_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        srqkind_password_lb.setBounds(7, 12, 81, 18);
        setting_password_panel.add(srqkind_password_lb);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 70, 253, 35);
            setting_password_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton srqkind_password_bt_ok = new GradientButton("OK");
                srqkind_password_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                srqkind_password_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String password = srqkind_password_text.getText();
                        if (password.equals("famos12#")) {
                            dispose();
                            ToolSRQKIND ToolSRQKIND = new ToolSRQKIND(MainDual.main_frm_d);
                            ToolSRQKIND.setVisible(true);
                        } else {
                            srqkind_pass_error_lb.setText("! Administrator password is mismatched.");
                        }
                    }
                });
                srqkind_password_bt_ok.setBounds(41, 5, 53, 27);
                srqkind_password_bt_ok.setActionCommand("OK");
                buttonPane.add(srqkind_password_bt_ok);
                getRootPane().setDefaultButton(srqkind_password_bt_ok);
            }

            {
                JButton srqkind_password_bt_exit = new GradientButton("EXIT");
                srqkind_password_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                srqkind_password_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                srqkind_password_bt_exit.setBounds(135, 5, 77, 27);
                srqkind_password_bt_exit.setActionCommand("Cancel");
                buttonPane.add(srqkind_password_bt_exit);
            }
        }
        
        srqkind_password_text = new JPasswordField();
        srqkind_password_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        srqkind_password_text.setBounds(95, 9, 165, 24);
        setting_password_panel.add(srqkind_password_text);
        {
            JSeparator separator = new JSeparator();
            separator.setForeground(Color.LIGHT_GRAY);
            separator.setBackground(Color.WHITE);
            separator.setBounds(7, 63, 253, 2);
            setting_password_panel.add(separator);
        }
        
        srqkind_pass_error_lb = new JLabel("");
        srqkind_pass_error_lb.setHorizontalAlignment(SwingConstants.CENTER);
        srqkind_pass_error_lb.setForeground(Color.RED);
        srqkind_pass_error_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        srqkind_pass_error_lb.setBounds(7, 42, 253, 18);
        setting_password_panel.add(srqkind_pass_error_lb);
    }
}
