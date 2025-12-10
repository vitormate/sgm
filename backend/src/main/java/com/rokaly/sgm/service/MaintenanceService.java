package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.ActiveMachineDTO;
import com.rokaly.sgm.dto.GetMachineDTO;
import com.rokaly.sgm.dto.GetMaintenanceDTO;
import com.rokaly.sgm.dto.MaintenanceDTO;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.model.Maintenance;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository repositoryMaintenance;

    @Autowired
    private MachineRepository repositoryMachine;

    public ResponseEntity<MaintenanceDTO> createService(MaintenanceDTO data, UriComponentsBuilder uriBuilder) {
        Machine machine = repositoryMachine.getReferenceById(data.idMachine());
        machine.maintenance();
        Maintenance maintenance = new Maintenance(data, machine);
        repositoryMaintenance.save(maintenance);

        var uri = uriBuilder.path("/maintenance/{id}").buildAndExpand(maintenance.getId()).toUri();
        MaintenanceDTO dto = new MaintenanceDTO(data);
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<Page<GetMaintenanceDTO>> getAllService(Pageable pagination) {
        Page<GetMaintenanceDTO> page = repositoryMaintenance.findAll(pagination).map(GetMaintenanceDTO::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<GetMachineDTO> removeMaintenance(ActiveMachineDTO data) {
        Machine machine = repositoryMachine.getReferenceById(data.id());
        machine.activate();
        return ResponseEntity.ok(new GetMachineDTO(machine));
    }

    public ResponseEntity<GetMaintenanceDTO> getByIdService(Long id) {
        Maintenance maintenance = repositoryMaintenance.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manutenção não encontrada com id: " + id));

        return ResponseEntity.ok(new GetMaintenanceDTO(maintenance));
    }
}
