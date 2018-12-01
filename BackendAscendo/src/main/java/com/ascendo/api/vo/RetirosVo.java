/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.vo;

/**
 *
 * @author cesar
 */
public class RetirosVo {
    
    private Integer idRetiro;
    private Integer valor;
    private Integer idUsuario;
    private Integer idCajero;
    private String mensaje;
    private String mensajeExt;

    public RetirosVo() {
    }

    public RetirosVo(Integer idRetiro, Integer valor, Integer idUsuario, Integer idCajero, String mensaje, String mensajeExt) {
        this.idRetiro = idRetiro;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.idCajero = idCajero;
        this.mensaje = mensaje;
        this.mensajeExt = mensajeExt;
    }

    public Integer getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(Integer idRetiro) {
        this.idRetiro = idRetiro;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeExt() {
        return mensajeExt;
    }

    public void setMensajeExt(String mensajeExt) {
        this.mensajeExt = mensajeExt;
    }

    
   
}
