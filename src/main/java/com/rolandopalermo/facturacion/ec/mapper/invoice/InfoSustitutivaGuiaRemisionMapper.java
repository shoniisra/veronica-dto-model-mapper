package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.DestinoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoSustitutivaGuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Destino;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoSustitutivaGuiaRemision;

public class InfoSustitutivaGuiaRemisionMapper implements Mapper<InfoSustitutivaGuiaRemisionDTO, InfoSustitutivaGuiaRemision> {

    private Mapper<DestinoDTO, Destino> destinoMapper;

    public InfoSustitutivaGuiaRemisionMapper() {
        this.destinoMapper = new DestinoMapper();
    }

    @Override
    public InfoSustitutivaGuiaRemision convert(InfoSustitutivaGuiaRemisionDTO infoSustitutivaGuiaRemisionDTO) {
        if (infoSustitutivaGuiaRemisionDTO == null) {
            return null;
        }
        InfoSustitutivaGuiaRemision infoSustitutivaGuiaRemision = new InfoSustitutivaGuiaRemision();
        infoSustitutivaGuiaRemision.setDirPartida(infoSustitutivaGuiaRemisionDTO.getDirPartida());
        infoSustitutivaGuiaRemision.setDirDestinatario(infoSustitutivaGuiaRemisionDTO.getDirDestinatario());
        infoSustitutivaGuiaRemision.setFechaIniTransporte(infoSustitutivaGuiaRemisionDTO.getFechaIniTransporte());
        infoSustitutivaGuiaRemision.setFechaFinTransporte(infoSustitutivaGuiaRemisionDTO.getFechaFinTransporte());
        infoSustitutivaGuiaRemision.setRazonSocialTransportista(infoSustitutivaGuiaRemisionDTO.getRazonSocialTransportista());
        infoSustitutivaGuiaRemision.setTipoIdentificacionTransportista(infoSustitutivaGuiaRemisionDTO.getTipoIdentificacionTransportista());
        infoSustitutivaGuiaRemision.setRucTransportista(infoSustitutivaGuiaRemisionDTO.getRucTransportista());
        infoSustitutivaGuiaRemision.setPlaca(infoSustitutivaGuiaRemisionDTO.getPlaca());
        if (infoSustitutivaGuiaRemisionDTO.getDestino() != null) {
            infoSustitutivaGuiaRemision.setDestino(getDestinoMapper().convertAll(infoSustitutivaGuiaRemisionDTO.getDestino()));
        }
        return infoSustitutivaGuiaRemision;
    }

    public Mapper<DestinoDTO, Destino> getDestinoMapper() {
        return destinoMapper;
    }

    public void setDestinoMapper(Mapper<DestinoDTO, Destino> destinoMapper) {
        this.destinoMapper = destinoMapper;
    }

}