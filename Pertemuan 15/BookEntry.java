public class BookEntry {
    private int serialId;
    private String title;
    private String writer;
    private int year;
    private String category;

    public BookEntry(int serialId, String title, String writer, int year, String category) {
        this.serialId = serialId;
        this.title = title;
        this.writer = writer;
        this.year = year;
        this.category = category;
    }

    // Getter dan Setter dengan penamaan baru
    public int getSerialId() { return serialId; }
    public String getTitle() { return title; }
    public String getWriter() { return writer; }
    public int getYear() { return year; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%d) | Kategori: %s", 
                serialId, title.toUpperCase(), writer, year, category);
    }
}
