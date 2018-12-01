/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.dao;

import com.ascendo.api.vo.UsuarioVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface IUsuarioDao {
    //Metodo que guarda el usuario
    public boolean guardarUsuario(UsuarioVo guardarUsuario);
    //Metodo que busca usuario
    public ArrayList<UsuarioVo> loginUsuario(UsuarioVo loginUsuario);
    
}
