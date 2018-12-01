/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.fachada;

import com.ascendo.api.vo.CajeroAutomaticoVo;
import com.ascendo.api.vo.RetirosVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface ICajeroAutomaticoFachada {
    
    //guarda dinero cajero
     public String guardarDineroCajero(CajeroAutomaticoVo guardarDinero);
     //consulta dinero cajero
     public ArrayList<CajeroAutomaticoVo> consultaDineroCajero();
     //retiro dinero cajero
     public String retiroDineroCajero(RetirosVo retiroDinero);
    
}
