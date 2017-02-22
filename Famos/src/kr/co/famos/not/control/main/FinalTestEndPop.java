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

public class FinalTestEndPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private FinalTestEndProgress task;

    // 테스트 카운터 횟수
    static int final_test_end_count_st1 = -1;
    static int final_test_end_count_st2 = -1;

    /**
     * Create the dialog.
     */
    public FinalTestEndPop(final Frame parent) {

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

class FinalTestEndProgress {

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
                
                CommonUtil cu = new CommonUtil();
                
                // 서브빈
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "FINAL_END", false);
                    
                    String header_data = MainDual.header_data;

                    // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                    // 테스트
                    String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADA", true);
                    header_data += "\n" + sub_bin_data;
                    cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADA", header_data, true);
                    ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADA", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADA");
                } else {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "FINAL_END", false);
                    
                    String header_data = MainDual.header_data;
                    // String sub_bin_data = cu.execExecutionFile("/home/fsdiag/NLcommand_FS/LCommand.sh", "로컬 경로", "SUM1.DAT");
                    // 테스트
                    String sub_bin_data = cu.FileReaderData(PathProperties.local_08, "input_lotid123456_subbin_report-20170630135555.HEADB", true);
                    header_data += "\n" + sub_bin_data;
                    cu.FileNew(PathProperties.ftp_sub_bin, "input_lotid123456_subbin_report-20170630135555.HEADB", header_data, true);
                    ftpModule.fileCopy(PathProperties.ftp_sub_bin + "input_lotid123456_subbin_report-20170630135555.HEADB", PathProperties.ftppre + "input_lotid123456_subbin_report-20170630135555.HEADB");
                }
                
                ftpModule.re_test_end_exit = false;
                ftpModule.FtpServerSend(0);
                
                while (true) {
                    if (ftpModule.re_test_end_exit) {
                        break;
                    }
                }
                
                ld.setVisible(false);
                ld.dispose();

                BinSummaryPop BinSummaryPop = new BinSummaryPop(MainDual.main_frm_d);
                BinSummaryPop.setVisible(true);
            
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
