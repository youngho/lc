package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.GradientButton;
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

public class ReTestEndPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private ReTestEndProgress task;

    // 테스트 카운터 횟수
    static int re_test_end_count_st1 = 0;
    static int re_test_end_count_st2 = 0;

    /**
     * Create the dialog.
     */
    public ReTestEndPop(final Frame parent) {

        super(parent, true);
        setTitle("RE-TEST_END");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 416, 203);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel re_test_end_panel = new JPanel();
            re_test_end_panel.setLayout(null);
            re_test_end_panel.setBackground(new Color(20, 84, 148));
            re_test_end_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(re_test_end_panel);
            {
                JLabel re_test_end_title_lb = new JLabel("HEAD A");
                re_test_end_title_lb.setForeground(Color.WHITE);
                re_test_end_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                re_test_end_title_lb.setBounds(156, 52, 71, 18);
                re_test_end_panel.add(re_test_end_title_lb);
            }
            {
                JLabel re_test_end_header_lb = new JLabel("RE-TEST_END");
                re_test_end_header_lb.setForeground(Color.WHITE);
                re_test_end_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                re_test_end_header_lb.setBounds(130, 17, 124, 18);
                re_test_end_panel.add(re_test_end_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 113, 384, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton re_test_end_bt_ok = new GradientButton("OK");
                re_test_end_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                re_test_end_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        dispose();

                        task = new ReTestEndProgress();
                        task.go();
                    }
                });
                re_test_end_bt_ok.setBounds(89, 5, 53, 27);
                re_test_end_bt_ok.setActionCommand("OK");
                buttonPane.add(re_test_end_bt_ok);
                getRootPane().setDefaultButton(re_test_end_bt_ok);
            }

            {
                JButton re_test_end_bt_exit = new GradientButton("EXIT");
                re_test_end_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                re_test_end_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                re_test_end_bt_exit.setBounds(231, 5, 77, 27);
                re_test_end_bt_exit.setActionCommand("Cancel");
                buttonPane.add(re_test_end_bt_exit);
            }
        }
        {
            JSeparator separator = new JSeparator();
            separator.setBounds(7, 108, 384, 1);
            test_in_auto__parent_panel.add(separator);
            separator.setForeground(Color.LIGHT_GRAY);
            separator.setBackground(Color.WHITE);
        }
    }
}

//class ReTestEndProgress {
//
//    /**
//     * Called from ProgressBarDemo to start the task.
//     */
//    public void go() {
//        SwingWorker worker = new SwingWorker() {
//            public Object construct() {
//                return new ReTestEndTask();
//            }
//        };
//        worker.start();
//    }
//
//    /**
//     * The actual long running task. This runs in a SwingWorker thread.
//     */
//    class ReTestEndTask {
//        Loading ld;
//
//        ReTestEndTask() {
//
//            try {
//
//                ld = new Loading(MainDual.main_frm_d);
//                ld.setVisible(true);
//
//                String sys_number = "";
//                String inputLotNo = "";
//                String test_counter = "";
//                String bets_step_id = "";
//                String betsPartNumber = "";
//                String bets_function_key = "";
//                String bets_qty = "";
//                String input_operator_id = "";
//
//                if (MainDual.main_radio_st1.isSelected()) {
//                    sys_number = MainDual.main_title_lb.getText();
//                    inputLotNo = MainDual.main_lotno_text_st1.getText();
//                    test_counter = Integer.toString(ReTestEndPop.re_test_end_count_st1);
//                    bets_step_id = (String) MainDual.info_map_st1.get("BETS_STEP_ID");
//                    betsPartNumber = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
//                    bets_function_key = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
//                    bets_qty = (String) MainDual.info_map_st1.get("BETS_QUANTITY");
//                    input_operator_id = MainDual.main_operator_text_st1.getText();
//                } else {
//                    sys_number = MainDual.main_title_lb.getText();
//                    inputLotNo = MainDual.main_lotno_text_st2.getText();
//                    test_counter = Integer.toString(ReTestEndPop.re_test_end_count_st2);
//                    bets_step_id = (String) MainDual.info_map_st2.get("BETS_STEP_ID");
//                    betsPartNumber = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
//                    bets_function_key = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
//                    bets_qty = (String) MainDual.info_map_st2.get("BETS_QUANTITY");
//                    input_operator_id = MainDual.main_operator_text_st2.getText();
//                }
//
//                try {
//                    
//                    File file;
//                    if (MainDual.main_radio_st1.isSelected()) {
//                        file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                    } else {
//                        file = new File(PathProperties.ftpcat + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
//                    }
//                    // true 지정시 파일의 기존 내용에 이어서 작성
//                    FileWriter fw = new FileWriter(file, false);
//
//                    // 파일안에 문자열 쓰기
//                    fw.write(sys_number + "\r\n");
//                    fw.write(inputLotNo + "\r\n");
//                    fw.write(test_counter + "\r\n");
//                    fw.write(bets_step_id + "\r\n");
//                    fw.write(betsPartNumber + "\r\n");
//                    fw.write(bets_function_key + "\r\n");
//                    fw.write(bets_qty + "\r\n");
//                    fw.write(input_operator_id + "\r\n");
//                    fw.write(MainDual.start_date + "\r\n");
//                    fw.write(MainDual.end_date + "\r\n");
//
//                    Process re_test_end_data = Runtime.getRuntime().exec("java");
//                    BufferedReader re_test_end_data_read = new BufferedReader(new InputStreamReader(re_test_end_data.getInputStream()));
//
//                    while (true) {
//                        String re_test_end_line = re_test_end_data_read.readLine();
//                        if (re_test_end_line == null) {
//                            break;
//                        } else {
//                            MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + re_test_end_line + "\n", Color.BLACK);
//                            fw.write(re_test_end_line + "\r\n");
//                        }
//                    }
//
//                    fw.flush();
//                    fw.close();
//                    
//                    if (MainDual.main_radio_st1.isSelected()) {
//                        // 파일 복사
//                        ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
//                        MainDual.station_step1 = "step_re"; 
//                    } else {
//                        // 파일 복사
//                        ftpModule.fileCopy(PathProperties.ftpcat + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
//                        MainDual.station_step2 = "step_re";
//                    }
//                    
//                    if (MainDual.main_radio_st1.isSelected()) {
//                        ReTestEndPop.re_test_end_count_st1++;
//                    } else {
//                        ReTestEndPop.re_test_end_count_st2++;
//                    }
//                    
//                    ftpModule.FtpServerSend(0);
//                   
//                    while (true) {
//                        if (ftpModule.re_test_end_exit) {
//                            ld.setVisible(false);
//                            ld.dispose();
//                            break;
//                        }
//                    }
//                   
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
//                }
//
//            } catch (NumberFormatException e1) {
//                e1.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
//                ld.setVisible(false);
//                ld.dispose();
//            }
//        }
//    }
//}
