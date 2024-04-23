package ru.rostselmash.ui.listener;

import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class DelButtonListener implements ActionListener {

    private DefaultTableModel model;
    private JTable table;

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getSelectedRow());
        model.removeRow(row);
    }
}
