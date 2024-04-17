package com.raven.component.uis;

import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RShadowRender;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

/**
 *
 * @author Raven
 */
public class RPopupMenuUI extends BasicPopupMenuUI {

    public static ComponentUI createUI(JComponent c) {
        return new RPopupMenuUI();
    }

    private RShadowRender rShadowRender;

    public RPopupMenuUI() {
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
        if (1 == 2) {
            c.addContainerListener(new ContainerAdapter() {
                @Override
                public void componentAdded(ContainerEvent e) {
                    if (e.getChild() instanceof JMenu) {
                        JMenu item = (JMenu) e.getChild();
                        item.setUI(new RMenuUI());
                    } else if (e.getChild() instanceof JCheckBoxMenuItem) {
                    } else if (e.getChild() instanceof JRadioButtonMenuItem) {
                    } else if (e.getChild() instanceof JMenuItem) {
                        JMenuItem item = (JMenuItem) e.getChild();
                        item.setUI(new RMenuItemUI());
                    }
                }
            });
        }

        rShadowRender = new RShadowRender(c);
        c.setOpaque(false);
        c.setForeground(RDefaultUI.FORGROUND);
        c.setBackground(Color.WHITE);
        c.setOpaque(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        rShadowRender.paint(g);
    }
}
