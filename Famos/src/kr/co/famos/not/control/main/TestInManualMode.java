package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
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
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 224, 215);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(12, 125, 184, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_mode_bt_ok = new GradientButton("OK");
                test_mode_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 15));
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
                test_mode_bt_ok.setBounds(14, 5, 65, 27);
                test_mode_bt_ok.setActionCommand("OK");
                buttonPane.add(test_mode_bt_ok);
                getRootPane().setDefaultButton(test_mode_bt_ok);
            }

            {
                JButton test_mode_bt_exit = new GradientButton("EXIT");
                test_mode_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
                test_mode_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_mode_bt_exit.setBounds(93, 5, 77, 27);
                test_mode_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_mode_bt_exit);
            }
        }
        
        JLabel product_mode_lb = new JLabel("PRODUCT MODE");
        product_mode_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        product_mode_lb.setBounds(12, 8, 149, 27);
        test_in_auto__parent_panel.add(product_mode_lb);
        
        product_mode_radio = new JRadioButton("");
        product_mode_radio.setBackground(Color.WHITE);
        product_mode_radio.setSelected(true);
        buttonGroup_1.add(product_mode_radio);
        product_mode_radio.setBounds(171, 8, 25, 27);
        test_in_auto__parent_panel.add(product_mode_radio);
        
        JLabel qa_mode_lb = new JLabel("QA MODE");
        qa_mode_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        qa_mode_lb.setBounds(12, 33, 149, 27);
        test_in_auto__parent_panel.add(qa_mode_lb);
        
        JLabel maintance_mode_lb = new JLabel("MAINTANCE MODE");
        maintance_mode_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        maintance_mode_lb.setBounds(12, 60, 149, 27);
        test_in_auto__parent_panel.add(maintance_mode_lb);
        
        JLabel engineer_mode_lb = new JLabel("ENGINEER MODE");
        engineer_mode_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        engineer_mode_lb.setBounds(12, 86, 149, 27);
        test_in_auto__parent_panel.add(engineer_mode_lb);
        
        qa_mode_radio = new JRadioButton("");
        qa_mode_radio.setBackground(Color.WHITE);
        buttonGroup_1.add(qa_mode_radio);
        qa_mode_radio.setBounds(171, 33, 25, 27);
        test_in_auto__parent_panel.add(qa_mode_radio);
        
        maintance_mode_radio = new JRadioButton("");
        maintance_mode_radio.setBackground(Color.WHITE);
        buttonGroup_1.add(maintance_mode_radio);
        maintance_mode_radio.setBounds(171, 60, 25, 27);
        test_in_auto__parent_panel.add(maintance_mode_radio);
        
        engineer_mode_radio = new JRadioButton("");
        engineer_mode_radio.setBackground(Color.WHITE);
        buttonGroup_1.add(engineer_mode_radio);
        engineer_mode_radio.setBounds(171, 86, 25, 27);
        test_in_auto__parent_panel.add(engineer_mode_radio);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(16, 121, 180, 2);
        test_in_auto__parent_panel.add(separator);
        
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
