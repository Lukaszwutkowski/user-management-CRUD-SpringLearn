package com.sda.userSda.dao;

import com.sda.userSda.model.UserRole;
import com.sda.userSda.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, UserRoleId> {
}
