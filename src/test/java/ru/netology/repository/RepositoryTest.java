package ru.netology.repository;
import ru.netology.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.NotFoundException;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


public class RepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    Book first = new Book(1, "Real Boys", 899, "G.Mathew", 700, 2011);
    Book second = new Book(2, "Lolita", 1500, "V.Nabokov", 236, 1955);
    Book third = new Book(3, "Poems", 460, "A.Bartho", 104, 1983);
    Smartphone fourth = new Smartphone(4, "Krutoy # 1", 23500, "SPM#1");
    Smartphone fifth = new Smartphone(5, "Very Krutoy", 99900, "SPM#1");
    Smartphone sixth = new Smartphone(6, "Most Krutoy", 123500, "NeSamSung");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }
    @Test
    void shouldRemoveByIdIfExists() {
        int idToRemove = 4;
        repository.removeById(idToRemove);
        Product[] expected = new Product[]{first, second, third, fifth, sixth};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldGenerateExceptionWhenRemovesNonExistsElement() {
        int idToRemove = 7;
        Exception e = assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
