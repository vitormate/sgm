package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.ActiveMachineDTO;
import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.GetMaintenanceDTO;
import com.rokaly.sgm.dto.MaintenanceDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.model.Maintenance;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.repository.MaintenanceRepository;
import com.rokaly.sgm.service.MaintenanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("maintenance")
@SecurityRequirement(name = "bearer-key")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    @Transactional
    public ResponseEntity<MaintenanceDTO> create(@RequestBody @Valid MaintenanceDTO data, UriComponentsBuilder uriBuilder) {
        return maintenanceService.createService(data, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<GetMaintenanceDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return maintenanceService.getAllService(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineDTO> leftMaintenance(@RequestBody @Valid ActiveMachineDTO data) {
        return maintenanceService.finishMaintenance(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMaintenanceDTO> getById(@PathVariable Long id) {
        return maintenanceService.getByIdService(id);
    }
}
