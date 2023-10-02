package org.energy.util;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class MathOperation {

    private static Random rand = null;

    public MathOperation() throws NoSuchAlgorithmException {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }

    /**
     * Método encargado de generar una secuencia alfabética entre mayúsculas y minúsculas de manera aleatoria
     *
     * @param lengthPassword largo de la secuencia alfabética a generar
     * @return secuencia alfabética
     */
    public static String generatePassword(int lengthPassword) {
        var rta = new StringBuilder();
        for (var i = 0; i < lengthPassword; i++) {
            int result = (rand.nextInt() * (10 - 1));
            if (result % 2 == 0) {
                rta.append(capitalLetter());
            } else {
                rta.append(minimumLetter());
            }
        }
        return rta.toString();
    }

    /**
     * Método encargado de seleccionar el número ASCII de una palabra mayúscula
     *
     * @return ASCII de una palabra mayúscula
     */
    public static String capitalLetter() {
        return Character.toString((char) (rand.nextInt(90 - 65)) + 65);
    }

    /**
     * Método encargado de seleccionar el número ASCII de una palabra minúscula
     *
     * @return ASCII de una palabra minúscula
     */
    public static String minimumLetter() {
        return Character.toString((char) (rand.nextInt(120 - 97)) + 97);
    }


    public static String generatePasswordNew(int lengthPassword) {
        return IntStream.range(0, lengthPassword)
                .mapToObj(i -> rand.nextInt(2) == 0 ? capitalLetter() : minimumLetter())
                .collect(Collectors.joining());
    }

    public static String compareGeneratePassword(){
        BigDecimal actualValue = BigDecimal.valueOf(1000000000000L);
        BigDecimal pastValue = BigDecimal.valueOf(500000000000L);

        long startTime = System.nanoTime();
        String percentage = generatePassword(100);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución del código original: " + (endTime - startTime) / 1000000);

        startTime = System.nanoTime();
        String percentageNew = generatePasswordNew(100);
        endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución del código optimizado: " + (endTime - startTime) / 1000000);
        return percentage + "-" + percentageNew;
    }
    /*El ciclo for y los else if son ineficientes y pueden ser reemplazados por los métodos de la API Stream. El método mapToObj()
    de la API Stream permite generar una secuencia de objetos a partir de una secuencia de valores. En este caso, los objetos
    generados son cadenas de caracteres que representan las letras a generar. El método collect(Collectors.joining()) de la API Stream
    permite unir los elementos de una secuencia en una cadena.*/

    /*private static final String[] UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    private static final String[] LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz".split("");

    public static String generatePassword2(int lengthPassword) {
        var builder = new StringBuilder();
        for (int i = 0; i < lengthPassword; i++) {
            builder.append(getRandomLetter());
        }
        return builder.toString();
    }

    private static String getRandomLetter() {
        return Arrays.asList(UPPER_CASE_LETTERS, LOWER_CASE_LETTERS).get(new Random().nextInt(2))[new Random().nextInt(UPPER_CASE_LETTERS.length)];
    }*/
    /**
     * Método encargado de obtener el porcentaje de diferencia entre dos números
     *
     * @param actualValue el valor actual
     * @param pastValue   el valor pasado a comparar
     * @return el porcentaje de diferencia
     */
    public static double percentage(BigInteger actualValue, BigInteger pastValue) {
        double rta;
        if (pastValue.intValue() == 0)
            rta = 0;
        else {
            var actual = actualValue.doubleValue();
            var past = pastValue.doubleValue();
            double division = actual / past;
            rta = Math.round(((division - 1) * 100) * 100) / 100.0;
        }
        return rta;
    }
    /*
    public static double percentage(BigInteger actualValue, BigInteger pastValue) {
        return actualValue.map(Double::doubleValue)
                .orElse(0.0) / pastValue.map(Double::doubleValue)
                .orElse(0.0) - 1.0;
    }
    Elimina el else: Se utiliza la función orElse() de Optional para devolver directamente el valor 0 si el valor pasado es 0.
    Elimina el ciclo for: Se utiliza la función map() para transformar cada elemento del Stream en un Double.

    La primera optimización se realiza reemplazando el if por la función orElse() de Optional. Esta función devuelve el valor
    del Optional si no está vacío, o el valor especificado como argumento si está vacío. En este caso, el valor especificado como argumento es 0.

    La segunda optimización se realiza reemplazando el ciclo for por la función map(). Esta función transforma cada elemento
    de un Stream en un nuevo elemento. En este caso, la función map() transforma cada elemento del Stream en un Double.
     */

    /**
     * Método encargado de obtener el porcentaje de un número con respecto a un total
     *
     * @param total número a saber el porcentaje
     * @param data  el total de la muestra
     * @return el porcentaje del número
     */
    public static double percent(BigInteger total, BigInteger data) {
        if (total.compareTo(BigInteger.valueOf(0L)) == 0)
            return 0.0;
        return Math.round((data.doubleValue() * 100 / total.doubleValue()) * 1000d) / 1000d;
    }

    /**
     * Método encargado comparar si un valor es mayor al un límite
     *
     * @param limit limite a alcanzar
     * @param value valor a comparar
     * @return true si el valor superó el límite, false en caso contrario
     */
    public static boolean compareLimit(int limit, long value) {
        return value > limit;
    }

    /**
     * Método encargado de hacer la suma enter varios bigDecimal
     *
     * @param numbers números a sumar
     * @return la suma de los números
     */
    public static BigDecimal sum(BigDecimal... numbers) {
        var rta = BigDecimal.valueOf(0L);
        for (BigDecimal number : numbers) {
            if (number != null)
                rta = rta.add(number);
        }
        return rta;
    }
    /*


    public static BigDecimal sum(BigDecimal... numbers) {
        return Arrays.stream(numbers)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    Elimina el else: Se utiliza la función filter() para eliminar los valores null del Stream.
    Elimina el ciclo for: Se utiliza la función reduce() para sumar los valores del Stream.

    La primera optimización se realiza reemplazando el if por la función filter(). Esta función devuelve un
    Stream que contiene solo los elementos que cumplen una condición. En este caso, la condición es que el elemento no sea null.

    La segunda optimización se realiza reemplazando el ciclo for por la función reduce(). Esta función
    reduce un Stream a un único valor, aplicando una operación a cada elemento del Stream. En este caso, la operación es la suma.
     */

    /**
     * Método encargado de sumar todos los números de una lista de double
     *
     * @param numbers lista de números
     * @return suma total de la lista
     */
    public static Double sum(List<Double> numbers) {
        var rta = 0.0;
        for (Double number : numbers) {
            if (number != null)
                rta += number;
        }
        return rta;
    }
    /*

    public static Double sum(List<Double> numbers) {
        return numbers.stream()
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
    }

    Elimina el else: Se utiliza la función filter() para eliminar los valores null del Stream.
    Elimina el ciclo for: Se utiliza la función reduce() para sumar los valores del Stream.

    La primera optimización se realiza reemplazando el if por la función filter(). Esta función
    devuelve un Stream que contiene solo los elementos que cumplen una condición. En este caso, la condición es que el elemento no sea null.

    La segunda optimización se realiza reemplazando el ciclo for por la función reduce(). Esta función
    reduce un Stream a un único valor, aplicando una operación a cada elemento del Stream. En este caso, la operación es la suma.
     */

    /**
     * Método encargado de obtener el procentaje/ diferencia entre dos números
     *
     * @param actualValue el valor actual
     * @param pastValue   el valor pasado a comparar
     * @return el porcentaje de diferencia
     */
    public static double percentage(Double actualValue, Double pastValue) {
        double rta;
        if (pastValue.intValue() == 0)
            rta = 0;
        else {
            var division = actualValue / pastValue;
            rta = Math.round(((division - 1) * 100) * 100) / 100.0;
        }
        return rta;
    }
    /*

    public static double percentage(Double actualValue, Double pastValue) {
        return actualValue.map(Double::doubleValue)
                .orElse(0.0) / pastValue.map(Double::doubleValue)
                .orElse(0.0) - 1.0;
    }

    Elimina el else: Se utiliza la función orElse() de Optional para devolver directamente el valor 0 si el valor pasado es 0.
    Elimina el ciclo for: Se utiliza la función map() para transformar cada elemento del Stream en un nuevo elemento. En este caso,
    la función map() transforma cada elemento del Stream en un Double.

    La primera optimización se realiza reemplazando el if por la función orElse() de Optional. Esta función devuelve el valor del
    Optional si no está vacío, o el valor especificado como argumento si está vacío. En este caso, el valor especificado como argumento es 0.

    La segunda optimización se realiza reemplazando el ciclo for por la función map(). Esta función transforma cada elemento de
    un Stream en un nuevo elemento. En este caso, la función map() transforma cada elemento del Stream en un Double.


     */

}
