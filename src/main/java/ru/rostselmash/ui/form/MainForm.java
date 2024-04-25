package ru.rostselmash.ui.form;

import ru.rostselmash.domain.DocumentType;
import ru.rostselmash.ui.listener.*;
import ru.rostselmash.ui.panel.ButtonPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MainForm extends JFrame {
    private ButtonPanel buttonPanel;
    private Container container;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    public MainForm() {
        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        String[] columnNames = {"Имя", "Фамилия"};
        Object[][] rowData = {
                {"Ram", "Kumar"},
                {"Mohan", "Nath"},
                {"Rita", "Singh"},
                {"Anita", "Sharma"},
                {"Chris", "Redfield"},
                {"Jill", "Valentine"}
        };

        model = new DefaultTableModel(rowData, columnNames);
        table = new JTable(model);
        table.setRowHeight(50);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
        renderer.setHorizontalAlignment(JLabel.CENTER);

        scroll = new JScrollPane(table);
        container.add(scroll);

        buttonPanel = new ButtonPanel();
        buttonPanel.getAdd().addActionListener(new AddButtonListener(model));
        buttonPanel.getDel().addActionListener(new DelButtonListener(model, table));

        buttonPanel.getExcelExport().addActionListener(new ExcelTxtExportButtonListener(model, table, DocumentType.EXCEL));
        buttonPanel.getTxtExport().addActionListener(new ExcelTxtExportButtonListener(model, table, DocumentType.TXT));
        buttonPanel.getExcelImport().addActionListener(new ExcelTxtImportButtonListener(model, DocumentType.EXCEL));
        buttonPanel.getTxtImport().addActionListener(new ExcelTxtImportButtonListener(model, DocumentType.TXT));
        buttonPanel.getUp().addActionListener(new UpDownButtonListener(model, table, true));
        buttonPanel.getDown().addActionListener(new UpDownButtonListener(model, table, false));
        container.add(buttonPanel, BorderLayout.SOUTH);
        setTitle("Пользователи");
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
