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
import kr.co.famos.not.control.util.PathProperties;

/**
 * <code>TestInAutoPop.java</code>
 * 
 * @company : FAMOS
 * @Description : TEST_IN_AUTO 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.02
 * @version : 1.0
 */

public class TestInAutoPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    public static JTextField test_in_auto_text;

    // 에러 메시지 표시
    private JLabel operator_id_error_message;

    // 백그라운드 뒤단 처리
    private TestInAutoProgress task;

    // 테스트 카운터 횟수
    public static int test_st1 = 0;
    public static int test_st2 = 0;

    /**
     * Create the dialog.
     */
    public TestInAutoPop(final Frame parent) {

        super(parent, true);
        setTitle("TEST_IN_AUTO");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 416, 253);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel test_in_auto_panel = new JPanel();
            test_in_auto_panel.setLayout(null);
            test_in_auto_panel.setBackground(Color.LIGHT_GRAY);
            test_in_auto_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(test_in_auto_panel);
            {
                JLabel test_in_auto_title_lb = new JLabel("HEAD A");
                test_in_auto_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_auto_title_lb.setBounds(156, 52, 72, 18);
                test_in_auto_panel.add(test_in_auto_title_lb);
            }
            {
                JLabel test_in_auto_header_lb = new JLabel("TEST_IN_AUTO");
                test_in_auto_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_auto_header_lb.setBounds(131, 17, 124, 18);
                test_in_auto_panel.add(test_in_auto_header_lb);
            }
        }

        JLabel test_in_auto_lb = new JLabel("LOT ID");
        test_in_auto_lb.setBounds(15, 108, 108, 18);
        test_in_auto__parent_panel.add(test_in_auto_lb);

        test_in_auto_text = new JTextField();
        test_in_auto_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String lot_no_check = test_in_auto_text.getText();
                if (lot_no_check.length() >= 30) {
                    test_in_auto_text.setText(test_in_auto_text.getText().substring(0, 30));
                }
            }
        });
        test_in_auto_text.setColumns(10);
        test_in_auto_text.setBounds(151, 108, 231, 24);
        test_in_auto__parent_panel.add(test_in_auto_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 168, 398, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_in_auto_bt_ok = new JButton("OK");
                test_in_auto_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input_not = test_in_auto_text.getText();
                        
                        if (input_not.length() < 10 || input_not.length() > 30) {
                            operator_id_error_message.setText("! 10자리 이상 30자리 이하 입력 가능 합니다.");
                        } else {
                            
                            CommonUtil cu = new CommonUtil();
                            
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "lot_id_h1.dat", input_not, false);
                                cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "TEST_IN_AUTO", false);
                                // PRE_PGM_RESTORE READY 모드를 위해서 백업
                                cu.FileNew(PathProperties.ftplocal, "lc_seq_h1.dat", "TEST_IN_AUTO", false);
                            } else {
                                cu.FileNew(PathProperties.local_Header, "lot_id_h2.dat", input_not, false);
                                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "TEST_IN_AUTO", false);
                                // PRE_PGM_RESTORE READY 모드를 위해서 백업
                                cu.FileNew(PathProperties.ftplocal, "lc_seq_h2.dat", "TEST_IN_AUTO", false);
                            }
                            
                            dispose();
                            
                            task = new TestInAutoProgress();
                            task.go();
                        }
                    }
                });
                test_in_auto_bt_ok.setBounds(89, 5, 53, 27);
                test_in_auto_bt_ok.setActionCommand("OK");
                buttonPane.add(test_in_auto_bt_ok);
                getRootPane().setDefaultButton(test_in_auto_bt_ok);
            }

            {
                JButton test_in_auto_bt_exit = new JButton("EXIT");
                test_in_auto_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_in_auto_bt_exit.setBounds(231, 5, 77, 27);
                test_in_auto_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_in_auto_bt_exit);
            }
        }

        operator_id_error_message = new JLabel("");
        operator_id_error_message.setForeground(Color.RED);
        operator_id_error_message.setBounds(15, 138, 367, 18);
        test_in_auto__parent_panel.add(operator_id_error_message);
    }
}
