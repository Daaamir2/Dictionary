package ru.itpark.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.service.DictionaryService;
import ru.itpark.dictionary.service.WordService;

@Controller
@RequestMapping("/")
public class DictionaryController {
    private final DictionaryService dictionaryService;
    private final WordService wordService;

    public DictionaryController(DictionaryService dictionaryService, WordService wordService) {
        this.dictionaryService = dictionaryService;
        this.wordService = wordService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("dictionaries", dictionaryService.findAll());
        return "pages/allDictionaries";
    }

    @PostMapping
    public String add(@ModelAttribute DictionaryEntity dictionary) {
        dictionaryService.save(dictionary);
        return "redirect:/";
    }

    @GetMapping("/dictionary/{id}")
    public String dictionary(@PathVariable int id, Model model){
        model.addAttribute("dictionary", dictionaryService.findById(id));
        model.addAttribute("words", dictionaryService.findAllWordsInDictionary(id));
        return "pages/dictionary";
    }

    @PostMapping("/dictionary/{id}")
    public String add(@PathVariable int id, @ModelAttribute WordEntity word) {
        dictionaryService.addWord(word, id);

        return "redirect:/dictionary/{id}";
    }

    @GetMapping("/word")
    public String word(Model model){
        model.addAttribute("words", wordService.findAll());
        return "pages/word";
    }
}

