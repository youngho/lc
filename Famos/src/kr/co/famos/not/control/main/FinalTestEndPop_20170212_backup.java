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
 * <code>FinalTestEndPop.java</code>
 * 
 * @company : FAMOS
 * @Description : FINAL_TEST_END 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.03
 * @version : 1.0
 */

public class FinalTestEndPop_20170212_backup extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private FinalTestEndProgress task;

    // 테스트 카운터 횟수
    static int final_test_end_count_st1 = -1;
    static int final_test_end_count_st2 = -1;

    /**
     * Create the dialog.
     */
    public FinalTestEndPop_20170212_backup(final Frame parent) {

        super(parent, true);
        setTitle("FINAL_TEST_END");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = test_in_auto__parent_panel.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width) / 3, (screenSize.height - frameSize.height) / 3, 416, 189);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel final_test_end_panel = new JPanel();
            final_test_end_panel.setLayout(null);
            final_test_end_panel.setBackground(Color.LIGHT_GRAY);
            final_test_end_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(final_test_end_panel);
            {
                JLabel final_test_end_title_lb = new JLabel("HEAD A");
                final_test_end_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                final_test_end_title_lb.setBounds(158, 52, 67, 18);
                final_test_end_panel.add(final_test_end_title_lb);
            }
            {
                JLabel final_test_end_header_lb = new JLabel("FINAL_TEST_END");
                final_test_end_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                final_test_end_header_lb.setBounds(116, 17, 152, 18);
                final_test_end_panel.add(final_test_end_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(17, 108, 367, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton final_test_end_bt_ok = new JButton("OK");
                final_test_end_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (MainDual.main_radio_st1.isSelected()) {
                            final_test_end_count_st1++;
                        } else {
                            final_test_end_count_st2++;
                        }

                        // 시작 날자 종료 날자 선언
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        Date dt = new Date();
                        MainDual.end_date = sdf.format(dt);

                        dispose();

                        task = new FinalTestEndProgress();
                        task.go();
                    }
                });
                final_test_end_bt_ok.setBounds(89, 5, 53, 27);
                final_test_end_bt_ok.setActionCommand("OK");
                buttonPane.add(final_test_end_bt_ok);
                getRootPane().setDefaultButton(final_test_end_bt_ok);
            }

            {
                JButton final_test_end_bt_exit = new JButton("EXIT");
                final_test_end_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                final_test_end_bt_exit.setBounds(231, 5, 77, 27);
                final_test_end_bt_exit.setActionCommand("Cancel");
                buttonPane.add(final_test_end_bt_exit);
            }
        }
    }
}

class FinalTestEnd1Progress {

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new FinalTestEndTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class FinalTestEndTask {
        Loading ld;

        FinalTestEndTask() {

            try {

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);
                
                String sys_number = "";
                String inputLotNo = "";
                String test_counter = "";
                String bets_step_id = "";
                String betsPartNumber = "";
                String bets_function_key = "";
                String bets_qty = "";
                String input_operator_id = "";

                if (MainDual.main_radio_st1.isSelected()) {
                    sys_number = MainDual.main_title_lb.getText();
                    inputLotNo = MainDual.main_lotno_text_st1.getText();
                    test_counter = Integer.toString(FinalTestEndPop_20170212_backup.final_test_end_count_st1);
                    bets_step_id = (String) MainDual.info_map_st1.get("BETS_STEP_ID");
                    betsPartNumber = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                    bets_function_key = (String) MainDual.info_map_st1.get("BETS_PART_NUMBER");
                    bets_qty = (String) MainDual.info_map_st1.get("BETS_QUANTITY");
                    input_operator_id = MainDual.main_operator_text_st1.getText();
                } else {
                    sys_number = MainDual.main_title_lb.getText();
                    inputLotNo = MainDual.main_lotno_text_st2.getText();
                    test_counter = Integer.toString(FinalTestEndPop_20170212_backup.final_test_end_count_st2);
                    bets_step_id = (String) MainDual.info_map_st2.get("BETS_STEP_ID");
                    betsPartNumber = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
                    bets_function_key = (String) MainDual.info_map_st2.get("BETS_PART_NUMBER");
                    bets_qty = (String) MainDual.info_map_st2.get("BETS_QUANTITY");
                    input_operator_id = MainDual.main_operator_text_st2.getText();
                }

                try {
                    
                    File file;
                    if (MainDual.main_radio_st1.isSelected()) {
                        file = new File(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1");
                    } else {
                        file = new File(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2");
                    }
                    // true 지정시 파일의 기존 내용에 이어서 작성
                    FileWriter fw = new FileWriter(file, false);

                    // 파일안에 문자열 쓰기
                    fw.write(sys_number + "\r\n");
                    fw.write(inputLotNo + "\r\n");
                    fw.write(test_counter + "\r\n");
                    fw.write(bets_step_id + "\r\n");
                    fw.write(betsPartNumber + "\r\n");
                    fw.write(bets_function_key + "\r\n");
                    fw.write(bets_qty + "\r\n");
                    fw.write(input_operator_id + "\r\n");
                    fw.write(MainDual.start_date + "\r\n");
                    fw.write(MainDual.end_date + "\r\n");

                    Process final_test_end_data = Runtime.getRuntime().exec("java");
                    BufferedReader final_test_end_data_read = new BufferedReader(new InputStreamReader(final_test_end_data.getInputStream()));

                    while (true) {
                        String final_test_end_line = final_test_end_data_read.readLine();
                        if (final_test_end_line == null) {
                            break;
                        } else {
                            MainDual.appendToPane(MainDual.main_log_textPane, "exec :" + final_test_end_line + "\n", Color.BLACK);
                            fw.write(final_test_end_line + "\r\n");
                        }
                    }

                    fw.flush();
                    fw.close();
                    
                    if (MainDual.main_radio_st1.isSelected()) {
                        // 파일 복사
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST1", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST1");
                        MainDual.station_step1 = "step_final"; 
                    } else {
                        // 파일 복사
                        ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lot_no-cat_report-end_" + MainDual.end_date + ".ST2", PathProperties.ftppre + "input_lot_no-cat_report-" + MainDual.end_date + ".ST2");
                        MainDual.station_step2 = "step_final";
                    }
                    
                    ld.setVisible(false);
                    ld.dispose();
                    
                    BinSummaryPop_20170216 BinSummaryPop = new BinSummaryPop_20170216(MainDual.main_frm_d);
                    BinSummaryPop.setVisible(true);
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                    MainDual.appendToPane(MainDual.main_log_textPane, "ERROR : File upload error \n", Color.RED);
                }

            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
