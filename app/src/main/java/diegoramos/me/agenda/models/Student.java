package diegoramos.me.agenda.models;

public class Student {
    private String name;
    private String telephone;
    private String address;
    private String site;
    private double rate;

    public Student(String name, String telephone, String address, String site, double rate) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.site = site;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
