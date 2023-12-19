package com.app.mycityweatherapp.controller;

import com.app.mycityweatherapp.dto.MatricesRequest;
import com.app.mycityweatherapp.service.MatrixOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matrices")
@Tag(name = "Matrices Operations",
        description = "API realiza operaciones sobre matrices.")
public class MatrixOperationsController {

    MatrixOperationService matrixOperationService;

    public MatrixOperationsController(MatrixOperationService matrixOperationService){
        this.matrixOperationService = matrixOperationService;
    }

    @PostMapping("/multiplicar")
    @Operation(description = "Realiza la multiplicación de dos matrices",
            operationId = "matrixMultiplying")
    public int[][] matrixMultiplying(
            @RequestBody MatricesRequest matricesRequest) {
        int[][] matrixA = matricesRequest.getMatrix1();
        int[][] matrixB = matricesRequest.getMatrix2();
        return this.matrixOperationService.multiply(matrixA,matrixB);
    }
}
