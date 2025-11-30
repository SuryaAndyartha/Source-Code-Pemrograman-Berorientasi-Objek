import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Daftar kendaraan tersedia
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList();
        daftarKendaraan.add(new Mobil("Honda", "Brio", 2021, 4));
        daftarKendaraan.add(new Motor("Yamaha", "Aerox", 2023, 2));
        daftarKendaraan.add(new Sepeda("Trek", "Domane SL6", 2022, "Balap"));
        daftarKendaraan.add(new Mobil("Suzuki", "Ertiga", 2020, 4));
        daftarKendaraan.add(new Sepeda("Pacific", "Veloce 5.0", 2019, "MTB"));
        daftarKendaraan.add(new Motor("Kawasaki", "Ninja ZX-25R", 2021, 2));
        daftarKendaraan.add(new Mobil("Toyota", "Fortuner", 2018, 4));
        daftarKendaraan.add(new Sepeda("Element", "Folding Troy", 2023, "Lipat"));
        daftarKendaraan.add(new Motor("Vespa", "Primavera", 2020, 2));
        daftarKendaraan.add(new Mobil("Hyundai", "Stargazer", 2022, 4));

        // Daftar penyewa
        ArrayList<Penyewa> daftarPenyewa = new ArrayList();
        daftarPenyewa.add(new Penyewa("Anton", daftarKendaraan.get(1)));
        daftarPenyewa.add(new Penyewa("Bagas", daftarKendaraan.get(5)));
        daftarPenyewa.add(new Penyewa("Chika", daftarKendaraan.get(3)));

        int pilihan;

        do {
            System.out.println("\n===== MENU RENTAL KENDARAAN =====");
            System.out.println("1. Tampilkan daftar kendaraan");
            System.out.println("2. Tampilkan daftar penyewa");
            System.out.println("3. Tambah penyewa baru");
            System.out.println("4. Hapus penyewa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); 
            
            switch(pilihan) {
                case 1:
                    System.out.println("\n=== DAFTAR KENDARAAN TERSEDIA ===");
                    int index = 1;
                    for (Kendaraan k : daftarKendaraan) {
                        System.out.println(index + ". " + k.getInfo());
                        index++;
                    }
                    break;

                case 2:
                    System.out.println("\n=== DAFTAR PENYEWA ===");
                    if (daftarPenyewa.isEmpty()) {
                        System.out.println("Belum ada penyewa.");
                    } else {
                        for (Penyewa p : daftarPenyewa) {
                            System.out.println(p.getInfo());
                            System.out.println("---------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nTambahkan Penyewa Baru");
                    System.out.print("Nama penyewa: ");
                    String nama = input.nextLine();

                    System.out.println("\nPilih kendaraan yang ingin disewa:");
                    for (int i = 0; i < daftarKendaraan.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarKendaraan.get(i).getInfo());
                    }
                    System.out.print("Masukkan nomor kendaraan: ");
                    int pilihKendaraan = input.nextInt();
                    input.nextLine();

                    if (pilihKendaraan < 1 || pilihKendaraan > daftarKendaraan.size()) {
                        System.out.println("Pilihan tidak valid!");
                    } else {
                        Kendaraan dipilih = daftarKendaraan.get(pilihKendaraan - 1);
                        daftarPenyewa.add(new Penyewa(nama, dipilih));
                        System.out.println("Penyewa berhasil ditambahkan!");
                    }
                    break;

                case 4:
                    System.out.println("\nMasukkan nama penyewa yang akan dihapus: ");
                    String hapusNama = input.nextLine();
                    
                    boolean ditemukan = false;
                    for (int i = 0; i < daftarPenyewa.size(); i++) {
                        if (daftarPenyewa.get(i).getNama().equalsIgnoreCase(hapusNama)) {
                            daftarPenyewa.remove(i);
                            System.out.println("Penyewa berhasil dihapus!");
                            ditemukan = true;
                            break;
                        }
                    }
                    if (!ditemukan) {
                        System.out.println("Penyewa tidak ditemukan.");
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem rental!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while(pilihan != 5);

        input.close();
    }
}
