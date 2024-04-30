package org.example;

import org.example.Model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperationsTest {
    //Addition
    @ParameterizedTest
    @MethodSource("provideInputAdd")
    void testAddition(String firstString, String secondString, String expectedResult){
        assertEquals(expectedResult, Polynomial.add(firstString, secondString)); }
    private static List<Arguments> provideInputAdd(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("x+2x^2+x^3", "x^2+1", "1+x+3x^2+x^3"));
        arguments.add(Arguments.of("x+1", "x^2+2", "3+x+x^2"));
        arguments.add(Arguments.of("2x^3+x+1", "x^3+x^2+3", "4+x+x^2+3x^3"));
        return arguments;
    }
    //Subtraction
    @ParameterizedTest
    @MethodSource("provideInputSub")
    void testSubtraction(String firstString, String secondString, String expectedResult){
        assertEquals(expectedResult, Polynomial.subtract(firstString, secondString)); }
    private static List<Arguments> provideInputSub(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("x", "x", "0"));
        arguments.add(Arguments.of("2x^2-x", "x^2+2", "-2-x+x^2"));
        arguments.add(Arguments.of("2x^3-7x+4", "-x^2+3x^3-21", "25-7x+x^2-x^3"));
        return arguments;
    }

    //Multiplication
    @ParameterizedTest
    @MethodSource("provideInputMulti")
    void testMultiplication(String firstString, String secondString, String expectedResult){
        assertEquals(expectedResult, Polynomial.multiply(firstString, secondString)); }
    private static List<Arguments> provideInputMulti(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("2x+3", "x+1", "3+5x+2x^2"));
        arguments.add(Arguments.of("0", "x^2", "0"));
        arguments.add(Arguments.of("x+1", "x-1", "-1+x^2"));
        return arguments;
    }

    //Division

    @ParameterizedTest
    @MethodSource("provideInputDiv")
    void testDivision(String firstString, String secondString, String[] expectedResult){
        assertEquals(expectedResult[0], Objects.requireNonNull(Polynomial.division(firstString, secondString))[0]);
        assertEquals(expectedResult[1], Objects.requireNonNull(Polynomial.division(firstString, secondString))[1]); }
    private static List<Arguments> provideInputDiv(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("x^2+2x+1", "x+1", new String[]{"1+x", "0"}));
        arguments.add(Arguments.of("0", "x^2", new String[]{"0", "x^2"}));
        arguments.add(Arguments.of("x", "x", new String[]{"1", "0"}));
        return arguments;
    }
    //Integration
    @ParameterizedTest
    @MethodSource("provideInputIntegr")
    void testIntegration(String inputString, String expectedResult){
        assertEquals(expectedResult, Polynomial.integrate(inputString)); }
    private static List<Arguments> provideInputIntegr(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("2x", "x^2"));
        arguments.add(Arguments.of("1", "x"));
        arguments.add(Arguments.of("x+1", "x+0.5x^2"));
        return arguments;
    }
    //Differentiation
    @ParameterizedTest
    @MethodSource("provideInputDiff")
    void testDifferentiation(String inputString, String expectedResult){
        assertEquals(expectedResult, Polynomial.differentiate(inputString)); }
    private static List<Arguments> provideInputDiff(){
        List<Arguments> arguments = new ArrayList<>();
        arguments.add(Arguments.of("2x", "2"));
        arguments.add(Arguments.of("1", "0"));
        arguments.add(Arguments.of("x^2-2x+1", "-2+2x"));
        return arguments;
    }
}