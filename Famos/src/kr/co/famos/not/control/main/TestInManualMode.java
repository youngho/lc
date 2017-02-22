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
import kr.co.famos.not.control.util.CommonUtil;
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

public class TestInManualMode extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    // 라디오 버튼 
    JRadioButton product_mode_radio;
    JRadioButton qa_mode_radio;
    JRadioButton maintance_mode_radio;
    JRadioButton engineer_mode_radio;
    
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    
    

    /**
     * Create the dialog.
     */
    public TestInManualMode(final Frame parent) {

        super(parent, true);
        setTitle("TEST MODE");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 212, 245);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(12, 151, 176, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_mode_bt_ok = new JButton("OK");
                test_mode_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 라디오 선택
                        if (MainDual.main_radio_st1.isSelected()) {
                            if (product_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h1 = "PRODUCT MODE";
                            } else if (qa_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h1 = "QA MODE";
                            } else if (maintance_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h1 = "MAINTANCE MODE";
                            } else if (engineer_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h1 = "ENGINEER MODE";
                            }
                        } else {
                            if (product_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h2 = "PRODUCT MODE";
                            } else if (qa_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h2 = "QA MODE";
                            } else if (maintance_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h2 = "MAINTANCE MODE";
                            } else if (engineer_mode_radio.isSelected()) {
                                MainDual.test_in_manual_mode_h2 = "ENGINEER MODE";
                            }
                        }
                        
                        dispose();
                        
                        CommonUtil cu = new CommonUtil();
                        
                        if (MainDual.main_radio_st1.isSelected()) {
                            cu.FileNew(PathProperties.local_Header, "test_mode_h1.dat", MainDual.test_in_manual_mode_h1, false);
                            MainDual.main_testmode_text_st1.setText(MainDual.test_in_manual_mode_h1);
                        } else {
                            cu.FileNew(PathProperties.local_Header, "test_mode_h2.dat", MainDual.test_in_manual_mode_h2, false);
                            MainDual.main_testmode_text_st2.setText(MainDual.test_in_manual_mode_h2);
                        }
                        
                        TestInManualPop testinmanualpop = new TestInManualPop(MainDual.main_frm_d);
                        testinmanualpop.setVisible(true);
                        return;
                    }
                });
                test_mode_bt_ok.setBounds(15, 5, 53, 27);
                test_mode_bt_ok.setActionCommand("OK");
                buttonPane.add(test_mode_bt_ok);
                getRootPane().setDefaultButton(test_mode_bt_ok);
            }

            {
                JButton test_mode_bt_exit = new JButton("EXIT");
                test_mode_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_mode_bt_exit.setBounds(83, 5, 77, 27);
                test_mode_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_mode_bt_exit);
            }
        }
        
        JLabel product_mode_lb = new JLabel("PRODUCT MODE");
        product_mode_lb.setBounds(12, 17, 132, 18);
        test_in_auto__parent_panel.add(product_mode_lb);
        
        product_mode_radio = new JRadioButton("");
        product_mode_radio.setSelected(true);
        buttonGroup_1.add(product_mode_radio);
        product_mode_radio.setBounds(156, 10, 25, 27);
        test_in_auto__parent_panel.add(product_mode_radio);
        
        JLabel qa_mode_lb = new JLabel("QA MODE");
        qa_mode_lb.setBounds(12, 52, 132, 18);
        test_in_auto__parent_panel.add(qa_mode_lb);
        
        JLabel maintance_mode_lb = new JLabel("MAINTANCE MODE");
        maintance_mode_lb.setBounds(12, 87, 132, 18);
        test_in_auto__parent_panel.add(maintance_mode_lb);
        
        JLabel engineer_mode_lb = new JLabel("ENGINEER MODE");
        engineer_mode_lb.setBounds(12, 122, 132, 18);
        test_in_auto__parent_panel.add(engineer_mode_lb);
        
        qa_mode_radio = new JRadioButton("");
        buttonGroup_1.add(qa_mode_radio);
        qa_mode_radio.setBounds(156, 47, 25, 27);
        test_in_auto__parent_panel.add(qa_mode_radio);
        
        maintance_mode_radio = new JRadioButton("");
        buttonGroup_1.add(maintance_mode_radio);
        maintance_mode_radio.setBounds(156, 84, 25, 27);
        test_in_auto__parent_panel.add(maintance_mode_radio);
        
        engineer_mode_radio = new JRadioButton("");
        buttonGroup_1.add(engineer_mode_radio);
        engineer_mode_radio.setBounds(156, 121, 25, 27);
        test_in_auto__parent_panel.add(engineer_mode_radio);
        
        if (MainDual.main_radio_st1.isSelected()) {
            if (MainDual.test_in_manual_mode_h1.equals("PRODUCT MODE")) {
                product_mode_radio.setSelected(true);
            } else if (MainDual.test_in_manual_mode_h1.equals("QA MODE")) {
                qa_mode_radio.setSelected(true);
            } else if (MainDual.test_in_manual_mode_h1.equals("MAINTANCE MODE")) {
                maintance_mode_radio.setSelected(true);
            } else {
                engineer_mode_radio.setSelected(true);
            }
        } else {
            if (MainDual.test_in_manual_mode_h2.equals("PRODUCT MODE")) {
                product_mode_radio.setSelected(true);
            } else if (MainDual.test_in_manual_mode_h2.equals("QA MODE")) {
                qa_mode_radio.setSelected(true);
            } else if (MainDual.test_in_manual_mode_h2.equals("MAINTANCE MODE")) {
                maintance_mode_radio.setSelected(true);
            } else {
                engineer_mode_radio.setSelected(true);
            }
        }
    }
}
