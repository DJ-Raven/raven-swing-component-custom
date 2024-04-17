package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RShadowRender;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author Raven
 */
public class RMenuBarUI extends BasicMenuBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new RMenuBarUI();
    }
    private RShadowRender rShadowRender;

    public RMenuBarUI() {

    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        init(c);
    }

    private void init(JComponent c) {
        c.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rShadowRender.updateUI();
                c.repaint();
            }
        });
        rShadowRender = new RShadowRender(c);
        c.setOpaque(false);
        c.setBackground(Color.WHITE);
    }

    @Override
    public void update(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(250, 250, 250));
        Area area = new Area(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), RDefaultUI.ROUND, RDefaultUI.ROUND));
        area.add(new Area(new Rectangle(0, RDefaultUI.ROUND, c.getWidth(), c.getHeight() - RDefaultUI.ROUND)));
        g2.fill(area);
        rShadowRender.paint(g2);
        g2.dispose();
    }
}
