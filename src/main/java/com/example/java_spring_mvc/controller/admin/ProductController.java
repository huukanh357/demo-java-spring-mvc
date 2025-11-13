package com.example.java_spring_mvc.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.java_spring_mvc.domain.Product;
import com.example.java_spring_mvc.service.ProductService;
import com.example.java_spring_mvc.service.UploadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postMethodName(
            @ModelAttribute("newProduct") @Valid Product huukhanh,
            BindingResult newProductBindingResult,
            @RequestParam("gicungdcFile") MultipartFile file) {

        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError e : errors) {
            System.out.println(">>>>>>>>> " + e.getField() + " - " + e.getDefaultMessage());
        }

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }

        String image = this.uploadService.handleSaveUploadFile(file, "product");
        huukhanh.setImage(image);

        this.productService.handleSaveProduct(huukhanh);
        return "redirect:/admin/product";

    }

    @RequestMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String updateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/update-product";
    }

    @RequestMapping(value = "/admin/product/update", method = RequestMethod.POST)
    public String updateProductPage(Model model,
            @ModelAttribute("product") @Valid Product huukhanh,
            BindingResult newProductBindingResult,
            @RequestParam("gicungdcFile") MultipartFile file) {

        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError e : errors) {
            System.out.println(">>>>>>>>> " + e.getField() + " - " + e.getDefaultMessage());
        }
        Product currentProduct = this.productService.getProductById(huukhanh.getId());

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/update-product";
        }

        if (currentProduct != null) {
            String imageProduct = this.uploadService.handleSaveUploadFile(file, "product");
            currentProduct.setId(huukhanh.getId());
            currentProduct.setName(huukhanh.getName());
            currentProduct.setPrice(huukhanh.getPrice());
            currentProduct.setDetailDesc(huukhanh.getDetailDesc());
            currentProduct.setShortDesc(huukhanh.getShortDesc());
            currentProduct.setFactory(huukhanh.getFactory());
            currentProduct.setQuantity(huukhanh.getQuantity());
            currentProduct.setSold(huukhanh.getSold());
            currentProduct.setTarget(huukhanh.getTarget());
            currentProduct.setImage(imageProduct);
            this.productService.handleSaveProduct(currentProduct);
        }
        // this.userService.handleSaveUser(huukhanh);
        return "redirect:/admin/product";
    }

    @RequestMapping("/admin/product/delete/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteUserPage(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProductById(product.getId());
        return "redirect:/admin/product";
    }
}
