package com.example.parkingdost;

public class Users {
    String name,mail,password,drivingno,lic,carmodel,mobileno;

//    public Users(String name, String mail, String password, String drivingno, String lic, String carmodel, String mobileno) {
//        this.name = name;
//        this.mail = mail;
//        this.password = password;
//        this.drivingno = drivingno;
//        this.lic = lic;
//        this.carmodel = carmodel;
//        this.mobileno = mobileno;
//    }

    public Users(String name, String mail, String password, String mobileno) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.mobileno = mobileno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDrivingno(String drivingno) {
        this.drivingno = drivingno;
    }

    public void setLic(String lic) {
        this.lic = lic;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getDrivingno() {
        return drivingno;
    }

    public String getLic() {
        return lic;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public String getMobileno() {
        return mobileno;
    }


}
