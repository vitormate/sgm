package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.MachineDTO;
import com.rokaly.sgm.dto.PutMachineDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.repository.MachineRepository;
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
public class MachineController {

    @Autowired
    private MachineRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<MachineDTO> create(@RequestBody @Valid MachineDTO data, UriComponentsBuilder uriBuilder) {
        Machine machine = new Machine(data);
        repository.save(machine);

        var uri = uriBuilder.path("/machines/{id}").buildAndExpand(machine.getId()).toUri();
        MachineDTO dto = new MachineDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<GetMachineDTO>> read(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagination) {
        Page<GetMachineDTO> page = repository.findAll(pagination).map(GetMachineDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetMachineDTO> put(@RequestBody @Valid PutMachineDTO data) {
        Machine machine = repository.getReferenceById(data.id());
        machine.updateData(data);
        return ResponseEntity.ok(new GetMachineDTO(machine));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Machine machine = repository.getReferenceById(id);
        machine.deleteForklift();
        return ResponseEntity.noContent().build();
    }
}
