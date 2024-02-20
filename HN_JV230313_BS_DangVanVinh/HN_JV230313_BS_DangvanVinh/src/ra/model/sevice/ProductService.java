package ra.model.sevice;

import ra.model.model.Catalog;
import ra.model.model.Product;
import ra.model.sevice.IGenericService;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product, String> {
    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
        }
    }

    @Override
    public boolean hasProductsInCatalog(Catalog catalogToDelete) {
        return false;
    }

    @Override
    public Object findById() {
        return null;
    }
}
