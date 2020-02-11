package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.withholding.ImpuestoDocSustentoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ImpuestoDocSustento;

public class ImpuestoDocSustentoMapper implements Mapper<ImpuestoDocSustentoDTO, ImpuestoDocSustento> {

    @Override
    public ImpuestoDocSustento convert(ImpuestoDocSustentoDTO impuestoDocSustentoDTO) {
        if (impuestoDocSustentoDTO == null) {
            return null;
        }
        ImpuestoDocSustento impuestoDocSustento = new ImpuestoDocSustento();
        impuestoDocSustento.setCodImpuestoDocSustento(impuestoDocSustentoDTO.getCodImpuestoDocSustento());
        impuestoDocSustento.setCodigoPorcentaje(impuestoDocSustentoDTO.getCodigoPorcentaje());
        impuestoDocSustento.setBaseImponible(impuestoDocSustentoDTO.getBaseImponible());
        impuestoDocSustento.setTarifa(impuestoDocSustentoDTO.getTarifa());
        impuestoDocSustento.setValorImpuesto(impuestoDocSustentoDTO.getValorImpuesto());
        return impuestoDocSustento;
    }

}
