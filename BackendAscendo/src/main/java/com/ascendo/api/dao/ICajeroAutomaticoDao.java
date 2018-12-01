/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.dao;

import com.ascendo.api.vo.CajeroAutomaticoVo;
import com.ascendo.api.vo.RetirosVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface ICajeroAutomaticoDao {
    
    //Metodo que guarda dinero en cajero
    public boolean guardarDineroCajero(CajeroAutomaticoVo guardarDinero);
    //Metodo que consulta dinero en cajero
    public ArrayList<CajeroAutomaticoVo> consultaDineroCajero();
    //Metodo que retira dinero en cajero
    public boolean retiroDineroCajero(RetirosVo retiroDinero);
    
}
