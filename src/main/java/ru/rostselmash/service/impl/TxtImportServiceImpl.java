package ru.rostselmash.service.impl;

import ru.rostselmash.domain.User;
import ru.rostselmash.service.ImportService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtImportServiceImpl implements ImportService {

    @Override
    public List<User> importData(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            List<User> users = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                users.add(new User(words[0], words[1]));
            }

            return users;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
