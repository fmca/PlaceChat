/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.resultados;

import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Filipe_2
 */
public class TwitterAdapter implements IRedeSociaisAdapter{

    Twitter twitter;
    
    public TwitterAdapter(){
        inicializar();
    }
    @Override
    public List<Resultado> getResultados(GeoPoint ponto, double raio) {
        
        List<Resultado> resultados = new ArrayList();
        try {
            Query query = new Query("");
            GeoLocation geo = new GeoLocation(ponto.getLatitude(), ponto.getLongitude());
            query.setGeoCode(geo, raio, Query.KILOMETERS);
            query.resultType(Query.RECENT);
            query.setCount(20);
            QueryResult result = twitter.search(query);
            
            for (Status status : result.getTweets()) {
                Resultado r = new Resultado();
                
                r.setDescricao(status.getText());
                r.setUrl("http://twitter.com/statuses/"+String.valueOf(status.getId()));
                r.setNomeUsuario(status.getUser().getName());
                r.setHorario(new SimpleDateFormat("dd/MM HH:mm").format(status.getCreatedAt()));
                r.setFotoUrl(status.getUser().getProfileImageURL());
                //r.setLocal(new GeoPoint(status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude()));
                r.setTipo(Resultado.TEXTO);
                
                resultados.add(r);
                
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }

    private void inicializar() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("56dJMai5O3H1MUjUl607LPgOh")
		  .setOAuthConsumerSecret("wLsRXEDxDWAgOW217XbSiSK1J2WNG2gqxVDuLIqreI5VYfKEyD")
		  .setOAuthAccessToken("64546879-myofEJ5cfPHwaRIud8mSBq2BmM68gHo9gGNgvj8Qb")
		  .setOAuthAccessTokenSecret("dzJYWMs4QJUAwOCTisg5eSVLT5xhTp61ecuZ6fNpnARAI");
		TwitterFactory tf = new TwitterFactory(cb.build());
		 twitter = tf.getInstance();

    }
    
}
