package com.raven.components;

import com.raven.component.uis.RPanelUI;
import javax.swing.JPanel;

/**
 *
 * @author Raven
 */
public class RPanel extends JPanel {

    public RPanel() {
        setUI(new RPanelUI());
    }
}
