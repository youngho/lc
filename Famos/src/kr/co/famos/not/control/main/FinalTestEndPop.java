package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JSeparator;

/**
 * <code>FinalTestEndPop.java</code>
 * 
 * @company : FAMOS
 * @Description : FINAL_TEST_END �˾� ȭ��
 * @author : ������ ����
 * @modify :
 * @since : 2017.02.03
 * @version : 1.0
 */

public class FinalTestEndPop extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();

    // ��׶��� �ڴ� ó��
    private FinalTestEndProgress task;

    // �׽�Ʈ ī���� Ƚ��
    static int final_test_end_count_st1 = -1;
    static int final_test_end_count_st2 = -1;

    /**
     * Create the dialog.
     */
    public FinalTestEndPop(final Frame parent) {

        super(parent, true);
        setTitle("FINAL_TEST_END");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* ������ ȭ�� ��� */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 416, 199);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel final_test_end_panel = new JPanel();
            final_test_end_panel.setLayout(null);
            final_test_end_panel.setBackground(new Color(20, 86, 148));
            final_test_end_panel.setBounds(7, 12, 384, 89);
            test_in_auto__parent_panel.add(final_test_end_panel);
            {
                JLabel final_test_end_title_lb = new JLabel("HEAD A");
                final_test_end_title_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                final_test_end_title_lb.setBounds(158, 52, 67, 18);
                final_test_end_panel.add(final_test_end_title_lb);
            }
            {
                JLabel final_test_end_header_lb = new JLabel("FINAL_TEST_END");
                final_test_end_header_lb.setFont(new Font("Tahoma", Font.BOLD, 17));
                final_test_end_header_lb.setBounds(116, 17, 152, 18);
                final_test_end_panel.add(final_test_end_header_lb);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(7, 113, 384, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton final_test_end_bt_ok = new GradientButton("OK");
                final_test_end_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                final_test_end_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        task = new FinalTestEndProgress();
                        task.go();
                    }
                });
                final_test_end_bt_ok.setBounds(84, 5, 53, 27);
                final_test_end_bt_ok.setActionCommand("OK");
                buttonPane.add(final_test_end_bt_ok);
                getRootPane().setDefaultButton(final_test_end_bt_ok);
            }

            {
                JButton final_test_end_bt_exit = new GradientButton("EXIT");
                final_test_end_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                final_test_end_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                final_test_end_bt_exit.setBounds(221, 5, 77, 27);
                final_test_end_bt_exit.setActionCommand("Cancel");
                buttonPane.add(final_test_end_bt_exit);
            }
        }
        {
            JSeparator separator = new JSeparator();
            separator.setForeground(Color.LIGHT_GRAY);
            separator.setBackground(Color.WHITE);
            separator.setBounds(7, 105, 384, 1);
            test_in_auto__parent_panel.add(separator);
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
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "FINAL_END", false);
                    CommonUtil.ButtonConditionA();
                } else {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "FINAL_END", false);
                    CommonUtil.ButtonConditionB();
                }
                
                // cu.execExecution("/home/fsdiag/NLcommand_FS/LCommand.sh");
                // �׽�Ʈ
                String handlerBinData = cu.FileReaderData(PathProperties.local, "SUM2.DAT", true);
                // ���� :  cu.SubBinData(sub_bin_data);
                
                cu.HandlerbinCalculator(handlerBinData);
                
                Date end_time_dt = new Date();
                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "FINAL", false);
                    cu.FileNew(PathProperties.local_Header, "end_time_h1.dat", sdf.format(end_time_dt), false);
                } else {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "FINAL", false);
                    cu.FileNew(PathProperties.local_Header, "end_time_h2.dat", sdf.format(end_time_dt), false);
                }
                
                if (MainDual.finalTestEnd) {
                    cu.Sequence(end_time_dt, "0");

                    ftpModule.re_test_end_exit = false;
                    ftpModule.FtpServerSend(0);

                    while (true) {
                        if (ftpModule.re_test_end_exit) {
                            break;
                        }
                    }

                    ld.setVisible(false);
                    ld.dispose();
                }

                BinSummaryPop BinSummaryPop = new BinSummaryPop(MainDual.main_frm_d);
                BinSummaryPop.setVisible(true);
                
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
