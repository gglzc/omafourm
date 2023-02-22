package com.example.Omafourm.repository;

import com.example.Omafourm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @param: UserRepository
 * @package: com.example.Omafourm.repository
 * @className: UserRepository
 * @description: TODO
 * @return:
 */
interface UserRepository extends JpaRepository<User,Long> {
}
