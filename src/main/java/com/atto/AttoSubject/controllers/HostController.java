package com.atto.AttoSubject.controllers;

import com.atto.AttoSubject.dtos.*;
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
    @RequestMapping(method=RequestMethod.PUT,path="/{hostId}")
    public HostDto updateHost(@PathVariable("hostId") long id,@RequestBody HostUpdateRequest request){
        return hostService.updateHost(id,request);
    }
    @RequestMapping(method=RequestMethod.DELETE,path="/{hostId}")
    public void deleteHost(@PathVariable("hostId") long id){
        hostService.deleteHost(id);
    }

    @RequestMapping(method=RequestMethod.POST,path="/{hostId}/host-alive-history")
    public HostAliveHistoryResponse postHostAliveHistoryById(@PathVariable("hostId") long id){
        return hostService.postHostAliveHistoryById(id);
    }


}
