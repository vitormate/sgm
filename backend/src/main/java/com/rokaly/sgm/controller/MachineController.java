package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.MachineDTO;
import com.rokaly.sgm.dto.PutMachineDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.service.MachineService;
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
@RequestMapping("machines")
@SecurityRequirement(name = "bearer-key")
public class MachineController {

    @Autowired
    private MachineRepository repository;

    @Autowired
    private MachineService machineService;

    @PostMapping
    @Transactional
    public ResponseEntity<MachineDTO> create(@RequestBody @Valid MachineDTO data, UriComponentsBuilder uriBuilder) {
        return machineService.createService(data, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<GetMachineDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        return machineService.readService(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineDTO> put(@RequestBody @Valid PutMachineDTO data) {
        return machineService.putService(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return machineService.deleteService(id);
    }
}
