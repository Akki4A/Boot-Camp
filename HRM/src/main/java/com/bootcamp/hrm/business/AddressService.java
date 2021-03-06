package com.bootcamp.hrm.business;

import com.bootcamp.hrm.common.AddressRequest;
import com.bootcamp.hrm.entities.Address;
import com.bootcamp.hrm.repository.AddressRepository;
import com.bootcamp.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        address.setCreatedAt(CommonUtils.getCurrentTime());
        address.setCreatedBy("Dev_Department");
        addressRepository.save(address);
    }

    public List<HashMap<String, Object>> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<HashMap<String, Object>> addressRequestList = new ArrayList<>();
        for (Address address : addresses) {
            addressRequestList.add(setAddressResponse(address));
        }
        return addressRequestList;
    }

    public LinkedHashMap<String, Object> getAddressById(UUID id) {
        Address address = addressRepository.findById(id).orElse(new Address());
        return setAddressResponse(address);
    }

    public void updateAddressById(AddressRequest addressRequest, UUID id) {
        Address existingAddress = addressRepository.findById(id).get();
        existingAddress.setState(addressRequest.getState());
        existingAddress.setPinCode(addressRequest.getPinCode());
        existingAddress.setCity(addressRequest.getCity());
        existingAddress.setColony(addressRequest.getColony());
        existingAddress.setHouseNumber(addressRequest.getHouseNumber());
        existingAddress.setUpdatedAt(CommonUtils.getCurrentTime());
        existingAddress.setUpdatedBy("Dev_Department");
        addressRepository.save(existingAddress);

    }

    public void deleteAddressById(UUID id) {
        addressRepository.deleteById(id);
    }

    public void deleteAllAddress() {
        addressRepository.deleteAll();
    }

    public LinkedHashMap<String, Object> setAddressResponse(Address address) {
        LinkedHashMap<String, Object> setAddressResp = new LinkedHashMap<>();
        setAddressResp.put("id", address.getId());
        setAddressResp.put("house number", address.getHouseNumber());
        setAddressResp.put("colony", address.getColony());
        setAddressResp.put("city", address.getCity());
        setAddressResp.put("state", address.getState());
        return setAddressResp;
    }
}