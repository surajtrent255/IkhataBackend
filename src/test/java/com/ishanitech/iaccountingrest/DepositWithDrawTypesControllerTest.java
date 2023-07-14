package com.ishanitech.iaccountingrest;

import com.ishanitech.iaccountingrest.controller.DepositWithDrawTypesController;
import com.ishanitech.iaccountingrest.dto.DepositWithdrawTypesDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DepositWithDrawTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class DepositWithDrawTypesControllerTest {

    @Mock
    private DepositWithDrawTypeService depositWithDrawTypeService;

    private DepositWithDrawTypesController depositWithDrawTypesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        depositWithDrawTypesController = new DepositWithDrawTypesController(depositWithDrawTypeService);
    }

    @Test
    void testGetAllDepositWithDrawTypes_Success() {
        // Arrange
        List<DepositWithdrawTypesDTO> expectedTypes = Arrays.asList(
                new DepositWithdrawTypesDTO(1, "Type1"),
                new DepositWithdrawTypesDTO(2, "Type2")
        );

        when(depositWithDrawTypeService.getAllTypesOfDepositWithdraw()).thenReturn(expectedTypes);

        // Act
        Mono<ResponseDTO<List<DepositWithdrawTypesDTO>>> result = depositWithDrawTypesController.getAllDepositWithDrawTypes();

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(responseDTO -> {
                    // Assert the response
                    List<DepositWithdrawTypesDTO> actualTypes = responseDTO.getData();
                    return "Success".equals("Success") &&
                            actualTypes.size() == expectedTypes.size() &&
                            actualTypes.containsAll(expectedTypes);
                })
                .verifyComplete();

        verify(depositWithDrawTypeService, times(1)).getAllTypesOfDepositWithdraw();
    }

    @Test
    void testGetAllDepositWithDrawTypes_Failure() {
        // Arrange
        when(depositWithDrawTypeService.getAllTypesOfDepositWithdraw())
                .thenThrow(new RuntimeException("Test exception"));

        // Act
        Mono<ResponseDTO<List<DepositWithdrawTypesDTO>>> result = depositWithDrawTypesController.getAllDepositWithDrawTypes();

        // Assert
        StepVerifier.create(result)
                .expectError(CustomSqlException.class)
                .verify();

        verify(depositWithDrawTypeService, times(1)).getAllTypesOfDepositWithdraw();
    }
}
