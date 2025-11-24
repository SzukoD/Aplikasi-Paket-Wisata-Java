package utsaplikasipaketwisata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class Order untuk menyimpan data pemesanan paket wisata.
 * Order terkait dengan 1 paket wisata (melalui kode paket).
 */
public class Order {
    private String orderId;
    private String paketKode;
    private int jumlahOrang;
    private String tanggalWaktu;
    private double totalBayar;

    /**
     * Constructor order yang otomatis mencatat tanggal dan waktu pemesanan.
     */
    public Order(String orderId, String paketKode, int jumlahOrang, double totalBayar) {
        this.orderId = orderId;
        this.paketKode = paketKode;
        this.jumlahOrang = jumlahOrang;
        this.totalBayar = totalBayar;
        this.tanggalWaktu = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getOrderId() { return orderId; }
    public String getPaketKode() { return paketKode; }
    public int getJumlahOrang() { return jumlahOrang; }
    public String getTanggalWaktu() { return tanggalWaktu; }
    public double getTotalBayar() { return totalBayar; }

    /**
     * Mengembalikan ringkasan detail order.
     */
    public String infoTable() {
        return String.format(
                "%s | Paket:%s | PER-ORANG | Orang:%d | Rp%.0f | %s",
                orderId, paketKode, jumlahOrang, totalBayar, tanggalWaktu
        );
    }
}
