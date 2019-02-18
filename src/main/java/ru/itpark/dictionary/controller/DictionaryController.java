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
    public String addDictionary(@ModelAttribute DictionaryEntity dictionary) {
        dictionaryService.save(dictionary);
        return "redirect:/";
    }

    @GetMapping("/dictionary/{id}")
    public String dictionary(@PathVariable int id, Model model) {
        model.addAttribute("dictionary", dictionaryService.findById(id));
        model.addAttribute("words", dictionaryService.findById(id).getWord());
        return "pages/dictionary";
    }

    @PostMapping("/dictionary/{id}")
    public String addWord(@PathVariable int id, @ModelAttribute WordEntity word) {
        dictionaryService.addWord(word, id);
        return "redirect:/dictionary/{id}";
    }

    @GetMapping(value = "/search", params = "name")
    public String searchDictionary(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("dictionaries", dictionaryService.findAllByName(name));
        return "pages/allDictionariesWithoutAdd";
    }

    @GetMapping(value = "/dictionary/search", params = "word")
    public String searchWord(@RequestParam String word, Model model) {
        model.addAttribute("name", word);
        model.addAttribute("words", dictionaryService.findWord(word));

        return "pages/dictionary";
    }

    @GetMapping("/word/{idWord}")
    public String word(@PathVariable int idWord, Model model) {
        model.addAttribute("word", wordService.findById(idWord));
        return "pages/word";
    }

    @PostMapping("/word/{idWord}")
    public String editWord(@PathVariable int idWord, @ModelAttribute WordEntity wordEntity) {
        int id = wordService.findById(idWord).getDictionaryEntity().getId();
        wordService.editWord(wordEntity, idWord);
        return "redirect:/dictionary/" + id;
    }

    @GetMapping("/word/{idWord}/remove")
    public String removeWord(@PathVariable int idWord, Model model) {
        model.addAttribute("word", wordService.findById(idWord));
        return "pages/removeWord";
    }

    @PostMapping("/word/{idWord}/remove")
    public String removeWord(@PathVariable int idWord) {
        int id = wordService.findById(idWord).getDictionaryEntity().getId();
        dictionaryService.removeWord(idWord);
        return "redirect:/dictionary/" + id;
    }

    @GetMapping("/dictionary/{idDictionary}/remove")
    public String removeDictionary(@PathVariable int idDictionary, Model model) {
        model.addAttribute("dictionary", dictionaryService.findById(idDictionary));
        return "pages/removeDictionary";
    }

    @PostMapping("/dictionary/{idDictionary}/remove")
    public String removeDictionary(@PathVariable int idDictionary) {
        dictionaryService.removeById(idDictionary);
        return "redirect:/";
    }
}

