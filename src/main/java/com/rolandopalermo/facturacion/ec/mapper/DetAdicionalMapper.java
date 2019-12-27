package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;

public class DetAdicionalMapper implements Mapper<DetAdicionalDTO, DetAdicional> {

    @Override
    public DetAdicional convert(final DetAdicionalDTO detAdicionalDTO) {
        if (detAdicionalDTO == null) {
            return null;
        }
        final DetAdicional detAdicional = new DetAdicional();
        detAdicional.setNombre(detAdicionalDTO.getNombre());
        detAdicional.setValor(detAdicionalDTO.getValor());
        return detAdicional;
    }

}