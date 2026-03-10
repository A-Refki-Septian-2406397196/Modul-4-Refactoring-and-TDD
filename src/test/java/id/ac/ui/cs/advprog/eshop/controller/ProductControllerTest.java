package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void root_redirectsToList() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PRODUCT_LIST));
    }

    @Test
    void createGet_returnsCreateProductView_andAddsEmptyProduct() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createPost_callsServiceCreate_andRedirectsToList() throws Exception {
        mockMvc.perform(post("/product/create")
                        // sesuaikan nama field dengan properti di class Product kamu
                        .param("productName", "Sampo Cap Bambang")
                        .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PRODUCT_LIST));

        verify(service, times(1)).create(any(Product.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void listGet_returnsProductListView_andAddsProducts() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Product(), new Product()));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void editGet_whenProductNotFound_redirectsToList() throws Exception {
        when(service.findById("abc")).thenReturn(null);

        mockMvc.perform(get("/product/edit/abc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PRODUCT_LIST));

        verify(service, times(1)).findById("abc");
        verifyNoMoreInteractions(service);
    }

    @Test
    void editGet_whenProductFound_returnsEditProductView_andAddsProduct() throws Exception {
        Product p = new Product();
        when(service.findById("abc")).thenReturn(p);

        mockMvc.perform(get("/product/edit/abc"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attribute("product", p));

        verify(service, times(1)).findById("abc");
        verifyNoMoreInteractions(service);
    }

    @Test
    void editPost_callsServiceEdit_andRedirectsToList() throws Exception {
        mockMvc.perform(post("/product/edit")
                        // sesuaikan param ini dengan field di Product kamu
                        .param("productId", "abc")
                        .param("productName", "Updated")
                        .param("productQuantity", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PRODUCT_LIST));

        verify(service, times(1)).edit(any(Product.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteGet_callsServiceDelete_andRedirectsToList() throws Exception {
        mockMvc.perform(get("/product/delete/abc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_PRODUCT_LIST));

        verify(service, times(1)).delete("abc");
        verifyNoMoreInteractions(service);
    }
}
