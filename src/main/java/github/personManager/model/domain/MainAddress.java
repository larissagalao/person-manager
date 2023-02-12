package github.personManager.model.domain;

import jakarta.persistence.*;

@Entity
public class MainAddress {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer mainAddressId;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String postalCode;

    @Column
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getMainAddressId() {
        return mainAddressId;
    }

    public void setMainAddressId(Integer mainAddressId) {
        this.mainAddressId = mainAddressId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
