package utsaplikasipaketwisata;

/**
 * Superclass untuk semua jenis paket wisata.
 * Menyimpan atribut dasar seperti kode, nama, harga, dan kuota.
 */
public class PaketWisata {
    private String kode;
    private String nama;
    private double harga;
    private int kuota;

    /**
     * Constructor paket wisata dengan parameter lengkap.
     */
    public PaketWisata(String kode, String nama, double harga, int kuota) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.kuota = kuota;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getKuota() { return kuota; }

    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setKuota(int kuota) { this.kuota = kuota; }

    /**
     * Mengecek apakah paket masih tersedia berdasarkan kuota.
     */
    public boolean isAvailable() {
        return kuota > 0;
    }

    /**
     * Mengurangi kuota paket sesuai jumlah orang yang memesan.
     * Mengembalikan true jika berhasil, false jika kuota tidak cukup.
     */
    public boolean reduceKuota(int n) {
        if (n <= 0) return false;
        if (kuota >= n) {
            kuota -= n;
            return true;
        }
        return false;
    }

    /**
     * Mengembalikan ringkasan informasi paket dalam bentuk teks.
     */
    public String basicInfo() {
        return kode + " | " + nama + " | Rp" + (long)harga + " | Kuota: " + kuota;
    }

    /**
     * Method default untuk menampilkan info tabel.
     * Akan dioverride di subclass.
     */
    public String infoTable() {
        return basicInfo();
    }
}
