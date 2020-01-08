package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.InfoRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.retencion.InfoCompRetencion;

import java.util.Optional;

public class RetencionMapper extends AbstractComprobanteMapper<RetencionDTO> implements Mapper<RetencionDTO, ComprobanteRetencion> {

    private Mapper<InfoRetencionDTO, InfoCompRetencion> infoCompRetencionMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;

    public RetencionMapper() {
        this.infoCompRetencionMapper = new InfoCompRetencionMapper();
        this.impuestoMapper = new ImpuestoMapper();
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
        if (retencionDTO.getImpuesto() != null && !retencionDTO.getImpuesto().isEmpty()) {
            comprobanteRetencion.getImpuesto().addAll(getImpuestoMapper().convertAll(retencionDTO.getImpuesto()));
        }
        return comprobanteRetencion;
    }

    @Override
    protected String getFechaEmision(final RetencionDTO comprobanteDTO) {
        return Optional.ofNullable(comprobanteDTO).map(RetencionDTO::getInfoRetencion).map(InfoRetencionDTO::getFechaEmision).orElse(null);
    }

    public Mapper<ImpuestoDTO, Impuesto> getImpuestoMapper() {
        return impuestoMapper;
    }

    public void setImpuestoMapper(Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.impuestoMapper = impuestoMapper;
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