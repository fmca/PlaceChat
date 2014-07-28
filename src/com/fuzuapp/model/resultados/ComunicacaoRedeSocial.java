/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.resultados;

import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe_2
 */
public class ComunicacaoRedeSocial {

    IRedeSociaisAdapter twitter;
    IRedeSociaisAdapter youtube;
    IRedeSociaisAdapter flickr;
    
    public ComunicacaoRedeSocial(){
        inicializarRedes();
    }
    
    private void inicializarRedes(){
        twitter = new TwitterAdapter();
        youtube = new YoutubeAdapter();
        flickr = new FlickrAdapter();
    }
    public List<Resultado> buscarResultados(GeoPoint localizacao, double raio) {


        List<Resultado> resultados = new ArrayList<Resultado>();
        resultados.addAll(twitter.getResultados(localizacao,raio));
        resultados.addAll(youtube.getResultados(localizacao, raio));
        resultados.addAll(flickr.getResultados(localizacao, raio));
        return resultados;
    }
    
}
