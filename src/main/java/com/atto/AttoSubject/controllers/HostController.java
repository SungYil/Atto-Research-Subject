package com.atto.AttoSubject.controllers;

import com.atto.AttoSubject.dtos.HostDto;
import com.atto.AttoSubject.dtos.HostPostRequest;
import com.atto.AttoSubject.dtos.HostRegisterResponse;
import com.atto.AttoSubject.dtos.HostUpdateRequest;
import com.atto.AttoSubject.services.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hosts")
public class HostController {
    @Autowired
    HostService hostService;

    @RequestMapping(method= RequestMethod.POST, path="")
    public HostDto postHost(@RequestBody HostPostRequest request){
        return hostService.postHost(request);
    }

    @RequestMapping(method=RequestMethod.GET, path="/{hostId}")
    public HostDto getHost(@PathVariable("hostId") long id){
        return hostService.getHost(id);
    }
    @RequestMapping(method=RequestMethod.GET, path="")
    public List<HostDto> getHosts(){
        return hostService.getHosts();
    }

    @RequestMapping(method=RequestMethod.PUT,path="")
    public HostDto updateHost(@RequestBody HostUpdateRequest request){
        return hostService.updateHost(request);
    }

}
