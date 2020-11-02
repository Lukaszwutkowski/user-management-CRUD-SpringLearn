package com.sda.userSda;

import com.sda.userSda.dao.AppUserDao;
import com.sda.userSda.dao.UserRoleDao;
import com.sda.userSda.model.AppUser;
import com.sda.userSda.model.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner {

    UserRoleDao userRoleDao;
    AppUserDao appUserDao;

    public Starter(UserRoleDao userRoleDao, AppUserDao appUserDao) {
        this.userRoleDao = userRoleDao;
        this.appUserDao = appUserDao;
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser lukasz = new AppUser();
        lukasz.setLogin("lukasz");
        lukasz.setPassword("$2a$10$XUj4oXMZuPLOhuu8.pV.vOroFYLTKp.0TLT4V9QQ6CMUWnQldPWbi");
        lukasz.setEnabled(true);

        AppUser kasia = new AppUser();
        kasia.setLogin("kasia");
        kasia.setPassword("$2a$10$qnyaXennEtScFDd3acdAeOg5VhszUjGuLCK.8Y/ZO9evOxz.ub84a");
        kasia.setEnabled(true);

        UserRole lukaszAdmin = new UserRole();
        lukaszAdmin.setLogin("lukasz");
        lukaszAdmin.setRole("ADMIN");

        UserRole lukaszUser = new UserRole();
        lukaszUser.setLogin("lukasz");
        lukaszUser.setRole("USER");

        UserRole kasiaUser = new UserRole();
        kasiaUser.setLogin("kasia");
        kasiaUser.setRole("USER");

        appUserDao.save(lukasz);
        appUserDao.save(kasia);
        userRoleDao.save(lukaszAdmin);
        userRoleDao.save(lukaszUser);
        userRoleDao.save(kasiaUser);
    }
}
