package com.rolandopalermo.facturacion.ec.mapper.bol;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.bol.DestinatarioDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.bol.GuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.bol.InfoGuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.guia.Destinatario;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.guia.InfoGuiaRemision;

import java.util.Optional;

public class GuiaRemisionMapper extends AbstractComprobanteMapper<GuiaRemisionDTO> implements Mapper<GuiaRemisionDTO, GuiaRemision> {

    private Mapper<DestinatarioDTO, Destinatario> destinatarioMapper;
    private Mapper<InfoGuiaRemisionDTO, InfoGuiaRemision> infoGuiaRemisionMapper;

    public GuiaRemisionMapper() {
        this.destinatarioMapper = new DestinatarioMapper();
        this.infoGuiaRemisionMapper = new InfoGuiaRemisionMapper();
    }

    @Override
    public GuiaRemision convert(final GuiaRemisionDTO guiaRemisionDTO) {
        if (guiaRemisionDTO == null) {
            return null;
        }
        final GuiaRemision guiaRemision = new GuiaRemision();
        guiaRemision.setId(guiaRemisionDTO.getId());
        guiaRemision.setVersion(guiaRemisionDTO.getVersion());
        guiaRemision.getCampoAdicional().addAll(getCampoAdicionalMapper().convertAll(guiaRemisionDTO.getCampoAdicional()));

        if (guiaRemisionDTO.getDestinatario() != null && !guiaRemisionDTO.getDestinatario().isEmpty()) {
            guiaRemision.getDestinatario().addAll(getDestinatarioMapper().convertAll(guiaRemisionDTO.getDestinatario()));
        }

        guiaRemision.setInfoGuiaRemision(getInfoGuiaRemisionMapper().convert(guiaRemisionDTO.getInfoGuiaRemisionDTO()));
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(guiaRemisionDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(guiaRemisionDTO)));
            guiaRemision.setInfoTributaria(infoTributaria);
        }
        return guiaRemision;
    }

    @Override
    protected String getFechaEmision(final GuiaRemisionDTO comprobanteDTO) {
        return Optional.ofNullable(comprobanteDTO).map(GuiaRemisionDTO::getInfoGuiaRemisionDTO).map(InfoGuiaRemisionDTO::getFechaIniTransporte).orElse(null);
    }

    protected Mapper<DestinatarioDTO, Destinatario> getDestinatarioMapper() {
        return destinatarioMapper;
    }

    public void setDestinatarioMapper(Mapper<DestinatarioDTO, Destinatario> destinatarioMapper) {
        this.destinatarioMapper = destinatarioMapper;
    }

    protected Mapper<InfoGuiaRemisionDTO, InfoGuiaRemision> getInfoGuiaRemisionMapper() {
        return infoGuiaRemisionMapper;
    }

    public void setInfoGuiaRemisionMapper(Mapper<InfoGuiaRemisionDTO, InfoGuiaRemision> infoGuiaRemisionMapper) {
        this.infoGuiaRemisionMapper = infoGuiaRemisionMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.GUITA_REMISION);
    }

}