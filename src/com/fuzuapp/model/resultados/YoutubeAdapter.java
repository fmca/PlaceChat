/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.resultados;

import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author filipe
 */
public class YoutubeAdapter implements IRedeSociaisAdapter{

    private YouTube youtube;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    
    @Override
    public List<Resultado> getResultados(GeoPoint ponto, double raio) {
        
        try {
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("place-chat").build();

            // Prompt the user to enter a query term.
            String queryTerm = getInputQuery();

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = "AIzaSyBUJTCSWev-WSsmjvpUHT2u1T12rQWPFTc";
            search.setKey(apiKey);
            search.setLocation(ponto.getLatitude()+","+ponto.getLongitude());
            search.setLocationRadius(String.valueOf(raio)+"km");

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url,snippet/description,snippet/publishedAt)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            search.setOrder("date");

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();

            List<Resultado> resultados = new ArrayList<Resultado>();

            List<SearchResult> searchResultList = searchResponse.getItems();

            for(SearchResult sr: searchResultList){
                ResourceId rId = sr.getId();

                if (rId.getKind().equals("youtube#video")) {
                    Resultado resultado = new Resultado();
                    resultado.setTipo(Resultado.VIDEO);
                    resultado.setFotoUrl(sr.getSnippet().getThumbnails().getDefault().getUrl());
                    resultado.setHorario(sr.getSnippet().getPublishedAt().toString());
                    resultado.setNomeUsuario(sr.getSnippet().getTitle());
                    resultado.setDescricao(sr.getSnippet().getTitle()+" - "+sr.getSnippet().getDescription());
                    resultado.setUrl("http://www.youtube.com/watch?v="+rId.getVideoId());
                    resultados.add(resultado);
                    System.out.println(resultado);

                }
            }
            
            return resultados;

        } catch (IOException ex) {
            Logger.getLogger(YoutubeAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList();
    }
    
     private static String getInputQuery() throws IOException {

        String inputQuery = "";

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        inputQuery = "YouTube Developers Live";
        
        return inputQuery;
    }
   
     private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
    
}
