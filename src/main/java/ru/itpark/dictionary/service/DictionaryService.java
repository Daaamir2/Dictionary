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

    public void removeWord(int id) {
        DictionaryEntity dictionaryEntity = wordRepository.findById(id)
                .orElseThrow()
                .getDictionaryEntity();
        List<WordEntity> list = dictionaryEntity.getWord();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
            }
        }
        dictionaryRepository.save(dictionaryEntity);
    }

    public void editWord(WordEntity word, int id) {
        DictionaryEntity dictionaryEntity = wordRepository.findById(id)
                .orElseThrow()
                .getDictionaryEntity();
        List<WordEntity> list = dictionaryEntity.getWord();
        WordEntity entity = new WordEntity();
        for (WordEntity entity1 : list) {
            if (entity1.getId() == id) {
                entity = entity1;
            }
        }
        entity.setDictionaryEntity(word.getDictionaryEntity());
        entity.setWord(word.getWord());
        entity.setTranscription(word.getTranscription());
        entity.setTranslation(word.getTranslation());
        dictionaryRepository.save(dictionaryEntity);
    }


}
