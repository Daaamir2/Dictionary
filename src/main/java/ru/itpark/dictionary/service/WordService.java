package ru.itpark.dictionary.service;

import org.springframework.stereotype.Service;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.exception.WordNotFoundException;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.List;

@Service
public class WordService {
    private final WordRepository repository;
    private final DictionaryService service;

    public WordService(WordRepository repository, DictionaryService service) {
        this.repository = repository;
        this.service = service;
    }

    public List<WordEntity> findAll (){
        return repository.findAll();
    }

    public WordEntity findById(int id){
        return repository.findById(id)
                .orElseThrow(WordNotFoundException::new);
    }

    public List<WordEntity> findAllByName (String word){
        return repository.findAllByWordIgnoreCase(word);
    }

    public void removeWord(int id){
        repository.deleteById(id);
    }

    public void save(WordEntity entity){
        repository.save(entity);
    }

    public void editWord(WordEntity word, int id){
        WordEntity entity = repository
                .findById(id)
                .orElseThrow();
        entity.setWord(word.getWord());
        entity.setTranscription(word.getTranscription());
        entity.setTranslation(word.getTranslation());
        repository.save(entity);
    }
}
