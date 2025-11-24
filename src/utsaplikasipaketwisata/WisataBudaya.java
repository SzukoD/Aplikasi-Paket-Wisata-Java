package utsaplikasipaketwisata;

/**
 * Subclass PaketWisata untuk paket wisata budaya.
 * Menambahkan atribut lokasi budaya dan durasi.
 */
public class WisataBudaya extends PaketWisata {
    private String budayaLokasi;
    private int durasiHari;

    /**
     * Constructor khusus wisata budaya.
     */
    public WisataBudaya(String kode, String nama, double harga, int kuota,
                        String budayaLokasi, int durasiHari) {
        super(kode, nama, harga, kuota);
        this.budayaLokasi = budayaLokasi;
        this.durasiHari = durasiHari;
    }

    public String getBudayaLokasi() { return budayaLokasi; }
    public int getDurasiHari() { return durasiHari; }

    public void setBudayaLokasi(String budayaLokasi) { this.budayaLokasi = budayaLokasi; }
    public void setDurasiHari(int durasiHari) { this.durasiHari = durasiHari; }

    /**
     * Mengembalikan ringkasan info budaya.
     */
    public String ringkasan() {
        return budayaLokasi + " (" + durasiHari + " hari)";
    }

    /**
     * Override untuk menampilkan data paket budaya dalam bentuk tabel.
     */
    @Override
    public String infoTable() {
        return String.format("%s | BUDAYA | %s",
                super.basicInfo(), ringkasan());
    }
}
