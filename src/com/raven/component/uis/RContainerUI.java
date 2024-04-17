package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 *
 * @author Raven
 */
public class RContainerUI extends BasicPanelUI {

    public RContainerUI() {
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(250, 250, 250));
        g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), RDefaultUI.ROUND, RDefaultUI.ROUND));
        g2.dispose();
        super.paint(g, c);
    }

}
