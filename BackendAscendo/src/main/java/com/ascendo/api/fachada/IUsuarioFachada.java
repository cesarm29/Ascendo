/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.fachada;

import com.ascendo.api.vo.UsuarioVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface IUsuarioFachada {
    
    //guarda usuario
     public String guardarUsuario(UsuarioVo guardarUsuario);
     //busca usuario
     public ArrayList<UsuarioVo> loginUsuario(UsuarioVo loginUsuario);
    
}
