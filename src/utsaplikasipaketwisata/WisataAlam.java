package utsaplikasipaketwisata;

/**
 * Subclass PaketWisata untuk paket wisata alam.
 * Menambahkan atribut lokasi dan durasi hari.
 */
public class WisataAlam extends PaketWisata {
    private String lokasi;
    private int durasiHari;

    /**
     * Constructor khusus wisata alam.
     */
    public WisataAlam(String kode, String nama, double harga, int kuota,
                      String lokasi, int durasiHari) {
        super(kode, nama, harga, kuota);
        this.lokasi = lokasi;
        this.durasiHari = durasiHari;
    }

    public String getLokasi() { return lokasi; }
    public int getDurasiHari() { return durasiHari; }

    public void setLokasi(String lokasi) { this.lokasi = lokasi; }
    public void setDurasiHari(int durasiHari) { this.durasiHari = durasiHari; }

    /**
     * Override untuk menampilkan data paket wisata alam dalam format tabel.
     */
    @Override
    public String infoTable() {
        return String.format("%s | ALAM | %s | %d hari",
                super.basicInfo(), lokasi, durasiHari);
    }
}
