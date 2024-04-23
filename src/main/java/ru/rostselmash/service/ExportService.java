package ru.rostselmash.service;

import ru.rostselmash.domain.User;

import java.io.FileOutputStream;
import java.util.List;

public interface ExportService {
    FileOutputStream export(List<User> users, String path);
}
