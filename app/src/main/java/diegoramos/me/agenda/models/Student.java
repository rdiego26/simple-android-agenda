package diegoramos.me.agenda.models;

import java.io.Serializable;

public class Student implements Serializable {
    private Long id;
    private String name;
    private String telephone;
    private String address;
    private String site;
    private double grade;
    private String photoPath;

    public Student() { }

    public Student(String name, String telephone, String address, String site, double grade) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.site = site;
        this.grade = grade;
    }

    public Student(Long id, String name, String telephone, String address, String site, double grade) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.site = site;
        this.grade = grade;
    }

    public Student(Long id, String name, String telephone, String address, String site, double grade, String photoPath) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.site = site;
        this.grade = grade;
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getGrade();
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
