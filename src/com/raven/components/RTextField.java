package com.raven.components;

import com.raven.component.uis.RTextFieldUI;
import javax.swing.JTextField;

/**
 *
 * @author Raven
 */
public class RTextField extends JTextField {

    public RTextField() {
        setUI(new RTextFieldUI());
    }
}
