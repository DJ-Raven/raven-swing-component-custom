package com.raven.component.uis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 *
 * @author Raven
 */
public class RPanelUI extends BasicPanelUI {

    public static ComponentUI createUI(JComponent c) {
        return new RPanelUI();
    }
    // private RShadowRender rShadowRender;

    public RPanelUI() {
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
                //    rShadowRender.updateUI();
                c.repaint();
            }
        });
        //   rShadowRender = new RShadowRender(c);
        //  c.setOpaque(false);
        c.setBackground(new Color(250, 250, 250));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        //   rShadowRender.paint(g);
        super.paint(g, c);
    }
}
