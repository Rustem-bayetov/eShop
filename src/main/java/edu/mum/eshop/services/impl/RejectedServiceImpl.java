package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.users.RejectedUser;
import edu.mum.eshop.repositories.RejectedUserRepository;
import edu.mum.eshop.services.RejectedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectedServiceImpl implements RejectedUserService {
    @Autowired
    RejectedUserRepository rejectedUserRepository;
    public RejectedUser save(RejectedUser rejectedUser){
        return rejectedUserRepository.save(rejectedUser);
    }
}
