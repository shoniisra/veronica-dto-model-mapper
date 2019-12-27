package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;

public class ImpuestoMapper implements Mapper<ImpuestoDTO, Impuesto> {

    @Override
    public Impuesto convert(final ImpuestoDTO impuestoDTO) {
        if (impuestoDTO == null) {
            return null;
        }
        final Impuesto impuesto = new Impuesto();
        impuesto.setCodigo(impuestoDTO.getCodigo());
        impuesto.setCodigoRetencion(impuestoDTO.getCodigoRetencion());
        impuesto.setBaseImponible(impuestoDTO.getBaseImponible());
        impuesto.setPorcentajeRetener(impuestoDTO.getPorcentajeRetener());
        impuesto.setValorRetenido(impuestoDTO.getValorRetenido());
        impuesto.setCodDocSustento(impuestoDTO.getCodDocSustento());
        impuesto.setNumDocSustento(impuestoDTO.getNumDocSustento());
        impuesto.setFechaEmisionDocSustento(impuestoDTO.getFechaEmisionDocSustento());
        return impuesto;
    }

}