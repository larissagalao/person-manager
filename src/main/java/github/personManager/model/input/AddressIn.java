package github.personManager.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressIn {

    @JsonProperty("street")
    private String streetIn;

    @JsonProperty("number")
    private String numberIn;

    @JsonProperty("postalCode")
    private String postalCodeIn;

    @JsonProperty("city")
    private String cityIn;

    public String getStreetIn() {
        return streetIn;
    }

    public void setStreetIn(String streetIn) {
        this.streetIn = streetIn;
    }

    public String getNumberIn() {
        return numberIn;
    }

    public void setNumberIn(String numberIn) {
        this.numberIn = numberIn;
    }

    public String getPostalCodeIn() {
        return postalCodeIn;
    }

    public void setPostalCodeIn(String postalCodeIn) {
        this.postalCodeIn = postalCodeIn;
    }

    public String getCityIn() {
        return cityIn;
    }

    public void setCityIn(String cityIn) {
        this.cityIn = cityIn;
    }
}
