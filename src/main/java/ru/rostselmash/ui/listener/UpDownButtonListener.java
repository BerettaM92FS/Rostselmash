package ru.rostselmash.ui.listener;

import lombok.AllArgsConstructor;
import ru.rostselmash.domain.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UpDownButtonListener extends UsersTableValue implements ActionListener {

    private DefaultTableModel model;
    private JTable table;
    private boolean symbol;

    @Override
    public void actionPerformed(ActionEvent e) {
        int tRow = table.getSelectedRow();
        int mRow = table.convertRowIndexToModel(table.getSelectedRow());
        if (tRow == mRow) {
            moveRow(tRow);
        } else {
            List<User> users = getTableValue(model, table);
            model.setRowCount(0);
            for (User user : users) {
                model.addRow(new Object[] {user.getFirstName(), user.getLastName()});
            }
            moveRow(tRow);
        }
    }

    private void moveRow(int row) {
        if (symbol) {
            model.moveRow(row, row, row - 1);
            table.setRowSelectionInterval(row - 1, row - 1);
        } else {
            model.moveRow(row, row, row + 1);
            table.setRowSelectionInterval(row + 1, row + 1);
        }
    }
}
