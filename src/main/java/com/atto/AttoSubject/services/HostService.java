package com.atto.AttoSubject.services;

import com.atto.AttoSubject.dtos.*;
import com.atto.AttoSubject.entities.Alive;
import com.atto.AttoSubject.entities.Host;
import com.atto.AttoSubject.enums.AliveState;
import com.atto.AttoSubject.mappers.HostMapper;
import com.atto.AttoSubject.repositories.AliveRepository;
import com.atto.AttoSubject.repositories.HostRepository;
import com.atto.AttoSubject.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetAddress;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public HostAliveHistoryResponse getHostAliveHistoryById(long id){
        if(!hostRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 id가 없습니다.");
        }

        Host host=hostRepository.findById(id).get();
        Alive alive=aliveRepository.findByHostId(id);

        return new HostAliveHistoryResponse(id,host.getIp(),host.getName(),alive.getState(),alive.getCheckTime());
    }
    @Transactional
    public List<HostAliveHistoryResponse> getHostAliveHistory(){
        List<HostAliveHistoryResponse> list=new ArrayList<>();
        List<Host> hostList=hostRepository.findAllByOrderByName();

        for(int i=0;i<hostList.size();++i){
            Host host=hostList.get(i);
            Alive alive=aliveRepository.findByHostId(host.getId());
            list.add(new HostAliveHistoryResponse(host.getId(),host.getIp(),host.getName(),alive.getState(),alive.getCheckTime()));
        }

        return list;
    }


    @Transactional
    public HostAliveHistoryResponse postHostAliveHistoryById(long id){
        if(!hostRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 id가 없습니다.");
        }

        Host host=hostRepository.findById(id).get();
        Alive alive=aliveRepository.findByHostId(id);

        if(isReachable(host.getIp())){
            alive.setState(AliveState.alive);
        }else{
            alive.setState(AliveState.notAlive);
        }
        alive.setCheckTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        aliveRepository.save(alive);

        return new HostAliveHistoryResponse(id,host.getIp(),host.getName(),alive.getState(),alive.getCheckTime());
    }

    @Transactional
    public void deleteHost(long id){
        if(!hostRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 id가 없습니다.");
        }

        Alive alive=aliveRepository.findByHostId(id);
        aliveRepository.deleteById(alive.getId());
        hostRepository.deleteById(id);
    }
    @Transactional
    public HostDto postHost(HostPostRequest request){
        if(!validate.validateHostIp(request.getIp())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ip 입력이 잘못되었습니다.");
        }else if(hostRepository.findByName(request.getName())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이름이 중복됩니다.");
        }else if(hostRepository.findByIp(request.getIp())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ip가 중복됩니다.");
        }else if(hostRepository.findAll().size()>=100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"host의 개수가 100개 입니다.");
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
        return hostMapper.toDto(host);
    }

    @Transactional
    public HostDto getHost(long id){
        if(!hostRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"일치하는 id가 없습니다.");
        }
        return hostMapper.toDto(hostRepository.findById(id).get());
    }

    @Transactional
    public List<HostDto> getHosts(){
        return hostMapper.toDto(hostRepository.findAllByOrderByName());
    }

    @Transactional
    public HostDto updateHost(long id,HostUpdateRequest request){
        if(!validate.validateHostIp(request.getIp())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ip주소가 유효하지 않습니다");
        }else if(!hostRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 Host입니다");
        }else if(hostRepository.findByIp(request.getIp())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 존재하는 IP 주소입니다");
        }else if(hostRepository.findByName(request.getName())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 존재하는 Host 이름 입니다");
        }

        Host host=hostMapper.toHost(request);
        host.setId(id);
        host.setCreatedAt(hostRepository.findById(id).get().getCreatedAt());
        host.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        Alive alive=aliveRepository.findByHostId(id);
        alive.setHost(host);
        if(isReachable(request.getIp())){
            alive.setState(AliveState.alive);
        }else{
            alive.setState(AliveState.notAlive);
        }
        alive.setCheckTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        hostRepository.save(host);
        aliveRepository.save(alive);
        return hostMapper.toDto(host);
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
