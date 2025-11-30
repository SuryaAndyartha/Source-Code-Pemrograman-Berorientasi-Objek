public class Manusia extends MakhlukHidup {

    public Manusia(String nama){
        super(nama);
    }

    @Override
    public void bernafas(){
        System.out.println(getNama() + " bernafas menggunakan paru-paru.");
    }

    @Override
    public void berkembangBiak(){
        System.out.println(getNama() + " berkembang biak secara melahirkan.");
    }
}
