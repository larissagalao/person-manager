package github.personManager.model.adapter;

import github.personManager.model.domain.Address;
import github.personManager.model.domain.MainAddress;
import github.personManager.model.domain.Person;
import github.personManager.model.output.AddressOut;
import github.personManager.model.output.MainAddressOut;
import github.personManager.model.output.PersonOut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonAdapterOut {

    public PersonOut convertDomainToOutput(Person person) {

        PersonOut personOut = new PersonOut();
        List<AddressOut> addressOutList = new ArrayList<>();

        personOut.setNameOut(person.getName());
        personOut.setDateOut(person.getDate());
        personOut.setIdOut(person.getPersonId());

        MainAddressOut mainAddressOut = convertMainAddressToOutput(person.getMainAddress());

        personOut.setMainAddressOut(mainAddressOut);

        for(Address address : person.getAddress()){

            AddressOut addressOut =  new AddressOut();

            addressOut.setId(address.getAddressId());
            addressOut.setStreetOut(address.getStreet());
            addressOut.setCityOut(address.getCity());
            addressOut.setNumberOut(address.getNumber());
            addressOut.setPostalCodeOut(address.getPostalCode());

            addressOutList.add(addressOut);

        }

        personOut.setAddressOutList(addressOutList);

        return personOut;
    }

    private MainAddressOut convertMainAddressToOutput(MainAddress mainAddress) {

        MainAddressOut mainAddressOut = new MainAddressOut();

        if (mainAddress != null) {

            mainAddressOut.setStreet(mainAddress.getStreet());
            mainAddressOut.setNumber(mainAddress.getNumber());
            mainAddressOut.setCity(mainAddress.getCity());
            mainAddressOut.setPostalCode(mainAddress.getPostalCode());
        }

        return mainAddressOut;
    }

}
