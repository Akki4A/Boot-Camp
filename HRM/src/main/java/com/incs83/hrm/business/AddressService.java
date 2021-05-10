package com.incs83.hrm.business;

import com.incs83.hrm.common.AddressRequest;
import com.incs83.hrm.entities.Address;
import com.incs83.hrm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void createAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setPinCode(addressRequest.getPinCode());
        address.setState(addressRequest.getState());
        address.setCity(addressRequest.getCity());
        address.setColony(addressRequest.getColony());
        address.setHouseNumber(addressRequest.getHouseNumber());
        address.setCreatedAt(new Timestamp(new Date().getTime()));
        address.setCreatedBy("Dev_Department");
        addressRepository.save(address);
    }
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID id){
        return addressRepository.findById(id).orElse(null);
    }

    public void updateAddressById(AddressRequest addressRequest, UUID id) {
        Address existingAddress = addressRepository.findById(id).orElse(null);
        if (existingAddress != null) {
            existingAddress.setState(addressRequest.getState());
            existingAddress.setCity(addressRequest.getCity());
            existingAddress.setColony(addressRequest.getColony());
            existingAddress.setHouseNumber(addressRequest.getHouseNumber());
            existingAddress.setUpdatedAt(new Timestamp(new Date().getTime()));
            existingAddress.setUpdatedBy("Dev_Department");
            addressRepository.save(existingAddress);
        }

    }
    public void deleteAddressById(UUID id){
        addressRepository.deleteById(id);
    }

}