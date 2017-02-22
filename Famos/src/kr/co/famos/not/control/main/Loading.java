package kr.co.famos.not.control.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * <code>Loading.java</code>
 * 
 * @company : FAMOS 
 * @Description : 로딩 화면 
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.02
 * @version : 1.0
 */

public class Loading extends JDialog {

    private final JPanel loading_panel = new JPanel();

    /**
     * Create the dialog.
     */
    public Loading(Frame parent) {

        super(parent, false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /* 프레임 화면 가운데 */
        Dimension frameSize = parent.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setBounds((screenSize.width - (frameSize.width / 3)) / 2, (screenSize.height - (frameSize.height/3) ) / 2, 254, 193);
        getContentPane().setLayout(new BorderLayout());
        loading_panel.setForeground(Color.RED);
        loading_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        getContentPane().add(loading_panel, BorderLayout.CENTER);
        loading_panel.setLayout(null);

        JLabel loading_lb = new JLabel("");
        loading_lb.setIcon(new ImageIcon(Loading.class.getResource("/kr/co/famos/not/control/ftp/img/loading_img.gif")));
        loading_lb.setBounds(0, 0, 254, 194);
        loading_panel.add(loading_lb);
        setUndecorated(true);
    }

    public static void EXIT_ON_CLOSE(int i) {
        // TODO Auto-generated method stub
        
    }
}
