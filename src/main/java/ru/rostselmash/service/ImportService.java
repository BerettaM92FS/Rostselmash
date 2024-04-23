package ru.rostselmash.service;

import ru.rostselmash.domain.User;

import java.io.File;
import java.util.List;

public interface ImportService {
    List<User> importData(File file);
}
