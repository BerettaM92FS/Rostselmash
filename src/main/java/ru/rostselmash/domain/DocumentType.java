package ru.rostselmash.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType {
    EXCEL(".xlsx"),
    TXT(".txt");

    private final String type;
}
