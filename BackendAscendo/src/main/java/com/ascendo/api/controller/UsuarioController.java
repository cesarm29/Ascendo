/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.controller;

import com.ascendo.api.fachada.IUsuarioFachada;
import com.ascendo.api.vo.UsuarioVo;
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
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioFachada usuarioFachada;
    
    @RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioVo> guardarUsuario(@RequestBody UsuarioVo guardarUsuario) {

        String mensaje = "";
        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Entro al servicio guardarUsuario");
            UsuarioVo usuario = new UsuarioVo();
            mensaje = usuarioFachada.guardarUsuario(guardarUsuario);
            usuario.setMensaje(mensaje);
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<UsuarioVo>(usuario, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
            return new ResponseEntity<UsuarioVo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/loginUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<UsuarioVo>> loginUsuario(@RequestBody UsuarioVo loginUsuario) {

        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Entro al servicio loginUsuario");           
            ArrayList<UsuarioVo> usuario = new ArrayList<>();
             usuario = usuarioFachada.loginUsuario(loginUsuario);
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<ArrayList<UsuarioVo>>(usuario, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
            return new ResponseEntity<ArrayList<UsuarioVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
