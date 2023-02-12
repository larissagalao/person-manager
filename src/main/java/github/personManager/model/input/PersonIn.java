package github.personManager.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonIn {

	@JsonProperty("name")
    private String nameIn;

	@JsonProperty("date")
	private String dateIn;

	@JsonProperty("address")
	private List<AddressIn> addressIn;

	public String getNameIn() {
		return nameIn;
	}

	public void setNameIn(String nameIn) {
		this.nameIn = nameIn;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public List<AddressIn> getAddressIn() {
		return addressIn;
	}

	public void setAddressIn(List<AddressIn> addressIn) {
		this.addressIn = addressIn;
	}

}
