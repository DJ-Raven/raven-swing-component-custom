package com.raven.component.uis;

import com.raven.components.utils.RComboBoxCellRender;
import com.raven.components.utils.RDefaultUI;
import com.raven.components.utils.RRippleEffect;
import com.raven.components.utils.RShadowRender;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

/**
 *
 * @author Raven
 */
public class RComboBoxUI extends BasicComboBoxUI {

    public static ComponentUI createUI(JComponent c) {
        return new RComboBoxUI();
    }

    private RRippleEffect rRippleEffect;
    private RShadowRender rShadowRender;

    public RComboBoxUI() {
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
        c.setBorder(new EmptyBorder(5, 4, 10, 4));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        rShadowRender.paint(g);
        super.paint(g, c);
        rRippleEffect.reder(g, rShadowRender.getShape());
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {

    }

    @Override
    protected JButton createArrowButton() {
        return new ArrowButton();
    }

    @Override
    protected ComboPopup createPopup() {
        return new ComboPopupCustom(comboBox);
    }

    @Override
    protected ComboBoxEditor createEditor() {
        ComboBoxEditor edit = super.createEditor();
        JTextField com = (JTextField) edit.getEditorComponent();
        com.setBackground(new Color(0, 0, 0, 0));
        com.setForeground(RDefaultUI.FORGROUND);
        com.setSelectedTextColor(Color.WHITE);
        com.setSelectionColor(RDefaultUI.SELECTTION_BACKGROUND);
        com.setBorder(new EmptyBorder(0, 8, 0, 0));
        return edit;
    }

    @Override
    protected ListCellRenderer createRenderer() {
        return new RComboBoxCellRender();
    }

    private class ComboPopupCustom extends BasicComboPopup {

        public int getRound() {
            return rShadowRender.getRound();
        }

        public void setRound(int round) {
            rShadowRender.setRound(round);
        }

        public Color getShadowColor() {
            return rShadowRender.getShadowColor();
        }

        public void setShadowColor(Color shadowColor) {
            rShadowRender.setShadowColor(shadowColor);
        }

        public ComboPopupCustom(JComboBox jcb) {
            super(jcb);
            setUI(new RPopupMenuUI());
        }

        @Override
        public void setBounds(int x, int y, int width, int height) {
            super.setBounds(x, y, width, height);
            rShadowRender.updateUI();
        }

        @Override
        protected JScrollPane createScroller() {
            JScrollPane scroll = new JScrollPane(list);
            changeUIScroll(scroll.getVerticalScrollBar());
            changeUIScroll(scroll.getHorizontalScrollBar());
            list.setBackground(new Color(255, 255, 255));
            scroll.setViewportBorder(new EmptyBorder(0, 0, 3, 3));
            return scroll;
        }

        private void changeUIScroll(JScrollBar scroll) {
            scroll.setUI(new RScrollBarUI());
            scroll.setForeground(new Color(200, 200, 200));
            scroll.setBackground(Color.WHITE);
            scroll.setPreferredSize(new Dimension(8, 8));
        }
    }

    private class ArrowButton extends JButton {

        public ArrowButton() {
            setBorder(new EmptyBorder(10, 6, 10, 6));
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            double size = Math.min(width, height) * 0.35f;
            double x = (width - size) / 2;
            double y = (height - size) / 2;
            Path2D p = new Path2D.Double();
            p.moveTo(x, y);
            p.lineTo(x + size, y);
            p.lineTo(x + size / 2, y + size);
            if (isPopupVisible(comboBox)) {
                g2.setColor(new Color(200, 200, 200));
            } else {
                g2.setColor(new Color(150, 150, 150));
            }
            g2.fill(p);
            g2.dispose();
        }
    }
}
