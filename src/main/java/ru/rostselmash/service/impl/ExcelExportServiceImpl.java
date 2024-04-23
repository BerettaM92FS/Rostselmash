package ru.rostselmash.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.rostselmash.domain.User;
import ru.rostselmash.service.ExportService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportServiceImpl implements ExportService {

    @Override
    public FileOutputStream export(List<User> users, String path) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(path)) {
            XSSFSheet sheet = workbook.createSheet("report");
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);

            XSSFFont headFont = workbook.createFont();
            settingFont(headFont, true);

            XSSFFont tableFont = workbook.createFont();
            settingFont(tableFont, false);

            XSSFCellStyle headCellStyle = workbook.createCellStyle();
            settingCellStyle(headCellStyle, headFont);

            XSSFCellStyle tableCellStyle = workbook.createCellStyle();
            settingCellStyle(tableCellStyle, tableFont);

            Row headRow = sheet.createRow(0);
            headRow.setHeight((short) 400);

            Cell headName = headRow.createCell(0);
            headName.setCellValue("Имя");
            headName.setCellStyle(headCellStyle);

            Cell headLastName = headRow.createCell(1);
            headLastName.setCellValue("Фамилия");
            headLastName.setCellStyle(headCellStyle);

            insertData(users, sheet, tableCellStyle);
            workbook.write(fos);
            return fos;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void settingFont(XSSFFont font, boolean bold) {
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 12);
        if (bold) {
            font.setBold(true);
        }
    }

    private void settingCellStyle(XSSFCellStyle cs, XSSFFont font) {
        cs.setWrapText(true);
        cs.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setFont(font);
    }

    private void insertData(List<User> users, XSSFSheet sheet, XSSFCellStyle cs) {
        int startRow = 1;

        for (User user : users) {
            Row row = sheet.createRow(startRow++);
            row.setHeight((short) 400);

            Cell cellName = row.createCell(0);
            cellName.setCellValue(user.getFirstName());
            cellName.setCellStyle(cs);

            Cell cellLastName = row.createCell(1);
            cellLastName.setCellValue(user.getLastName());
            cellLastName.setCellStyle(cs);
        }
    }
}
