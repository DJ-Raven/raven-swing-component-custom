package com.raven.lnf;

import com.raven.component.uis.RButtonUI;
import com.raven.component.uis.RMenuItemUI;
import com.raven.component.uis.RPopupMenuUI;
import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Raven
 */
public class RavenLookAndFeel extends MetalLookAndFeel {

    @Override
    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        Object[] uiDefaults = {
            "ButtonUI", RButtonUI.class.getCanonicalName(),
            "PopupMenuUI", RPopupMenuUI.class.getCanonicalName(),
            "MenuItemUI", RMenuItemUI.class.getCanonicalName(),};
        table.putDefaults(uiDefaults);
    }

    @Override
    public String getName() {
        return "1";
    }

    @Override
    public String getID() {
        return "1";
    }

    @Override
    public String getDescription() {
        return "1";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
}
