package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.withholding.DividendosDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Dividendos;

public class DividendosMapper implements Mapper<DividendosDTO, Dividendos> {

    @Override
    public Dividendos convert(DividendosDTO dividendosDTO) {
        if(dividendosDTO == null) {
            return null;
        }
        Dividendos dividendos = new Dividendos();
        dividendos.setFechaPagoDiv(dividendosDTO.getFechaPagoDiv());
        dividendos.setImRentaSoc(dividendosDTO.getImRentaSoc());
        dividendos.setEjerFisUtDiv(dividendosDTO.getEjerFisUtDiv());
        return dividendos;
    }

}