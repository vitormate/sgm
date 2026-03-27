package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.MaintenanceRequest;
import com.rokaly.sgm.dto.UpdateMachineStatusResquest;
import com.rokaly.sgm.exception.BusinessRuleException;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.model.Maintenance;
import com.rokaly.sgm.repository.MachineRepository;
import com.rokaly.sgm.repository.MaintenanceRepository;
import com.rokaly.sgm.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

    @Mock
    MachineRepository machineRepository;

    @Mock
    MaintenanceRepository maintenanceRepository;

    @InjectMocks
    MaintenanceService maintenanceService;

    Machine machine;
    UriComponentsBuilder uriBuilder;
    UpdateMachineStatusResquest update;

    @BeforeEach
    void setUp() {
        this.machine = new Machine("123321D23", "Empilhadeira", "HELI", "CB150", 12352.9);
        this.machine.setId(1L);

        this.uriBuilder = UriComponentsBuilder.newInstance();

        this.update = new UpdateMachineStatusResquest(1L);
    }

    @Nested
    class createServiceTests {

        @Test
        void shouldReturnStatusCode201() {

            MaintenanceRequest data = new MaintenanceRequest(LocalDateTime.now().minusMinutes(30), "Burned", 15000.4, machine.getId());
            Maintenance maintenance = new Maintenance(data.dateTime(), data.description(), data.hourMeter(), machine);

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));
            when(maintenanceRepository.save(any(Maintenance.class))).thenReturn(maintenance);

            int result = maintenanceService.createService(data, uriBuilder).getStatusCode().value();

            assertEquals(HttpStatus.CREATED.value(), result);
            verify(maintenanceRepository, times(1)).save(any(Maintenance.class));
        }

        @Test
        void shouldThrowEntityFoundExceptionWhenMachineNotFound() {
            MaintenanceRequest data = new MaintenanceRequest(LocalDateTime.now().minusMinutes(30), "Burned", 15000.4, machine.getId());

            when(machineRepository.findById(1L)).thenReturn(Optional.empty());

            final EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class, () -> maintenanceService.createService(data, uriBuilder)
            );

            assertEquals("Machine not found with id: " + machine.getId(), exception.getMessage());
            verifyNoInteractions(maintenanceRepository);
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenMachineAlreadyInMaintenance() {
            machine.setStatus(Status.MANUTENCAO);
            MaintenanceRequest data = new MaintenanceRequest(LocalDateTime.now().minusMinutes(30), "Burned", 15000.4, machine.getId());

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            final BusinessRuleException exception = assertThrows(
                    BusinessRuleException.class, () -> maintenanceService.createService(data, uriBuilder)
            );

            assertEquals("Machine already in maintenance!", exception.getMessage());
            verifyNoInteractions(maintenanceRepository);
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenHourMeterIsLowerThanActualValue() {
            MaintenanceRequest data = new MaintenanceRequest(LocalDateTime.now().minusMinutes(30), "Burned", 1500.4, machine.getId());

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            final BusinessRuleException exception = assertThrows(
                    BusinessRuleException.class, () -> maintenanceService.createService(data, uriBuilder)
            );

            assertEquals("Hour meter can't be lower than current value!", exception.getMessage());
            verifyNoInteractions(maintenanceRepository);
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenDateTimeInFuture() {
            MaintenanceRequest data = new MaintenanceRequest(LocalDateTime.now().plusHours(30), "Burned", 15000.4, machine.getId());

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            final BusinessRuleException exception = assertThrows(
                    BusinessRuleException.class, () -> maintenanceService.createService(data, uriBuilder)
            );

            assertEquals("Datetime can't be in the future!", exception.getMessage());
            verifyNoInteractions(maintenanceRepository);
        }

    }

    @Nested
    class finishMaintenanceTests {

        @Test
        void shouldReturnStatusCode200() {
            UpdateMachineStatusResquest data = new UpdateMachineStatusResquest(1L);
            machine.setStatus(Status.MANUTENCAO);

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            int statusCode = maintenanceService.finishMaintenance(data).getStatusCode().value();

            assertEquals(HttpStatus.OK.value(), statusCode);
        }

        @Test
        void shouldReturnEntityNotFoundWhenMachineNotFound() {
            when(machineRepository.findById(1L)).thenReturn(Optional.empty());

            final EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> maintenanceService.finishMaintenance(update)
            );

            assertEquals("Machine not found with id: " + update.id(), exception.getMessage());
        }

        @Test
        void shouldReturnBusinessRuleExceptionWhenMachineAlreadyActive() {
            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            final BusinessRuleException exception = assertThrows(
                    BusinessRuleException.class,
                    () -> maintenanceService.finishMaintenance(update)
            );

            assertEquals("Machine already ACTIVE!", exception.getMessage());

        }
    }
}