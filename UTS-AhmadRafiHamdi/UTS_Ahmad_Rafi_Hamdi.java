// 1_Ahmad Rafi Hamdi
// SIB1G

import java.util.Scanner;

class Barang {
    String kode, namaBarang;
    int stok, hargaSatuan;
    int jumlahTerjual = 0;

    Barang(String kode, String namaBarang, int stok, int hargaSatuan) {
        this.kode = kode;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.hargaSatuan = hargaSatuan;
    }

    void tampilkan() {
        System.out.printf("%-5s %-15s %-10d %-13d %-10d%n", kode, namaBarang, stok, hargaSatuan, jumlahTerjual);
    }
}

public class UTS_Ahmad_Rafi_Hamdi {
    static Scanner input = new Scanner(System.in);
    static Barang[] daftarBarang = new Barang[100];
    static int jumlahBarang = 0;

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== MENU TOKO ABC ===");
            System.out.println("1. Input Data Barang");
            System.out.println("2. Penjualan Barang");
            System.out.println("3. Urutkan Data Barang (Stok Terbanyak)");
            System.out.println("4. Tampilkan Barang Terlaris");
            System.out.println("5. Tampilkan Semua Data Barang");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1: inputDataBarang(); break;
                case 2: penjualanBarang(); break;
                case 3: urutkanBarangBerdasarkanStok(); break;
                case 4: tampilkanBarangTerlaris(); break;
                case 5: tampilkanSemuaBarang(); break;
                case 6: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 6);
    }

    static void inputDataBarang() {
        System.out.print("Berapa banyak barang yang ingin diinput? ");
        int jumlah = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nBarang ke-" + (i + 1));
            System.out.print("Kode: ");
            String kode = input.nextLine();
            System.out.print("Nama Barang: ");
            String nama = input.nextLine();
            System.out.print("Stok: ");
            int stok = input.nextInt();
            System.out.print("Harga Satuan: ");
            int harga = input.nextInt();
            input.nextLine();

            daftarBarang[jumlahBarang] = new Barang(kode, nama, stok, harga);
            jumlahBarang++;
        }
    }

    static void penjualanBarang() {
        if (jumlahBarang == 0) {
            System.out.println("Belum ada data barang!");
            return;
        }

        System.out.print("Berapa jenis barang yang ingin dibeli? ");
        int jumlahJenis = input.nextInt();
        input.nextLine();

        String[][] struk = new String[jumlahJenis][5];
        int totalBayar = 0;

        for (int i = 0; i < jumlahJenis; i++) {
            System.out.print("\nMasukkan kode barang ke-" + (i + 1) + ": ");
            String kode = input.nextLine();
            Barang barang = cariBarang(kode);

            if (barang == null) {
                System.out.println("Barang tidak ditemukan!");
                i--;
                continue;
            }

            System.out.print("Jumlah beli: ");
            int jumlahBeli = input.nextInt();
            input.nextLine();

            if (jumlahBeli > barang.stok) {
                System.out.println("Stok tidak cukup! Sisa stok: " + barang.stok);
                i--;
                continue;
            }

            int totalHarga = jumlahBeli * barang.hargaSatuan;
            barang.stok -= jumlahBeli;
            barang.jumlahTerjual += jumlahBeli;

            struk[i][0] = barang.kode;
            struk[i][1] = barang.namaBarang;
            struk[i][2] = String.valueOf(jumlahBeli);
            struk[i][3] = String.valueOf(barang.hargaSatuan);
            struk[i][4] = String.valueOf(totalHarga);

            totalBayar += totalHarga;
        }

        System.out.println("\n=== STRUK PENJUALAN ===");
        System.out.printf("%-5s %-15s %-12s %-13s %-12s%n", "Kode", "Nama Barang", "Jumlah Beli", "Harga Satuan", "Total Harga");

        for (int i = 0; i < jumlahJenis; i++) {
            System.out.printf("%-5s %-15s %-12s %-13s %-12s%n",
                struk[i][0], struk[i][1], struk[i][2], struk[i][3], struk[i][4]);
        }

        System.out.println("Total Bayar: " + totalBayar);
    }

    static Barang cariBarang(String kode) {
        for (int i = 0; i < jumlahBarang; i++) {
            if (daftarBarang[i].kode.equalsIgnoreCase(kode)) {
                return daftarBarang[i];
            }
        }
        return null;
    }

    static void urutkanBarangBerdasarkanStok() {
        if (jumlahBarang == 0) {
            System.out.println("Belum ada data barang.");
            return;
        }

        for (int i = 0; i < jumlahBarang - 1; i++) {
            for (int j = 0; j < jumlahBarang - i - 1; j++) {
                if (daftarBarang[j].stok < daftarBarang[j + 1].stok) {
                    Barang temp = daftarBarang[j];
                    daftarBarang[j] = daftarBarang[j + 1];
                    daftarBarang[j + 1] = temp;
                }
            }
        }

        System.out.println("\nData barang setelah diurutkan berdasarkan stok:");
        System.out.printf("%-5s %-15s %-10s %-13s %-10s%n", "Kode", "Nama Barang", "Stok", "Harga Satuan", "Terjual");
        for (int i = 0; i < jumlahBarang; i++) {
            daftarBarang[i].tampilkan();
        }
    }

    static void tampilkanBarangTerlaris() {
        if (jumlahBarang == 0) {
            System.out.println("Belum ada data barang.");
            return;
        }

        Barang terlaris = daftarBarang[0];

        for (int i = 1; i < jumlahBarang; i++) {
            if (daftarBarang[i].jumlahTerjual > terlaris.jumlahTerjual) {
                terlaris = daftarBarang[i];
            }
        }

        if (terlaris.jumlahTerjual == 0) {
            System.out.println("Belum ada transaksi penjualan.");
        } else {
            System.out.println("\n=== Barang Terlaris ===");
            System.out.println("Kode        : " + terlaris.kode);
            System.out.println("Nama Barang : " + terlaris.namaBarang);
            System.out.println("Jumlah Terjual: " + terlaris.jumlahTerjual);
        }
    }

    static void tampilkanSemuaBarang() {
        if (jumlahBarang == 0) {
            System.out.println("Belum ada data barang.");
            return;
        }

        System.out.println("\n=== Daftar Seluruh Barang ===");
        System.out.printf("%-5s %-15s %-10s %-13s %-10s%n", "Kode", "Nama Barang", "Stok", "Harga Satuan", "Terjual");

        for (int i = 0; i < jumlahBarang; i++) {
            daftarBarang[i].tampilkan();
        }
    }
}
