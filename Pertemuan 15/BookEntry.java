import java.sql.*;
import java.util.Scanner;

public class LibraryApp {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseGateway gateway = new DatabaseGateway();
        boolean isActive = true;

        while (isActive) {
            System.out.println("\n--- SISTEM INFORMASI PERPUSTAKAAN ---");
            System.out.println("""
                1) Registrasi Buku Baru
                2) Daftar Koleksi
                3) Edit Data
                4) Hapus Buku
                5) Keluar
                """);
            System.out.print("Pilih menu: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> processInsert(gateway);
                case "2" -> displayAll(gateway);
                case "3" -> processUpdate(gateway);
                case "4" -> processDelete(gateway);
                case "5" -> isActive = false;
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        }

        gateway.terminate();
    }

    // ================= INSERT =================
    private static void processInsert(DatabaseGateway dw) {

        if (dw.openLink() == null) {
            System.out.println("Koneksi database belum aktif.");
            return;
        }

        System.out.print("Judul   : ");
        String t = input.nextLine().trim();

        System.out.print("Penulis : ");
        String w = input.nextLine().trim();

        int y;
        while (true) {
            try {
                System.out.print("Tahun   : ");
                y = Integer.parseInt(input.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Tahun harus berupa angka!");
            }
        }

        String c;
        while (true) {
            System.out.print("Kategori: ");
            c = input.nextLine().trim();
            if (!c.isEmpty()) break;
            System.out.println("Kategori tidak boleh kosong!");
        }

        String query = """
            INSERT INTO book_records
            (book_title, writer_name, release_year, category_type)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setString(1, t);
            ps.setString(2, w);
            ps.setInt(3, y);
            ps.setString(4, c);

            ps.executeUpdate();
            System.out.println("Data berhasil disimpan.");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data");
            e.printStackTrace();
        }
    }

    // ================= READ =================
    private static void displayAll(DatabaseGateway dw) {

        if (dw.openLink() == null) {
            System.out.println("Koneksi database belum aktif.");
            return;
        }

        String query = "SELECT * FROM book_records";

        try (Statement st = dw.openLink().createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.println("\n--- DAFTAR BUKU ---");

            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.println(new BookEntry(
                    rs.getInt("serial_id"),
                    rs.getString("book_title"),
                    rs.getString("writer_name"),
                    rs.getInt("release_year"),
                    rs.getString("category_type")
                ));
            }

            if (empty) {
                System.out.println("(Belum ada data buku)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    private static void processUpdate(DatabaseGateway dw) {

        System.out.print("ID Buku     : ");
        int id = Integer.parseInt(input.nextLine().trim());

        System.out.print("Judul Baru  : ");
        String t = input.nextLine().trim();

        String query =
            "UPDATE book_records SET book_title = ? WHERE serial_id = ?";

        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setString(1, t);
            ps.setInt(2, id);

            int affected = ps.executeUpdate();
            if (affected > 0)
                System.out.println("Update berhasil.");
            else
                System.out.println("ID tidak ditemukan.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    private static void processDelete(DatabaseGateway dw) {

        System.out.print("ID Buku : ");
        int id = Integer.parseInt(input.nextLine().trim());

        String query = "DELETE FROM book_records WHERE serial_id = ?";

        try (PreparedStatement ps = dw.openLink().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data telah dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
