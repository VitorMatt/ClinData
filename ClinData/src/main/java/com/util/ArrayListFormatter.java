package main.java.com.util;
import java.util.List;

public class ArrayListFormatter {

    public ArrayListFormatter() {
    }

    public String formatarArray(List unidades) {

        String list = "";

        for (Object unidade : unidades) {

            list = list + unidade.toString();
        }

        return list;
    }
}
