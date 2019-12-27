package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;

public class CampoAdicionalMapper implements Mapper<CampoAdicionalDTO, CampoAdicional> {

    @Override
    public CampoAdicional convert(final CampoAdicionalDTO campoAdicionalDTO) {
        if (campoAdicionalDTO == null) {
            return null;
        }
        final CampoAdicional campoAdicional = new CampoAdicional();
        campoAdicional.setNombre(campoAdicionalDTO.getNombre());
        campoAdicional.setValue(campoAdicionalDTO.getValue());
        return campoAdicional;
    }

}