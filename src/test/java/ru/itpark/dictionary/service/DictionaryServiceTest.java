package ru.itpark.dictionary.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.repository.DictionaryRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DictionaryServiceTest {
    @Mock
    private DictionaryRepository repository;

    @InjectMocks
    private DictionaryService service;


    @Test
    void findById() {
        var dummy = new DictionaryEntity(1, "", null);
        doReturn(Optional.of(dummy)).when(repository).findById(1);
        assertEquals(dummy, service.findById(1));
    }

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(new DictionaryEntity(1, "Первый", null),
                        new DictionaryEntity(2, "Первый", null)
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
        when(repository.findAllByNameIgnoreCase(name)).thenReturn(Stream
                .of(new DictionaryEntity(1, "Первый", null),
                        new DictionaryEntity(2, "Первый", null)
                )
                .collect(Collectors.toList())
        );
        assertEquals(2, service.findAllByName(name).size());
    }

    @Test
    public void findAllByNameTestWithZeroResult() {
        String name = "Первый";

        when(repository.findAllByNameIgnoreCase(name)).thenReturn((Collections.emptyList()));
        assertEquals(0, service.findAllByName(name).size());
    }

    @Test
    public void saveTest(){
        var dummy = new DictionaryEntity(1, "Первый", null);
        service.save(dummy);
        verify(repository, times(1)).save( new DictionaryEntity(1, "Первый", null));
    }

    @Test
    public void deleteDictionaryTest(){
        var dummy = new DictionaryEntity(1, "Первый", null);
        service.removeById(1);
        verify(repository, times(1)).deleteById(1);
        assertEquals(0, service.findAll().size());
    }
}

