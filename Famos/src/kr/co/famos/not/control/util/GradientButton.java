package kr.co.famos.not.control.util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * GradientButton.java
 * @author JDhilsukh
 *
 */
public class GradientButton extends JButton {

    private Color startColor = new Color(20, 86 , 148);
    private Color endColor = new Color(20, 86, 148);
    private Color rollOverColor = new Color(20, 86, 148);
    private Color pressedColor = new Color(148, 148, 148);
    private static final int HORIZONTAL =0;
    private static final int VERTICAL =1;
    private int direction = VERTICAL;
    private int outerRoundRectSize = 10;
    private int innerRoundRectSize = 8;
    private GradientPaint GP;

    
    
    
    public GradientButton(String text) {
        super();
        setText(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("Thoma", Font.BOLD, 12));
        setForeground(Color.BLACK);
        setFocusable(false);

    }

    public GradientButton(Color startColor, Color endColor,
            Color rollOverColor, Color pressedColor,int adirection) {
        super();
        this.startColor = startColor;
        this.endColor = endColor;
        this.rollOverColor = rollOverColor;
        this.pressedColor = pressedColor;
        direction =adirection;
        setForeground(Color.BLACK);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int h = getHeight();
        int w = getWidth();
        ButtonModel model = getModel();
        if (!model.isEnabled()) {
            setForeground(Color.GRAY);
            GP = new GradientPaint(0, 0, new Color(192,192,192), 0, h, new Color(192,192,192),
                    true);
        }else{
            setForeground(Color.WHITE);
            if (model.isRollover()) {
                if(direction==VERTICAL){
                GP = new GradientPaint(0, 0, startColor, 0, h, rollOverColor,
                        true);
                }else{
                GP = new GradientPaint(0, 0, startColor, w, 0, rollOverColor,
                            true);
                }
            } else {
                if(direction==VERTICAL){
                    GP = new GradientPaint(0, 0, startColor, 0, h, endColor,
                            true);
                    }else{
                    GP = new GradientPaint(0, 0, startColor, w, 0, endColor,
                                true);
            }   }
        }
        g2d.setPaint(GP);
        GradientPaint p1;
        GradientPaint p2;
        if (getModel().isPressed()) {
            
            if(direction==VERTICAL){
                GP = new GradientPaint(0, 0, startColor, 0, h, pressedColor,
                        true);
                }else{
                GP = new GradientPaint(0, 0, startColor, w, 0, pressedColor,
                            true);
        }   g2d.setPaint(GP);
            
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
                    new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                    new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
                    new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
                    h - 3, new Color(0, 0, 0, 50));
            GP = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
        }
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, outerRoundRectSize, outerRoundRectSize);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);
        g2d.fillRect(0, 0, w, h);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
                outerRoundRectSize);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
                innerRoundRectSize);
        g2d.dispose();

        super.paintComponent(g);
    }

    /**
     *  This method sets the Actual Background Color of the Button
     */
    public void setStartColor(Color color) {
        startColor = color;
    }

    /**
     *  This method sets the Pressed Color of the Button
     */
    public void setEndColor(Color pressedColor) {
        endColor = pressedColor;
    }

    /**
     * @return  Starting Color of the Button
     */
    public Color getStartColor() {
        return startColor;
    }

    /**
     * @return  Ending Color of the Button
     */
    public Color getEndColor() {
        return endColor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public JPanel getButtonsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        GradientButton standardButton = new GradientButton("Standard Button");
        standardButton.setPreferredSize(new Dimension(130, 28));
        standardButton.setDirection(GradientButton.VERTICAL);
        
        GradientButton rollOverButton = new GradientButton("RollOver Button");
        rollOverButton.setPreferredSize(new Dimension(130, 28));
        rollOverButton.setDirection(GradientButton.VERTICAL);
        
        GradientButton disabledButton = new GradientButton("Disable Button");
        disabledButton.setPreferredSize(new Dimension(130, 28));
        disabledButton.setEnabled(false);
        disabledButton.setDirection(GradientButton.HORIZONTAL);
        
        GradientButton pressedButton = new GradientButton("Pressed Button");
        pressedButton.setPreferredSize(new Dimension(130, 28));
        pressedButton.setDirection(GradientButton.VERTICAL);
        
        panel.add(standardButton);
        panel.add(rollOverButton);
        panel.add(disabledButton);
        panel.add(pressedButton);
        return panel;
    
    }
}