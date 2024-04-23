package ru.rostselmash.ui.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ButtonPanel extends JPanel {

    private final JButton add = new JButton("Добавить");
    private final JButton del = new JButton("Удалить");
    private final JButton excelExport = new JButton("Экспорт Excel");
    private final JButton txtExport = new JButton("Экспорт TXT");
    private final JButton excelImport = new JButton("Импорт Excel");
    private final JButton txtImport = new JButton("Импорт ТХТ");

    public ButtonPanel() {
        GridLayout gridLayout = new GridLayout(2, 3, 10, 10);
        setLayout(gridLayout);
        add(add);
        add(del);
        add(excelExport);
        add(txtExport);
        add(excelImport);
        add(txtImport);
        setVisible(true);
    }
}
