package com.raven.component.uis;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.text.Element;
import javax.swing.text.PasswordView;
import javax.swing.text.View;

/**
 *
 * @author Raven
 */
public class RPasswordFieldUI extends RTextFieldUI {

    @Override
    protected String getPropertyPrefix() {
        return "PasswordField";
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        String prefix = getPropertyPrefix();
        Character echoChar = (Character) UIManager.getDefaults().get(prefix + ".echoChar");
        if (echoChar != null) {
            LookAndFeel.installProperty(getComponent(), "echoChar", echoChar);
        }
    }

    @Override
    public View create(Element elem) {
        return new PasswordView(elem);
    }
}
