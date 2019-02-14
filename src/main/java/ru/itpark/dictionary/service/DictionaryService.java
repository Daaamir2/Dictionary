package ru.itpark.dictionary.service;

import org.springframework.stereotype.Service;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.exception.DictionaryNotFoundException;
import ru.itpark.dictionary.repository.DictionaryRepository;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.List;

@Service
public class DictionaryService {
    private final DictionaryRepository repository;
    private final WordRepository wordRepository;

    public DictionaryService(DictionaryRepository repository, WordRepository wordRepository) {
        this.repository = repository;
        this.wordRepository = wordRepository;
    }

    public List<DictionaryEntity> findAll() {
        return repository.findAll();
    }

    public DictionaryEntity findById(int id) {
        return repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new);
    }

    public List<WordEntity> findAllWordsInDictionary(int id) {
        return repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new)
                .getWord();
    }


    public void removeById(int id) {
        repository.deleteById(id);
    }

    public void save(DictionaryEntity dictionary) {
        repository.save(dictionary);
    }

    public void addWord(WordEntity word,int id) {
        word.setDictionaryEntity(repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new));
        wordRepository.save(word);

        List<WordEntity> list = repository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new)
                .getWord();
        list.add(word);
        repository.save(word.getDictionaryEntity());
    }
}
