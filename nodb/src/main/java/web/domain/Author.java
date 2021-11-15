package web.domain;

import java.util.UUID;

public class Author {

    private UUID id;
    private String fullname;
    private Integer age;

    public Author(UUID id, String name, int age) {
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
