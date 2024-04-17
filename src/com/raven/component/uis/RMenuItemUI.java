package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RRippleEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 *
 * @author Raven
 */
public class RMenuItemUI extends BasicMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new RMenuItemUI();
    }
    private RRippleEffect rRippleEffect;

    public RMenuItemUI() {
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        init((JMenuItem) c);
    }

    private void init(JMenuItem c) {
        rRippleEffect = new RRippleEffect(c);
        c.setOpaque(false);
        c.setBackground(Color.WHITE);
        c.setForeground(RDefaultUI.FORGROUND);
        c.setBorder(new EmptyBorder(5, 3, 5, 3));
    }

    @Override
    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0, 0, menuItem.getWidth(), menuItem.getHeight(), RDefaultUI.ROUND, RDefaultUI.ROUND);
        if (menuItem.isArmed()) {
            g2.setColor(RDefaultUI.SELECTTION_BACKGROUND);
            g2.fill(shape);
        }
        rRippleEffect.reder(g2, shape);
    }

    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        if (menuItem.isArmed()) {
            g.setColor(Color.WHITE);
        }
        super.paintText(g, menuItem, textRect, text);
    }
}
