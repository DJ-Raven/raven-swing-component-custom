package com.raven.components;

import com.raven.component.uis.RPasswordFieldUI;
import javax.swing.JPasswordField;

/**
 *
 * @author Raven
 */
public class RPasswordField extends JPasswordField {

    public RPasswordField() {
        setUI(new RPasswordFieldUI());
    }
}
