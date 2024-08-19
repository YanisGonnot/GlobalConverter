package com.company;

import java.util.ArrayList;

public class Converter {

    private String message;
    private String after_converter;
    private String before_converter;

    // Dans le constructeur, les bases Avant et Après servent uniquement pour la méthode globale de conversion
    public Converter(String string, String base1, String base2){
        this.message = string;
        this.before_converter = base1;
        this.after_converter = base2;
        Ascii tab_ascii_valid = new Ascii();
    }

    /*
            On admet que notre chaîne de caractères comporte des espaces et les traite en plus caractères alphabétiques et numériques.
            Les caractères de ponctuation ne seront pas traités et donc conservés comme des erreurs.
    */
    public static boolean isCaractereValide(int caractere_ASCII){
        if (caractere_ASCII == 32){ // 32 correspond au caractère vide ' '.
            return true;
        }
        else if (Ascii.numbers_ASCII.contains(caractere_ASCII)) {
            return true;
        }
        else if (Ascii.upperCaseLetters_ASCII.contains(caractere_ASCII)) {
            return true;
        }
        else if (Ascii.lowerCaseLetters_ASCII.contains(caractere_ASCII)) {
            return true;
        }
        return false;
    }


    public boolean isBaseValid(String base){
        return base.matches("text|t|hexadecimal|h|octal|o|decimal|d|binary|b");
    }



    public ArrayList<Integer> convertFromTextToDecimal() throws IllegalArgumentException{
        ArrayList<Integer> liste_ASCII = new ArrayList();
        for (int index = 0; index < message.length(); index++){
            int caractere_ASCII = (int) message.charAt(index);
            if (isCaractereValide(caractere_ASCII)) {
                liste_ASCII.add(caractere_ASCII);
            }
            else {
                System.out.println(message.charAt(index) + " n'est pas pris en compte dans la base écrite. \n" +
                        "Veuillez taper un nouveau message.");
                //IllegalArgumentException exception = new IllegalArgumentException();
                return new ArrayList<>();
            }
        }
        //System.out.println("Le message codé en ASCII:" + liste_ASCII);
        return liste_ASCII;
    }


    public ArrayList<Integer> convertFromDecimalStringToDecimal() {
        ArrayList<Integer> liste_ASCII = new ArrayList<>();
        if (message.contains(" ")){
            String sub_message = message;
            while (sub_message.contains(" ")){
                int index1 = sub_message.indexOf(' ');
                //System.out.println("index1: "+ index1);
                Integer num = Integer.valueOf(sub_message.substring(0, index1));
                if (isCaractereValide(num)){
                    liste_ASCII.add(num);
                    //System.out.println(liste_ASCII);
                    sub_message = sub_message.substring(Math.min(index1+1, sub_message.length()));
                    //System.out.println("Substring: " + sub_message);
                }
                else {
                    System.out.println(num + " ne correspond pas ni à une lettre, ni à un chiffre.\n" +
                            "Veuillez taper un nouveau message.");
                    //IllegalArgumentException exception = new IllegalArgumentException();
                    return new ArrayList<>();
                }

            }
            if (!sub_message.isEmpty()){
                if (isCaractereValide(Integer.valueOf(sub_message))){
                    liste_ASCII.add(Integer.valueOf(sub_message));
                }
                else {
                    System.out.println(Integer.valueOf(sub_message) + " ne correspond pas ni à une lettre, ni à un chiffre.\n" +
                            "Veuillez taper un nouveau message.");
                    //IllegalArgumentException exception = new IllegalArgumentException();
                    return new ArrayList<>();
                }
            }
            return liste_ASCII;
        }
        else {
            liste_ASCII.add(Integer.valueOf(message.substring(0, message.length())));
            return liste_ASCII;
        }
    }


    public ArrayList<Integer> convertFromBinaryToDecimal() throws IllegalArgumentException {
        ArrayList<Integer> liste_ASCII = new ArrayList();
        for (int index = 0; index < message.length(); index += 8) {
            if (message.charAt(index) == ' '){
                index += 1;
            }
            String binary_number = message.substring(index, Math.min(index + 8, message.length()));
            //System.out.println(binary_number);
            int caractere_ASCII = 0;
            int power = 0;
            for (int i = binary_number.length() - 1; i >= 0; i--) {
                char bit = binary_number.charAt(i);
                if (bit == '1') {
                    caractere_ASCII += Math.pow(2, power);
                }
                power++;
            }
            if (isCaractereValide(caractere_ASCII)) {
                liste_ASCII.add(caractere_ASCII);
            } else {
                System.out.println((char) caractere_ASCII + " n'est pas pris en compte dans la base écrite.\n" +
                        "Veuillez taper un nouveau message.");
                //IllegalArgumentException exception = new IllegalArgumentException();
                return new ArrayList<>();
            }
        }
        //System.out.println(liste_ASCII);
        return liste_ASCII;
    }


