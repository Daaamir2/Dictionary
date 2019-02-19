package ru.itpark.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.repository.DictionaryRepository;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DictionaryApplication.class, args);
        var repository = context.getBean(DictionaryRepository.class);
        var repository2 = context.getBean(WordRepository.class);

        DictionaryEntity entity = new DictionaryEntity(0, "Числа", Collections.emptyList());
        DictionaryEntity entity1 = new DictionaryEntity(0,"Животные",Collections.emptyList());

        repository.saveAll(List.of(entity, entity1));

        List<WordEntity> word = repository2.saveAll(List.of(
                new WordEntity(0, "One", "[wʌn]", "Один", "4", "",entity),
                new WordEntity(0, "Two", "[tuː]", "Два", "4", "",entity),
                new WordEntity(0, "Three", "[θriː]", "Три", "4", "",entity),
                new WordEntity(0, "Four", "[fɔː]", "Четыре", "4", "",entity),
                new WordEntity(0, "Five", "[faɪv]", "Пять", "4", "",entity)
        ));

        List<WordEntity> word1 = repository2.saveAll(List.of(
                new WordEntity(0, "Cat", "[kæt]", "Кошка", "9", "10",entity1),
                new WordEntity(0, "Dog", "[dɔg]", "Собака", "9", "10",entity1),
                new WordEntity(0, "Mouse", "[maʊs]", "Мышь", "9", "10",entity1)
        ));
        entity.setWord(word);
        entity1.setWord(word1);
        repository.saveAll(List.of(entity,entity1));
    }
}

