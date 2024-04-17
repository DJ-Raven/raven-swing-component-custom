package com.raven.components.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Raven
 */
public class RComboBoxCellRender extends DefaultListCellRenderer {

    public RComboBoxCellRender() {
        setOpaque(false);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent com = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (isSelected) {
            com.setBackground(RDefaultUI.SELECTTION_BACKGROUND);
            com.setForeground(Color.WHITE);
        }
        if (index != -1) {
            com.setBorder(new EmptyBorder(6, 5, 6, 5));
        }
        return com;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), RDefaultUI.ROUND, RDefaultUI.ROUND));
        g2.dispose();
        super.paintComponent(g);
    }
}
