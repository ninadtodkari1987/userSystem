package com.solution.userSystem.Controller;

import com.solution.userSystem.Model.Address;
import com.solution.userSystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    
    // 4. Add Address to person [multiple required] (id, street, city, state, postalCode)
    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    // 5. Edit Address (street, city, state, postalCode)
    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") int AddressId, @RequestBody Address Address) throws HttpClientErrorException {

        Address existingAddress = addressRepository.findById(AddressId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        existingAddress.setId(Address.getId());
        existingAddress.setStreet(Address.getStreet());
        existingAddress.setCity(Address.getCity());
        existingAddress.setState(Address.getState());
        existingAddress.setPostalCode(Address.getPostalCode());

        final Address updatedAddress = addressRepository.save(Address);
        return ResponseEntity.ok(updatedAddress);
    }

    // 6. Delete Address (id)
    @DeleteMapping("/address/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable(value = "id") int AddressId)throws HttpClientErrorException {

        Address existingAddress = addressRepository.findById(AddressId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        addressRepository.delete(existingAddress);

        return ResponseEntity.ok(existingAddress);
    }

}
