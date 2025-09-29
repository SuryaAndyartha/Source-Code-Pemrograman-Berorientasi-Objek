public class FRSItem{
    private MataKuliah matkul;
    private Dosen dosen;

    public FRSItem(MataKuliah matkul, Dosen dosen){
        this.matkul = matkul;
        this.dosen = dosen;
    }

    public MataKuliah getMatkul(){ 
        return matkul; 
    }
    public Dosen getDosen(){ 
        return dosen; 
    }

    public String toString(){
        return matkul.getKode() + " - " + matkul.getNama() + " (" + matkul.getSks() + " SKS)"
               + " | Dosen: " + dosen.getNama();
    }
}
