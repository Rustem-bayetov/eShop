package edu.mum.eshop.services;

import edu.mum.eshop.domain.users.RejectedUser;
import org.springframework.stereotype.Service;

public interface RejectedUserService {
    public RejectedUser save(RejectedUser rejectedUser);
}
