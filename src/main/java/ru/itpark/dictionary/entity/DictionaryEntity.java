package ru.itpark.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "Dictionary")
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private List<WordEntity> word;
}
