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
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 265, 170);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);

        JLabel setting_ip_lb = new JLabel("IP");
        setting_ip_lb.setBounds(17, 12, 55, 18);
        test_in_auto__parent_panel.add(setting_ip_lb);

        setting_ip_text = new JTextField("121.185.32.49");
        setting_ip_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                
            }
        });
        setting_ip_text.setColumns(10);
        setting_ip_text.setBounds(86, 9, 149, 24);
        test_in_auto__parent_panel.add(setting_ip_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(17, 81, 216, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton setting_bt_ok = new JButton("OK");
                setting_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        MainDual.socket_ip = setting_ip_text.getText();
                        MainDual.socket_port = setting_port_text.getText();
                        dispose();
                    }
                });
                setting_bt_ok.setBounds(28, 5, 53, 27);
                setting_bt_ok.setActionCommand("OK");
                buttonPane.add(setting_bt_ok);
                getRootPane().setDefaultButton(setting_bt_ok);
            }

            {
                JButton setting_bt_exit = new JButton("EXIT");
                setting_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                setting_bt_exit.setBounds(109, 5, 77, 27);
                setting_bt_exit.setActionCommand("Cancel");
                buttonPane.add(setting_bt_exit);
            }
        }
        
        JLabel setting_port_lb = new JLabel("PORT");
        setting_port_lb.setBounds(17, 45, 55, 18);
        test_in_auto__parent_panel.add(setting_port_lb);
        
        setting_port_text = new JTextField("7074");
        setting_port_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
            }
        });
        setting_port_text.setColumns(10);
        setting_port_text.setBounds(86, 45, 149, 24);
        test_in_auto__parent_panel.add(setting_port_text);
    }
}
