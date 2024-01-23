package com.example.lista12.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.lista12.entity.Product;
import com.example.lista12.service.ProductService;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/")
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String serverTime = dateFormat.format(date);
        model.addAttribute("serverTime", serverTime);
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList );
        return "product/index";
    }

    @GetMapping("/product/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "/product/add";
    }

    @PostMapping("/product/add")
    public String add(@ModelAttribute Product product, Model model) {
        if(productService.getProductById(product.getProductId()).isPresent()){
            model.addAttribute("error", "Product with this ID already exists.");
            return "/product/add";
        }
        productService.addProduct(product);
        return "redirect:/product/";
    }

    @GetMapping("/product/details")
    public String add(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("product", productService.getProductById(inputId));
        return "/product/details";
    }

    @GetMapping(value = {"/product/{productId}/edit"})
    public String edit(Model model, @PathVariable long productId) {
        model.addAttribute("product", productService.getProductById(productId));
        return  "/product/edit";
    }

    @PostMapping(value = {"/product/edit"})
    public String edit (@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/product/";
    }

    @GetMapping("/product/remove")
    public String remove(@RequestParam("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/product/";
    }


}
