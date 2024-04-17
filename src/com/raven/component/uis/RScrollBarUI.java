package com.raven.component.uis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author Raven
 */
public class RScrollBarUI extends BasicScrollBarUI {

    public RScrollBarUI() {

    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {

    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(50, 50);
    }

    @Override
    protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = rctngl.x;
        int y = rctngl.y;
        int width = rctngl.width;
        int height = rctngl.height;
        int round;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            y += 8;
            height -= 16;
            round = width;
        } else {
            x += 8;
            width -= 16;
            round = height;
        }
        if (isDragging) {
            g2.setColor(new Color(150, 150, 150));
        } else {
            if (isThumbRollover()) {
                g2.setColor(new Color(180, 180, 180));
            } else {
                g2.setColor(new Color(200, 200, 200));
            }
        }
        g2.fillRoundRect(x, y, width, height, round, round);
        g2.dispose();
    }

    private class ScrollBarButton extends JButton {

        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void paint(Graphics grphcs) {
        }
    }
}
