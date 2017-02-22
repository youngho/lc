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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.net.ftp.FTPClient;

import kr.co.famos.not.control.ftp.FtpConnect;
import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JPasswordField;

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

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 286, 128);
        
        getContentPane().setLayout(new BorderLayout());
        setting_password_panel.setForeground(Color.RED);
        setting_password_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(setting_password_panel, BorderLayout.CENTER);
        setting_password_panel.setLayout(null);

        JLabel close_lb = new JLabel("password");
        close_lb.setBounds(7, 12, 81, 18);
        setting_password_panel.add(close_lb);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(7, 42, 253, 35);
            setting_password_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton close_bt_ok = new JButton("OK");
                close_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String password = close_text.getText();
                        if (password.equals("famos12#")) {
                            System.exit(0);
                        } else {
                            JOptionPane.showMessageDialog(null, "패스워드가 불일치 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                close_bt_ok.setBounds(41, 5, 53, 27);
                close_bt_ok.setActionCommand("OK");
                buttonPane.add(close_bt_ok);
                getRootPane().setDefaultButton(close_bt_ok);
            }

            {
                JButton close_bt_exit = new JButton("EXIT");
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
        close_text.setBounds(95, 9, 165, 24);
        setting_password_panel.add(close_text);
    }
}
