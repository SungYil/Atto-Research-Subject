package com.atto.AttoSubject.controllers;

import com.atto.AttoSubject.dtos.HostDto;
import com.atto.AttoSubject.dtos.HostRegisterRequest;
import com.atto.AttoSubject.dtos.HostRegisterResponse;
import com.atto.AttoSubject.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(method=RequestMethod.GET, path="/getHost")
    public HostDto getHost(@RequestParam String ip){
        return hostService.getHost(ip);
    }
    @RequestMapping(method=RequestMethod.GET, path="/getHosts")
    public List<HostDto> getHosts(){
        return hostService.getHosts();
    }
}
