package model;

public class Book {

    private final int id;
    private final String title;
    private final String author;
    private final int numberOfPages;

    public Book(int id,
                String title,
                String author,
                int numberOfPages) {

        validateId(id);
        validateTitle(title);
        validateAuthor(author);
        validateNumberOfPages(numberOfPages);

        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank.");
        }
    }

    private void validateNumberOfPages(int numberOfPages) {
        if (numberOfPages <= 0) {
            throw new IllegalArgumentException("Number of pages must be greater than 0.");
        }
    }
}
