package github.personManager.model.adapter;

import github.personManager.exception.CustomException;
import github.personManager.model.domain.Address;
import github.personManager.model.domain.Person;
import github.personManager.model.input.AddressIn;
import github.personManager.model.input.PersonIn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonAdapterIn{

    public Person convertInputToDomain(PersonIn personIn) throws CustomException {

        if(personIn == null){
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "body is null. \n");
        }

        validPersonIn(personIn);

        Person person = new Person();
        List<Address> addressList = new ArrayList<>();

        person.setName(personIn.getNameIn());
        person.setDate(personIn.getDateIn());

        for(AddressIn addressIn : personIn.getAddressIn()){

            Address address = new Address();

            address.setCity(addressIn.getCityIn());
            address.setNumber(addressIn.getNumberIn());
            address.setStreet(addressIn.getStreetIn());
            address.setPostalCode(addressIn.getPostalCodeIn());

            addressList.add(address);
        }

        person.setAddress(addressList);

        return person;
    }

    private static void validPersonIn(PersonIn personIn) throws CustomException {

        StringBuilder sb = new StringBuilder();

        if (personIn.getNameIn() == null || personIn.getNameIn().isBlank()) {
            sb.append("name is missing. \n");
        }
        if (personIn.getDateIn() == null || personIn.getDateIn().isBlank()) {
            sb.append("data is missing. \n");
        }
        if (personIn.getAddressIn() == null) {
            sb.append("address is missing. \n");
        } else {
            for(AddressIn addressIn : personIn.getAddressIn()){
                if (addressIn.getCityIn() == null || addressIn.getCityIn().isBlank() ) {
                    sb.append("address[").append(personIn.getAddressIn().indexOf(addressIn)).append("]:").append("city is missing. \n");
                }
                if (addressIn.getNumberIn() == null || addressIn.getNumberIn().isBlank()) {
                    sb.append("address[").append(personIn.getAddressIn().indexOf(addressIn)).append("]:").append("number is missing. \n");
                }
                if (addressIn.getPostalCodeIn() == null || addressIn.getPostalCodeIn().isBlank()) {
                    sb.append("address[").append(personIn.getAddressIn().indexOf(addressIn)).append("]:").append("postalCode is missing. \n");
                }
                if (addressIn.getStreetIn() == null || addressIn.getStreetIn().isBlank()) {
                    sb.append("address[").append(personIn.getAddressIn().indexOf(addressIn)).append("]:").append("street is missing. \n");
                }
            }
        }
        if (sb.length() != 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), sb.toString());
        }
    }
}
