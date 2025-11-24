package utsaplikasipaketwisata;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ArrayList<PaketWisata> paketList = new ArrayList<>();
    static ArrayList<Pelanggan> pelangganList = new ArrayList<>();
    static int pelangganCounter = 1;
    static int orderCounter = 1;

    public static void main(String[] args) {
        objekData();
        int pilihan = -1;

        while (pilihan != 0) {
            showMenu();
            pilihan = inputInt("Pilih menu (angka): ");
            switch (pilihan) {
                case 1: tambahPaket(); break;
                case 2: tampilPaketTabel(); break;
                case 3: cariPaket(); break;
                case 4: ubahPaket(); break;
                case 5: hapusPaket(); break;
                case 6: filterPaket(); break;
                case 7: pemesanan(); break;
                case 8: lihatSemuaPesanan(); break;
                case 0: System.out.println("Keluar. Terima kasih."); break;
                default: System.out.println("Pilihan tidak valid."); break;
            }
        }
    }

    //menu
    static void showMenu() {
        System.out.println("\n--- APLIKASI PAKET WISATA ---");
        System.out.println("1. Tambah Paket");
        System.out.println("2. Tampilkan Semua Paket");
        System.out.println("3. Cari Paket");
        System.out.println("4. Ubah Paket");
        System.out.println("5. Hapus Paket");
        System.out.println("6. Filter Paket (Alam/Budaya)");
        System.out.println("7. Pemesanan");
        System.out.println("8. Lihat Semua Pesanan");
        System.out.println("0. Keluar");
    }

    //input
    static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Input harus angka!");
            }
        }
    }

    static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double d = Double.parseDouble(in.nextLine());
                if (d <= 0) {
                    System.out.println("Harus lebih dari 0.");
                    continue;
                }
                return d;
            } catch (Exception e) {
                System.out.println("Input harus angka!");
            }
        }
    }

    static String inputNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Tidak boleh kosong!");
        }
    }

    //tambah paket
    static void tambahPaket() {
        System.out.println("-- Tambah Paket --");
        String kode = inputNonEmpty("Kode paket: ");
        if (getPaketByKode(kode) != null) {
            System.out.println("Kode sudah terdaftar!");
            return;
        }

        String nama = inputNonEmpty("Nama paket: ");
        double harga = inputDouble("Harga: ");
        int kuota = inputInt("Kuota awal: ");

        System.out.println("Jenis paket: 1=Alam, 2=Budaya");
        int jenis = inputInt("Pilih jenis: ");

        if (jenis == 1) {
            String lokasi = inputNonEmpty("Lokasi wisata: ");
            int durasi = inputInt("Durasi (hari): ");

            paketList.add(new WisataAlam(kode, nama, harga, kuota, lokasi, durasi));

        } else if (jenis == 2) {
            String budaya = inputNonEmpty("Lokasi budaya: ");
            int durasi = inputInt("Durasi (hari): ");

            paketList.add(new WisataBudaya(kode, nama, harga, kuota, budaya, durasi));
        } else {
            System.out.println("Jenis tidak valid.");
        }

        System.out.println("Paket berhasil ditambahkan.");
    }

    //tampilan paket
    static void tampilPaketTabel() {
        if (paketList.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        printPaketTableHeader();
        for (PaketWisata p : paketList) {
            if (p instanceof WisataAlam) {
                WisataAlam wa = (WisataAlam) p;
                System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | ALAM   | %-14s | %3d hari |\n",
                        wa.getKode(), truncate(wa.getNama(),20),
                        wa.getHarga(), wa.getKuota(),
                        truncate(wa.getLokasi(),14), wa.getDurasiHari());
            } else {
                WisataBudaya wb = (WisataBudaya) p;
                System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | BUDAYA | %-14s | %3d hari |\n",
                        wb.getKode(), truncate(wb.getNama(),20),
                        wb.getHarga(), wb.getKuota(),
                        truncate(wb.getBudayaLokasi(),14), wb.getDurasiHari());
            }
        }
        printPaketTableFooter();
    }

    static void printPaketTableHeader() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-20s | %-12s | %-5s | %-6s | %-14s | %-8s |\n",
                "KODE","NAMA PAKET","HARGA","KUOTA","JENIS","LOKASI","DURASI");
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    static void printPaketTableFooter() {
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    static String truncate(String s, int len) {
        return s.length() <= len ? s : s.substring(0, len-3) + "...";
    }

    static PaketWisata getPaketByKode(String kode) {
        for (PaketWisata p : paketList) {
            if (p.getKode().equalsIgnoreCase(kode)) return p;
        }
        return null;
    }

    //cari paket
    static void cariPaket() {
        String keyword = inputNonEmpty("Masukkan paket yang ingin dicari (Berdasarkan kode, nama, jenis paket)): ").toLowerCase();
        boolean ada = false;

        printPaketTableHeader();
        for (PaketWisata p : paketList) {
            String kode = p.getKode().toLowerCase();
            String nama = p.getNama().toLowerCase();
            String harga = String.valueOf((long)p.getHarga());
            String kuota = String.valueOf(p.getKuota());

            if (p instanceof WisataAlam) {
                WisataAlam wa = (WisataAlam) p;
                if (kode.contains(keyword) || nama.contains(keyword) ||
                        harga.contains(keyword) || kuota.contains(keyword) ||
                        wa.getLokasi().toLowerCase().contains(keyword) ||
                        String.valueOf(wa.getDurasiHari()).contains(keyword) ||
                        "alam".contains(keyword)) {

                    ada = true;
                    System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | ALAM   | %-14s | %3d hari |\n",
                            wa.getKode(), truncate(wa.getNama(),20),
                            wa.getHarga(), wa.getKuota(),
                            truncate(wa.getLokasi(),14), wa.getDurasiHari());
                }

            } else {
                WisataBudaya wb = (WisataBudaya) p;
                if (kode.contains(keyword) || nama.contains(keyword) ||
                        harga.contains(keyword) || kuota.contains(keyword) ||
                        wb.getBudayaLokasi().toLowerCase().contains(keyword) ||
                        String.valueOf(wb.getDurasiHari()).contains(keyword) ||
                        "budaya".contains(keyword)) {

                    ada = true;
                    System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | BUDAYA | %-14s | %3d hari |\n",
                            wb.getKode(), truncate(wb.getNama(),20),
                            wb.getHarga(), wb.getKuota(),
                            truncate(wb.getBudayaLokasi(),14), wb.getDurasiHari());
                }
            }
        }

        printPaketTableFooter();
        if (!ada) System.out.println("Tidak ada paket yang cocok.");
    }

    //ubah
    static void ubahPaket() {
        System.out.println("\n--- DAFTAR PAKET ---");
        tampilPaketTabel();

        String kode = inputNonEmpty("Masukkan kode paket yang ingin diubah: ");
        PaketWisata p = getPaketByKode(kode);

        if (p == null) {
            System.out.println("Paket tidak ditemukan.");
            return;
        }

        System.out.println("\n-- Ubah Data Paket --");
        String nama = inputNonEmpty("Nama baru: ");
        double harga = inputDouble("Harga baru: ");
        int kuota = inputInt("Kuota baru: ");

        p.setNama(nama);
        p.setHarga(harga);
        p.setKuota(kuota);

        System.out.println("Paket berhasil diperbarui.");
    }

    //hapus paket
    static void hapusPaket() {
        System.out.println("\n--- DAFTAR PAKET ---");
        tampilPaketTabel();

        String kode = inputNonEmpty("Masukkan kode paket: ");
        PaketWisata p = getPaketByKode(kode);

        if (p == null) {
            System.out.println("Paket tidak ditemukan.");
            return;
        }

        paketList.remove(p);
        System.out.println("Paket telah dihapus.");
    }

    //filter paket
    static void filterPaket() {
        System.out.println("Filter: 1=Alam, 2=Budaya");
        int jenis = inputInt("Pilih: ");

        printPaketTableHeader();
        boolean ada = false;

        for (PaketWisata p : paketList) {
            if (jenis == 1 && p instanceof WisataAlam) {
                WisataAlam wa = (WisataAlam) p;
                ada = true;
                System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | ALAM   | %-14s | %3d hari |\n",
                        wa.getKode(), truncate(wa.getNama(),20), wa.getHarga(),
                        wa.getKuota(), truncate(wa.getLokasi(),14), wa.getDurasiHari());

            } else if (jenis == 2 && p instanceof WisataBudaya) {
                WisataBudaya wb = (WisataBudaya) p;
                ada = true;
                System.out.printf("| %-6s | %-20s | Rp%-10.0f | %-5d | BUDAYA | %-14s | %3d hari |\n",
                        wb.getKode(), truncate(wb.getNama(),20), wb.getHarga(),
                        wb.getKuota(), truncate(wb.getBudayaLokasi(),14), wb.getDurasiHari());
            }
        }

        printPaketTableFooter();
        if (!ada) System.out.println("Tidak ada paket sesuai filter.");
    }

    //pemesanan
    static void pemesanan() {
        System.out.println("-- Pemesanan --");

        System.out.println("\nDaftar paket:");
        tampilPaketTabel();

        String nama = inputNonEmpty("Nama pelanggan: ");
        String telp = inputNonEmpty("No telp: ");
        String idPelanggan = String.format("P%03d", pelangganCounter++);

        Pelanggan p = new Pelanggan(idPelanggan, nama, telp);
        pelangganList.add(p);

        String kode = inputNonEmpty("Masukkan kode paket: ");
        PaketWisata paket = getPaketByKode(kode);

        if (paket == null) {
            System.out.println("Paket tidak ditemukan.");
            return;
        }

        int jumlah = inputInt("Jumlah orang: ");
        //mengurangu jumlah kuota
        if (!paket.reduceKuota(jumlah)) {
            System.out.println("Kuota tidak cukup. Sisa: " + paket.getKuota());
            return;
        }

        double total = paket.getHarga() * jumlah;
        String orderId = String.format("O%04d", orderCounter++);

        Order o = new Order(orderId, paket.getKode(), jumlah, total);
        p.tambahOrder(o);

        System.out.println("\nPemesanan berhasil!");
        System.out.println("Order ID : " + orderId);
        System.out.println("Total    : Rp" + (long) total);
    }

    //lihat semua pesanan
    static void lihatSemuaPesanan() {
        boolean ada = false;
        printPesananTableHeader();

        for (Pelanggan p : pelangganList) {
            for (Order o : p.getOrders()) {
                ada = true;
                PaketInfo info = getPaketInfo(o.getPaketKode());

               System.out.printf(
            "| %-6s | %-6s | %-20s | %-13s | %-6s | %-20s | %-6s | %-14s | %-4s | %-4d | Rp%-12.0f | %-16s |\n",
            o.getOrderId(), p.getId(), truncate(p.getNama(),20),
            p.getTelp(),
            o.getPaketKode(), truncate(info.nama,20),
            info.jenis, truncate(info.lokasiOrBudaya,14),
            info.durasi + "d", o.getJumlahOrang(), o.getTotalBayar(),
            o.getTanggalWaktu()
);

            }
        }

        printPesananTableFooter();
        if (!ada) System.out.println("Belum ada pesanan.");
    }

    static void printPesananTableHeader() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf(
        "| %-6s | %-6s | %-20s | %-13s | %-6s | %-20s | %-6s | %-14s | %-4s | %-4s | %-12s | %-16s |\n",
        "ORDID","IDPEL","NAMA","TELP","PAKET","NAMA_PAKET","JENIS","LOK/BUDAYA","DUR","ORG","TOTAL","TANGGAL"
    );
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    static void printPesananTableFooter() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //info paket pesanan
    static class PaketInfo {
        String nama = "-";
        String jenis = "-";
        String lokasiOrBudaya = "-";
        int durasi = 0;
    }

    static PaketInfo getPaketInfo(String kode) {
        PaketInfo info = new PaketInfo();
        PaketWisata p = getPaketByKode(kode);

        if (p == null) return info;

        info.nama = p.getNama();

        if (p instanceof WisataAlam) {
            WisataAlam wa = (WisataAlam) p;
            info.jenis = "ALAM";
            info.lokasiOrBudaya = wa.getLokasi();
            info.durasi = wa.getDurasiHari();

        } else {
            WisataBudaya wb = (WisataBudaya) p;
            info.jenis = "BUDAYA";
            info.lokasiOrBudaya = wb.getBudayaLokasi();
            info.durasi = wb.getDurasiHari();
        }

        return info;
    }

    //data objek
  static void objekData() {
    paketList.add(new WisataAlam(
            "A01", "Gunung Tangkuban Perahu", 250000, 20, "Bandung", 1
    ));

    paketList.add(new WisataBudaya(
            "B01", "Angklung Udjo", 150000, 25, "Garut", 1
    ));


    Pelanggan p1 = new Pelanggan(
            "P001", "M Dafa Dwi Saputra", "081321445567"
    );
    pelangganList.add(p1);

 
    Order o1 = new Order(
            "O0001", "A01", 2, 
            paketList.get(0).getHarga() * 2
    );
    p1.tambahOrder(o1);
}

}
