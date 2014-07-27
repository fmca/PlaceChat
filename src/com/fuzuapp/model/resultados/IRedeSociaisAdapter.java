/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fuzuapp.model.resultados;

import com.fuzuapp.model.resultados.entidades.GeoPoint;
import com.fuzuapp.model.resultados.entidades.Resultado;
import java.util.List;

/**
 *
 * @author Filipe_2
 */
interface IRedeSociaisAdapter {
    List<Resultado> getResultados(GeoPoint ponto, double raio);
}
