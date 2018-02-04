package diegoramos.me.agenda.models;

public class Student {
    private Long id;
    private String name;
    private String telephone;
    private String address;
    private String site;
    private double grade;

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

    @Override
    public String toString() {
        return this.getName() + " - " + this.getGrade();
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
