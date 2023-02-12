package github.personManager.model.adapter;

import github.personManager.exception.CustomException;
import github.personManager.model.domain.Address;
import github.personManager.model.input.AddressIn;
import org.junit.Assert;
import org.junit.Test;

public class AddressAdapterInTest {
    private AddressAdapterIn addressAdapterIn = new AddressAdapterIn();

    @Test
    public void testConvertInputToDomainWithCorrectData() {
        AddressIn addressIn = new AddressIn();
        addressIn.setStreetIn("Rua Teste");
        addressIn.setNumberIn("123");
        addressIn.setCityIn("Cidade Teste");
        addressIn.setPostalCodeIn("12345678");

        Address address = addressAdapterIn.convertInputToDomain(addressIn);

        Assert.assertEquals("Rua Teste", address.getStreet());
        Assert.assertEquals("123", address.getNumber());
        Assert.assertEquals("Cidade Teste", address.getCity());
        Assert.assertEquals("12345678", address.getPostalCode());
    }

    @Test(expected = CustomException.class)
    public void testConvertInputToDomainWithNullBody() {
        addressAdapterIn.convertInputToDomain(null);
    }

    @Test(expected = CustomException.class)
    public void testConvertInputToDomainWithStreetMissing() {
        AddressIn addressIn = new AddressIn();
        addressIn.setNumberIn("123");
        addressIn.setCityIn("Cidade Teste");
        addressIn.setPostalCodeIn("12345678");

        addressAdapterIn.convertInputToDomain(addressIn);
    }

    @Test(expected = CustomException.class)
    public void testConvertInputToDomainWithNumberMissing() {
        AddressIn addressIn = new AddressIn();
        addressIn.setStreetIn("Rua Teste");
        addressIn.setCityIn("Cidade Teste");
        addressIn.setPostalCodeIn("12345678");

        addressAdapterIn.convertInputToDomain(addressIn);
    }

    @Test(expected = CustomException.class)
    public void testConvertInputToDomainWithCityMissing() {
        AddressIn addressIn = new AddressIn();
        addressIn.setStreetIn("Rua Teste");
        addressIn.setNumberIn("123");
        addressIn.setPostalCodeIn("12345678");

        addressAdapterIn.convertInputToDomain(addressIn);
    }

    @Test(expected = CustomException.class)
    public void testConvertInputToDomainWithPostalCodeMissing() {
        AddressIn addressIn = new AddressIn();
        addressIn.setStreetIn("Rua Teste");
        addressIn.setNumberIn("123");
        addressIn.setCityIn("Cidade Teste");

        addressAdapterIn.convertInputToDomain(addressIn);
    }
}
