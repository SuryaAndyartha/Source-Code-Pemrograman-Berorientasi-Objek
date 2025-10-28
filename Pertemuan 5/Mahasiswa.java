import java.util.ArrayList;

public class Mahasiswa{
    private String nama;
    private String nrp;
    private ArrayList<FRSItem> frs;

    public Mahasiswa(String nama, String nrp){
        this.nama = nama;
        this.nrp = nrp;
        this.frs = new ArrayList<>();
    }

    public void ambilMatkul(MataKuliah matkul, Dosen dosen){
        FRSItem item = new FRSItem(matkul, dosen);
        if(!frs.contains(item)){  
            frs.add(item);
            System.out.println(nama + " berhasil mengambil " + matkul.getNama() + " dengan " + dosen.getNama());
        } 
        else{
            System.out.println("Mata kuliah sudah diambil!");
        }
    }

    public void tampilkanFRS(){
        System.out.println("\nFRS " + nama + " (" + nrp + "):");
        for (FRSItem item : frs) {
            System.out.println("- " + item);
        }
    }
}
