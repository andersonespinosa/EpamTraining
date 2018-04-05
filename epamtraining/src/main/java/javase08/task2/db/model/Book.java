package javase08.task2.db.model;

public class Book extends Entity {

    private String title;
    private String author;
    private String publishingHouse;
    private int pages;

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

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + ", author='"
                + author + ", publishingHouse='"
                + publishingHouse +
                ", pages=" + pages + ", getId()=" + getId() + '}';
    }
}
