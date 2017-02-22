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
    
    /**
     * Create the dialog.
     */
    public SRQKINDPassWord(final Frame parent) {

        super(parent, true);
        setTitle("SRQKIND");

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

        JLabel srqkind_password_lb = new JLabel("password");
        srqkind_password_lb.setBounds(7, 12, 81, 18);
        setting_password_panel.add(srqkind_password_lb);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(7, 42, 253, 35);
            setting_password_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton srqkind_password_bt_ok = new JButton("OK");
                srqkind_password_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String password = srqkind_password_text.getText();
                        if (password.equals("famos12#")) {
                            dispose();
                            ToolSRQKIND ToolSRQKIND = new ToolSRQKIND(MainDual.main_frm_d);
                            ToolSRQKIND.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "관지자 패스워드가 불일치 합니다.", "오류", JOptionPane.ERROR_MESSAGE); 
                        }
                    }
                });
                srqkind_password_bt_ok.setBounds(41, 5, 53, 27);
                srqkind_password_bt_ok.setActionCommand("OK");
                buttonPane.add(srqkind_password_bt_ok);
                getRootPane().setDefaultButton(srqkind_password_bt_ok);
            }

            {
                JButton srqkind_password_bt_exit = new JButton("EXIT");
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
        srqkind_password_text.setBounds(95, 9, 165, 24);
        setting_password_panel.add(srqkind_password_text);
    }
}
