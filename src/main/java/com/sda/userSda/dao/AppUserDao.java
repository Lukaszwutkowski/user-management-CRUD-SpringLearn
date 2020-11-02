package com.sda.userSda.dao;

import com.sda.userSda.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser, String> {
}
