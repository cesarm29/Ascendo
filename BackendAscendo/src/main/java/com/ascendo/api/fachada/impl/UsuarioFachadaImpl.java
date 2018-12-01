/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.fachada.impl;

import com.ascendo.api.dao.IUsuarioDao;
import com.ascendo.api.fachada.IUsuarioFachada;
import com.ascendo.api.vo.UsuarioVo;
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
@Service("UsuarioService")
@Transactional
public class UsuarioFachadaImpl implements IUsuarioFachada {
    
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(UsuarioFachadaImpl.class);
    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public String guardarUsuario(UsuarioVo guardarUsuario) {
        //init variables
        String mensaje = "";
        boolean banderaInsert = true;
        try {
            //guardo usuario
            banderaInsert = banderaInsert && usuarioDao.guardarUsuario(guardarUsuario);
            //valido insert    
            if (banderaInsert) {
                mensaje = "Guardado satisfactoriamente";
            } else {
                mensaje = "Error al guardar el usuario";
            }
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
        }
        return mensaje;
    }

    @Override
    public ArrayList<UsuarioVo> loginUsuario(UsuarioVo loginUsuario) {
        
         ArrayList<UsuarioVo> usuarioList = new ArrayList<>();
        try {
            usuarioList = usuarioDao.loginUsuario(loginUsuario);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
        }
        return usuarioList;
    }
    
}
