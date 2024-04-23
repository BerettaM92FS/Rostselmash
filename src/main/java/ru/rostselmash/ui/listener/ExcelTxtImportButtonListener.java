package ru.rostselmash.ui.listener;

import lombok.AllArgsConstructor;
import ru.rostselmash.domain.DocumentType;
import ru.rostselmash.domain.User;
import ru.rostselmash.service.ImportService;
import ru.rostselmash.service.impl.ExcelImportServiceImpl;
import ru.rostselmash.service.impl.TxtImportServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

@AllArgsConstructor
public class ExcelTxtImportButtonListener implements ActionListener {

    private DefaultTableModel model;
    private DocumentType type;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(null);
        if(option == JFileChooser.OPEN_DIALOG) {
            File file = chooser.getSelectedFile();

            ImportService service;
            List<User> users;

            if (type == DocumentType.EXCEL) {
                service = new ExcelImportServiceImpl();
            } else {
                service = new TxtImportServiceImpl();
            }

            users = service.importData(file);
            model.setRowCount(0);

            for (User user : users) {
                model.addRow(new Object[]{user.getFirstName(), user.getLastName()});
            }
        }
    }

}
