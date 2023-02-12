package github.personManager.model.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import github.personManager.model.domain.Address;
import github.personManager.model.domain.MainAddress;
import github.personManager.model.domain.Person;
import github.personManager.model.output.AddressOut;
import github.personManager.model.output.MainAddressOut;
import github.personManager.model.output.PersonOut;

public class PersonAdapterOutTest {

    private PersonAdapterOut personAdapterOut;

    @Before
    public void setUp() {
        personAdapterOut = new PersonAdapterOut();
    }

    @Test
    public void testConvertDomainToOutput() {
        Person person = new Person();
        person.setName("Nome Teste");
        person.setDate("01/01/2000");
        person.setPersonId(1);

        MainAddress mainAddress = new MainAddress();
        mainAddress.setStreet("Rua Principal Teste");
        mainAddress.setNumber("0");
        mainAddress.setCity("Cidade Principal Teste");
        mainAddress.setPostalCode("12345678");
        person.setMainAddress(mainAddress);

        List<Address> addressList = new ArrayList<>();
        Address address1 = new Address();
        address1.setAddressId(1);
        address1.setStreet("Rua Teste");
        address1.setNumber("1");
        address1.setCity("Cidade Teste");
        address1.setPostalCode("12345678");
        addressList.add(address1);

        person.setAddress(addressList);

        PersonOut personOut = personAdapterOut.convertDomainToOutput(person);

        assertNotNull(personOut);
        assertEquals("Nome Teste", personOut.getNameOut());
        assertEquals("01/01/2000", personOut.getDateOut());
        assertEquals(1, personOut.getIdOut().intValue());

        MainAddressOut mainAddressOut = personOut.getMainAddressOut();
        assertNotNull(mainAddressOut);
        assertEquals("Rua Principal Teste", mainAddressOut.getStreet());
        assertEquals("0", mainAddressOut.getNumber());
        assertEquals("Cidade Principal Teste", mainAddressOut.getCity());
        assertEquals("12345678", mainAddressOut.getPostalCode());

        List<AddressOut> addressOutList = personOut.getAddressOutList();
        assertNotNull(addressOutList);
        assertEquals(1, addressOutList.size());

        AddressOut addressOut1 = addressOutList.get(0);
        assertNotNull(addressOut1);
        assertEquals("Rua Teste", addressOut1.getStreetOut());
        assertEquals("1", addressOut1.getNumberOut());
        assertEquals("Cidade Teste", addressOut1.getCityOut());
        assertEquals("12345678", mainAddressOut.getPostalCode());
    }
}
