package com.example.parkingdost.data;

class users{
    String name;
    String moblileno;
    String carmo;
    String licenceplate;
    String password;
    String driverno;
    String ggle;
//     public user(String name,String ggle,String moblileno,String password)
//     {
//      this.moblileno=moblileno;
//      this.ggle=ggle;
//      this.password=password;
//      this.name=name;
//     }

    public users(String moblileno, String carmo, String licenceplate, String driverno) {
        this.moblileno = moblileno;
        this.carmo = carmo;
        this.licenceplate = licenceplate;
        this.driverno = driverno;
    }

    public users() {
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setMoblileno(String moblileno) {
        this.moblileno = moblileno;
    }

    public void setCarmo(String carmo) {
        this.carmo = carmo;
    }

    public void setLicenceplate(String licenceplate) {
        this.licenceplate = licenceplate;
    }

    public void setDriverno(String driverno) {
        this.driverno = driverno;
    }

    public void setGgle(String ggle) {
        this.ggle = ggle;
    }

    public void setFbb(String fbb) {
        this.fbb = fbb;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String fbb;

    public String getName() {
        return name;
    }

    public String getMoblileno() {
        return moblileno;
    }

    public String getCarmo() {
        return carmo;
    }

    public String getLicenceplate() {
        return licenceplate;
    }

    public String getDriverno() {
        return driverno;
    }

    public String getGgle() {
        return ggle;
    }

    public String getFbb() {
        return fbb;
    }

    public String getPassword() {
        return password;
    }

    public users(String name, String moblileno, String carmo, String licenceplate, String driverno, String ggle, String fbb, String password) {
        this.name = name;
        this.moblileno = moblileno;
        this.carmo = carmo;
        this.licenceplate = licenceplate;
        this.driverno = driverno;
        this.ggle = ggle;
        this.fbb = fbb;
        this.password = password;
    }



}
