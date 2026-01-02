package com.rokaly.sgm.repository;

import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.utils.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    Page<Machine> findByStatus(Pageable pagination, Status status);
}
