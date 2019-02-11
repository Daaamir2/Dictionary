package ru.itpark.dictionary.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.exception.WordNotFoundException;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.List;

@Service
public class WordService {
    private final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public List<WordEntity> findAll (){
        return repository.findAll();
    }

    public WordEntity findById(int id){
        return repository.findById(id)
                .orElseThrow(WordNotFoundException::new);
    }

    public List<WordEntity> findByWord (String word){
        return repository.findAllByWordIgnoreCase(word);
    }

    public void removeById(int id){
        repository.deleteById(id);
    }

    public void save(WordEntity entity){
        repository.save(entity);

    }

}
