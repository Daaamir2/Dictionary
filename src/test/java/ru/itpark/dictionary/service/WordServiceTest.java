package ru.itpark.dictionary.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.entity.WordEntity;
import ru.itpark.dictionary.repository.WordRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class WordServiceTest {
    @Mock
    private WordRepository repository;

    @InjectMocks
    private WordService service;


    @Test
    void findById() {
        var dummy = new WordEntity(1, "", "", "", "", "", null);
        doReturn(Optional.of(dummy)).when(repository).findById(1);
        assertEquals(dummy, service.findById(1));
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new WordEntity(1, "", "", "", "", "", null),
                        new WordEntity(1, "", "", "", "", "", null)
                )
                .collect(Collectors.toList())
        );
        assertEquals(2, service.findAll().size());
    }

    @Test
    public void findAllTestWithZeroResult() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, service.findAll().size());
    }

    @Test
    public void findAllByNameTest() {
        String name = "Первый";
        when(repository.findAllByWordIgnoreCase(name)).thenReturn(Stream
                .of(new WordEntity(1, "Первый", "", "", "", "", null),
                        new WordEntity(1, "Первый", "", "", "", "", null)
                )
                .collect(Collectors.toList())
        );
        assertEquals(2, service.findAllByName(name).size());
    }

    @Test
    public void findAllByNameTestWithZeroResult() {
        String name = "Первый";

        when(repository.findAllByWordIgnoreCase(name)).thenReturn((Collections.emptyList()));
        assertEquals(0, service.findAllByName(name).size());
    }

    @Test
    public void saveTest() {
        var dummy = new WordEntity(1, "Первый", "", "", "", "", null);
        service.save(dummy);
        verify(repository, times(1)).save(new WordEntity(1, "Первый", "", "", "", "", null));
    }
}