package run;

import ra.model.model.CartItem;
import ra.model.model.Catalog;
import ra.model.model.Product;
import ra.model.sevice.CartService;
import ra.model.sevice.CatalogService;
import ra.model.sevice.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    // ... (Phần khai báo biến và đối tượng đã có)
    private static CatalogService catalogService = new CatalogService();
    private static  ProductService productService = new ProductService();
    private static CartService cartService = new CartService();
    private static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {
        while (true) {
            // Hiển thị menu chính
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Dành cho người dùng");
            System.out.println("4. Thoát");

            // Nhập lựa chọn từ người dùng
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            switch (choice) {
                case 1:
                    manageCatalog();
                    break;
                case 2:
                    manageProduct();
                    break;
                case 3:
                    userMenu();
                    break;
                case 4:
                    System.out.println("Đã thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        }
    }

    private static void userMenu() {
        while (true) {
            // Hiển thị menu người dùng
            System.out.println("**************************MENU-USER**************************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. Quay lại");

            // Nhập lựa chọn từ người dùng
            System.out.print("Nhập lựa chọn: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            switch (userChoice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    displayCart();
                    break;
                case 4:
                    updateCartItemQuantity();
                    break;
                case 5:
                    removeItemFromCart();
                    break;
                case 6:
                    clearCart();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        }
    }

    private static void addToCart() {
       // displayAllProducts(); // Hiển thị danh sách sản phẩm để người dùng chọn
        System.out.print("Nhập mã sản phẩm muốn thêm vào giỏ hàng: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        System.out.print("Nhập số lượng sản phẩm muốn thêm vào giỏ hàng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        // Gọi phương thức addToCart của CartService để thêm sản phẩm vào giỏ hàng
        cartService.addToCart(productId, quantity);

        System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
    }

    private static void displayCart() {
        List<CartItem> cartItems = cartService.getCartItems();

        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                if (product != null && product.getCatalog() != null && product.getCatalog().getCatalogName() != null) {
                    System.out.println("ID: " + cartItem.getCartItemId() +
                            ", Tên sản phẩm: " + product.getProductName() +
                            ", Danh mục: " + product.getCatalog().getCatalogName() +
                            ", Giá: " + cartItem.getPrice() +
                            ", Số lượng: " + cartItem.getQuantity());
                } else {
                    System.out.println("Sản phẩm không hợp lệ trong giỏ hàng.");
                }
            }
        }
    }



    private static void updateCartItemQuantity() {
    }

    private static void removeItemFromCart() {
    }

    private static void clearCart() {
    }


    private static void manageCatalog() {
        while (true) {
            // Hiển thị menu quản lý danh mục
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");
            Scanner scanner = new Scanner(System.in);
            // Nhập lựa chọn từ người dùng
            System.out.print("Nhập lựa chọn: ");
            int catalogChoice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            switch (catalogChoice) {
                case 1:
                    addNewCatalogs();
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    updateCatalogName();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        }
    }

    // (Các phần code và import khác đã được bảo toàn)

    private static void addNewCatalogs() {
        CatalogService catalogService = new CatalogService();
        System.out.print("Nhập số danh mục thêm mới: ");

        int numberOfCatalogs = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        for (int i = 0; i < numberOfCatalogs; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1) + ":");
            System.out.print("Tên danh mục: ");
            String catalogName = scanner.nextLine();
            System.out.print("Mô tả danh mục: ");
            String catalogDescription = scanner.nextLine();

            Catalog newCatalog = new Catalog(catalogService.getAll().size() + 1, catalogName, catalogDescription);
            catalogService.save(newCatalog);
            System.out.println("Đã thêm mới danh mục: " + newCatalog.toString());
        }
    }

    private static void displayAllCatalogs() {
        List<Catalog> catalogs = catalogService.getAll();

        if (catalogs.isEmpty()) {
            System.out.println("Danh sách danh mục trống.");
        } else {
            System.out.println("Danh sách danh mục:");
            for (Catalog catalog : catalogs) {
                System.out.println("ID: " + catalog.getCatalogId() +
                        ", Tên danh mục: " + catalog.getCatalogName() +
                        ", Mô tả: " + catalog.getDescriptions());
            }
        }
    }

    private static void updateCatalogName() {
        System.out.print("Nhập mã danh mục cần sửa: ");

        int catalogId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng


        Catalog catalogToUpdate = catalogService.findById(catalogId);
        if (catalogToUpdate != null) {
            System.out.print("Nhập tên mới cho danh mục: ");
            String newCatalogName = scanner.nextLine();
            catalogToUpdate.setCatalogName(newCatalogName);
            System.out.println("Đã sửa tên danh mục có mã " + catalogId);
        } else {
            System.out.println("Không tìm thấy danh mục có mã " + catalogId);
        }
    }

    private static void deleteCatalog() {

    }




    private static void manageProduct() {
        while (true) {
            // Hiển thị menu quản lý sản phẩm
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm thêm mới và nhập thông tin cho từng sản phẩm");
            System.out.println("2. Hiển thị thông tin tất cả các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sản phẩm theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");

            Scanner scanner = new Scanner(System.in);
            // Nhập lựa chọn từ người dùng
            System.out.print("Nhập lựa chọn: ");
            int productChoice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            switch (productChoice) {
                case 1:
                    addNewProducts();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    sortProductsByPriceDescending();
                    break;
                case 4:
                    deleteProductById();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    updateProductInformation();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        }
    }

    private static void addNewProducts() {
        System.out.print("Nhập số sản phẩm thêm mới: ");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1) + ":");

            System.out.print("ID sản phẩm: ");
            String productId  = scanner.nextLine();

            // Nhập thông tin cho sản phẩm
            System.out.print("Tên sản phẩm: ");
            String productName = scanner.nextLine();

            System.out.print("Giá sản phẩm: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            System.out.print("Mô tả sản phẩm: ");
            String productDescription = scanner.nextLine();

            System.out.print("Số lượng tồn kho: ");
            int stock = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            // Hiển thị danh sách danh mục để người dùng chọn
            displayAllCatalogs();
            System.out.print("Chọn mã danh mục cho sản phẩm: ");
            int catalogId = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng

            // Tạo đối tượng sản phẩm và thêm vào danh sách sản phẩm
            Catalog catalog = catalogService.findById(catalogId);
            if (catalog != null) {
                Product newProduct = new Product(productId, productName, productPrice, productDescription, stock, catalog);
                productService.save(newProduct);
                System.out.println("Đã thêm mới sản phẩm: " + newProduct.toString());
            } else {
                System.out.println("Không tìm thấy danh mục có mã " + catalogId + ". Không thể thêm mới sản phẩm.");
            }
        }
    }


    private static void displayAllProducts() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
        } else {
            System.out.println("Danh sách sản phẩm:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductId() +
                        ", Tên sản phẩm: " + product.getProductName() +
                        ", Giá: " + product.getProductPrice() +
                        ", Mô tả: " + product.getDescription() +
                        ", Số lượng tồn kho: " + product.getStock() +
                        ", Danh mục: " + product.getCatalog().getCatalogName());
            }
        }
    }


    private static void sortProductsByPriceDescending() {
    }

    private static void deleteProductById() {
    }

    private static void searchProductByName() {
    }

    private static void updateProductInformation() {
    }
}


