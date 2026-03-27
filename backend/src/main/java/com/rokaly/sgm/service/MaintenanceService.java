package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.GetMachineResponse;
import com.rokaly.sgm.dto.GetMaintenanceResponse;
import com.rokaly.sgm.dto.MaintenanceRequest;
import com.rokaly.sgm.dto.UpdateMachineStatusResquest;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.model.Maintenance;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repositoryMaintenance;
    private final MachineRepository repositoryMachine;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, MachineRepository repositoryMachine) {
        this.repositoryMaintenance = maintenanceRepository;
        this.repositoryMachine = repositoryMachine;
    }

    public ResponseEntity<MaintenanceRequest> createService(MaintenanceRequest data, UriComponentsBuilder uriBuilder) {
        Machine machine = repositoryMachine.findById(data.idMachine()).orElseThrow(
                () -> new EntityNotFoundException("Machine not found with id: " + data.idMachine())
        );
        machine.startMaintenance(data.hourMeter(), data.dateTime());
        Maintenance maintenance = new Maintenance(data.dateTime(), data.description(), data.hourMeter(), machine);

        repositoryMaintenance.save(maintenance);

        var uri = uriBuilder.path("/maintenance/{id}").buildAndExpand(maintenance.getId()).toUri();
        MaintenanceRequest dto = new MaintenanceRequest(data);
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<Page<GetMaintenanceResponse>> getAllService(Pageable pagination) {
        Page<GetMaintenanceResponse> page = repositoryMaintenance.findAll(pagination).map(GetMaintenanceResponse::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<GetMachineResponse> finishMaintenance(UpdateMachineStatusResquest data) {
        Machine machine = repositoryMachine.findById(data.id()).orElseThrow(
                () -> new EntityNotFoundException("Machine not found with id: " + data.id())
        );
        machine.activate();
        return ResponseEntity.ok(new GetMachineResponse(machine));
    }

    public ResponseEntity<GetMaintenanceResponse> getByIdService(Long id) {
        Maintenance maintenance = repositoryMaintenance.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manutenção não encontrada com id: " + id));

        return ResponseEntity.ok(new GetMaintenanceResponse(maintenance));
    }
}
