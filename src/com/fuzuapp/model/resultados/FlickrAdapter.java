package com.fuzuapp.model.resultados;

import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by filipe on 28/07/14.
 */
public class FlickrAdapter implements IRedeSociaisAdapter {

    public static void main(String[] args) throws FlickrException {
        GeoPoint g = new GeoPoint();
        g.setLatitude(-8.08);
        g.setLongitude(-34.94);
        for (Resultado r : new  FlickrAdapter().getResultados(g, 2)){
            System.out.println(r);
        }
    }

    @Override
    public List<Resultado> getResultados(GeoPoint ponto, double raio) {

            String apikey = "af10cdd6ab2eda09f4a832ed791aaade";
            String secret = "68a23ba8834f93ae";

            Flickr flickr = new Flickr(apikey, secret, new REST());

            SearchParameters searchParameters = new SearchParameters();

            searchParameters.setLatitude(ponto.getLatitude().toString());
            searchParameters.setLongitude(ponto.getLongitude().toString());
            searchParameters.setRadius(raio>0 ? (int) raio : 1);


        PhotoList<Photo> list = new PhotoList<>();
        try {
            list = flickr.getPhotosInterface().search(searchParameters, 20, 0);
        } catch (FlickrException e) {
            e.printStackTrace();
        }

        List<Resultado> resultados = new ArrayList<>();


        List<String> urls = new ArrayList<String>();

            for(Photo photo: list){
                try {
                    Resultado r = new Resultado();
                    r.setTipo(Resultado.IMAGEM);
                    //r.setHorario(new SimpleDateFormat("dd/MM HH:mm").format(photo.));
                    r.setNomeUsuario(photo.getOwner().getId());
                    r.setUrl(photo.getUrl());
                    r.setDescricao(photo.getTitle() + " - " + photo.getDescription());
                    r.setFotoUrl(photo.getMedium640Url());
                    resultados.add(r);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            return resultados;
        }

}
