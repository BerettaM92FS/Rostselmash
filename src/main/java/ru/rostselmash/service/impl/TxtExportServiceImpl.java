package ru.rostselmash.service.impl;

import ru.rostselmash.domain.User;
import ru.rostselmash.service.ExportService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TxtExportServiceImpl implements ExportService {

    @Override
    public FileOutputStream export(List<User> users, String path) {
        try (FileOutputStream fos = new FileOutputStream(path, true)) {
            for (User user : users) {
                fos.write(user.toString().getBytes());
                fos.write("\n".getBytes());
            }
            fos.flush();
            return fos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
