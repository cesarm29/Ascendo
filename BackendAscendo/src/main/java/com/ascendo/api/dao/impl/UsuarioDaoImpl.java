/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.dao.impl;

import com.ascendo.api.dao.AbstractDao;
import com.ascendo.api.dao.IUsuarioDao;
import com.ascendo.api.vo.UsuarioVo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cesar
 */
@Repository("UsuarioDao")
public class UsuarioDaoImpl extends AbstractDao implements IUsuarioDao {

    @Override
    public boolean guardarUsuario(UsuarioVo guardarUsuario) {
        //init session
        Session session = getSession();
        //variables 
        String squery = "";
        SQLQuery query = null;
        int filas = 0;
        //insert en tabla usuario
        squery = "INSERT INTO public.usuarios  (idusuarios, nombres, email, password, telefono, direccion, rol_idrol) values (DEFAULT , '" + guardarUsuario.getNombres() + "', '" + guardarUsuario.getEmail() + "', '" + guardarUsuario.getPassword() + "' , " + guardarUsuario.getTelefono() + " , '" + guardarUsuario.getDireccion() + "', " + guardarUsuario.getRol() + ")";
        query = session.createSQLQuery(squery);
        filas = filas + query.executeUpdate();
        //validacion de insert
        if (filas == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<UsuarioVo> loginUsuario(UsuarioVo loginUsuario) {
        //array usuario
        ArrayList<UsuarioVo> usuarioList = new ArrayList<>();
        //init session
        Session sesion = getSession();
        String squery = "SELECT idusuarios, nombres, email, password, telefono, direccion, rol_idrol FROM  public.usuarios where email = '" + loginUsuario.getEmail() + "' and password = '" + loginUsuario.getPassword() + "' ";
        SQLQuery query = sesion.createSQLQuery(squery);
        ArrayList<Object[]> usuarioObject = (ArrayList<Object[]>) query.list();
        //cicle for data
        if (usuarioObject.size() > 0) {
            for (Object[] objectReservacion : usuarioObject) {
                UsuarioVo usuarioType = mapeoUsuario(objectReservacion);
                usuarioList.add(usuarioType);
            }
        }
        return usuarioList;        
    }
    
    //Map to user login
    public UsuarioVo mapeoUsuario(Object[] retorno) {
        UsuarioVo usuario = new UsuarioVo();
        //set data user
        usuario.setIdUsuario(retorno[0] != null ? (Integer) retorno[0] : 0);
        usuario.setNombres(retorno[1] != null ? retorno[1].toString() : null);
        usuario.setEmail(retorno[2] != null ? retorno[2].toString() : null);
        usuario.setPassword(retorno[3] != null ? retorno[3].toString() : null);
        usuario.setTelefono(retorno[4] != null ? (Integer) retorno[4] : 0);
        usuario.setDireccion(retorno[5] != null ? retorno[5].toString() : null);
        usuario.setRol(retorno[6] != null ? (Integer) retorno[6] : 0);
        return usuario;
    }
    
    
    
    
}
