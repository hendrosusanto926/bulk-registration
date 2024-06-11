package com.mlpt.bulkregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mlpt.bulkregistration.entity.Approval;
import java.util.List;


public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    
    public List<Approval> findByIdEntity(String idEntity);
}
