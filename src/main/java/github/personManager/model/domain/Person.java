package github.personManager.model.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;

    @Column
    private String name;

    @Column
    private String date;

    @OneToMany
    @JoinColumn
    private List<Address> address;

    @OneToOne
    private MainAddress mainAddress;

    public MainAddress getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(MainAddress mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
