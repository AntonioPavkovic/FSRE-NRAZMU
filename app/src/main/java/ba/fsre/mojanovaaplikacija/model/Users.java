package ba.fsre.mojanovaaplikacija.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    public String firstName;

    public String lastName;

    public String phone;

    public String address;

    public String place;

    public String country;


    public Users(){}

    public Users(String firstName, String lastName, String phone, String address, String place, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.place = place;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
