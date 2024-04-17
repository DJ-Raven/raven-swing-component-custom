package com.raven.components.utils;

import com.raven.components.utils.shadow.ShadowRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Raven
 */
public class RShadowRender {

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        component.repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        component.repaint();
    }

    private BufferedImage imageShadow;
    private int round = RDefaultUI.ROUND;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    private Color shadowColor = new Color(200, 200, 200);
    private final Component component;

    public RShadowRender(JComponent component) {
        this.component = component;
        component.setBorder(new EmptyBorder(10, 12, 15, 12));
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(imageShadow, (int) 0, 0, null);
        g2.setColor(component.getBackground());
        g2.fill(getShape());
        g2.dispose();
    }

    private void createImageShadow() {
        int width = component.getWidth();
        int height = component.getHeight();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            g2.drawImage(createShadow(), 0, 0, null);
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = component.getWidth() - (shadowSize.left + shadowSize.right);
        int height = component.getHeight() - (shadowSize.top + shadowSize.bottom);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
        g2.dispose();
        return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
    }

    public Shape getShape() {
        double width = component.getWidth() - (shadowSize.left + shadowSize.right);
        double height = component.getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        return new RoundRectangle2D.Double(x, y, width, height, round, round);
    }

    public Shape getShape(float border) {
        double width = component.getWidth() - (shadowSize.left + shadowSize.right + border * 2f);
        double height = component.getHeight() - (shadowSize.top + shadowSize.bottom + border * 2f);
        double x = shadowSize.left + border;
        double y = shadowSize.top + border;
        return new RoundRectangle2D.Double(x, y, width, height, round, round);
    }

    public void updateUI() {
        createImageShadow();
    }
}
