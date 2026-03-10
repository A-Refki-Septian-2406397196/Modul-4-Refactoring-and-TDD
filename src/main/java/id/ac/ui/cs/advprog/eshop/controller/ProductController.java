package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    @GetMapping("")
    public String root() {
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "CreateProduct"; 
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList"; 
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product p = service.findById(productId);
        if (p == null) return REDIRECT_PRODUCT_LIST;
        model.addAttribute("product", p);
        return "EditProduct"; 
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product) {
        service.edit(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        service.delete(productId);
        return REDIRECT_PRODUCT_LIST;
    }
    
}

