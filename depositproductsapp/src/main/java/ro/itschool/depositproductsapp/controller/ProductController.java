package ro.itschool.depositproductsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.itschool.depositproductsapp.entity.DepositModel;
import ro.itschool.depositproductsapp.entity.ProductModel;
import ro.itschool.depositproductsapp.service.ProductService;
import ro.itschool.depositproductsapp.service.exception.ProductNotFoundException;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("view-products")
    public String viewProducts(Model model) {
        List<ProductModel> productList = productService.getProducts();
        model.addAttribute("products", productList); // view products
        model.addAttribute("product", new ProductModel()); // search product by name
        return "products";
    }

    @GetMapping("addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductModel());
        return "add-products";
    }

    @PostMapping("add-new-product")
    private String addNewProduct(ProductModel productModel) {
        productService.addProduct(productModel);
        return "redirect:/view-products";
    }

    @GetMapping("edit-product-page/{id}")
    private String editProductPage(@PathVariable("id") int id, Model model) throws ProductNotFoundException {
        ProductModel productModel = productService.getProduct(id);
        model.addAttribute("product", productModel);
        return "edit-products";
    }

    @PostMapping("edit-product")
    private String editProduct(ProductModel productModel) throws ProductNotFoundException {
        productService.updateProduct(productModel);
        return "redirect:/view-products";
    }

    @GetMapping("delete-product/{id}")
    private String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/view-products";
    }

    @PostMapping("products-found")
    private String searchByName(String name, Model model) throws ProductNotFoundException {
        List<ProductModel> productModels = productService.searchProductByName(name);
        model.addAttribute("productList", productModels);
    return "redirect:/view-products";
    }

}
