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
 * <code>PrePgmRestore.java</code>
 * 
 * @company : FAMOS
 * @Description : PRE_PGM_RESTORE 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.16
 * @version : 1.0
 */

public class PrePgmRestore extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    public static JTextField pre_pgm_restore_text;

    // 에러 메시지 표시
    private JLabel operator_id_error_message;

    // 백그라운드 뒤단 처리
    private PrePgmRestoreProgress task;

    /**
     * Create the dialog.
     */
    public PrePgmRestore(final Frame parent) {

        super(parent, true);
        setTitle("PRE_PGM_RESTORE");

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
                JLabel pre_pgm_restore_title_lb = new JLabel("HEAD A");
                pre_pgm_restore_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                pre_pgm_restore_title_lb.setBounds(156, 52, 72, 18);
                test_in_auto_panel.add(pre_pgm_restore_title_lb);
            }
            {
                JLabel pre_pgm_restore_header_lb = new JLabel("PRE_PGM_RESTORE");
                pre_pgm_restore_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                pre_pgm_restore_header_lb.setBounds(104, 17, 175, 18);
                test_in_auto_panel.add(pre_pgm_restore_header_lb);
            }
        }

        JLabel pre_pgm_restore_lb = new JLabel("LOT ID");
        pre_pgm_restore_lb.setBounds(15, 108, 108, 18);
        test_in_auto__parent_panel.add(pre_pgm_restore_lb);

        pre_pgm_restore_text = new JTextField();
        pre_pgm_restore_text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String lot_no_check = pre_pgm_restore_text.getText();
                if (lot_no_check.length() >= 30) {
                    pre_pgm_restore_text.setText(pre_pgm_restore_text.getText().substring(0, 30));
                }
            }
        });
        pre_pgm_restore_text.setColumns(10);
        pre_pgm_restore_text.setBounds(151, 108, 231, 24);
        test_in_auto__parent_panel.add(pre_pgm_restore_text);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 168, 398, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton pre_pgm_restore_bt_ok = new JButton("OK");
                pre_pgm_restore_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input_not = pre_pgm_restore_text.getText();
                        
                        if (input_not.length() < 10 || input_not.length() > 30) {
                            operator_id_error_message.setText("! 10자리 이상 30자리 이하 입력 가능 합니다.");
                        } else {
                            
                            CommonUtil cu = new CommonUtil();
                            
                            if (MainDual.main_radio_st1.isSelected()) {
                                cu.FileNew(PathProperties.local_Header, "lot_id_h1.dat", input_not, false);
                                cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "PRE_PGM_RESTORE", false);
                            } else {
                                cu.FileNew(PathProperties.local_Header, "lot_id_h2.dat", input_not, false);
                                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "PRE_PGM_RESTORE", false);
                            }
                            
                            dispose();
                            
                            task = new PrePgmRestoreProgress();
                            task.go();
                            
//                            if (MainDual.main_radio_st1.isSelected()) {
//                                MainDual.main_lotno_text_st1.setText(cu.HederData(PathProperties.ftplocal, "lot_id_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "lot_id_h1.dat").trim());
//                                MainDual.main_partnumber_text_st1.setText(cu.HederData(PathProperties.ftplocal, "partnumber_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "partnumber_h1.dat").trim());
//                                MainDual.main_processcode_text_st1.setText(cu.HederData(PathProperties.ftplocal, "process_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_h1.dat").trim());
//                                MainDual.main_fab_text_st1.setText(cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h1.dat").trim());
//                                MainDual.main_grade_text_st1.setText(cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h1.dat").trim());
//                                MainDual.main_temp_text_st1.setText(cu.HederData(PathProperties.ftplocal, "temp_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temp_h1.dat").trim());
//                                MainDual.main_qty_text_st1.setText(cu.HederData(PathProperties.ftplocal, "qty_h1.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "qty_h1.dat"));
//
//                                cu.FileNew(PathProperties.local_Header, "lot_id_h1.dat", input_not, false);
//                                cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "PRE_PGM_RESTORE", false);
//
//                                // 로컬에 파일 데이터를 읽어와서 현재 버튼의 상태를 컨트롤 한다.
//                                CommonUtil.ButtonConditionA();
//                            } else {
//
//                                // 핸들러 2 상태
//                                MainDual.main_lotno_text_st2.setText(cu.HederData(PathProperties.ftplocal, "lot_id_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "lot_id_h2.dat").trim());
//                                MainDual.main_partnumber_text_st2.setText(cu.HederData(PathProperties.ftplocal, "partnumber_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "partnumber_h2.dat").trim());
//                                MainDual.main_processcode_text_st2.setText(cu.HederData(PathProperties.ftplocal, "process_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "process_h2.dat").trim());
//                                MainDual.main_fab_text_st2.setText(cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "fab_h2.dat").trim());
//                                MainDual.main_grade_text_st2.setText(cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "grade_h2.dat").trim());
//                                MainDual.main_temp_text_st2.setText(cu.HederData(PathProperties.ftplocal, "temp_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "temp_h2.dat").trim());
//                                MainDual.main_qty_text_st2.setText(cu.HederData(PathProperties.ftplocal, "qty_h2.dat").trim().equals("0") ? "" : cu.HederData(PathProperties.ftplocal, "qty_h.dat"));
//
//                                cu.FileNew(PathProperties.local_Header, "lot_id_h2.dat", input_not, false);
//                                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "PRE_PGM_RESTORE", false);
//
//                                // 로컬에 파일 데이터를 읽어와서 현재 버튼의 상태를 컨트롤 한다.
//                                CommonUtil.ButtonConditionB();
//                            }
                            
                            dispose();
                        }
                    }
                });
                pre_pgm_restore_bt_ok.setBounds(89, 5, 53, 27);
                pre_pgm_restore_bt_ok.setActionCommand("OK");
                buttonPane.add(pre_pgm_restore_bt_ok);
                getRootPane().setDefaultButton(pre_pgm_restore_bt_ok);
            }

            {
                JButton pre_pgm_restore_bt_exit = new JButton("EXIT");
                pre_pgm_restore_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                pre_pgm_restore_bt_exit.setBounds(231, 5, 77, 27);
                pre_pgm_restore_bt_exit.setActionCommand("Cancel");
                buttonPane.add(pre_pgm_restore_bt_exit);
            }
        }

        operator_id_error_message = new JLabel("");
        operator_id_error_message.setForeground(Color.RED);
        operator_id_error_message.setBounds(15, 138, 367, 18);
        test_in_auto__parent_panel.add(operator_id_error_message);
    }
}
