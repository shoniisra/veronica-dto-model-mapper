package com.rolandopalermo.facturacion.ec.mapper.sri;

import com.rolandopalermo.facturacion.ec.dto.v1.sri.ComprobanteRespuestaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.MensajeDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.RespuestaSolicitudDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import recepcion.ws.sri.gob.ec.Mensaje;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RespuestaSolicitudMapper {

    private Mapper<Mensaje, MensajeDTO> mensajeRecepcionMapper;

    public RespuestaSolicitudMapper() {
        this.mensajeRecepcionMapper = new MensajeRecepcionMapper();
    }

    public RespuestaSolicitudDTO convert(RespuestaSolicitud respuestaSolicitud) {
        RespuestaSolicitudDTO dto = new RespuestaSolicitudDTO();
        if (respuestaSolicitud != null) {
            dto.setEstado(respuestaSolicitud.getEstado());
            List<ComprobanteRespuestaDTO> respuestas = new ArrayList<>();
            if (respuestaSolicitud.getComprobantes() != null && respuestaSolicitud.getComprobantes().getComprobante() != null) {
                respuestas = respuestaSolicitud.getComprobantes().getComprobante().stream().map(comprobante -> {
                    ComprobanteRespuestaDTO comprobanteRespuestaDTO = new ComprobanteRespuestaDTO();
                    comprobanteRespuestaDTO.setClaveAcceso(comprobante.getClaveAcceso());
                    if (comprobante.getMensajes() != null && comprobante.getMensajes().getMensaje() != null) {
                        comprobanteRespuestaDTO.setMensajes(getMensajeRecepcionMapper().convertAll(comprobante.getMensajes().getMensaje()));
                    }
                    return comprobanteRespuestaDTO;
                }).collect(Collectors.toList());
            }
            dto.setComprobantes(respuestas);
        }
        return dto;
    }

    public Mapper<Mensaje, MensajeDTO> getMensajeRecepcionMapper() {
        return mensajeRecepcionMapper;
    }

    public void setMensajeRecepcionMapper(Mapper<Mensaje, MensajeDTO> mensajeRecepcionMapper) {
        this.mensajeRecepcionMapper = mensajeRecepcionMapper;
    }

}