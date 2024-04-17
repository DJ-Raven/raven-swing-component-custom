package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RRippleEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

/**
 *
 * @author Raven
 */
public class RMenuUI extends BasicMenuUI {

    public static ComponentUI createUI(JComponent c) {
        return new RMenuUI();
    }
    private RRippleEffect rRippleEffect;

    public RMenuUI() {
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        init((JMenu) c);
    }

    private void init(JMenu c) {
        rRippleEffect = new RRippleEffect(c);
        c.setOpaque(false);
        c.setBackground(new Color(102, 102, 0));
        c.setForeground(RDefaultUI.FORGROUND);
        c.setBorder(new EmptyBorder(5, 3, 5, 3));
    }

    @Override
    protected MouseInputListener createMouseInputListener(JComponent c) {
        return new BasicMenuUI.MouseInputHandler() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                rollover(true, e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                rollover(false, e);

            }

            private void rollover(boolean rollover, MouseEvent e) {
                JMenu menu = (JMenu) e.getSource();
                menu.getModel().setRollover(rollover);
                menu.repaint();
            }
        };
    }

    @Override
    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0, 0, menuItem.getWidth(), menuItem.getHeight(), RDefaultUI.ROUND, RDefaultUI.ROUND);
        if (menuItem.getModel().isRollover()) {
            g2.setColor(RDefaultUI.SELECTTION_BACKGROUND);
            g2.fill(shape);
        }
        rRippleEffect.reder(g2, shape);
    }

    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        if (menuItem.getModel().isRollover()) {
            g.setColor(Color.WHITE);
        }
        super.paintText(g, menuItem, textRect, text);
    }

}
