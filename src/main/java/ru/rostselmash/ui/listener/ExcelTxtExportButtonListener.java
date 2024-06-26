package ru.rostselmash.ui.listener;

import lombok.AllArgsConstructor;
import ru.rostselmash.domain.DocumentType;
import ru.rostselmash.domain.User;
import ru.rostselmash.service.ExportService;
import ru.rostselmash.service.impl.ExcelExportServiceImpl;
import ru.rostselmash.service.impl.TxtExportServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class ExcelTxtExportButtonListener extends UsersTableValue implements ActionListener {
    private DefaultTableModel model;
    private JTable table;
    private DocumentType type;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(null);
        if(option == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getName();
            String path = chooser.getSelectedFile().getParentFile().getPath();
            String file = path + "\\" + name + type.getType();
            try {
                ExportService service;
                if (type==DocumentType.EXCEL) {
                    service = new ExcelExportServiceImpl();
                } else {
                    service = new TxtExportServiceImpl();
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.close();
                }
                service.export(getTableValue(model, table), file).close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
