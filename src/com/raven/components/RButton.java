package com.raven.components;

import com.raven.component.uis.RButtonUI;
import javax.swing.JButton;

/**
 *
 * @author Raven
 */
public class RButton extends JButton {

    public RButton() {
       setUI(new RButtonUI());
    }
}
