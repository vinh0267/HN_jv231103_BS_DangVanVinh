package ra.model.sevice;

import ra.model.model.CartItem;
import ra.model.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    ProductService productService = new ProductService();
    private List<CartItem> cartItems;

    public CartService() {
        this.cartItems = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        // Trả về danh sách tất cả sản phẩm đang được bán của cửa hàng
        return productService.getAll();
    }

    public void addToCart(int productId, int quantity) {

        // lấy sản phẩm từ danh sách sản phẩm của cửa hàng
        Product product = getProductById(productId);
        if (product == null) {
            validateAndAddToCart(product, quantity);
        } else {
            System.out.println(" không tìm thấy sản phẩm vs mã sản phẩm " + productId);
        }
    }

    private Product getProductById(int productId) { // lấy sản phẩm theo id
        // lấy danh sách sản phẩm từ cửa hàng
        List<Product> allProducts = getAllProducts();

        // Lặp qua danh sách để tìm sản phẩm có mã sản phẩm trùng khớp
        for (Product product : allProducts) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }


    public void updateQuantity(int cartItemId, int newQuantity) {
        // Cập nhật số lượng sản phẩm muốn mua theo trường cartItemId
    }

    public void removeFromCart(int cartItemId) {
        // Xóa một sản phẩm ra khỏi giỏ hàng theo cartItemId
    }

    public void removeAllFromCart() {
        // Xóa toàn bộ sản phẩm trong giỏ hàng
    }

    // Phương thức này kiểm tra stock của sản phẩm còn thì mới được thêm vào giỏ hàng
    // Sản phẩm đã có trong giỏ hàng thì tăng số lượng lên
    private void validateAndAddToCart(Product product, int quantity) {
        // Kiểm tra stock của sản phẩm và thực hiện các thao tác cần thiết
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductId() == product.getProductId()) { // kiểm tra trùng
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
    }

    // Phương thức này cập nhật stock của sản phẩm khi có sự thay đổi số lượng trong giỏ hàng
    private void updateProductStock(Product product, int newQuantity) {
        // Cập nhật stock của sản phẩm tương ứng khi có sự thay đổi số lượng trong giỏ hàng
        int currentStock = product.getStock();
        int updatedStock = currentStock - newQuantity;

        // Kiểm tra stock mới không âm và cập nhật nếu hợp lệ
        if (updatedStock >= 0) {
            product.setStock(updatedStock);
        } else {
            System.out.println("Số lượng trong giỏ hàng vượt quá số lượng tồn kho của sản phẩm " + product.getProductName());
        }
    }
}
