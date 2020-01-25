package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.DetalleImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetalleImpuesto;

public class DetalleImpuestoMapper implements Mapper<DetalleImpuestoDTO, DetalleImpuesto> {

    @Override
    public DetalleImpuesto convert(DetalleImpuestoDTO detalleImpuestoDTO) {
        if (detalleImpuestoDTO == null) {
            return null;
        }
        DetalleImpuesto detalleImpuesto = new DetalleImpuesto();
        detalleImpuesto.setCodigo(detalleImpuestoDTO.getCodigo());
        detalleImpuesto.setCodigoPorcentaje(detalleImpuestoDTO.getCodigoPorcentaje());
        detalleImpuesto.setTarifa(detalleImpuestoDTO.getTarifa());
        detalleImpuesto.setBaseImponibleReembolso(detalleImpuestoDTO.getBaseImponibleReembolso());
        detalleImpuesto.setImpuestoReembolso(detalleImpuestoDTO.getImpuestoReembolso());
        return detalleImpuesto;
    }

}