public class MainApp {
    public static void main(String[] args) {
        MakhlukHidup m1 = new Manusia("Andi");
        MakhlukHidup m2 = new Hewan("Kucing");
        MakhlukHidup m3 = new Tumbuhan("Mangga");

        m1.info();
        m1.bernafas();
        m1.berkembangBiak();

        System.out.println();

        m2.info();
        m2.bernafas();
        m2.berkembangBiak();

        System.out.println();

        m3.info();
        m3.bernafas();
        m3.berkembangBiak();
    }
}
