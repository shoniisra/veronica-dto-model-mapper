package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;

public class TotalImpuestoMapper implements Mapper<TotalImpuestoDTO, TotalImpuesto> {

    @Override
    public TotalImpuesto convert(final TotalImpuestoDTO totalImpuestoDTO) {
        if (totalImpuestoDTO == null) {
            return null;
        }
        final TotalImpuesto totalImpuesto = new TotalImpuesto();
        totalImpuesto.setCodigo(totalImpuestoDTO.getCodigo());
        totalImpuesto.setCodigoPorcentaje(totalImpuestoDTO.getCodigoPorcentaje());
        totalImpuesto.setDescuentoAdicional(totalImpuestoDTO.getDescuentoAdicional());
        totalImpuesto.setBaseImponible(totalImpuestoDTO.getBaseImponible());
        totalImpuesto.setTarifa(totalImpuestoDTO.getTarifa());
        totalImpuesto.setValor(totalImpuestoDTO.getValor());
        totalImpuesto.setValorDevolucionIva(totalImpuestoDTO.getValorDevolucionIva());
        return totalImpuesto;
    }

}