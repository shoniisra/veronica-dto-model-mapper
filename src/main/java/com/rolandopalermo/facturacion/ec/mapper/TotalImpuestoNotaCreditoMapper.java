package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.TotalImpuesto;

public class TotalImpuestoNotaCreditoMapper implements Mapper<TotalImpuestoDTO, TotalImpuesto> {

    @Override
    public TotalImpuesto convert(final TotalImpuestoDTO totalImpuestoDTO) {
        if (totalImpuestoDTO == null) {
            return null;
        }
        final TotalImpuesto totalImpuesto = new TotalImpuesto();
        totalImpuesto.setCodigo(totalImpuestoDTO.getCodigo());
        totalImpuesto.setCodigoPorcentaje(totalImpuestoDTO.getCodigoPorcentaje());
        totalImpuesto.setBaseImponible(totalImpuestoDTO.getBaseImponible());
        totalImpuesto.setValor(totalImpuestoDTO.getValor());
        return totalImpuesto;
    }

}