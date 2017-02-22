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
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>OperatorIdPop.java</code>
 * 
 * @company : FAMOS 
 * @Description : OPERATOR ID 입력 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.01.31
 * @version : 1.0
 */

public class OperatorIdPop extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField operator_id_text;

    // 에러 메시지 표시
    private JLabel operator_id_error_message;

    /**
     * Create the dialog.
     */
    public OperatorIdPop(Frame parent) {

        super(parent, true);
        setTitle("OPERATOR ID");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = contentPanel.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width) / 3, (screenSize.height - frameSize.height) / 3, 416, 253);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setForeground(Color.RED);
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JPanel operator_id_panel = new JPanel();
            operator_id_panel.setLayout(null);
            operator_id_panel.setBackground(Color.LIGHT_GRAY);
            operator_id_panel.setBounds(7, 12, 384, 89);
            contentPanel.add(operator_id_panel);
            {
                JLabel operator_id_title_lb = new JLabel("HEAD A");
                operator_id_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                operator_id_title_lb.setBounds(156, 52, 72, 18);
                operator_id_panel.add(operator_id_title_lb);
            }
            {
                JLabel operator_id_header_lb = new JLabel("OPERATOR ID");
                operator_id_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                operator_id_header_lb.setBounds(131, 17, 121, 18);
                operator_id_panel.add(operator_id_header_lb);
            }
        }

        JLabel operator_id_lb = new JLabel("OPERATOR  ID");
        operator_id_lb.setBounds(15, 108, 121, 18);
        contentPanel.add(operator_id_lb);

        operator_id_text = new JTextField();
        operator_id_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String str = operator_id_text.getText().replaceAll("[^0-9a-zA-Z]", "");
                if (str.length() <= 10) {
                    operator_id_text.setText(str);
                } else {
                    operator_id_text.setText(operator_id_text.getText().substring(0, 10));
                }                
            }
        });
        operator_id_text.setColumns(10);
        operator_id_text.setBounds(151, 108, 231, 24);
        contentPanel.add(operator_id_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 168, 398, 35);
            contentPanel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton operator_id_bt_ok = new JButton("OK");
                operator_id_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String pop_op_id = operator_id_text.getText();

                        if (pop_op_id.length() < 7 || pop_op_id.length() > 10) {
                            operator_id_error_message.setText("! 7자리 이상 10자리 이하 입력 가능 합니다.");
                            return;
                        } else {
                            if (MainDual.main_radio_st1.isSelected()) {
                                MainDual.main_operator_text_st1.setText(pop_op_id);
                            } else {
                                MainDual.main_operator_text_st2.setText(pop_op_id);
                            }

                            try {
                                CommonUtil cu = new CommonUtil();
                                
                                if (MainDual.main_radio_st1.isSelected()) {
                                    cu.FileNew(PathProperties.local_Header, "input_operator_id_h1.dat", pop_op_id, false);
                                    cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "OPERATOR_ID", false);
                                    CommonUtil.ButtonConditionA();
                                    
//                                    MainDual.main_operatorId_bt.setEnabled(false);
//                                    MainDual.main_test_in_auto_bt.setEnabled(true);
//                                    MainDual.main_test_in_manual_bt.setEnabled(true);
//                                    MainDual.main_test_in_cancel_bt.setEnabled(true);
                                    
                                } else {
                                    cu.FileNew(PathProperties.local_Header, "input_operator_id_h2.dat", pop_op_id, false);
                                    cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "OPERATOR_ID", false);
                                    CommonUtil.ButtonConditionB();
//                                    MainDual.main_operatorId_bt.setEnabled(false);
//                                    MainDual.main_test_in_auto_bt.setEnabled(true);
//                                    MainDual.main_test_in_manual_bt.setEnabled(true);
//                                    MainDual.main_test_in_cancel_bt.setEnabled(true);
                                }
                                

                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            dispose();
                        }
                    }
                });
                operator_id_bt_ok.setBounds(89, 5, 53, 27);
                operator_id_bt_ok.setActionCommand("OK");
                buttonPane.add(operator_id_bt_ok);
                getRootPane().setDefaultButton(operator_id_bt_ok);
            }
            {
                JButton operator_id_bt_exit = new JButton("EXIT");
                operator_id_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                operator_id_bt_exit.setBounds(231, 5, 77, 27);
                operator_id_bt_exit.setActionCommand("Cancel");
                buttonPane.add(operator_id_bt_exit);
            }
        }

        operator_id_error_message = new JLabel("");
        operator_id_error_message.setForeground(Color.RED);
        operator_id_error_message.setBounds(15, 138, 367, 18);
        contentPanel.add(operator_id_error_message);
    }
}
