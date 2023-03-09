package com.example.Omafourm.repository;

import com.example.Omafourm.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @param: UserRepository
 * @package: com.example.Omafourm.repository
 * @className: UserRepository
 * @description: TODO
 * @return:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserByEmail(String email);
    User findByUsername(String username);
    User findByEmail(String email);

}
