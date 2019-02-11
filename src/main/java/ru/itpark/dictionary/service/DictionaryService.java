package ru.itpark.dictionary.service;

import org.springframework.stereotype.Service;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.exception.DictionaryNotFoundException;
import ru.itpark.dictionary.repository.DictionaryRepository;

import java.util.List;

@Service
public class DictionaryService {
    private final DictionaryRepository repository;

    public DictionaryService(DictionaryRepository repository) {
        this.repository = repository;
    }

    public List<DictionaryEntity> findAll(){
        return repository.findAll();
    }

    public DictionaryEntity findById(int id){
        return repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new);
    }

    public void removeById(int id){
        repository.deleteById(id);
    }

    public void save(DictionaryEntity entity){
        repository.save(entity);
    }
}
