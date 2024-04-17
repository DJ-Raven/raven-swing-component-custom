package com.raven.components;

import com.raven.component.uis.RPopupMenuUI;
import javax.swing.JPopupMenu;

/**
 *
 * @author Raven
 */
public class RPopupMenu extends JPopupMenu {

    public RPopupMenu() {
        setUI(new RPopupMenuUI());
    }
}
