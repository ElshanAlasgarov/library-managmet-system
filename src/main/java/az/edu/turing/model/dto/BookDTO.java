package az.edu.turing.model.dto;

public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean status;

    public BookDTO(int id, String title, String author, String category, boolean status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    public BookDTO(String title, String author, String category) {
        this(0, title, author, category, true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


