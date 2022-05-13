package com.atto.AttoSubject.controllers;

import com.atto.AttoSubject.dtos.HostDto;
import com.atto.AttoSubject.dtos.HostRegisterRequest;
import com.atto.AttoSubject.dtos.HostRegisterResponse;
import com.atto.AttoSubject.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/host")
public class HostController {
    @Autowired
    HostService hostService;

    @RequestMapping(method= RequestMethod.POST, path="/register")
    public HostRegisterResponse register(@RequestBody HostRegisterRequest request){
        return hostService.register(request);
    }
}
