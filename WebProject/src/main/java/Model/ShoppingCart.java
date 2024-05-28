package Model;

import Service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<String, CartItems> data = new HashMap<>();

    //Thêm sản phẩm vào giỏ hàng
    public boolean add(String maSP) {
        return add(maSP, 1);
    }
    //Hàm để tăng sản phẩm đã tồn tại trong giỏ hàng lên 1 đơn vị
    public boolean add(String maSP, int soLuong) {
        Product products = ProductService.getInstance().findById(maSP);
        //Nếu sản phẩm không có id sẽ trả về lôỗi
        if (products == null) {
            return false;
        }
        // Gọi đến model để lấy ra số lượng sản phẩm của vật phẩm
        CartItems cartItems;
        if (data.containsKey(maSP)) {
            // Lấy ra sản phẩm với id và thực hiện tăng số lượng
            cartItems = data.get(maSP);
            cartItems.increaseQuantity(soLuong);
        } else {

            cartItems = new CartItems(products, soLuong);
        }

        data.put(maSP, cartItems);
        return true;
    }
    //Giam số lượng
    public boolean decrease(String maSP, int soLuong) {
        if (data.containsKey(maSP)) {
            CartItems cartItems = data.get(maSP);
            cartItems.decreaseQuantity(soLuong);
            //Nêu số lượng <0 thì xóa sản phẩm

            if (cartItems.getQuantity() <= 0) {
                data.remove(maSP);
            }
            return true;
        }
        return false;
    }

    public int getToTal() {
        return data.size();
    }
    //Lấy ra ds sản phẩm đang có trong giỏ hàng
    public List<CartItems> getDanhSachSanPham() {
        return new ArrayList<>(data.values());
    }

    public CartItems remove(String maSP) {
        return data.remove(maSP);
    }

    public int getSize() {
        return data.size();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "data=" + data +
                '}';
    }
}
