package com.app.mycityweatherapp.service;

import org.springframework.stereotype.Controller;

import java.util.stream.IntStream;

@Controller
public class MatrixOperationsServiceImpl implements MatrixOperationService{

    public int[][] multiply(int[][] matrixA, int [][]matrixB){

        int rowsInFirstMatrix = matrixA.length;
        int columnsInFirstMatrix = matrixA[0].length;
        int columnsInSecondMatrix = matrixB[0].length;

        int[][] resultMatrix = IntStream.range(0, rowsInFirstMatrix)
                .parallel() //Parallel hace que sea multihilo
                .mapToObj(i -> IntStream.range(0, columnsInSecondMatrix)
                        .parallel()
                        .map(j -> IntStream.range(0, columnsInFirstMatrix)
                                .parallel()
                                .map(k -> matrixA[i][k] * matrixB[k][j])
                                .sum())
                        .toArray())
                .toArray(int[][]::new);
        return resultMatrix;
    }

}
