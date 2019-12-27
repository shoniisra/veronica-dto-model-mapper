package com.rolandopalermo.facturacion.ec.mapper.dm;

import com.rolandopalermo.facturacion.ec.dto.v1.dm.MotivoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.Motivo;

public class MotivoMapper implements Mapper<MotivoDTO, Motivo> {

    @Override
    public Motivo convert(final MotivoDTO motivoDTO) {
        if (motivoDTO == null) {
            return null;
        }
        final Motivo motivo = new Motivo();
        motivo.setRazon(motivoDTO.getRazon());
        motivo.setValor(motivoDTO.getValor());
        return motivo;
    }

}