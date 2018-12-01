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
public class CajeroAutomaticoVo {
    
    private Integer idCajeroAutomatico;
    private Integer denominacion;
    private Integer cantidad;
    private String mensaje;

    public CajeroAutomaticoVo() {
    }

    public CajeroAutomaticoVo(Integer idCajeroAutomatico, Integer denominacion, Integer cantidad, String mensaje) {
        this.idCajeroAutomatico = idCajeroAutomatico;
        this.denominacion = denominacion;
        this.cantidad = cantidad;
        this.mensaje = mensaje;
    }

    public Integer getIdCajeroAutomatico() {
        return idCajeroAutomatico;
    }

    public void setIdCajeroAutomatico(Integer idCajeroAutomatico) {
        this.idCajeroAutomatico = idCajeroAutomatico;
    }

    public Integer getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Integer denominacion) {
        this.denominacion = denominacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }  
}
