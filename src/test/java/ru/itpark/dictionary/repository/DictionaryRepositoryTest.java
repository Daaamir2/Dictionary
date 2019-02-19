package ru.itpark.dictionary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.dictionary.entity.DictionaryEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DictionaryRepositoryTest {
    @Autowired
    private DictionaryRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void setRepository() {
        DictionaryEntity entity = new DictionaryEntity(1, "Первый", null);
        em.merge(entity);
    }

    @Test
    public void findAll() {
        var actual = repository
                .findAll()
                .size();
        assertEquals(1, actual);
    }

    @Test
    public void findAllByName() {
        var actual = repository
                .findAllByNameIgnoreCase("Первый")
                .size();
        assertEquals(1, actual);
    }

    @Test
    public void findAllByNameWithZero() {
        {
            var actual = repository
                    .findAllByNameIgnoreCase("")
                    .size();
            assertEquals(0, actual);
        }
        {
            var actual = repository
                    .findAllByNameIgnoreCase("Второй")
                    .size();
            assertEquals(0, actual);
        }
    }

    @Test
    public void add(){
        repository.save(new DictionaryEntity(2, "Первый", null));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void removeAll(){
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
}
