package ru.itpark.dictionary.service;

import org.springframework.stereotype.Service;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.exception.DictionaryNotFoundException;
import ru.itpark.dictionary.exception.WordNotFoundException;
import ru.itpark.dictionary.repository.DictionaryRepository;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.List;

@Service
public class WordService {
    private final WordRepository repository;
    private final DictionaryRepository dictionaryRepository;
    private final DictionaryService service;

    public WordService(WordRepository repository, DictionaryRepository dictionaryRepository, DictionaryService service) {
        this.repository = repository;
        this.dictionaryRepository = dictionaryRepository;
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
         DictionaryEntity dictionaryEntity = repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new)
                .getDictionaryEntity();
        List<WordEntity> list = dictionaryEntity.getWord();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
            }
        }
        repository.deleteById(id);
        dictionaryRepository.save(dictionaryEntity);
    }

    public void save(WordEntity entity){
        repository.save(entity);
    }

    public void editWord(WordEntity word, int id){
        WordEntity entity = repository
                .findById(id)
                .orElseThrow(WordNotFoundException::new);
        entity.setWord(word.getWord());
        entity.setTranscription(word.getTranscription());
        entity.setTranslation(word.getTranslation());
        repository.save(entity);
    }
}
