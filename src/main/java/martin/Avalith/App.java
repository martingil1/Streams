package martin.Avalith;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<String> listString = new ArrayList<>();
        listString.add("NoEsPar");
        listString.add("EsParr");
        listString.add("OtroParr");
        listString.add("Impar");
        // removeEvenLength(listString);
        //digitsLettersAndSpaces("Soy Independiente, aunque no gano la libertadores desde 1985, sigo siendo el máximo campeón con 7 copas");
        //revert("Arriba la Birra");
        //String c = caesarCypher("abcde");
        //System.out.println(c);
    }

    /*1) Escribir un método removeEvenLength() que reciba un ArrayList de String
    y elimine todos los String de longitud par.*/
    public static void removeEvenLength(List<String> listString) {
        listString.stream().filter(s -> s.length() % 2 != 0)
                .forEach(System.out::println);
    }


    /*
    2) Escribir un método, que dado un String cuente diferentes tipos de caracteres. Deberá imprimir el número de letras,
    dígitos y espacios en blanco de la cadena.
    Input: “Soy Independiente, aunque no gano la libertadores
    desde 1985, sigo siendo el máximo campeón con 7 copas”
	Output: N/A
	Screen: Dígitos: 5 Letras: 80 Espacios: 16*/

    public static void digitsLettersAndSpaces(String s) {

        s.chars().mapToObj(i -> (char) i)
                .map(App::getCharType)
                .filter(charType -> !charType.equals(CharType.OTHER))
                .collect(Collectors.groupingBy(CharType::name, Collectors.counting()))
                .forEach((k, v) -> System.out.println(String.format("%s: %d", k, v)));
    }

    public static CharType getCharType(Character c) {
        if (Character.isAlphabetic(c)) {
            return CharType.ALPHABETIC;
        }
        if (Character.isDigit(c)) {
            return CharType.NUMERIC;
        }
        if (Character.isSpaceChar(c)) {
            return CharType.SPACE;
        }
        return CharType.OTHER;
    }

    private enum CharType {
        NUMERIC, ALPHABETIC, SPACE, OTHER
    }

        /*String srs[] = s.split("");
        List<Character> arrayString = new ArrayList<>();
        System.out.println(arrayString.stream()
               .collect(groupingBy((c ->Character.isDigit((Character) c)))));
        Long digits = arrayString.stream()
               .filter(c -> c.matches("[0-9]"))
                .collect(counting());
        Long letters = arrayString.stream()
                .filter(c -> c.matches("[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ]"))
                .collect(counting());
        Long spaces = arrayString.stream()
                .filter(c -> c.matches("[ ]"))
                .collect(counting());

        System.out.println("Dígitos: " + digits + " Letras: " + letters + " Espacios: " + spaces);*/



    /*
    3)Realizar un método, el cual reciba un String y realice el cifrado Caesar. Se debe cambiar cada letra por la siguiente.
    Input: “abcde”
        Output: “bcdef”*/
    public static String caesarCypher(String s) {
        return s.chars()
                .boxed()
                .map(App::caesar)
                .map(i -> String.valueOf((char) i.intValue()))
                .reduce("", (a, b) -> a + b);
    }

    public static Integer caesar(Integer i) {
        if (i.equals(90)) {
            return 65;
        } else if (i.equals(122)) {
            return 97;
        } else {
            return i + 1;
        }
    }



    /*
    4) Escribir un método, que dado un String reordena las palabras de atrás hacia adelante
    Input: “Arriba la Birra”
    Output: “Birra la Arriba”*/


    public static void revert(String string) {
        StringBuilder sBuilder = new StringBuilder(string);
        string = (sBuilder.reverse()).toString();

        System.out.println(Stream.of(string.split(" "))
                .map(sAux -> new StringBuilder(sAux).reverse().append(" "))
                .collect(Collectors.joining()));
    }


}
