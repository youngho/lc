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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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

public class ToolSRQKIND extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    // 라디오 버튼 
    JRadioButton srqkind_radio_on;
    
    

    /**
     * Create the dialog.
     */
    public ToolSRQKIND(final Frame parent) {

        super(parent, true);
        setTitle("SRQKIND");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 246, 129);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(8, 42, 211, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton srqkind_bt_ok = new JButton("OK");
                srqkind_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 라디오 선택
                        if (srqkind_radio_on.isSelected()) {
                            MainDual.srqkind = "on";
                        } else {
                            MainDual.srqkind = "off";
                        }
                        dispose();
                        return;
                    }
                });
                srqkind_bt_ok.setBounds(27, 5, 53, 27);
                srqkind_bt_ok.setActionCommand("OK");
                buttonPane.add(srqkind_bt_ok);
                getRootPane().setDefaultButton(srqkind_bt_ok);
            }

            {
                JButton srqkind_bt_exit = new JButton("EXIT");
                srqkind_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                srqkind_bt_exit.setBounds(107, 5, 77, 27);
                srqkind_bt_exit.setActionCommand("Cancel");
                buttonPane.add(srqkind_bt_exit);
            }
        }
        
        JLabel srqkind_lb = new JLabel("SRQKIND");
        srqkind_lb.setBounds(10, 12, 70, 18);
        test_in_auto__parent_panel.add(srqkind_lb);
        
        srqkind_radio_on = new JRadioButton("ON");
        srqkind_radio_on.setSelected(true);
        buttonGroup.add(srqkind_radio_on);
        srqkind_radio_on.setBounds(90, 8, 58, 27);
        test_in_auto__parent_panel.add(srqkind_radio_on);
        
        JRadioButton srqkind_radio_off = new JRadioButton("OFF");
        buttonGroup.add(srqkind_radio_off);
        srqkind_radio_off.setBounds(158, 8, 58, 27);
        test_in_auto__parent_panel.add(srqkind_radio_off);
    }
}
