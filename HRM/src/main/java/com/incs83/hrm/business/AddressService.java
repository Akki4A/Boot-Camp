package com.incs83.hrm.business;

import com.incs83.hrm.common.AddressRequest;
import com.incs83.hrm.entities.Address;
import com.incs83.hrm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    public List<AddressRequest> getAllAddress(){
        List<Address> addresses = addressRepository.findAll();
        List<AddressRequest> addressRequestList = new ArrayList<>();
        for (Address address : addresses) {
            AddressRequest addressRequest = new AddressRequest();
            addressRequest.setId(address.getId());
            addressRequest.setPinCode(address.getPinCode());
            addressRequest.setState(address.getState());
            addressRequest.setCity(address.getCity());
            addressRequest.setColony(address.getColony());
            addressRequest.setHouseNumber(address.getHouseNumber());
            addressRequestList.add(addressRequest);
        }
        return addressRequestList;
    }

    public AddressRequest getAddressById(UUID id){
        Address address = addressRepository.findById(id).orElse(new Address());
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setId(address.getId());
        addressRequest.setPinCode(address.getPinCode());
        addressRequest.setState(address.getState());
        addressRequest.setCity(address.getCity());
        addressRequest.setColony(address.getColony());
        addressRequest.setHouseNumber(address.getHouseNumber());
        return addressRequest;
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

    public void deleteAllAddress(){
        addressRepository.deleteAll();
    }
}