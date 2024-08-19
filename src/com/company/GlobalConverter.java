package com.company;

public class GlobalConverter {

    public static void main(String[] args) {

        //Version finale
        String message = args[0] ;
        String base1 = args[1];
        String base2 = args[2];

        Converter converter = new Converter(message, base1, base2);
        System.out.println("Le résultat de la conversion est : " + converter.global_converter());

    }
}






