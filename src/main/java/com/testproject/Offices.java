package com.testproject;

public class Offices {
    private String officecode, city, phone, addressline1,
            addressline2, state, country, postalcode,
            territory;

    public Offices(String officecode, String city, String phone, String addressline1, String addressline2,
                    String state, String country, String postalcode, String territory) {
        this.officecode = officecode;
        this.city = city;
        this.phone = phone;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.state = state;
        this.country = country;
        this.postalcode = postalcode;
        this.territory = territory;
    }

    public String getOfficecode() {
        return officecode;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getTerritory() {
        return territory;
    }
}
