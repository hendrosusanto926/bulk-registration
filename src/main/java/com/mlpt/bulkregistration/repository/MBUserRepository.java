package com.mlpt.bulkregistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mlpt.bulkregistration.entity.MBUser;

public interface MBUserRepository extends JpaRepository<MBUser, String> {

    public List<MBUser> findByReffNumRegistration(String reffNumRegistration);
}
