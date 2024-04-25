package ru.rostselmash.ui.listener;

import ru.rostselmash.domain.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class UsersTableValue {

    protected List<User> getTableValue(DefaultTableModel model, JTable table) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            int row = table.convertRowIndexToModel(i);
            String name = String.valueOf(model.getValueAt(row, 0));
            String lastName = String.valueOf(model.getValueAt(row, 1));
            if(!name.equals("") || (!lastName.equals(""))) {
                users.add(new User(name, lastName));
            }
        }
        return users;
    }
}
