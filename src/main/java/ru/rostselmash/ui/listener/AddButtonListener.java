package ru.rostselmash.ui.listener;

import lombok.AllArgsConstructor;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class AddButtonListener implements ActionListener {

    private DefaultTableModel model;

    @Override
    public void actionPerformed(ActionEvent e) {
        model.addRow(new Object[]{"",""});
    }
}
