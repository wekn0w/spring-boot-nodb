package app.web.domain;

import java.util.UUID;

public class Author {

    private Long id;
    private String fullname;
    private Integer age;

    public Author() {
        this.id = UUID.randomUUID().getMostSignificantBits();
    }

    public Author(String name) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.fullname = name;
    }

    public Author(String name, int age) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.fullname = name;
        this.age = age;
    }

   public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
