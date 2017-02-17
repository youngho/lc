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
 * <code>TestInCancelPop.java</code>
 * 
 * @company : FAMOS
 * @Description : TEST_IN_CANCEL 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.04
 * @version : 1.0
 */

public class TestInCancelPop extends JDialog {

    private final JPanel test_in_cancel_parent_panel = new JPanel();

    // 백그라운드 뒤단 처리
    private TestInCancelProgress task;

    /**
     * Create the dialog.
     */
    public TestInCancelPop(final Frame parent) {

        super(parent, true);
        setTitle("TEST_IN_CANCEL");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 416, 189);
        getContentPane().setLayout(new BorderLayout());
        test_in_cancel_parent_panel.setForeground(Color.RED);
        test_in_cancel_parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_cancel_parent_panel, BorderLayout.CENTER);
        test_in_cancel_parent_panel.setLayout(null);
        {
            JPanel test_in_cancel_panel = new JPanel();
            test_in_cancel_panel.setLayout(null);
            test_in_cancel_panel.setBackground(Color.LIGHT_GRAY);
            test_in_cancel_panel.setBounds(7, 12, 384, 89);
            test_in_cancel_parent_panel.add(test_in_cancel_panel);
            {
                JLabel test_in_cancel_title_lb = new JLabel("HEAD A");
                test_in_cancel_title_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_cancel_title_lb.setBounds(157, 52, 70, 18);
                test_in_cancel_panel.add(test_in_cancel_title_lb);
            }
            {
                JLabel test_in_cancel_header_lb = new JLabel("TEST_IN_CANCEL");
                test_in_cancel_header_lb.setFont(new Font("굴림", Font.BOLD, 17));
                test_in_cancel_header_lb.setBounds(116, 17, 152, 18);
                test_in_cancel_panel.add(test_in_cancel_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(17, 108, 367, 35);
            test_in_cancel_parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton test_in_cancel_bt_ok = new JButton("OK");
                test_in_cancel_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        dispose();

                        task = new TestInCancelProgress();
                        task.go();
                    }
                });
                test_in_cancel_bt_ok.setBounds(89, 5, 53, 27);
                test_in_cancel_bt_ok.setActionCommand("OK");
                buttonPane.add(test_in_cancel_bt_ok);
                getRootPane().setDefaultButton(test_in_cancel_bt_ok);
            }

            {
                JButton test_in_cancel_bt_exit = new JButton("EXIT");
                test_in_cancel_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                test_in_cancel_bt_exit.setBounds(231, 5, 77, 27);
                test_in_cancel_bt_exit.setActionCommand("Cancel");
                buttonPane.add(test_in_cancel_bt_exit);
            }
        }
    }
}

class TestInCancelProgress {

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new TestInCancelTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class TestInCancelTask {
        Loading ld;

        TestInCancelTask() {

            try {
                
                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                CommonUtil cu = new CommonUtil();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "CANCEL", false);
                } else {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "CANCEL", false);
                }
                
                SimpleDateFormat final_end_time_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date final_end_time_dt = new Date();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    // 현재 LOT에 대한 LC의 완료시점에서 LC의 모든 DATA가 RESET된 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "final_end_time_h1.dat", final_end_time_sdf.format(final_end_time_dt), false);
                } else {
                    // 현재 LOT에 대한 LC의 완료시점에서 LC의 모든 DATA가 RESET된 시점을 뺀값.
                    cu.FileNew(PathProperties.local_Header, "final_end_time_h2.dat", final_end_time_sdf.format(final_end_time_dt), false);
                }
                
                cu.Headerdata();
                String header_data = MainDual.header_data;
                
                SimpleDateFormat cancel_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date cancel_dt = new Date();
                
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.ftpcancel, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADA", header_data, false);
                    ftpModule.fileCopy(PathProperties.ftpcancel + "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADA", PathProperties.ftppre + "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADA");
                } else {
                    cu.FileNew(PathProperties.ftpcancel, "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADB", header_data, false);
                    ftpModule.fileCopy(PathProperties.ftpcancel + "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADB", PathProperties.ftppre + "input_lotid"+MainDual.main_lotno_text_st1.getText()+"_cancel_report-"+cancel_sdf.format(cancel_dt)+".HEADB");
                }
                
                ftpModule.re_test_end_exit = false;
                ftpModule.FtpServerSend(1);

                while (true) {
                    if (ftpModule.re_test_end_exit) {
                        break;
                    }
                }
                
                ld.setVisible(false);
                ld.dispose();
                
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Network Down !!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                ld.setVisible(false);
                ld.dispose();
            }
        }
    }
}
