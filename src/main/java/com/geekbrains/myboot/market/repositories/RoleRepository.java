package com.geekbrains.myboot.market.repositories;


import com.geekbrains.myboot.market.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
