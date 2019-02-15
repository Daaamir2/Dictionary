package ru.itpark.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.dictionary.entity.DictionaryEntity;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Integer> {
    List<DictionaryEntity> findAllByNameIgnoreCase(String name);

}
