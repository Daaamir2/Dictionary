package ru.itpark.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.repository.DictionaryRepository;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DictionaryApplication.class, args);
        var repository = context.getBean(DictionaryRepository.class);
        var repository2 = context.getBean(WordRepository.class);
        List<WordEntity> word = repository2.saveAll(List.of(
                new WordEntity(0, "1", "2", "3", "4", "5"),
                new WordEntity(0, "1", "2", "3", "4", "5"),
                new WordEntity(0, "1", "2", "3", "4", "5")
        ));

        List<WordEntity> word1 = repository2.saveAll(List.of(
                new WordEntity(0, "6", "7", "8", "9", "10")
        ));

        repository.saveAll(List.of(
                new DictionaryEntity(0, "Первый", word)
        ));
        repository.saveAll(List.of(
                new DictionaryEntity(0,"Второй", word1)
        ));


    }
    

}

