package ru.itpark.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.dictionary.entity.DictionaryEntity;

public interface DictionaryRepository extends JpaRepository <DictionaryEntity, Integer> {


}
