package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.GetMachineResponse;
import com.rokaly.sgm.dto.MachineRequest;
import com.rokaly.sgm.dto.PutMachineRequest;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public ResponseEntity<MachineRequest> createService(MachineRequest data, UriComponentsBuilder uriBuilder) {
        Machine machine = new Machine(data.serial(), data.type(), data.brand(), data.model(), data.hourMeter());
        machineRepository.save(machine);

        var uri = uriBuilder.path("/machines/{id}").buildAndExpand(machine.getId()).toUri();
        MachineRequest dto = new MachineRequest(data);
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<Page<GetMachineResponse>> getAllService(Pageable pagination, Status status) {
        if (status == null) {
            Page<GetMachineResponse> page = machineRepository.findAll(pagination).map(GetMachineResponse::new);
            return ResponseEntity.ok(page);
        }

        Page<GetMachineResponse> page = machineRepository.findByStatus(pagination, status).map(GetMachineResponse::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<GetMachineResponse> putService(PutMachineRequest data) {
        Machine machine = machineRepository.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("Máquina não encontrada com id: " + data.id()));

        machine.updateData(data.type(), data.brand(), data.model(), data.hourMeter());
        return ResponseEntity.ok(new GetMachineResponse(machine));
    }

    public ResponseEntity<Void> deleteService(Long id) {
        Machine machine = machineRepository.getReferenceById(id);
        machine.inactivate();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<GetMachineResponse> getByIdService(Long id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Máquina não encontrada com id: " + id));

        return ResponseEntity.ok(new GetMachineResponse(machine));
    }
}
