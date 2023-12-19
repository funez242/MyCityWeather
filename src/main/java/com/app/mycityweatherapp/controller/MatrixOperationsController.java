package com.app.mycityweatherapp.controller;

import com.app.mycityweatherapp.service.MatrixOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matrix")
@Tag(name = "Matrix Operations",
        description = "API realiza operaciones sobre matrices.")
public class MatrixOperationsController {

    MatrixOperationService matrixOperationService;

    public MatrixOperationsController(MatrixOperationService matrixOperationService){
        this.matrixOperationService = matrixOperationService;
    }

    @PostMapping("/multiply")
    @Operation(description = "Realiza la multiplicación de dos matrices",
            operationId = "matrixMultiplying")
    public int[][] matrixMultiplying(
            @Parameter(name = "matrixA") @RequestBody int[][] matrixA,
            @Parameter(name = "matrixB") @RequestBody int[][] matrixB){
        return this.matrixOperationService.multiply(matrixA,matrixB);
    }
}
