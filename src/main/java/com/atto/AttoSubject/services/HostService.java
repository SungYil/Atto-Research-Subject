package com.atto.AttoSubject.services;

import com.atto.AttoSubject.dtos.HostDto;
import com.atto.AttoSubject.dtos.HostRegisterRequest;
import com.atto.AttoSubject.dtos.HostRegisterResponse;
import com.atto.AttoSubject.entities.Alive;
import com.atto.AttoSubject.entities.Host;
import com.atto.AttoSubject.enums.AliveState;
import com.atto.AttoSubject.mappers.HostMapper;
import com.atto.AttoSubject.repositories.AliveRepository;
import com.atto.AttoSubject.repositories.HostRepository;
import com.atto.AttoSubject.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private AliveRepository aliveRepository;

    @Autowired
    private Validate validate;

    @Autowired
    private HostMapper hostMapper;

    @Transactional
    public HostRegisterResponse register(HostRegisterRequest request){

        if(!validate.validateHostIp(request.getIp())){
            System.out.println("====================ip 입력이 잘못되었습니다.");
            return null;
        }else if(hostRepository.existsByIp(request.getIp())){
            System.out.println("====================이미 존재하는 호스트입니다");
            return null;
        }

        Host host=hostMapper.map(request);
        host.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        host.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        Alive alive=new Alive();
        alive.setHost(host);
        if(isReachable(request.getIp())){
            alive.setState(AliveState.alive);
        }else{
            alive.setState(AliveState.notAlive);
        }
        alive.setCheckTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        hostRepository.save(host);
        aliveRepository.save(alive);
        System.out.println(hostMapper.map(host));
        return hostMapper.map(host);
    }
    @Transactional
    public HostDto getHost(String ip){
        return hostMapper.toDto(hostRepository.findByIp(ip));
    }
    public boolean isReachable(String ip){
        boolean check=false;
        try{
            check=InetAddress.getByName(ip).isReachable(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        return check;
    }
}
