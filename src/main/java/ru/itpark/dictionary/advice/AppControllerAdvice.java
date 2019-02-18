package ru.itpark.dictionary.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itpark.dictionary.exception.DictionaryNotFoundException;
import ru.itpark.dictionary.exception.WordNotFoundException;

@ControllerAdvice
public class AppControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DictionaryNotFoundException.class)
    public String handleDictionaryNotFound(Model model){
        model.addAttribute("massage", "Dictionary Entity Not Found");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WordNotFoundException.class)
    public String handleWordNotFound(Model model){
        model.addAttribute("massage", "Word Entity Not Found");
        return "error";
    }
}
