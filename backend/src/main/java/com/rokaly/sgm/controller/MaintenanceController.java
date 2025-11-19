package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.ActiveMachineDTO;
import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.GetMaintenanceDTO;
import com.rokaly.sgm.dto.MaintenanceDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.model.Maintenance;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.repository.MaintenanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceRepository repositoryMaintenance;

    @Autowired
    private MachineRepository repositoryMachine;

    @PostMapping
    @Transactional
    public ResponseEntity<MaintenanceDTO> create(@RequestBody MaintenanceDTO data, UriComponentsBuilder uriBuilder) {
        Machine machine = repositoryMachine.getReferenceById(data.idMachine());
        machine.maintenance();
        Maintenance maintenance = new Maintenance(data, machine);
        repositoryMaintenance.save(maintenance);

        var uri = uriBuilder.path("/maintenance/{id}").buildAndExpand(maintenance.getId()).toUri();
        MaintenanceDTO dto = new MaintenanceDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<GetMaintenanceDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        Page<GetMaintenanceDTO> page = repositoryMaintenance.findAll(pagination).map(GetMaintenanceDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineDTO> leftMaintenance(@RequestBody ActiveMachineDTO data) {
        Machine machine = repositoryMachine.getReferenceById(data.id());
        machine.activate();
        return ResponseEntity.ok(new GetMachineDTO(machine));
    }
}
