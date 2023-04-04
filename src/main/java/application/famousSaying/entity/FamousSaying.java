package application.famousSaying.entity;


public class FamousSaying {
    private long id;
    private String author;
    private String contents;

    // id, author, contents를 입력받아 필드에 저장하는 생성자 메서드
    public FamousSaying(long id, String author, String contents) {
        this.id = id;
        this.author = author;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    // "Id / Author / Contents" 의 형태로 문자열을 만들어 반환하는 메서드
    public String getList() {
        return this.id +
                " / " + this.author +
                " / " + this.contents;
    }
}

