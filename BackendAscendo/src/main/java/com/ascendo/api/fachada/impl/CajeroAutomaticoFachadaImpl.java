/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.fachada.impl;

import com.ascendo.api.dao.ICajeroAutomaticoDao;
import com.ascendo.api.fachada.ICajeroAutomaticoFachada;
import com.ascendo.api.vo.CajeroAutomaticoVo;
import com.ascendo.api.vo.RetirosVo;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.Level;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cesar
 */
@Service("CajeroService")
@Transactional
public class CajeroAutomaticoFachadaImpl implements ICajeroAutomaticoFachada {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(CajeroAutomaticoFachadaImpl.class);
    @Autowired
    private ICajeroAutomaticoDao cajeroAutomaticoDao;

    @Override
    public String guardarDineroCajero(CajeroAutomaticoVo guardarDinero) {
        //init variables
        String mensaje = "";
        boolean banderaInsert = true;
        try {
            //guardo dinero cajero
            banderaInsert = banderaInsert && cajeroAutomaticoDao.guardarDineroCajero(guardarDinero);
            //valido insert    
            if (banderaInsert) {
                mensaje = "Guardado satisfactoriamente";
            } else {
                //se valida antes de enviar el mensaje generico si se envia el mensaje con regla de negocio de denominacion
                if (guardarDinero.getMensaje().equals("")) {
                    //error al guardar    
                    mensaje = "Error al guardar el usuario";
                } else {
                    //error de negocio
                    mensaje = "Error solo se permiten transacciones de billetes con denominacion para colombia (1000-2000-5000-10000-20000-50000-100000) ";
                }
            }
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
        }
        return mensaje;
    }

    @Override
    public ArrayList<CajeroAutomaticoVo> consultaDineroCajero() {
        //arreglo consulta dinero
        ArrayList<CajeroAutomaticoVo> consultaDineroList = new ArrayList<>();
        try {
            consultaDineroList = cajeroAutomaticoDao.consultaDineroCajero();
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
        }
        return consultaDineroList;
    }

    @Override
    public String retiroDineroCajero(RetirosVo retiroDinero) {
        //init variables
        String mensaje = "";
        boolean banderaInsert = true;
        try {
            //guardo usuario
            banderaInsert = banderaInsert && cajeroAutomaticoDao.retiroDineroCajero(retiroDinero);
            //valido insert    
            if (banderaInsert) {
                if (retiroDinero.getMensajeExt().equals("")) {
                    mensaje = retiroDinero.getMensajeExt();
                }
            } else {
                //se valida antes de enviar el mensaje generico si se envia el mensaje con regla de negocio de retiros
                if (retiroDinero.getMensaje().equals("")) {
                    //error al guardar    
                    mensaje = "Error al guardar el usuario";
                } else {
                    mensaje = retiroDinero.getMensaje();
                }

            }
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
        }
        return mensaje;
    }

}
