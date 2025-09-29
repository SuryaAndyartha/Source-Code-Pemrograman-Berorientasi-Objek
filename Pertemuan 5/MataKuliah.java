import java.util.ArrayList;

public class MataKuliah{
    private String kode;
    private String nama;
    private int sks;
    private ArrayList<Dosen> dosenPengampu;

    public MataKuliah(String kode, String nama, int sks){
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.dosenPengampu = new ArrayList<>();
    }

    public void tambahDosen(Dosen d){
        dosenPengampu.add(d);
    }

    public String getKode(){ 
        return kode; 
    }
    public String getNama(){ 
        return nama; 
    }
    public int getSks(){ 
        return sks; 
    }
    public ArrayList<Dosen> getDosenPengampu(){ 
        return dosenPengampu; 
    }

    public String toString(){
        return kode + " - " + nama + " (" + sks + " SKS)";
    }
}
