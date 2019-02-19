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
    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;

    public DictionaryService(DictionaryRepository repository, WordRepository wordRepository) {
        this.dictionaryRepository = repository;
        this.wordRepository = wordRepository;
    }

    public List<DictionaryEntity> findAll() {
        return dictionaryRepository.findAll();
    }

    public DictionaryEntity findById(int id) {
        return dictionaryRepository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new);
    }

    public void removeById(int id) {
        dictionaryRepository.deleteById(id);
    }

    public void save(DictionaryEntity dictionary) {
        dictionaryRepository.save(dictionary);
    }

    public List<DictionaryEntity> findAllByName(String name) {
        return dictionaryRepository.findAllByNameIgnoreCase(name);
    }

    public void addWord(WordEntity word, int id) {
        word.setDictionaryEntity(dictionaryRepository.findById(id)
                .orElseThrow(DictionaryNotFoundException::new));
        wordRepository.save(word);
        List<WordEntity> list = this.findById(id).getWord();
        list.add(word);
        dictionaryRepository.save(word.getDictionaryEntity());
    }

    public List<WordEntity> findWord(String word) {

        return wordRepository.findAllByWordIgnoreCase(word);
    }
}
