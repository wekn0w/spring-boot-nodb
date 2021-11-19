package web.dto;

public class ReviewDto {
    private Long id;
    private String comment;
    private BookDto book;

    public ReviewDto() {
    }

    public ReviewDto(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public ReviewDto(Long id, String comment, BookDto book) {
        this.id = id;
        this.comment = comment;
        this.book = book;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
