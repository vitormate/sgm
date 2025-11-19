package com.rokaly.sgm.repository;

import com.rokaly.sgm.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
