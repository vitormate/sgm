package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.MachineDTO;
import com.rokaly.sgm.dto.PutMachineDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public ResponseEntity<MachineDTO> createService(MachineDTO data, UriComponentsBuilder uriBuilder) {
        Machine machine = new Machine(data);
        machineRepository.save(machine);

        var uri = uriBuilder.path("/machines/{id}").buildAndExpand(machine.getId()).toUri();
        MachineDTO dto = new MachineDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<Page<GetMachineDTO>> readService(Pageable pagination) {
        Page<GetMachineDTO> page = machineRepository.findAll(pagination).map(GetMachineDTO::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<GetMachineDTO> putService(PutMachineDTO data) {
        Machine machine = machineRepository.getReferenceById(data.id());
        machine.updateData(data);
        return ResponseEntity.ok(new GetMachineDTO(machine));
    }

    public ResponseEntity<Void> deleteService(Long id) {
        Machine machine = machineRepository.getReferenceById(id);
        machine.deleteForklift();
        return ResponseEntity.noContent().build();
    }
}
