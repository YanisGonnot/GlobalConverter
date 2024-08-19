package com.company;

import java.util.ArrayList;

public class Ascii {

    // [0123456789]
    public static ArrayList numbers_list = new ArrayList();
    public static ArrayList numbers_ASCII = new ArrayList();
    public static char[] hexChars = "0123456789ABCDEF".toCharArray();
    public static ArrayList hexCharsList = new ArrayList();

    //LISTE de l'Alphabet en Maj
    public static ArrayList<Character> lettresMaj_list = new ArrayList<>();
    public static ArrayList<Character> lettresMin_list = new ArrayList<>();

    public static ArrayList upperCaseLetters_ASCII = new ArrayList();
    public static ArrayList lowerCaseLetters_ASCII = new ArrayList();


    public Ascii() {
        for (int i = 0; i <= 9; i++) {
            numbers_list.add(i);
            hexCharsList.add(i);
        }

        for (int number = 48; number <= 57; number++){
            numbers_ASCII.add(number);
        }

        for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
            lettresMaj_list.add(lettre);
            if (lettre <= 'F'){
                hexCharsList.add(lettre);
            }
        }

        for (char lettre = 'a'; lettre <= 'z'; lettre++) {
            lettresMin_list.add(lettre);
        }

        for (int i = 65; i <= 90; i++) {
            upperCaseLetters_ASCII.add(i);
        }

        for (int i = 97; i <= 122; i++) {
            lowerCaseLetters_ASCII.add(i);
        }
    }

    public static ArrayList getNumbers_list() { return numbers_list; }

    public static ArrayList getNumbers_ASCII() { return numbers_ASCII; }

    public static ArrayList<Character> getLettresMaj_list() { return lettresMaj_list; }

    public static ArrayList<Character> getLettresMin_list() { return lettresMin_list; }

    public static ArrayList getLowerCaseLetters_ASCII() { return lowerCaseLetters_ASCII; }

    public static ArrayList getUpperCaseLetters_ASCII() { return upperCaseLetters_ASCII; }
}
