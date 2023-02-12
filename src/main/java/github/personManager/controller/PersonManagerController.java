package github.personManager.controller;

import github.personManager.exception.CustomException;
import github.personManager.model.adapter.AddressAdapterIn;
import github.personManager.model.adapter.PersonAdapterIn;
import github.personManager.model.adapter.PersonAdapterOut;
import github.personManager.model.domain.Address;
import github.personManager.model.domain.MainAddress;
import github.personManager.model.domain.Person;
import github.personManager.model.input.AddressIn;
import github.personManager.model.input.PersonIn;
import github.personManager.model.output.AddressOut;
import github.personManager.model.output.PersonOut;
import github.personManager.repository.AddressRepository;
import github.personManager.repository.MainAddressRepository;
import github.personManager.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonManagerController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MainAddressRepository mainAddressRepository;

    @Autowired
    private PersonAdapterIn personAdapterIn;

    @Autowired
    private PersonAdapterOut personAdapterOut;

    @Autowired
    private AddressAdapterIn addressAdapterIn;

    @PostMapping("/create/person")
    public void createPerson(@RequestBody PersonIn personIn){
        try{
            Person person = personAdapterIn.convertInputToDomain(personIn);
            for(Address address : person.getAddress()){
                addressRepository.save(address);
            }
            personRepository.save(person);
        }catch (CustomException e){
            throw new CustomException(e.getStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/create/address/{id}")
    public void addAddress(@PathVariable Integer id, @RequestBody AddressIn addressIn){

        try {
            Address address = addressAdapterIn.convertInputToDomain(addressIn);

            Optional<Person> possiblePerson = personRepository.findById(id);
            if (possiblePerson.isPresent()) {
                Person person = possiblePerson.get();
                List<Address> addressList = person.getAddress();
                addressList.add(address);
                person.setAddress(addressList);
                addressRepository.save(address);
                personRepository.save(person);
            }else{
                throw new CustomException(HttpStatus.NOT_FOUND.value(), "person not found to this id" );
            }
        }catch (CustomException e){
            throw new CustomException(e.getStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/update/person/{id}")
    public void updatePerson(@PathVariable Integer id, @RequestBody PersonIn personIn){

        try{
            Optional<Person> possiblePerson = personRepository.findById(id);

            if(possiblePerson.isPresent()) {
                Person oldPerson = possiblePerson.get();
                Person person = personAdapterIn.convertInputToDomain(personIn);

                for(Address address : oldPerson.getAddress()){
                    addressRepository.delete(address);
                }

                oldPerson.setName(person.getName());
                oldPerson.setDate(person.getDate());
                oldPerson.setAddress(person.getAddress());
                oldPerson.setMainAddress(person.getMainAddress());

                for(Address address : person.getAddress()){
                    addressRepository.save(address);
                }

                personRepository.save(oldPerson);
            }else{
                throw new CustomException(HttpStatus.NOT_FOUND.value(), "person not found to this id" );
            }
        }catch (CustomException e){
            throw new CustomException(e.getStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/update/person/{personId}/main-address/{addressId}")
    public void updateMainAddress(@PathVariable Integer personId, @PathVariable Integer addressId){

        Optional<Person> possiblePerson = personRepository.findById(personId);
        if(possiblePerson.isPresent()) {
            Person person = possiblePerson.get();
            List<Integer> list = addressRepository.findAllByPersonId(personId);
            if (list.contains(addressId)) {
                Optional<Address> possibleAddress = addressRepository.findById(addressId);
                Address address = possibleAddress.get();
                MainAddress mainAddress = new MainAddress();
                mainAddress.setCity(address.getCity());
                mainAddress.setNumber(address.getNumber());
                mainAddress.setPostalCode(address.getPostalCode());
                mainAddress.setStreet(address.getStreet());

                mainAddressRepository.save(mainAddress);

                person.setMainAddress(mainAddress);
                personRepository.save(person);
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND.value(), "address not found to this id");
            }
        }else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "person not found to this id");
        }
    }

    @GetMapping("/list/person/all")
    public List<PersonOut> listAllPersons(){

        List<Person> personList = personRepository.findAll();
        List<PersonOut> personOutList = new ArrayList<>();

        for(Person person : personList){
            PersonOut personOut = personAdapterOut.convertDomainToOutput(person);
            personOutList.add(personOut);
        }

        return personOutList;
    }

    @GetMapping("/list/person/{id}")
    public PersonOut getById(@PathVariable Integer id){

        Optional<Person> possiblePerson = personRepository.findById(id);
        if(possiblePerson.isPresent()) {
            Person person = possiblePerson.get();
            PersonOut personOut = personAdapterOut.convertDomainToOutput(person);
            return personOut;
        }
        throw new CustomException(HttpStatus.NOT_FOUND.value(), "person not found to this id");
    }

    @GetMapping("/list/address/{id}")
    public List<AddressOut> getAddress(@PathVariable Integer id){

        List<AddressOut> addressOutList = new ArrayList<>();

        Optional<Person> possiblePerson = personRepository.findById(id);
        if(possiblePerson.isPresent()) {
            Person person = possiblePerson.get();
            PersonOut personOut = personAdapterOut.convertDomainToOutput(person);
            addressOutList = personOut.getAddressOutList();
            return addressOutList;
        }
        throw new CustomException(HttpStatus.NOT_FOUND.value(), "person not found to this id. impossible to list the address.");
    }

}
