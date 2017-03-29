package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import javax.swing.JSeparator;

/**
 * <code>TesterModel.java</code>
 * 
 * @company : FAMOS 
 * @Description : TESTER MODEL 팝업 화면
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.03.23
 * @version : 1.0
 */

public class TesterModel extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    // 라디오 버튼 
    JRadioButton tm_radio_t5503;
    
    

    /**
     * Create the dialog.
     */
    public TesterModel(final Frame parent) {

        super(parent, true);
        setTitle("TESTER MODEL");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - frameSize.width / 3) / 2, (screenSize.height - frameSize.height / 3) / 2, 322, 130);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(5, 46, 291, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton tm_bt_ok = new GradientButton("OK");
                tm_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 15));
                tm_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 라디오 선택
                        CommonUtil cu = new CommonUtil();
                        if (tm_radio_t5503.isSelected()) {
                            MainDual.tester_model = "T5503";
                             cu.FileNew(PathProperties.local_Header, "TESTID.dat", "T5503", false);
                        } else {
                            MainDual.tester_model = "T5588";
                             cu.FileNew(PathProperties.local_Header, "TESTID.dat", "T5588", false);
                        }
                        dispose();
                        return;
                    }
                });
                tm_bt_ok.setBounds(48, 5, 69, 27);
                tm_bt_ok.setActionCommand("OK");
                buttonPane.add(tm_bt_ok);
                getRootPane().setDefaultButton(tm_bt_ok);
            }

            {
                JButton tm_bt_exit = new GradientButton("EXIT");
                tm_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 15));
                tm_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                tm_bt_exit.setBounds(165, 5, 77, 27);
                tm_bt_exit.setActionCommand("Cancel");
                buttonPane.add(tm_bt_exit);
            }
        }
        
        JLabel srqkind_lb = new JLabel("TESTER MODEL");
        srqkind_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        srqkind_lb.setBounds(5, 12, 117, 22);
        test_in_auto__parent_panel.add(srqkind_lb);
        
        tm_radio_t5503 = new JRadioButton("T5503");
        tm_radio_t5503.setBackground(Color.WHITE);
        tm_radio_t5503.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonGroup.add(tm_radio_t5503);
        tm_radio_t5503.setBounds(135, 12, 77, 22);
        test_in_auto__parent_panel.add(tm_radio_t5503);
        
        JRadioButton tm_radio_t5588 = new JRadioButton("T5588");
        tm_radio_t5588.setBackground(Color.WHITE);
        tm_radio_t5588.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonGroup.add(tm_radio_t5588);
        tm_radio_t5588.setBounds(218, 14, 77, 18);
        test_in_auto__parent_panel.add(tm_radio_t5588);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(5, 42, 291, 2);
        test_in_auto__parent_panel.add(separator);
        
        System.out.println("MainDual.tester_model==> " + MainDual.tester_model);
        
        if ("T5503".equals(MainDual.tester_model)) {
            tm_radio_t5503.setSelected(true);
        } else {
            tm_radio_t5588.setSelected(true);
        }
    }
}
