package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.GetMachineResponse;
import com.rokaly.sgm.dto.MachineRequest;
import com.rokaly.sgm.dto.PutMachineRequest;
import com.rokaly.sgm.service.MachineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/machines")
@SecurityRequirement(name = "bearer-key")
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MachineRequest> create(@RequestBody @Valid MachineRequest data, UriComponentsBuilder uriBuilder) {
        return machineService.createService(data, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<GetMachineResponse>> getAllMachines(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return machineService.getAllService(pagination);
    }

    @GetMapping("/actives")
    public ResponseEntity<Page<GetMachineResponse>> getAllActiveMachines(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return machineService.getAllActives(pagination);
    }

    @GetMapping("/maintenace")
    public ResponseEntity<Page<GetMachineResponse>> getAllMaintenanceMachines(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return machineService.getAllMaintenance(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineResponse> put(@RequestBody @Valid PutMachineRequest data) {
        return machineService.putService(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return machineService.deleteService(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMachineResponse> getById(@PathVariable Long id) {
        return machineService.getByIdService(id);
    }
}
