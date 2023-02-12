package github.personManager.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressOut {

    @JsonProperty("addressId")
    private Integer id;

    @JsonProperty("street")
    private String streetOut;

    @JsonProperty("number")
    private String numberOut;

    @JsonProperty("postalCode")
    private String postalCodeOut;

    @JsonProperty("city")
    private String cityOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetOut() {
        return streetOut;
    }

    public void setStreetOut(String streetOut) {
        this.streetOut = streetOut;
    }

    public String getNumberOut() {
        return numberOut;
    }

    public void setNumberOut(String numberOut) {
        this.numberOut = numberOut;
    }

    public String getPostalCodeOut() {
        return postalCodeOut;
    }

    public void setPostalCodeOut(String postalCodeOut) {
        this.postalCodeOut = postalCodeOut;
    }

    public String getCityOut() {
        return cityOut;
    }

    public void setCityOut(String cityOut) {
        this.cityOut = cityOut;
    }

}
