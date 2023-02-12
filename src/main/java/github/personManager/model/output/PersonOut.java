package github.personManager.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonOut {

    @JsonProperty("name")
    private String nameOut;

    @JsonProperty("personId")
    private Integer idOut;

    @JsonProperty("date")
    private String dateOut;

    @JsonProperty("addressList")
    private List<AddressOut> addressOutList;

    @JsonProperty("mainAddress")
    private MainAddressOut mainAddressOut;

    public MainAddressOut getMainAddressOut() {
        return mainAddressOut;
    }

    public void setMainAddressOut(MainAddressOut mainAddressOut) {
        this.mainAddressOut = mainAddressOut;
    }

    public String getNameOut() {
        return nameOut;
    }

    public void setNameOut(String nameOut) {
        this.nameOut = nameOut;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public List<AddressOut> getAddressOutList() {
        return addressOutList;
    }

    public void setAddressOutList(List<AddressOut> addressOutList) {
        this.addressOutList = addressOutList;
    }

    public Integer getIdOut() {
        return idOut;
    }

    public void setIdOut(Integer idOut) {
        this.idOut = idOut;
    }
}
