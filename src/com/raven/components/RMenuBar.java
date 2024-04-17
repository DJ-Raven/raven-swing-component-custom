package com.raven.components;

import com.raven.component.uis.RMenuBarUI;
import javax.swing.JMenuBar;

/**
 *
 * @author Raven
 */
public class RMenuBar extends JMenuBar {

    public RMenuBar() {
        setUI(new RMenuBarUI());
    }
}
