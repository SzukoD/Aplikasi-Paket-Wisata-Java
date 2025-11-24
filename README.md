<div align="center">

# Aplikasi Paket Wisata  
### UTS Pemrograman Berorientasi Objek – Java Console App

<br>

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apachenetbeanside&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white)
![Console App](https://img.shields.io/badge/Console%20App-2b9348?style=for-the-badge)

<br>

🎓 **Dibuat oleh:**  
### **M Dafa Dwi Saputra**  
**NIM:** 24552011320  
**Kelas:** TIF RP 24E  

</div>

---

## 📌 **Deskripsi Aplikasi**
Aplikasi ini dibuat untuk memenuhi Ujian Tengah Semester (UTS) mata kuliah **Pemrograman Berorientasi Objek (PBO)**.  
Program berjalan di console dan digunakan untuk mengelola:

- Paket wisata (Alam & Budaya)  
- Data pelanggan  
- Transaksi pemesanan  
- Riwayat pesanan 

Fitur utama aplikasi:

- Tambah paket wisata  
- Ubah paket  
- Hapus paket  
- Cari paket (keyword/search)  
- Filter paket (Alam/Budaya)  
- Pemesanan oleh pelanggan  
- Pengurangan kuota otomatis  
- Riwayat pesanan dengan tabel 

---

## 🧠 **Penerapan Konsep OOP**

### 🔹 **1. Class**
Program memiliki 6 class utama:

- `Main`
- `PaketWisata` *(superclass)*
- `WisataAlam` *(subclass)*
- `WisataBudaya` *(subclass)*
- `Pelanggan`
- `Order`

---

### 🔹 **2. Object**
Setiap class digunakan untuk membuat objek.

Contoh objek paket wisata:

```java
WisataAlam a1 = new WisataAlam("A01", "Gunung Tangkuban Perahu", 250000, 20, "Bandung", 1);
WisataBudaya b1 = new WisataBudaya("B01", "Angklung Udjo", 150000, 25, "Garut", 1);
```

Objek pelanggan:
```java
Pelanggan p = new Pelanggan("P001", "M Dafa Dwi Saputra", "081321445567");
```

Objek order:
```java
Order o = new Order("O0001", "A01", 2, 500000);
```

### 🔹 3. Constructor

Setiap class menggunakan constructor berparameter untuk mengisi data awal objek:

```java
public PaketWisata(String kode, String nama, double harga, int kuota) {
    this.kode = kode;
    this.nama = nama;
    this.harga = harga;
    this.kuota = kuota;
}
```

### 🔹 4. Encapsulation

Semua atribut memakai private, dan akses menggunakan getter/setter.

```java
private String nama;

public String getNama() { 
    return nama; 
}

public void setNama(String nama) { 
    this.nama = nama; 
}
```

### 🔹 5. Inheritance

WisataAlam dan WisataBudaya mewarisi PaketWisata.
```java
public class WisataAlam extends PaketWisata { ... }
public class WisataBudaya extends PaketWisata { ... }
```

### 🔹 6. Polymorphism (Override)

Kedua subclass melakukan override method infoTable().
```java
@Override
public String infoTable() {
    return String.format("%s | ALAM | %s | %d hari", super.basicInfo(), lokasi, durasiHari);
}
```

### 🔹 7. Aggregation / Relasi Antar Class

Class Pelanggan memiliki banyak objek Order.
```java
ArrayList<Order> orders = new ArrayList<>();
```

Hubungannya:
1 pelanggan → banyak order

📂 Struktur Project
```java
src/
└── utsaplikasipaketwisata/
    ├── Main.java
    ├── PaketWisata.java
    ├── WisataAlam.java
    ├── WisataBudaya.java
    ├── Pelanggan.java
    └── Order.java
```

Cara Menjalankan Program

Clone repository:
```java
git clone https://github.com/SzukoD/Aplikasi-Paket-Wisata-Java.git
```

Buka di NetBeans / VSCode / IntelliJ

Jalankan:
```java
Main.java
```
