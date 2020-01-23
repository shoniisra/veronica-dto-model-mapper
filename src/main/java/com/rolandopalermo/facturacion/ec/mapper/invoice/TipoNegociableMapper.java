package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.TipoNegociableDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.TipoNegociable;

public class TipoNegociableMapper implements Mapper<TipoNegociableDTO, TipoNegociable> {

    @Override
    public TipoNegociable convert(TipoNegociableDTO tipoNegociableDTO) {
        if (tipoNegociableDTO == null) {
            return null;
        }
        TipoNegociable tipoNegociable = new TipoNegociable();
        tipoNegociable.setCorreo(tipoNegociableDTO.getCorreo());
        return tipoNegociable;
    }

}