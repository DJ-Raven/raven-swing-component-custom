package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RRippleEffect;
import com.raven.components.utils.RShadowRender;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Raven
 */
public class RButtonUI extends BasicButtonUI {

    public static ComponentUI createUI(JComponent c) {
        return new RButtonUI();
    }

    private RRippleEffect rRippleEffect;
    private RShadowRender rShadowRender;

    public RButtonUI() {
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
        rRippleEffect = new RRippleEffect(c);
        rShadowRender = new RShadowRender(c);
        rRippleEffect.setRippleColor(new Color(220, 220, 220));
        c.setOpaque(false);
        c.setBackground(new Color(255, 255, 255));
        c.setForeground(RDefaultUI.FORGROUND);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        rShadowRender.paint(g);
        super.paint(g, c);
        rRippleEffect.reder(g, rShadowRender.getShape());
    }
}
