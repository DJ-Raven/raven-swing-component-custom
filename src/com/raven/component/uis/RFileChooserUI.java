package com.raven.component.uis;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalFileChooserUI;

/**
 *
 * @author Raven
 */
public class RFileChooserUI extends MetalFileChooserUI {

    public static ComponentUI createUI(JComponent c) {
        return new RFileChooserUI((JFileChooser) c);
    }

    public RFileChooserUI(JFileChooser jfc) {
        super(jfc);
    }
}