    public ArrayList<Integer> convertFromOctalToDecimal() throws IllegalArgumentException {
        ArrayList<Integer> liste_ASCII = new ArrayList();
        for (int index = 0; index < message.length(); index += 3) {
            if (message.charAt(index) == ' '){
                index += 1;
            }
            String octal_number = message.substring(index, Math.min(index + 3, message.length()));
            //System.out.println(octal_number);
            int caractere_ASCII = 0;
            for (int index2 = 0; index2 < 3; index2++) {
                //System.out.println(index2);
                //System.out.println(octal_number.charAt(index2));
                caractere_ASCII += Character.getNumericValue(octal_number.charAt(index2)) * Math.pow(8, 2-index2);
                //System.out.println(caractere_ASCII);
            }
            if (isCaractereValide(caractere_ASCII)) {
                liste_ASCII.add(caractere_ASCII);
            } else {
                System.out.println((char) caractere_ASCII + " n'est pas pris en compte dans la base écrite.\n" +
                        "Veuillez taper un nouveau message.");
                //IllegalArgumentException exception = new IllegalArgumentException();
                return new ArrayList<>();
            }
        }
        //System.out.println(liste_ASCII);
        return liste_ASCII;
    }


    public ArrayList<Integer> convertFromHexToDecimal() throws IllegalArgumentException{
        ArrayList<Integer> list_ASCII = new ArrayList<>();
        for (int index = 0; index < message.length(); index+=2){
            if (message.charAt(index) == ' '){
                index += 1;
            }
            String hex_number = message.substring(index, Math.min(index + 2, message.length()));
            //System.out.println("word: " + hex_number);
            int caractere_ASCII = 0;
            for (int index2 = 0; index2 < 2; index2++) {
                caractere_ASCII += Character.getNumericValue(hex_number.charAt(index2))*Math.pow(16, 1-index2);
                //System.out.println(caractere_ASCII);
            }
            if (isCaractereValide(caractere_ASCII)) {
                list_ASCII.add(caractere_ASCII);
            } else {
                System.out.println((char) caractere_ASCII + " n'est pas pris en compte dans la base écrite.\n" +
                        "Veuillez taper un nouveau message.");
                //IllegalArgumentException exception = new IllegalArgumentException();
                return new ArrayList<>();
            }
        }
        //System.out.println(list_ASCII);
        return list_ASCII;
    }


    public String convertFromDecimalToText(ArrayList<Integer> code_ASCII) {
        StringBuilder text_string = new StringBuilder();
        for (Integer valeur_ascii : code_ASCII) {
            if (valeur_ascii == 32) {
                text_string.append(' ');
            }

            else if (Ascii.numbers_list.contains(valeur_ascii)){
                text_string.append(Ascii.numbers_list.indexOf(valeur_ascii));
            }

            else if (Ascii.upperCaseLetters_ASCII.contains(valeur_ascii)) {
                int index = Ascii.upperCaseLetters_ASCII.indexOf(valeur_ascii);
                text_string.append(Ascii.lettresMaj_list.get(index));
            }

            else if (Ascii.lowerCaseLetters_ASCII.contains(valeur_ascii)) {
                text_string.append(Ascii.lettresMin_list.get(
                        Ascii.lowerCaseLetters_ASCII.indexOf(valeur_ascii)));
            }
        }
        //System.out.println("Le message codé en texte est: " + text_string.toString());
        return text_string.toString();
    }


    public String convertFromDecimalToBinary(ArrayList<Integer> code_ASCII) {
        StringBuilder binaire_string = new StringBuilder();
        for (Integer valeur_ascii : code_ASCII) {
            if (valeur_ascii == 0){
                binaire_string.append("00000000");
            }
            String binaire_number = "";
            while (valeur_ascii > 0) {
                binaire_number = (valeur_ascii % 2) + binaire_number;
                //System.out.println(valeur_ascii + ": en binaire donne " + binaire_number);
                valeur_ascii = valeur_ascii / 2;
            }
            while (binaire_number.length() < 8){
                binaire_number = "0" + binaire_number;
            }
            binaire_string.append(binaire_number + " ");
        }
        if (binaire_string.toString().isEmpty()) { return "" ; }
        else {
            String result = binaire_string.substring(0, binaire_string.length()-1);
            //System.out.println("Le message codé en Binaire est : " + result);
            return result;
        }
    }


