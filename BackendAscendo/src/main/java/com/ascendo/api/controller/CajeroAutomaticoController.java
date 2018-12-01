/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.controller;

import com.ascendo.api.fachada.ICajeroAutomaticoFachada;
import com.ascendo.api.vo.CajeroAutomaticoVo;
import com.ascendo.api.vo.RetirosVo;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cesar
 */
@RestController
@RequestMapping("/api/cajero")
public class CajeroAutomaticoController {

    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(CajeroAutomaticoController.class);
    @Autowired
    private ICajeroAutomaticoFachada cajeroAutomaticoFachada;

    @RequestMapping(value = "/guardarDineroCajero", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CajeroAutomaticoVo> guardarDineroCajero(@RequestBody CajeroAutomaticoVo guardarDinero) {

        String mensaje = "";
        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Entro al servicio guardarDineroCajero");
            CajeroAutomaticoVo cajero = new CajeroAutomaticoVo();
            mensaje = cajeroAutomaticoFachada.guardarDineroCajero(guardarDinero);
            cajero.setMensaje(mensaje);
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<CajeroAutomaticoVo>(cajero, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
            return new ResponseEntity<CajeroAutomaticoVo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/consultaDineroCajero", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<CajeroAutomaticoVo>> consultaDineroCajero() {

        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Entro al servicio consultaDineroCajero");
            ArrayList<CajeroAutomaticoVo> consultaDinero = new ArrayList<>();
            consultaDinero = cajeroAutomaticoFachada.consultaDineroCajero();
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<ArrayList<CajeroAutomaticoVo>>(consultaDinero, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
            return new ResponseEntity<ArrayList<CajeroAutomaticoVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/retiroDineroCajero", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetirosVo> retiroDineroCajero(@RequestBody RetirosVo retiroDinero) {

        String mensaje = "";
        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Entro al servicio retiroDineroCajero");
            RetirosVo retiro = new RetirosVo();
            mensaje = cajeroAutomaticoFachada.retiroDineroCajero(retiroDinero);
            retiro.setMensaje(mensaje);
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<RetirosVo>(retiro, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
            return new ResponseEntity<RetirosVo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
