public class Tumbuhan extends MakhlukHidup {

    public Tumbuhan(String nama){
        super(nama);
    }

    @Override
    public void bernafas(){
        System.out.println(getNama() + " bernafas menggunakan stomata.");
    }

    @Override
    public void berkembangBiak(){
        System.out.println(getNama() + " berkembang biak dengan biji, tunas, atau stek.");
    }
}
