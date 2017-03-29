package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import kr.co.famos.not.control.util.CommonUtil;
import kr.co.famos.not.control.util.GradientButton;
import kr.co.famos.not.control.util.PathProperties;
import java.awt.Font;
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

public class ToolSRQKIND extends JDialog {

    private final JPanel test_in_auto__parent_panel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    // 라디오 버튼 
    JRadioButton srqkind_radio_on;
    
    

    /**
     * Create the dialog.
     */
    public ToolSRQKIND(final Frame parent) {

        super(parent, true);
        setTitle("SRQKIND");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = MainDual.main_frm_d.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width - (frameSize.width / 2)) / 2, (screenSize.height - (frameSize.height / 2)) / 2, 251, 138);
        getContentPane().setLayout(new BorderLayout());
        test_in_auto__parent_panel.setBackground(Color.WHITE);
        test_in_auto__parent_panel.setForeground(Color.RED);
        test_in_auto__parent_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(test_in_auto__parent_panel, BorderLayout.CENTER);
        test_in_auto__parent_panel.setLayout(null);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBounds(8, 46, 218, 35);
            test_in_auto__parent_panel.add(buttonPane);
            buttonPane.setLayout(null);
            {
                JButton srqkind_bt_ok = new GradientButton("OK");
                srqkind_bt_ok.setFont(new Font("Tahoma", Font.BOLD, 12));
                srqkind_bt_ok.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 라디오 선택
                        CommonUtil cu = new CommonUtil();
                        if (srqkind_radio_on.isSelected()) {
                            MainDual.srqkind = "ON";
                            // MainDual.main_srqkind_text.setText("ON");
                            cu.FileNew(PathProperties.local_Header, "srqkind.dat", "ON", false);

                            // 아이콘 크기 수정
                            ImageIcon onImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/on.png"));
                            Image onImage = onImageIcon.getImage();
                            Image onImageNew = onImage.getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH);
                            MainDual.srqImageIcon = new ImageIcon(onImageNew);
                            MainDual.on_off_img.setIcon(MainDual.srqImageIcon);

                        } else {
                            MainDual.srqkind = "OFF";
                            // MainDual.main_srqkind_text.setText("OFF");
                            cu.FileNew(PathProperties.local_Header, "srqkind.dat", "OFF", false);

                            ImageIcon offImageIcon = new ImageIcon(MainDual.class.getResource("/kr/co/famos/not/control/ftp/img/off.png"));
                            Image offImage = offImageIcon.getImage();
                            Image offImageNew = offImage.getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH);
                            MainDual.srqImageIcon = new ImageIcon(offImageNew);
                            MainDual.on_off_img.setIcon(MainDual.srqImageIcon);
                        }
                        dispose();
                        return;
                    }
                });
                srqkind_bt_ok.setBounds(27, 5, 53, 27);
                srqkind_bt_ok.setActionCommand("OK");
                buttonPane.add(srqkind_bt_ok);
                getRootPane().setDefaultButton(srqkind_bt_ok);
            }

            {
                JButton srqkind_bt_exit = new GradientButton("EXIT");
                srqkind_bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
                srqkind_bt_exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        return;
                    }
                });
                srqkind_bt_exit.setBounds(107, 5, 77, 27);
                srqkind_bt_exit.setActionCommand("Cancel");
                buttonPane.add(srqkind_bt_exit);
            }
        }
        
        JLabel srqkind_lb = new JLabel("SRQKIND");
        srqkind_lb.setFont(new Font("Tahoma", Font.BOLD, 15));
        srqkind_lb.setBounds(8, 7, 80, 27);
        test_in_auto__parent_panel.add(srqkind_lb);
        
        srqkind_radio_on = new JRadioButton("ON");
        srqkind_radio_on.setBackground(Color.WHITE);
        srqkind_radio_on.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonGroup.add(srqkind_radio_on);
        srqkind_radio_on.setBounds(100, 7, 58, 27);
        test_in_auto__parent_panel.add(srqkind_radio_on);
        
        JRadioButton srqkind_radio_off = new JRadioButton("OFF");
        srqkind_radio_off.setBackground(Color.WHITE);
        srqkind_radio_off.setFont(new Font("Tahoma", Font.BOLD, 15));
        buttonGroup.add(srqkind_radio_off);
        srqkind_radio_off.setBounds(168, 7, 58, 27);
        test_in_auto__parent_panel.add(srqkind_radio_off);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(8, 40, 211, 4);
        test_in_auto__parent_panel.add(separator);
        
        if (MainDual.srqkind.equals("ON")) {
            srqkind_radio_on.setSelected(true);
        } else {
            srqkind_radio_off.setSelected(true);
        }
    }
}
