package com.fuzuapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by filipe on 27/07/14.
 */
public class Prop {

    public static final String TYPE_LIST = "list";
    public static final String TYPE_HIBERNATE = "hibernate";
    public static final String TYPE = "type";

    public  String getPropriedade(String nome) {
        Properties prop = new Properties();
        InputStream input = null;
        String resp = null;

        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("repo.properties"));
            resp = prop.getProperty(nome);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return resp;


    }
}
