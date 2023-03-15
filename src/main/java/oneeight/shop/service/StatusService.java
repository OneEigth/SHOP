package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Status;
import oneeight.shop.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
private final StatusRepository statusRepository;

public List<Status> allStatus (){
        return statusRepository.findAll();
}

public Status statusFormed(){
    return statusRepository.findAllByStatus("Сформирован");}
}
