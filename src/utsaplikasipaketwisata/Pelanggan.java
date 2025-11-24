package utsaplikasipaketwisata;

import java.util.ArrayList;

/**
 * Class Pelanggan yang menyimpan data pelanggan dan daftar ordernya.
 * Mewakili relasi aggregation: pelanggan memiliki banyak order.
 */
public class Pelanggan {
    private String id;
    private String nama;
    private String telp;
    private ArrayList<Order> orders = new ArrayList<>();

    /**
     * Constructor pelanggan dengan id, nama, dan nomor telepon.
     */
    public Pelanggan(String id, String nama, String telp) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getTelp() { return telp; }  

    /**
     * Menambahkan order ke daftar order pelanggan.
     * Ini merupakan relasi AGGREGATION.
     */
    public void tambahOrder(Order o) {
        orders.add(o);
    }

    /**
     * Mengembalikan daftar semua order pelanggan.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
}
