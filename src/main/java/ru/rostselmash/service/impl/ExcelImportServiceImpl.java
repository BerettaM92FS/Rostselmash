package ru.rostselmash.service.impl;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.rostselmash.domain.User;
import ru.rostselmash.service.ImportService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportServiceImpl implements ImportService {
    @Override
    public List<User> importData(File file) {
        try(XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet(workbook.getSheetName(0));
            List<User> users = new ArrayList<>();
            for (int i = 0; i != sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i + 1);
                String name = row.getCell(0).getStringCellValue();
                String lastName = row.getCell(1).getStringCellValue();
                users.add(new User(name, lastName));
            }
            return users;
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
