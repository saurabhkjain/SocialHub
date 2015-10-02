package com.fdm.socialhub.repositories;

import com.fdm.socialhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
