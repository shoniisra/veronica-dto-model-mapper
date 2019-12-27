package com.rolandopalermo.facturacion.ec.mapper.dm;

import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.Impuesto;

public class ImpuestoMapper implements Mapper<ImpuestoDTO, Impuesto> {

    @Override
    public Impuesto convert(final ImpuestoDTO impuestoDTO) {
        if (impuestoDTO == null) {
            return null;
        }
        final Impuesto impuesto = new Impuesto();
        impuesto.setCodigo(impuestoDTO.getCodigo());
        impuesto.setCodigoPorcentaje(impuestoDTO.getCodigoPorcentaje());
        impuesto.setTarifa(impuestoDTO.getTarifa());
        impuesto.setBaseImponible(impuestoDTO.getBaseImponible());
        impuesto.setValor(impuestoDTO.getValor());
        return impuesto;
    }

}