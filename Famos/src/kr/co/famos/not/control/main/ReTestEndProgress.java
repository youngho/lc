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
            
            CommonUtil cu = new CommonUtil();

            if (MainDual.main_radio_st1.isSelected()) {
                // TEST È½¼ö (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h1.dat", cu.HederData(PathProperties.local_Header, "test_counter_h1.dat"), false);
                cu.FileNew(PathProperties.local_Header, "lc_seq_h1.dat", "RE_TEST", false);
            } else {
                // TEST È½¼ö (0 or 1 or 2.....)
                cu.FileNew(PathProperties.local_Header, "test_counter_h2.dat", cu.HederData(PathProperties.local_Header, "test_counter_h2.dat"), false);
                cu.FileNew(PathProperties.local_Header, "lc_seq_h2.dat", "RE_TEST", false);
            }
            
            try {

                ld = new Loading(MainDual.main_frm_d);
                ld.setVisible(true);
                
                MainDual.while_break = false;
                while (true) {
                    if (MainDual.while_break) {
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
