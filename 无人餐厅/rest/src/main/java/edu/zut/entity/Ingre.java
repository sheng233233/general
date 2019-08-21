package edu.zut.entity;

public class Ingre {
    private Integer id;

    private String name;

    private String surplus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus == null ? null : surplus.trim();
    }

    @Override
    public String toString() {
        return "Ingre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surplus='" + surplus + '\'' +
                '}';
    }
}