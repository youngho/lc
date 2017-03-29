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

public class TestInManualPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    static JTextField test_in_manua_lot_number_text;
    static JTextField test_in_manua_part_number_text;
    static JTextField test_in_manua_process_code_text;
    static JTextField test_in_manua_grade_text;
    static JTextField test_in_manua_fab_text;
    static JTextField test_in_manua_temp_text;
    static JTextField test_in_manua_qty_text;
    
    // 에러 메시지 표시
    private JLabel test_in_manua_error_message;

    // 백그라운드 뒤단 처리
    private TestInManualProgress task;
    
    // 테스트 카운터 횟수
    static int test_in_manual_count_st1 = -1;
    static int test_in_manual_count_st2 = -1;
    
    /**
     * Create the dialog.
     */
    public TestInManualPop(final Frame parent) {

        super(parent, true);
        setTitle("TEST_IN_MANUAL");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 416, 425);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel test_in_manua_panel = new JPanel();
            test_in_manua_panel.setLayout(null);
            test_in_manua_panel.setBackground(new Color(20, 86, 148));
            test_in_manua_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(test_in_manua_panel);
            {
                JLabel test_in_manua_title_lb = new JLabel("HEAD A");
                test_in_manua_title_lb.setForeground(Color.WHITE);
                test_in_manua_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_manua_title_lb.setBounds(154, 52, 76, 18);
                test_in_manua_panel.add(test_in_manua_title_lb);
            }
            {
                JLabel test_in_manual_header_lb = new JLabel("TEST_IN_MANUAL");
                test_in_manual_header_lb.setForeground(Color.WHITE);
                test_in_manual_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                test_in_manual_header_lb.setBounds(106, 17, 171, 18);
                test_in_manua_panel.add(test_in_manual_header_lb);
            }
        }
        
        CommonUtil cu = new CommonUtil();
        
        JLabel test_in_manua_lot_number_lb = new JLabel("LOT ID");
        test_in_manua_lot_number_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_lot_number_lb.setBounds(17, 109, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_lot_number_lb);
        
        test_in_manua_lot_number_text = new JTextField();
        test_in_manua_lot_number_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_lot_number_text.setText(cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "lot_id_h1.dat").trim());
        test_in_manua_lot_number_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                System.out.println("이벤트 키 ====> " + e.getKeyCode());
                
                String lot_no_check = test_in_manua_lot_number_text.getText();
                if (lot_no_check.length() >= 30) {
                    test_in_manua_lot_number_text.setText(test_in_manua_lot_number_text.getText().substring(0, 30));
                }
            }
        });
        test_in_manua_lot_number_text.setColumns(10);
        test_in_manua_lot_number_text.setBounds(153, 109, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_lot_number_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 332, 384, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_in_manua_bt_ok = new GradientButton("OK");
                test_in_manua_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 15));
                test_in_manua_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {    
                        dispose();
                        task = new TestInManualProgress();
                        task.go();
                    }
                });
                test_in_manua_bt_ok.setBounds(80, 5, 67, 27);
                test_in_manua_bt_ok.setActionCommand("OK");
                buttonPane.add(test_in_manua_bt_ok);
                getRootPane().setDefaultButton(test_in_manua_bt_ok);
            }

            {
                JButton test_in_manua_bt_exit = new GradientButton("EXIT");
                test_in_manua_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
                test_in_manua_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_in_manua_bt_exit.setBounds(227, 5, 77, 27);
                test_in_manua_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_in_manua_bt_exit);
            }
        }

        test_in_manua_error_message = new JLabel("");
        test_in_manua_error_message.setForeground(Color.RED);
        test_in_manua_error_message.setBounds(17, 370, 367, 18);
        test_in_auto__parent_panel.add(test_in_manua_error_message);
        
        JLabel test_in_manua_part_number_lb = new JLabel("PART NUMBER");
        test_in_manua_part_number_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_part_number_lb.setBounds(17, 142, 122, 18);
        test_in_auto__parent_panel.add(test_in_manua_part_number_lb);
        
        test_in_manua_part_number_text = new JTextField();
        test_in_manua_part_number_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_part_number_text.setText(cu.HederData(PathProperties.local_Header, "part_number_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "part_number_h1.dat").trim());
        test_in_manua_part_number_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent arg0) {
            }
        });
        test_in_manua_part_number_text.setColumns(10);
        test_in_manua_part_number_text.setBounds(153, 142, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_part_number_text);
        
        JLabel test_in_manua_process_code_lb = new JLabel("PROCESS CODE");
        test_in_manua_process_code_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_process_code_lb.setBounds(17, 175, 122, 18);
        test_in_auto__parent_panel.add(test_in_manua_process_code_lb);
        
        test_in_manua_process_code_text = new JTextField();
        test_in_manua_process_code_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_process_code_text.setText(cu.HederData(PathProperties.local_Header, "process_code_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "process_code_h1.dat").trim());
        test_in_manua_process_code_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_process_code_text.setColumns(10);
        test_in_manua_process_code_text.setBounds(153, 175, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_process_code_text);
        
        JLabel test_in_manua_grade_lb = new JLabel("GRADE");
        test_in_manua_grade_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_grade_lb.setBounds(17, 235, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_grade_lb);
        
        test_in_manua_grade_text = new JTextField();
        test_in_manua_grade_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_grade_text.setText(cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "grade_h1.dat").trim());
        test_in_manua_grade_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_grade_text.setColumns(10);
        test_in_manua_grade_text.setBounds(153, 235, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_grade_text);
        
        JLabel test_in_manua_fab_lb = new JLabel("FAB");
        test_in_manua_fab_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_fab_lb.setBounds(17, 205, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_fab_lb);
        
        test_in_manua_fab_text = new JTextField();
        test_in_manua_fab_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_fab_text.setText(cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "fab_h1.dat").trim());
        test_in_manua_fab_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_fab_text.setColumns(10);
        test_in_manua_fab_text.setBounds(153, 205, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_fab_text);
        
        JLabel test_in_manua_temp_lb = new JLabel("TEMP");
        test_in_manua_temp_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_temp_lb.setBounds(17, 266, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_temp_lb);
        
        test_in_manua_temp_text = new JTextField();
        test_in_manua_temp_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_temp_text.setText(cu.HederData(PathProperties.local_Header, "temperature_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "temperature_h1.dat").trim());
        test_in_manua_temp_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_temp_text.setColumns(10);
        test_in_manua_temp_text.setBounds(153, 266, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_temp_text);
        
        JLabel test_in_manua_qty_lb = new JLabel("QTY");
        test_in_manua_qty_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_qty_lb.setBounds(17, 296, 108, 18);
        test_in_auto__parent_panel.add(test_in_manua_qty_lb);
        
        test_in_manua_qty_text = new JTextField();
        test_in_manua_qty_text.setFont(new Font("Tahoma", Font.BOLD, 15));
        test_in_manua_qty_text.setText(cu.HederData(PathProperties.local_Header, "quantity_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.local_Header, "quantity_h1.dat"));
        test_in_manua_qty_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            }
        });
        test_in_manua_qty_text.setColumns(10);
        test_in_manua_qty_text.setBounds(153, 296, 231, 24);
        test_in_auto__parent_panel.add(test_in_manua_qty_text);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(7, 326, 384, 1);
        test_in_auto__parent_panel.add(separator);
    }
}