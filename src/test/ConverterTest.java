package test;

import com.company.Converter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



public class ConverterTest {

    String message1 = "Hello World" ;
    String message3 = " ";
    String message4 = "J'ai bien mangé (...)";

    String erreur = "La base choisie n'est pas prise en compte. \n" +
            "Veuillez en choisir une nouvelle.";

    String bin1 = "01001000 01100101 01101100 01101100 01101111 00100000 01010111 01101111 " +
            "01110010 01101100 01100100";

    String decimal1 = "89";
    String decimal2 = "8 9";
    String decimal3 = "100 56 32";

    String octal1 = "110 145 154 154 157 040 127 157 162 154 144";

    String hex1 = "48 65 6C 6C 6F 20 57 6F 72 6C 64";

    ArrayList creer_lists(int index){
        ArrayList<Integer> list = new ArrayList();
        switch (index){
            case 1:
                // "Hello Wordl" en decimal
                list.add(72);
                list.add(101);
                list.add(108);
                list.add(108);
                list.add(111);
                list.add(32);
                list.add(87);
                list.add(111);
                list.add(114);
                list.add(108);
                list.add(100);
                return list;

            case 2:
                // "Hel" en decimal
                list.add(72);
                list.add(101);
                list.add(108);
                return list;

            case 3:
                // espace vide en decimal
                list.add(32);
                return list;
            case 4:
                // liste vide
                return list;
            case 5:
                // [89]
                list.add(89);
                return list;
            case 6:
                //[8,9]
                list.add(8);
                list.add(9);
                return list;
            case 7:
                // [100, 56, 32]
                list.add(100);
                list.add(56);
                list.add(32);
                return list;
        }
        return list;
    }

    // Les bases ici ne servent uniquement qu'au test de ma méthode global_converter
    Converter converter1 = new Converter(message1, "t", "d");
    Converter converter2 = new Converter(message1, "text", "b");
    Converter converter3 = new Converter(message4, "t", "o");
    Converter converter4 = new Converter(hex1, "hexadecimal", "h");
    Converter converter5 = new Converter(decimal1, "decimal", "d");
    Converter converter6 = new Converter(bin1, "binary", "octal");
    Converter converter7 = new Converter(octal1, "o", "b");
    Converter converter8 = new Converter(decimal2, "d", "text");
    Converter converter9 = new Converter(decimal3, "d", "binary");
    Converter converter10 = new Converter("01100100 00111000 00100000", "binary", "d");


    @Test
    void test_isCaractereValide(){
        assertFalse(converter1.isCaractereValide((int)'!')); // caractère de ponctuation
        assertTrue(converter1.isCaractereValide((int)' ')); // espace
        assertTrue(converter1.isCaractereValide((int)'g')); // lettre minuscule
        assertTrue(converter1.isCaractereValide((int)'5')); // chiffre
        assertFalse(converter1.isCaractereValide((int)'{')); // caractère spécial
    }

    @Test
    void test_isBaseValid(){
        assertTrue(converter1.isBaseValid("b"));
    }

    @Test
    void test_convertFromTextToDecimal(){
        assertEquals(creer_lists(1), converter1.convertFromTextToDecimal());
        //assertNotEquals(creer_lists(1), converter3.convertFromTextToDecimal());
        assertEquals(creer_lists(4), converter3.convertFromTextToDecimal());
        assertEquals(creer_lists(1), converter2.convertFromTextToDecimal());
    }

    @Test
    void test_convertFromDecimalStringToDecimal(){
        assertEquals(creer_lists(5), converter5.convertFromDecimalStringToDecimal());
        assertNotEquals(creer_lists(6), converter8.convertFromDecimalStringToDecimal());
        assertEquals(creer_lists(7), converter9.convertFromDecimalStringToDecimal());
    }

    @Test
    void test_convertFromBinaryToDecimal(){
        assertEquals(creer_lists(1), converter6.convertFromBinaryToDecimal());
    }

    @Test
    void test_convertFromOctalToDecimal(){
        assertEquals(creer_lists(1), converter7.convertFromOctalToDecimal());
    }

    @Test
    void test_convertFromHexToDecimal(){
        assertEquals(creer_lists(1), converter4.convertFromHexToDecimal());
    }


    @Test
    void test_convertFromDecimalToText(){
        assertEquals(message1, converter1.convertFromDecimalToText(creer_lists(1)));
        assertEquals(message3, converter2.convertFromDecimalToText(creer_lists(3)));
        assertEquals("", converter3.convertFromDecimalToText(creer_lists(4)));
    }

    @Test
    void test_convertFromDecimalToBinary(){
        assertEquals(bin1, converter4.convertFromDecimalToBinary(creer_lists(1)));
        assertEquals(bin1, converter2.convertFromDecimalToBinary(creer_lists(1)));
    }

    @Test
    void test_convertFromDecimalToOctal(){
        assertEquals(octal1, converter5.convertFromDecimalToOctal(creer_lists(1)));
    }

    @Test
    void test_convertFromDecimalToHex(){
        assertEquals(hex1, converter5.convertFromDecimalToHex(creer_lists(1)));
    }

    @Test
    void test_convertFromDecimalToDecimalString(){
        assertEquals(decimal3, converter5.convertFromDecimalToDecimalString(creer_lists(7)));
    }


    @Test
    void test_globalconverter(){
        //assertEquals("", converter3.global_converter());
        assertEquals(hex1, converter4.global_converter());
        assertEquals(bin1, converter2.global_converter());
        assertEquals("01100100 00111000 00100000", converter9.global_converter());
        assertEquals(decimal3, converter10.global_converter());

    }

}
