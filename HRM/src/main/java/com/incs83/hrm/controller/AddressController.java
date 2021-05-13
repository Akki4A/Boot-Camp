package com.incs83.hrm.controller;

import com.incs83.hrm.business.AddressService;
import com.incs83.hrm.common.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.POST)
    public void createAddress(@RequestBody AddressRequest addressRequest){
        addressService.createAddress(addressRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<HashMap<String, Object>> getAllAddress(){
        return addressService.getAllAddress();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public LinkedHashMap<String, Object> getAddressById(@PathVariable UUID id){
        return addressService.getAddressById(id);
    }

    @RequestMapping(path = "{id}",method = RequestMethod.PUT)
    public void updateAddressByID(@RequestBody AddressRequest addressRequest, @PathVariable UUID id){
        addressService.updateAddressById(addressRequest,id);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public void deleteAddressById(@PathVariable UUID id){
        addressService.deleteAddressById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllAddress(){ addressService.deleteAllAddress(); }
}
