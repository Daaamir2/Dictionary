package ru.itpark.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDto {
    private int id;
    private String word = "Word";
    private String transcription;
    private String translation;
    private String img;
    private String reminder;
}
