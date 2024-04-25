package ru.rostselmash.service.impl;

import ru.rostselmash.domain.User;
import ru.rostselmash.service.ExportService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TxtExportServiceImpl implements ExportService {

    @Override
    public FileOutputStream export(List<User> users, String path) {
        try (FileOutputStream fos = new FileOutputStream(path, true);
             Writer writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.write("\n");
            }
            writer.flush();
            fos.flush();
            return fos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
