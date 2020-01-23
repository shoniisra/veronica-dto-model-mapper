package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaRetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Retencion;

public class RetencionMapper implements Mapper<FacturaRetencionDTO, Retencion> {

    @Override
    public Retencion convert(FacturaRetencionDTO retencionDTO) {
        if (retencionDTO == null) {
            return null;
        }
        Retencion retencion = new Retencion();
        retencion.setCodigo(retencionDTO.getCodigo());
        retencion.setCodigoPorcentaje(retencionDTO.getCodigoPorcentaje());
        retencion.setTarifa(retencionDTO.getTarifa());
        retencion.setValor(retencionDTO.getValor());
        return retencion;
    }

}
