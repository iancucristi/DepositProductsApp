package ro.itschool.depositproductsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.itschool.depositproductsapp.entity.DepositModel;
import ro.itschool.depositproductsapp.entity.ProductModel;
import ro.itschool.depositproductsapp.repository.ProductRepository;
import ro.itschool.depositproductsapp.service.exception.DepositNotFoundException;
import ro.itschool.depositproductsapp.service.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getProducts() {
        List<ProductModel> productModels = productRepository.findAll();
        return productModels;
    }

    public void addProduct(ProductModel product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public ProductModel getProduct(int id) throws ProductNotFoundException {
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("there is no deposit with id: " + id);
        }
        ProductModel product = productModel.get();
        return product;
    }

    public void updateProduct(ProductModel oldProductModel) throws ProductNotFoundException {
        ProductModel newProductModel = getProduct(oldProductModel.getSeriesNumber());
        newProductModel.setName(oldProductModel.getName());
        newProductModel.setProducer(oldProductModel.getProducer());
        newProductModel.setDescription(oldProductModel.getDescription());
        newProductModel.setQuantity(oldProductModel.getQuantity());
        productRepository.save(newProductModel);
    }

    public List<ProductModel> searchProductByName(String name) throws ProductNotFoundException {
        List<ProductModel> productModels = productRepository.findAll();
        List<ProductModel> productFound = new ArrayList<>();
        for (ProductModel product : productModels) {
            if (product.getName().equals(name)) {
                productFound.add(product);
            } else throw new ProductNotFoundException("Product with name " + name + " not found");
        }
        return productFound;
    }
}
