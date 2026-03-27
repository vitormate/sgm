package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.PutMachineRequest;
import com.rokaly.sgm.exception.BusinessRuleException;
import com.rokaly.sgm.model.Machine;
import com.rokaly.sgm.repository.MachineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {

    @Mock
    MachineRepository machineRepository;

    @InjectMocks
    MachineService machineService;

    Machine machine;

    @BeforeEach
    void setUp() {
        this.machine = new Machine("123321D23", "Empilhadeira", "HELI", "CB150", 12352.9);
    }

    @Nested
    class putMachineTests {

        @Test
        void shouldReturnStatusCode200() {
            PutMachineRequest data = new PutMachineRequest(1L, null, null, null, 20000.0);

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            int result = machineService.putService(data).getStatusCode().value();

            assertEquals(HttpStatus.OK.value(), result);
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenHourMeterIsLowerThanActualValue() {
            PutMachineRequest data = new PutMachineRequest(1L, null, null, null, 1500.0);

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));


            final BusinessRuleException exception = assertThrows(
                    BusinessRuleException.class, () -> machineService.putService(data)
            );

            assertEquals("Hour meter can't be lower than current value!", exception.getMessage());
        }

        @Test
        void shouldThrowEntityNotFoundExceptionWhenMachineIdNotFound() {
            PutMachineRequest data = new PutMachineRequest(1L, null, null, null, 1500.0);
            when(machineRepository.findById(1L)).thenReturn(Optional.empty());

            final EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class, () -> machineService.putService(data)
            );

            assertEquals("Machine not found with id: " + data.id(), exception.getMessage());
        }

        @Test
        void shouldNotRewriteMachineEntityWhenPutMachineRequestAtributeIsNull() {
            PutMachineRequest data = new PutMachineRequest(1L, null, null, null, 20000.0);

            when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

            machineService.putService(data);

            assertEquals("Empilhadeira", machine.getType());
            assertEquals("HELI", machine.getBrand());
            assertEquals("CB150", machine.getModel());
        }
    }

}