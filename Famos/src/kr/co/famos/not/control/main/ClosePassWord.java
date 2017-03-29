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
 * <code>ClosePassWord.java</code>
 * 
 * @company : FAMOS
 * @Description : 창닫기 확인 버튼 팝업
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.04
 * @version : 1.0
 */

public class ClosePassWord extends JDialog {

    private final JPanel setting_password_panel = new JPanel();

    // 에러 메시지 표시
    private JLabel test_in_manua_error_message;
    private JPasswordField close_text;
    JLabel close_pass_error_lb; 
    /**
     * Create the dialog.
     */
    public ClosePassWord(final Frame parent) {

        super(parent, true);
        setTitle("Close");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 286, 154);
        
        getContentPane().setLayout(new BorderLayout());
        setting_password_panel.setBackground(Color.WHITE);
        setting_password_panel.setForeground(Color.RED);
        setting_password_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(setting_password_panel, BorderLayout.CENTER);
        setting_password_panel.setLayout(null);

        JLabel close_lb = new JLabel("password");
        close_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        close_lb.setBounds(7, 12, 81, 18);
        setting_password_panel.add(close_lb);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 70, 253, 35);
            setting_password_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton close_bt_ok = new GradientButton("OK");
                close_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                close_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String password = close_text.getText();
                        if (password.equals("famos12#")) {
                            System.exit(0);
                        } else {
                            close_pass_error_lb.setText("! Administrator password is mismatched.");
                        }
                    }
                });
                close_bt_ok.setBounds(41, 5, 53, 27);
                close_bt_ok.setActionCommand("OK");
                buttonPane.add(close_bt_ok);
                getRootPane().setDefaultButton(close_bt_ok);
            }

            {
                JButton close_bt_exit = new GradientButton("EXIT");
                close_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                close_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        return;
                    }
                });
                close_bt_exit.setBounds(135, 5, 77, 27);
                close_bt_exit.setActionCommand("Cancel");
                buttonPane.add(close_bt_exit);
            }
        }

        test_in_manua_error_message = new JLabel("");
        test_in_manua_error_message.setForeground(Color.RED);
        test_in_manua_error_message.setBounds(17, 370, 367, 18);
        setting_password_panel.add(test_in_manua_error_message);

        close_text = new JPasswordField();
        close_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        close_text.setBounds(95, 9, 165, 24);
        setting_password_panel.add(close_text);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 66, 253, 2);
        setting_password_panel.add(separator);
        
        close_pass_error_lb = new JLabel("");
        close_pass_error_lb.setHorizontalAlignment(SwingConstants.CENTER);
        close_pass_error_lb.setForeground(Color.RED);
        close_pass_error_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
        close_pass_error_lb.setBounds(7, 42, 253, 18);
        setting_password_panel.add(close_pass_error_lb);
    }
}
