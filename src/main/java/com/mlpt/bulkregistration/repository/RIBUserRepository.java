package com.mlpt.bulkregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.mlpt.bulkregistration.entity.RIBUser;
import java.util.List;


public interface RIBUserRepository extends JpaRepository<RIBUser, String> {

    public List<RIBUser> findByReffNumRegistration(@Param("reffNumRegistration")String reffNumRegistration);
}