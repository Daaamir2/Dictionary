package ru.itpark.dictionary.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {
    @Mock
    private WordRepository repository;

    @InjectMocks
    private WordService service;


    @Test
    void findById() {
        var dummy = new WordEntity(1, "", "","","","",null);
        doReturn(Optional.of(dummy)).when(repository).findById(1);
        assertEquals(dummy, service.findById(1));

    }
}