    public String convertFromDecimalToOctal(ArrayList<Integer> code_ASCII) {
        StringBuilder octale_string = new StringBuilder();
        for (Integer valeur_ascii: code_ASCII) {
            if (valeur_ascii == 0){
                octale_string.append("000 ");
            }
            String octale_number = "";
            while (valeur_ascii > 0) {
                octale_number = (valeur_ascii % 8) + octale_number;
                //System.out.println(valeur_ascii + ": en base octale donne " + octale_number);
                valeur_ascii = valeur_ascii / 8;
            }
            while (octale_number.length() < 3){
                octale_number = "0" + octale_number;
            }
            octale_string.append(octale_number + " ");
        }
        //System.out.println("Le message codé en Octal est : " + octale_string.toString());
        if (octale_string.toString().isEmpty()){
            return "";
        }
        else {return octale_string.substring(0, octale_string.length()-1); }
    }


    public String convertFromDecimalToHex(ArrayList<Integer> code_ASCII) {
        StringBuilder hex_string = new StringBuilder();
        for (Integer valeur_ascii : code_ASCII){
            if (valeur_ascii == 0){
                hex_string.append("00 ");
            }
            String hex_number = "";
            while(valeur_ascii > 0){
                hex_number = Ascii.hexChars[(valeur_ascii % 16)] + hex_number;
                //System.out.println(valeur_ascii + ": en base hexadecimale donne " + hex_number);
                valeur_ascii = valeur_ascii / 16;
            }

            hex_string.append(hex_number + " ");
        }
        //System.out.println("Le message codé en Héxadécimal est : " + hex_string.toString());
        if (hex_string.toString().isEmpty()) {return ""; }
        else {return hex_string.substring(0, hex_string.length()-1); }
    }


    public String convertFromDecimalToDecimalString(ArrayList<Integer> code_ASCII){
        String decimal_string = "";
        StringBuilder builder = new StringBuilder(code_ASCII.toString());
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length()-1);
        decimal_string = builder.toString();
        //System.out.println(decimal_string);
        decimal_string = decimal_string.replace(",", "");
        //System.out.println(decimal_string);
        /*
        for(Integer number: code_ASCII){
            decimal_string = decimal_string + number;
        }
        */
        return decimal_string;


    }



    public String global_converter(){
        String result = "";
        while (!isBaseValid(this.before_converter)){
            System.out.println("La base actuelle n'est pas prise en compte. \n" +
                    "Veuillez en choisir une nouvelle parmi celles-ci : text|t|hexadecimal|h|octal|o|decimal|d|binary|b.");
            break;
        }
        while (!isBaseValid(this.after_converter)){
            System.out.println("La base souhaitée n'est pas prise en compte. \n" +
                    "Veuillez en choisir une nouvelle parmi celles-ci : text|t|hexadecimal|h|octal|o|decimal|d|binary|b.");
            break;
        }
        ArrayList<Integer> message_decimal = new ArrayList<>();
        switch (this.before_converter){
            case "decimal" :
            case "d" :
                message_decimal = convertFromDecimalStringToDecimal();
                break;
            case "text" :
            case "t" :
                message_decimal = convertFromTextToDecimal();
                break;
            case "binary" :
            case "b" :
                message_decimal = convertFromBinaryToDecimal();
                break;
            case "hexadecimal" :
            case "h" :
                message_decimal = convertFromHexToDecimal();
                break;
            case "octal" :
            case "o" :
                message_decimal = convertFromOctalToDecimal();
                break;
        }
        switch (this.after_converter){
            case "decimal" :
            case "d" :
                result = convertFromDecimalToDecimalString(message_decimal);
                break;
            case "text" :
            case"t" :
                result = convertFromDecimalToText(message_decimal);
                break;
            case "binary" :
            case "b" :
                result = convertFromDecimalToBinary(message_decimal);
                break;
            case "octal" :
            case "o" :
                result = convertFromDecimalToOctal(message_decimal);
                break;
            case "hexadecimal" :
            case "h" :
                result = convertFromDecimalToHex(message_decimal);
                break;
        }
        return result;
    }


}

