package com.nguyenoanh.downloadfile.Model;

public class Company {
    private String nameCompany;
    private String catchPharse;
    private String bs;

    public Company() {
    }

    public Company(String nameCompany, String catchPharse, String bs) {
        this.nameCompany = nameCompany;
        this.catchPharse = catchPharse;
        this.bs = bs;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCatchPharse() {
        return catchPharse;
    }

    public void setCatchPharse(String catchPharse) {
        this.catchPharse = catchPharse;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
