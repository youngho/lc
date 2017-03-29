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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 416, 242);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setForeground(Color.RED);
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JPanel operator_id_panel = new JPanel();
            operator_id_panel.setLayout(null);
            operator_id_panel.setBackground(new Color(20,86,148));
            operator_id_panel.setBounds(7, 12, 384, 89);
            contentPanel.add(operator_id_panel);
            {
                JLabel operator_id_title_lb = new JLabel("HEAD A");
                operator_id_title_lb.setForeground(Color.WHITE);
                operator_id_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                operator_id_title_lb.setBounds(156, 52, 72, 18);
                operator_id_panel.add(operator_id_title_lb);
            }
            {
                JLabel operator_id_header_lb = new JLabel("OPERATOR ID");
                operator_id_header_lb.setForeground(Color.WHITE);
                operator_id_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                operator_id_header_lb.setBounds(131, 17, 121, 18);
                operator_id_panel.add(operator_id_header_lb);
            }
        }

        JLabel operator_id_lb = new JLabel("OPERATOR  ID");
        operator_id_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        operator_id_lb.setBounds(15, 108, 121, 18);
        contentPanel.add(operator_id_lb);

        operator_id_text = new JTextField();
        operator_id_text.setFont(new Font("Tahoma", Font.BOLD, 15));
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
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 159, 384, 35);
            contentPanel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton operator_id_bt_ok = new GradientButton("OK");
                operator_id_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 15));
                operator_id_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String pop_op_id = operator_id_text.getText();

                        if (pop_op_id.length() < 7 || pop_op_id.length() > 10) {
                            operator_id_error_message.setText("<html>! You can input 7 digits or more and 10 digits or less.</html>");
                            return;
                        } else {
                            if (MainDual.main_radio_st1.isSelected()) {
                                MainDual.main_operator_text_st1.setText(pop_op_id);
                            } else {
                                MainDual.main_operator_text_st2.setText(pop_op_id);
                            }

                            try {
                                CommonUtil cu = new CommonUtil();
                                // LOT_IN_TIME
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                                Date lot_in_time_dt = new Date();
                                if (MainDual.main_radio_st1.isSelected()) {
                                    cu.FileNew(PathProperties.local_Header, "operator_id_h1.dat", pop_op_id, false);
                                    // OPERATOR ID 입력 시점 
                                    // 작업자가 OPERATOR ID를 입력하고 다음 작업을 하지 않을 경우 얼마동안 장비를 기동하지 않았나 보기 위해서 넣는다.
                                    cu.FileNew(PathProperties.local_Header, "lot_in_time_h1.dat", sdf.format(lot_in_time_dt), false);
                                    cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "OPERATOR_ID", false);
                                    CommonUtil.ButtonConditionA();
                                } else {
                                    cu.FileNew(PathProperties.local_Header, "operator_id_h2.dat", pop_op_id, false);
                                    // OPERATOR ID 입력 시점 
                                    // 작업자가 OPERATOR ID를 입력하고 다음 작업을 하지 않을 경우 얼마동안 장비를 기동하지 않았나 보기 위해서 넣는다.
                                    cu.FileNew(PathProperties.local_Header, "lot_in_time_h2.dat", sdf.format(lot_in_time_dt), false);
                                    cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "OPERATOR_ID", false);
                                    CommonUtil.ButtonConditionB();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            dispose();
                        }
                    }
                });
                operator_id_bt_ok.setBounds(85, 5, 65, 27);
                operator_id_bt_ok.setActionCommand("OK");
                buttonPane.add(operator_id_bt_ok);
                getRootPane().setDefaultButton(operator_id_bt_ok);
            }
            {
                JButton operator_id_bt_exit = new GradientButton("EXIT");
                operator_id_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
                operator_id_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                operator_id_bt_exit.setBounds(235, 5, 77, 27);
                operator_id_bt_exit.setActionCommand("Cancel");
                buttonPane.add(operator_id_bt_exit);
            }
        }

        operator_id_error_message = new JLabel("");
        operator_id_error_message.setHorizontalAlignment(SwingConstants.CENTER);
        operator_id_error_message.setFont(new Font("Tahoma", Font.BOLD, 12));
        operator_id_error_message.setForeground(Color.RED);
        operator_id_error_message.setBounds(15, 138, 367, 18);
        contentPanel.add(operator_id_error_message);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 155, 384, 1);
        contentPanel.add(separator);
    }
}
