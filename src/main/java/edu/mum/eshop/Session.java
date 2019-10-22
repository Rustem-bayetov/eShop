package edu.mum.eshop;


import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public final class Session {
    public static Integer getMyId() {
        return 1;
    }

    // public static User getMyUser() {
    //     UsersService usersService = new UsersServiceImpl();
    //     return usersService.getUserById(getMyId());
    // }
}
