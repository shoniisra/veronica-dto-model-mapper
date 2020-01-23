package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.CompensacionDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Compensacion;

public class CompensacionMapper implements Mapper<CompensacionDTO, Compensacion> {

    @Override
    public Compensacion convert(CompensacionDTO compensacionDTO) {
        if (compensacionDTO == null) {
            return null;
        }
        Compensacion compensacion = new Compensacion();
        compensacion.setCodigo(compensacionDTO.getCodigo());
        compensacion.setTarifa(compensacionDTO.getTarifa());
        compensacion.setValor(compensacionDTO.getValor());
        return compensacion;
    }

}