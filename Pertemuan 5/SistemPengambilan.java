import java.util.Scanner;

public class SistemPengambilan{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        // Untuk dosen
        Dosen d1 = new Dosen("Dr. Dwi Sunaryono, S.Kom., M.Kom.", "12345");
        Dosen d2 = new Dosen("Dr. Yudhi Purwananto, S.Kom., M.Kom.", "67890");
        Dosen d3 = new Dosen("Dr. Adhatus Solichah Ahmadiyah, S.Kom., M.Sc.", "54321");
        Dosen d4 = new Dosen("Dr. Kelly Rossa Sungkono, S.Kom., M.Kom.", "98765");
        Dosen d5 = new Dosen("Dr. Wahyu Suadi, S.Kom, M.Kom.", "98765");
        Dosen d6 = new Dosen("Prof. Ir. Supeno Djanali, M.Sc Ph.D.", "98765");

        // Untuk mata kuliah
        MataKuliah mk1 = new MataKuliah("IF101", "Dasar Pemrograman", 4);
        mk1.tambahDosen(d1);
        mk1.tambahDosen(d2);

        MataKuliah mk2 = new MataKuliah("IF102", "Sistem Basis Data", 4);
        mk2.tambahDosen(d3);
        mk2.tambahDosen(d4);

        MataKuliah mk3 = new MataKuliah("IF103", "Sistem Digital", 3);
        mk3.tambahDosen(d5);
        mk3.tambahDosen(d6);

        MataKuliah[] daftarMatkul = {mk1, mk2, mk3};

        // Input data mahasiswa
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.nextLine();
        System.out.print("Masukkan NRP mahasiswa: ");
        String NRP = input.nextLine();
        Mahasiswa mhs = new Mahasiswa(nama, NRP);

        // Pilih mata kuliah
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\nDaftar Mata Kuliah:");
            for (int i = 0; i < daftarMatkul.length; i++) {
                System.out.println((i+1) + ". " + daftarMatkul[i]);
            }
            System.out.print("Pilih mata kuliah (1-" + daftarMatkul.length + "), atau 0 untuk selesai: ");
            int pilih = input.nextInt();
            input.nextLine(); 

            if (pilih == 0) {
                lanjut = false;
            } else if (pilih >= 1 && pilih <= daftarMatkul.length) {
                MataKuliah matkulDipilih = daftarMatkul[pilih-1];

                System.out.println("Dosen untuk " + matkulDipilih.getNama() + ":");
                for (int j = 0; j < matkulDipilih.getDosenPengampu().size(); j++) {
                    System.out.println((j+1) + ". " + matkulDipilih.getDosenPengampu().get(j));
                }
                System.out.print("Pilih dosen (1-" + matkulDipilih.getDosenPengampu().size() + "): ");
                int pilihDosen = input.nextInt();
                input.nextLine();

                if (pilihDosen >= 1 && pilihDosen <= matkulDipilih.getDosenPengampu().size()) {
                    Dosen dosenDipilih = matkulDipilih.getDosenPengampu().get(pilihDosen-1);
                    mhs.ambilMatkul(matkulDipilih, dosenDipilih);
                } else {
                    System.out.println("Pilihan dosen tidak valid.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        mhs.tampilkanFRS();
        input.close();
    }
}
