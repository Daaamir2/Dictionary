package ru.itpark.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.dictionary.entity.WordEntity;

import java.util.List;

public interface WordRepository extends JpaRepository <WordEntity, Integer> {
    List<WordEntity> findAllByWordIgnoreCase (String word);
}
