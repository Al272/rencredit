package ru.burlakov.framework.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Parser {
    public static String stringFormat (String value){
        String valueF = value.replaceAll(" ","").replaceAll(",",".");
        double x = Double.parseDouble(valueF);

            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            return formatter.format(x);
    }

    public static void main(String[] args) {

        System.out.println(stringFormat("831003,59"));
    }
}
