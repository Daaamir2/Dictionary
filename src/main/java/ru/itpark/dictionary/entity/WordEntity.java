package ru.itpark.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String word = "Word";
    private String transcription;
    private String translation;
    private String img;
    private String reminder;

    @ManyToOne(fetch = FetchType.LAZY)
    private DictionaryEntity dictionaryEntity;


}
