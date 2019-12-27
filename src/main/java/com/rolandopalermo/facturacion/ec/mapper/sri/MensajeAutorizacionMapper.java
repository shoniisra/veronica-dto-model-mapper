package com.rolandopalermo.facturacion.ec.mapper.sri;

import autorizacion.ws.sri.gob.ec.Mensaje;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.MensajeDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;

public class MensajeAutorizacionMapper implements Mapper<Mensaje, MensajeDTO> {

    @Override
    public MensajeDTO convert(final Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }
        final MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setIdentificador(mensaje.getIdentificador());
        mensajeDTO.setInformacionAdicional(mensaje.getInformacionAdicional());
        mensajeDTO.setMensaje(mensaje.getMensaje());
        mensajeDTO.setTipo(mensaje.getTipo());
        return mensajeDTO;
    }

}