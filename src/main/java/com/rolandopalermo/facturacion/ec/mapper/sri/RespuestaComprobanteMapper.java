package com.rolandopalermo.facturacion.ec.mapper.sri;

import autorizacion.ws.sri.gob.ec.Mensaje;
import autorizacion.ws.sri.gob.ec.RespuestaComprobante;
import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.common.exception.VeronicaException;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.AutorizacionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.MensajeDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.sri.RespuestaComprobanteDTO;
import com.rolandopalermo.facturacion.ec.jaxb.JaxbService;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RespuestaComprobanteMapper {

    private Mapper<Mensaje, MensajeDTO> mensajeAutorizacionMapper;
    protected JaxbService<RespuestaComprobante> jaxbConverter;

    public RespuestaComprobanteMapper(JaxbService<RespuestaComprobante> jaxbConverter) {
        this.mensajeAutorizacionMapper = new MensajeAutorizacionMapper();
        this.jaxbConverter = jaxbConverter;
    }

    public RespuestaComprobanteDTO convert(RespuestaComprobante respuestaComprobante) {
        RespuestaComprobanteDTO dto = new RespuestaComprobanteDTO();
        if (respuestaComprobante != null) {
            List<AutorizacionDTO> autorizaciones;
            if (respuestaComprobante.getAutorizaciones() != null && respuestaComprobante.getAutorizaciones().getAutorizacion() != null) {
                autorizaciones = respuestaComprobante.getAutorizaciones().getAutorizacion()
                        .stream()
                        .map(autorizacion -> {
                            String fechaAutorizacion = "";
                            fechaAutorizacion = DateUtils.convertirGreggorianToDDMMYYYY(autorizacion.getFechaAutorizacion().toString());
                            AutorizacionDTO autorizacionDTO = new AutorizacionDTO();
                            autorizacionDTO.setComprobante(autorizacion.getComprobante());
                            autorizacionDTO.setAmbiente(autorizacion.getAmbiente());
                            autorizacionDTO.setEstado(autorizacion.getEstado());
                            autorizacionDTO.setNumeroAutorizacion(autorizacion.getNumeroAutorizacion());
                            autorizacionDTO.setFechaAutorizacion(fechaAutorizacion);
                            autorizacionDTO.setMensajes(getMensajeAutorizacionMapper().convertAll(autorizacion.getMensajes().getMensaje()));
                            return autorizacionDTO;
                        }).collect(Collectors.toList());
                if (!autorizaciones.isEmpty()) {
                    String claveAccesoConsultada = respuestaComprobante.getClaveAccesoConsultada();
                    dto.setTimestamp(respuestaComprobante.getAutorizaciones().getAutorizacion().get(0).getFechaAutorizacion().toGregorianCalendar().getTimeInMillis());
                    dto.setClaveAccesoConsultada(claveAccesoConsultada);
                    dto.setNumeroComprobantes(respuestaComprobante.getNumeroComprobantes());
                    dto.setAutorizaciones(autorizaciones);
                    try {
                        dto.setContentAsXML(getJaxbConverter().convertFromObjectToString(respuestaComprobante));
                    } catch (IOException ex) {
                        throw new VeronicaException("Ocurrió un error al procesar la respuesta del SRI");
                    }
                }
            }
        }
        return dto;
    }

    public Mapper<Mensaje, MensajeDTO> getMensajeAutorizacionMapper() {
        return mensajeAutorizacionMapper;
    }

    public void setMensajeAutorizacionMapper(Mapper<Mensaje, MensajeDTO> mensajeAutorizacionMapper) {
        this.mensajeAutorizacionMapper = mensajeAutorizacionMapper;
    }

    public JaxbService<RespuestaComprobante> getJaxbConverter() {
        return jaxbConverter;
    }

    public void setJaxbConverter(JaxbService<RespuestaComprobante> jaxbConverter) {
        this.jaxbConverter = jaxbConverter;
    }

}