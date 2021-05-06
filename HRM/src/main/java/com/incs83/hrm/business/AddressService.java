package com.incs83.hrm.business;

import com.incs83.hrm.common.AddressRequest;
import com.incs83.hrm.entities.Address;
import com.incs83.hrm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        addressRepository.save(address);
    }
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id){
        return addressRepository.findById(id).orElse(null);
    }

    public void updateAddressById(AddressRequest addressRequest, Integer id) {
        Address existingAddress = addressRepository.findById(id).orElse(null);
        if (existingAddress != null) {
            existingAddress.setState(addressRequest.getState());
            existingAddress.setCity(addressRequest.getCity());
            existingAddress.setColony(addressRequest.getColony());
            existingAddress.setHouseNumber(addressRequest.getHouseNumber());
            addressRepository.save(existingAddress);
        }

    }
    public void deleteAddressById(Integer id){
        addressRepository.deleteById(id);
    }

}