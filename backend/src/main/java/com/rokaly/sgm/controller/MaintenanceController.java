package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.GetMachineResponse;
import com.rokaly.sgm.dto.GetMaintenanceResponse;
import com.rokaly.sgm.dto.MaintenanceRequest;
import com.rokaly.sgm.dto.UpdateMachineStatusResquest;
import com.rokaly.sgm.service.MaintenanceService;
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
@RequestMapping("/api/v2/maintenance")
@SecurityRequirement(name = "bearer-key")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MaintenanceRequest> create(@RequestBody @Valid MaintenanceRequest data, UriComponentsBuilder uriBuilder) {
        return maintenanceService.createService(data, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<GetMaintenanceResponse>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return maintenanceService.getAllService(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineResponse> leftMaintenance(@RequestBody @Valid UpdateMachineStatusResquest data) {
        return maintenanceService.finishMaintenance(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMaintenanceResponse> getById(@PathVariable Long id) {
        return maintenanceService.getByIdService(id);
    }
}
