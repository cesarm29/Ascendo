/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascendo.api.dao.impl;

import com.ascendo.api.dao.AbstractDao;
import com.ascendo.api.dao.ICajeroAutomaticoDao;
import com.ascendo.api.vo.CajeroAutomaticoVo;
import com.ascendo.api.vo.RetirosVo;
import java.math.BigInteger;
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
@Repository("CajeroDao")
public class CajeroAutomaticoDaoImpl extends AbstractDao implements ICajeroAutomaticoDao {

    @Override
    public boolean guardarDineroCajero(CajeroAutomaticoVo guardarDinero) {
        //init session
        Session session = getSession();
        //variables insert
        String squery = "";
        SQLQuery query = null;
        int filas = 0;
        //variables update
        String squeryUpdate = "";
        SQLQuery queryUpdate = null;
        //variables consulta
        String squeryConsulta = "";
        SQLQuery queryConsulta = null;
        //init session
        Session sesion = getSession();
        //denominaciones permitidas por regla de negocio para colombia -- solo se toman denominaciones actuales de billetes para colombia
        if (guardarDinero.getDenominacion() == 1000 || guardarDinero.getDenominacion() == 2000 || guardarDinero.getDenominacion() == 5000 || guardarDinero.getDenominacion() == 10000 || guardarDinero.getDenominacion() == 20000 || guardarDinero.getDenominacion() == 50000 || guardarDinero.getDenominacion() == 100000) {
            //select buscando moneda ya ingresada anteriormente
            squeryConsulta = "SELECT cantidad FROM  public.cajero where denominacion = " + guardarDinero.getDenominacion() + " ";
            queryConsulta = sesion.createSQLQuery(squeryConsulta);
            ArrayList<Integer> cajeroObject = (ArrayList<Integer>) queryConsulta.list();
            //validacion mayor a 0 cuando la denominacion existe 
            if (cajeroObject.size() > 0) {
                //obenemos la cantidad actual de la denominacion
                Integer cantidadActual = cajeroObject.get(0);
                //realizamos la respectiva suma
                Integer cantidadNueva = cantidadActual + guardarDinero.getCantidad();
                //update en tabla cajero
                squery = "UPDATE public.cajero SET cantidad = " + cantidadNueva + " WHERE denominacion = " + guardarDinero.getDenominacion() + " ";
                query = session.createSQLQuery(squery);
                filas = filas + query.executeUpdate();
            } else {
                //insert en tabla cajero
                squery = "INSERT INTO public.cajero  (idcajero, denominacion, cantidad) values (DEFAULT , " + guardarDinero.getDenominacion() + ", " + guardarDinero.getCantidad() + ")";
                query = session.createSQLQuery(squery);
                filas = filas + query.executeUpdate();
            }
        } else {
            //envio mensaje de requerimiento de negocio especifico
            guardarDinero.setMensaje("Mensaje de error por negocio");
        }
        //validacion de insert o update al finalizar
        if (filas == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<CajeroAutomaticoVo> consultaDineroCajero() {
        //array cajero
        ArrayList<CajeroAutomaticoVo> cajeroList = new ArrayList<>();
        //init session
        Session sesion = getSession();
        //query select cajero
        String squery = "SELECT idcajero,denominacion, cantidad  FROM  public.cajero order by denominacion asc ";
        SQLQuery query = sesion.createSQLQuery(squery);
        ArrayList<Object[]> cajeroObject = (ArrayList<Object[]>) query.list();
        //cicle for data
        if (cajeroObject.size() > 0) {
            for (Object[] objectReservacion : cajeroObject) {
                CajeroAutomaticoVo usuarioType = mapeoCajero(objectReservacion);
                cajeroList.add(usuarioType);
            }
        }
        return cajeroList;
    }

    //Map cajero items
    public CajeroAutomaticoVo mapeoCajero(Object[] retorno) {
        CajeroAutomaticoVo cajero = new CajeroAutomaticoVo();
        //set data user
        cajero.setIdCajeroAutomatico(retorno[0] != null ? (Integer) retorno[0] : 0);
        cajero.setDenominacion(retorno[1] != null ? (Integer) retorno[1] : 0);
        cajero.setCantidad(retorno[2] != null ? (Integer) retorno[2] : 0);
        return cajero;
    }

    @Override
    public boolean retiroDineroCajero(RetirosVo retiroDinero) {
        //init session
        Session session = getSession();
        //variables insert
        String squery = "";
        SQLQuery query = null;
        int filas = 0;
        //variables update
        String squeryUpdate = "";
        SQLQuery queryUpdate = null;
        //init session
        Session sesion = getSession();
        //obtenemos el valor a retirar
        int cantidad_de_dinero = retiroDinero.getValor();
        //var denominacion 100000
        Integer cantidadBilletesCienMil = 0;
        //var denominacion 50000
        Integer cantidadBilletesCincuentaMil = 0;
        //var denominacion 20000
        Integer cantidadBilletesVeinteMil = 0;
        //var denominacion 10000
        Integer cantidadBilletesDiezMil = 0;
        //var denominacion 5000
        Integer cantidadBilletesCincoMil = 0;
        //var denominacion 2000
        Integer cantidadBilletesDosMil = 0;
        //var denominacion 1000
        Integer cantidadBilletesMil = 0;
        //inicialmente validamos si tenemos el dinero para poder permitir el retiro
        String squeryCantidadDinero = "SELECT  sum(cantidad * denominacion) AS total  FROM  public.cajero ";
        SQLQuery queryCantidadDinero = sesion.createSQLQuery(squeryCantidadDinero);
        ArrayList<BigInteger> dineroObject = (ArrayList<BigInteger>) queryCantidadDinero.list();
        //validacion que la consulta si tenga resultados  
        if (dineroObject.size() > 0) {
            //obtenemos el total del dinero por todas las denominaciones
            BigInteger cantidadDineroCajero = dineroObject.get(0);
            //validacion de cantidad dinero en cajero sea igual o mayor a la solicitada
            if (cantidadDineroCajero.intValue() >= cantidad_de_dinero) {
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 100000
                    String squeryConsultaCienMil = "SELECT cantidad FROM  public.cajero where denominacion = 100000 ";
                    SQLQuery queryConsultaCienMil = sesion.createSQLQuery(squeryConsultaCienMil);
                    ArrayList<Integer> cienObject = (ArrayList<Integer>) queryConsultaCienMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion  
                    if (cienObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesCienMil = cienObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 100000 * cantidadBilletesCienMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 50000
                    String squeryConsultaCincuentaMil = "SELECT cantidad FROM  public.cajero where denominacion = 50000 ";
                    SQLQuery queryConsultaCincuentaMil = sesion.createSQLQuery(squeryConsultaCincuentaMil);
                    ArrayList<Integer> cincuentaObject = (ArrayList<Integer>) queryConsultaCincuentaMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (cincuentaObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesCincuentaMil = cincuentaObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 50000 * cantidadBilletesCincuentaMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 20000
                    String squeryConsultaVeinteMil = "SELECT cantidad FROM  public.cajero where denominacion = 20000 ";
                    SQLQuery queryConsultaVeinteMil = sesion.createSQLQuery(squeryConsultaVeinteMil);
                    ArrayList<Integer> veinteObject = (ArrayList<Integer>) queryConsultaVeinteMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (veinteObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesVeinteMil = veinteObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 20000 * cantidadBilletesVeinteMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 10000
                    String squeryConsultaDiezMil = "SELECT cantidad FROM  public.cajero where denominacion = 10000 ";
                    SQLQuery queryConsultaDiezMil = sesion.createSQLQuery(squeryConsultaDiezMil);
                    ArrayList<Integer> diezObject = (ArrayList<Integer>) queryConsultaDiezMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (diezObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesDiezMil = diezObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 10000 * cantidadBilletesDiezMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 5000
                    String squeryConsultaCincoMil = "SELECT cantidad FROM  public.cajero where denominacion = 5000 ";
                    SQLQuery queryConsultaCincoMil = sesion.createSQLQuery(squeryConsultaCincoMil);
                    ArrayList<Integer> cincoObject = (ArrayList<Integer>) queryConsultaCincoMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (cincoObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesCincoMil = cincoObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 5000 * cantidadBilletesCincoMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 2000
                    String squeryConsultaDosMil = "SELECT cantidad FROM  public.cajero where denominacion = 2000 ";
                    SQLQuery queryConsultaDosMil = sesion.createSQLQuery(squeryConsultaDosMil);
                    ArrayList<Integer> dosObject = (ArrayList<Integer>) queryConsultaDosMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (dosObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesDosMil = dosObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 2000 * cantidadBilletesDosMil;
                    }
                }
                //validamos que la cantidad solicitada sea mayor a 0
                if (cantidad_de_dinero > 0) {
                    //select buscando la cantidad de billetes con denominacion de 1000
                    String squeryConsultaMil = "SELECT cantidad FROM  public.cajero where denominacion = 1000 ";
                    SQLQuery queryConsultaMil = sesion.createSQLQuery(squeryConsultaMil);
                    ArrayList<Integer> milObject = (ArrayList<Integer>) queryConsultaMil.list();
                    //validacion que la consulta si tenga billetes de esa denominacion
                    if (milObject.size() > 0) {
                        //obenemos la cantidad actual de la denominacion
                        cantidadBilletesMil = milObject.get(0);
                        cantidad_de_dinero = cantidad_de_dinero - 1000 * cantidadBilletesMil;
                    }
                }
                //por ultimo validamos si todavia hay saldo a cubrir del solicitado
                if (cantidad_de_dinero > 0) {
                    //mensaje de negocio
                    retiroDinero.setMensaje("Error la cantidad solicitada es mayor a el dinero que tiene el cajero");
                } else {
                    //se realiza actualizacion en public.cajero de cada denominacion
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 100000
                    if (cantidadBilletesCienMil > 0) {
                        //update en tabla cajero denominacion 100000
                        String squeryUpdateCienMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesCienMil + " WHERE denominacion = 100000 ";
                        SQLQuery queryUpdateCienMil = session.createSQLQuery(squeryUpdateCienMil);
                        filas = filas + queryUpdateCienMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 50000
                    if (cantidadBilletesCincuentaMil > 0) {
                        //update en tabla cajero denominacion 50000
                        String squeryUpdateCincuentaMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesCincuentaMil + " WHERE denominacion = 50000 ";
                        SQLQuery queryUpdateCincuentaMil = session.createSQLQuery(squeryUpdateCincuentaMil);
                        filas = filas + queryUpdateCincuentaMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 20000
                    if (cantidadBilletesVeinteMil > 0) {
                        //update en tabla cajero denominacion 20000
                        String squeryUpdateVeinteMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesVeinteMil + " WHERE denominacion = 20000 ";
                        SQLQuery queryUpdateVeinteMil = session.createSQLQuery(squeryUpdateVeinteMil);
                        filas = filas + queryUpdateVeinteMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 10000
                    if (cantidadBilletesDiezMil > 0) {
                        //update en tabla cajero denominacion 10000
                        String squeryUpdateDiezMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesDiezMil + " WHERE denominacion = 10000 ";
                        SQLQuery queryUpdateDiezMil = session.createSQLQuery(squeryUpdateDiezMil);
                        filas = filas + queryUpdateDiezMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 5000
                    if (cantidadBilletesCincoMil > 0) {
                        //update en tabla cajero denominacion 5000
                        String squeryUpdateVDiezMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesCincoMil + " WHERE denominacion = 5000 ";
                        SQLQuery queryUpdateDiezMil = session.createSQLQuery(squeryUpdateVDiezMil);
                        filas = filas + queryUpdateDiezMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 2000
                    if (cantidadBilletesDosMil > 0) {
                        //update en tabla cajero denominacion 2000
                        String squeryUpdateDosMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesDosMil + " WHERE denominacion = 2000 ";
                        SQLQuery queryUpdateDosMil = session.createSQLQuery(squeryUpdateDosMil);
                        filas = filas + queryUpdateDosMil.executeUpdate();
                    }
                    //validacion de cantidad de billetes usados por la transaccion de denominacion 1000
                    if (cantidadBilletesMil > 0) {
                        //update en tabla cajero denominacion 1000
                        String squeryUpdateMil = "UPDATE public.cajero SET cantidad = " + cantidadBilletesMil + " WHERE denominacion = 1000 ";
                        SQLQuery queryUpdateMil = session.createSQLQuery(squeryUpdateMil);
                        filas = filas + queryUpdateMil.executeUpdate();
                    }
                    //fin de actualizacion tabla public.cajero
                    //por ultimo se registra la transaccion en la tabla retiros
                    String squeryRetiro = "INSERT INTO public.retiros  (idretiros, cantidad, usuarios_idusuarios) values (DEFAULT , " + retiroDinero.getValor() + ", " + retiroDinero.getIdUsuario() + "  )";
                    SQLQuery queryRetiro = session.createSQLQuery(squeryRetiro);
                    filas = filas + queryRetiro.executeUpdate();
                    //mensaje exitoso
                    retiroDinero.setMensajeExt("Valor solicitado "+ cantidad_de_dinero +" Billetes Entregados "+cantidadBilletesCienMil+" (100.000) "+cantidadBilletesCincuentaMil+" (50.000) "+cantidadBilletesVeinteMil+" (20.000) "+cantidadBilletesDiezMil+"  (10.000) "+cantidadBilletesCincoMil+" (5.000) "+cantidadBilletesDosMil+" (2.000) "+cantidadBilletesMil+" (1.000) ");
                }
            } else {
                //mensaje de negocio
                retiroDinero.setMensaje("Error la cantidad solicitada es mayor a el dinero que tiene el cajero");
            }
        } else {
            //mensaje de negocio
            retiroDinero.setMensaje("Error el cajero no tiene dinero disponible");
        }
        if (filas == 1) {
            return true;
        } else {
            return false;
        }

    }

}
