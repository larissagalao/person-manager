package github.personManager.model.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import github.personManager.exception.CustomException;
import github.personManager.model.domain.Person;
import github.personManager.model.input.AddressIn;
import github.personManager.model.input.PersonIn;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapterInTest {

    private PersonAdapterIn adapter = new PersonAdapterIn();

    @Test
    public void testConvertInputToDomainWithCorrectData() throws CustomException {
        PersonIn personIn = new PersonIn();
        personIn.setNameIn("Nome Teste");
        personIn.setDateIn("2022-01-01");

        List<AddressIn> addressInList = new ArrayList<>();
        AddressIn addressIn = new AddressIn();
        addressIn.setCityIn("Cidade Teste");
        addressIn.setNumberIn("123");
        addressIn.setStreetIn("Rua Teste");
        addressIn.setPostalCodeIn("12345678");
        addressInList.add(addressIn);
        personIn.setAddressIn(addressInList);

        Person person = adapter.convertInputToDomain(personIn);

        assertEquals("Nome Teste", person.getName());
        assertEquals("2022-01-01", person.getDate());
        assertEquals("Cidade Teste", person.getAddress().get(0).getCity());
        assertEquals("123", person.getAddress().get(0).getNumber());
        assertEquals("Rua Teste", person.getAddress().get(0).getStreet());
        assertEquals("12345678", person.getAddress().get(0).getPostalCode());
    }

    @Test
    public void testConvertInputToDomainWithNullBody() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            adapter.convertInputToDomain(null);
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals("body is null. \n", exception.getMessage());
    }

    @Test
    public void convertInputToDomainWithMissingData() {

        PersonIn personIn = new PersonIn();
        personIn.setNameIn("");
        personIn.setDateIn(" ");

        AddressIn addressIn = new AddressIn();
        addressIn.setCityIn(" ");
        addressIn.setStreetIn(" ");
        addressIn.setNumberIn(" ");
        addressIn.setPostalCodeIn(" ");

        AddressIn addressIn2 = new AddressIn();
        addressIn2.setCityIn(" ");
        addressIn2.setStreetIn(" ");
        addressIn2.setNumberIn(" ");
        addressIn2.setPostalCodeIn(" ");

        List<AddressIn> addressList = new ArrayList<>();

        addressList.add(addressIn);
        addressList.add(addressIn2);

        personIn.setAddressIn(addressList);

        CustomException exception = assertThrows(CustomException.class, () -> adapter.convertInputToDomain(personIn));
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatusCode());
        assertEquals("name is missing. \n" +
                "data is missing. \n" +
                "address[0]:city is missing. \n" +
                "address[0]:number is missing. \n" +
                "address[0]:postalCode is missing. \n" +
                "address[0]:street is missing. \n" +
                "address[1]:city is missing. \n" +
                "address[1]:number is missing. \n" +
                "address[1]:postalCode is missing. \n" +
                "address[1]:street is missing. \n", exception.getMessage());
    }
}

