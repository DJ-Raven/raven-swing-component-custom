package com.raven.components;

import com.raven.component.uis.RComboBoxUI;
import javax.swing.JComboBox;

public class RComboBox<E> extends JComboBox<E> {

    public RComboBox() {
        setUI(new RComboBoxUI());
    }
}
