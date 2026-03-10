package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void create_delegatesToRepositoryAndReturnsCreatedProduct() {
        Product input = new Product();
        Product created = new Product();
        when(productRepository.create(input)).thenReturn(created);

        Product result = productService.create(input);

        assertSame(created, result);
        verify(productRepository, times(1)).create(input);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findAll_convertsIteratorToListInOrder() {
        Product p1 = new Product();
        Product p2 = new Product();
        Iterator<Product> it = List.of(p1, p2).iterator();
        when(productRepository.findAll()).thenReturn(it);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertSame(p1, result.get(0));
        assertSame(p2, result.get(1));
        verify(productRepository, times(1)).findAll();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findAll_whenRepositoryReturnsEmptyIterator_returnsEmptyList() {
        when(productRepository.findAll()).thenReturn(List.<Product>of().iterator());

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findById_delegatesToRepository() {
        String id = "abc";
        Product found = new Product();
        when(productRepository.findById(id)).thenReturn(found);

        Product result = productService.findById(id);

        assertSame(found, result);
        verify(productRepository, times(1)).findById(id);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void edit_delegatesToRepositoryAndReturnsEditedProduct() {
        Product input = new Product();
        Product edited = new Product();
        when(productRepository.edit(input)).thenReturn(edited);

        Product result = productService.edit(input);

        assertSame(edited, result);
        verify(productRepository, times(1)).edit(input);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void delete_delegatesToRepository() {
        String id = "abc";

        productService.delete(id);

        verify(productRepository, times(1)).delete(id);
        verifyNoMoreInteractions(productRepository);
    }
}