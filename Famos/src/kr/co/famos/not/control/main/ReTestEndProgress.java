package kr.co.famos.not.control.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import kr.co.famos.not.control.ftp.ftpModule;
import kr.co.famos.not.control.socket.ServerHelper;
import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.PathProperties;

public class ReTestEndProgress {
    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                return new ReTestEndTask();
            }
        };
        worker.start();
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class ReTestEndTask {
        
        Loading ld;

        ReTestEndTask() {

            try {
                
                MainDual.loading_flg = false;
                
                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Date re_start_time_dt = new Date();

                CommonUtil cu = new CommonUtil();

                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "RE_TEST", false);
                    CommonUtil.ButtonConditionA();
                } else {
                    cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "RE_TEST", false);
                    CommonUtil.ButtonConditionB();
                }

                if (MainDual.main_radio_st1.isSelected()) {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h1.dat", "RETEST", false);
                    // SRQKIND#08 or SRQKIND#10 받은 시점.
                    cu.FileNew(PathProperties.local_Header, "lot_start_time_h1.dat", sdf.format(re_start_time_dt), false);
                    String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h1.dat", true);
                    int count = Integer.parseInt(test_counter.trim()) + 1;
                    cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", String.valueOf(count), false);

                } else {
                    cu.FileNew(PathProperties.local_Header, "test_flow_h2.dat", "RETEST", false);
                    cu.FileNew(PathProperties.local_Header, "lot_start_time_h2.dat", sdf.format(re_start_time_dt), false);
                    String test_counter = cu.FileReaderData(PathProperties.local_Header, "test_counter_h2.dat", true);
                    int count = Integer.parseInt(test_counter.trim()) + 1;
                    cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", String.valueOf(count), false);

                }
                
                while (true) {
                    if (MainDual.thread_stop) {
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
