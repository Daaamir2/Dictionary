package ru.itpark.dictionary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.dictionary.entity.WordEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class WordRepositoryTest {
    @Autowired
    private WordRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void setRepository() {
        WordEntity entity = new WordEntity(1, "Первый", "","","","",null);
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
                .findAllByWordIgnoreCase("Первый")
                .size();
        assertEquals(1, actual);
    }

    @Test
    public void findAllByNameWithZero() {
        {
            var actual = repository
                    .findAllByWordIgnoreCase("")
                    .size();
            assertEquals(0, actual);
        }
        {
            var actual = repository
                    .findAllByWordIgnoreCase("Второй")
                    .size();
            assertEquals(0, actual);
        }
    }

    @Test
    public void add(){
        repository.save(new WordEntity(2, "Первый", "","","","",null));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void removeAll(){
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
}
