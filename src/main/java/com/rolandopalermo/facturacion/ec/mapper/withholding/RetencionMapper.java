package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.InfoRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.retencion.InfoCompRetencion;

import java.util.Optional;

public class RetencionMapper extends AbstractComprobanteMapper<RetencionDTO> implements Mapper<RetencionDTO, ComprobanteRetencion> {

    private Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;
    private Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper;
    private Mapper<InfoRetencionDTO, InfoCompRetencion> infoCompRetencionMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;

    public RetencionMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper,
                           Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper,
                           Mapper<InfoRetencionDTO, InfoCompRetencion> infoCompRetencionMapper,
                           Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
        this.infoTributariaMapper = infoTributariaMapper;
        this.infoCompRetencionMapper = infoCompRetencionMapper;
        this.impuestoMapper = impuestoMapper;
    }

    @Override
    public ComprobanteRetencion convert(final RetencionDTO retencionDTO) {
        if (retencionDTO == null) {
            return null;
        }
        ComprobanteRetencion comprobanteRetencion = new ComprobanteRetencion();
        comprobanteRetencion.getCampoAdicional().addAll(getCampoAdicionalMapper().convertAll(retencionDTO.getCampoAdicional()));
        comprobanteRetencion.setId(retencionDTO.getId());
        comprobanteRetencion.setVersion(retencionDTO.getVersion());
        comprobanteRetencion.setInfoCompRetencion(getInfoCompRetencionMapper().convert(retencionDTO.getInfoRetencion()));
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(retencionDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(retencionDTO)));
            comprobanteRetencion.setInfoTributaria(infoTributaria);
        }
        comprobanteRetencion.getImpuesto().addAll(getImpuestoMapper().convertAll(retencionDTO.getImpuesto()));
        return comprobanteRetencion;
    }

    @Override
    protected String getFechaEmision(final RetencionDTO comprobanteDTO) {
        return Optional.ofNullable(comprobanteDTO).map(RetencionDTO::getInfoRetencion).map(InfoRetencionDTO::getFechaEmision).orElse(null);
    }

    protected Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    public Mapper<ImpuestoDTO, Impuesto> getImpuestoMapper() {
        return impuestoMapper;
    }

    public void setImpuestoMapper(Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.impuestoMapper = impuestoMapper;
    }

    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
    }

    protected Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return infoTributariaMapper;
    }

    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        this.infoTributariaMapper = infoTributariaMapper;
    }

    protected Mapper<InfoRetencionDTO, InfoCompRetencion> getInfoCompRetencionMapper() {
        return infoCompRetencionMapper;
    }

    public void setInfoCompRetencionMapper(Mapper<InfoRetencionDTO, InfoCompRetencion> infoCompRetencionMapper) {
        this.infoCompRetencionMapper = infoCompRetencionMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.COMPROBANTE_RETENCION);
    }

}