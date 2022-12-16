package org.example.helpers;

import software.amazon.awssdk.services.athena.model.Datum;

import java.util.ArrayList;
import java.util.List;

public class Helpers {
    public static ArrayList<String> holdColumnInfo(List<Datum> columns){
        ArrayList<String> colList = new ArrayList<>();
        for(Datum eachCol: columns){
            colList.add(eachCol.varCharValue());
        }
        return colList;
    }

    public static <T> T createGenericInstance(Object o, Class<T> clase) {
        try {
            return clase.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }
}