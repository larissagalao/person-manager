package github.personManager.model.adapter;

import github.personManager.exception.CustomException;
import github.personManager.model.domain.Address;
import github.personManager.model.input.AddressIn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapterIn {

    public Address convertInputToDomain(AddressIn addressIn){

        if(addressIn == null){
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "body is null. \n");
        }

        validAddressIn(addressIn);

        Address address = new Address();

        address.setStreet(addressIn.getStreetIn());
        address.setCity(addressIn.getCityIn());
        address.setNumber(addressIn.getNumberIn());
        address.setPostalCode(addressIn.getPostalCodeIn());

        return address;
    }

    private void validAddressIn(AddressIn addressIn) {

        StringBuilder sb = new StringBuilder();


        if (addressIn.getStreetIn() == null || addressIn.getStreetIn().isBlank()) {
            sb.append("street is missing. \n");
        }
        if (addressIn.getNumberIn() == null || addressIn.getNumberIn().isBlank()) {
            sb.append("number is missing. \n");
        }
        if (addressIn.getCityIn() == null || addressIn.getCityIn().isBlank()) {
            sb.append("city is missing. \n");
        }
        if (addressIn.getPostalCodeIn() == null || addressIn.getPostalCodeIn().isBlank()) {
            sb.append("postalCode is missing. \n");
        }

        if (sb.length() > 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), sb.toString());
        }
    }
}
