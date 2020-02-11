package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.ComprobanteRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.DocSustentoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.InfoRetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.DocSustento;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.retencion.InfoCompRetencion;

import java.util.Optional;

public class ComprobanteRetencionMapper extends AbstractComprobanteMapper<ComprobanteRetencionDTO> implements Mapper<ComprobanteRetencionDTO, ComprobanteRetencion> {

    private Mapper<InfoRetencionDTO, InfoCompRetencion> infoCompRetencionMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;
    private Mapper<DocSustentoDTO, DocSustento> docSustentoMapper;

    public ComprobanteRetencionMapper() {
        this.infoCompRetencionMapper = new InfoCompRetencionMapper();
        this.impuestoMapper = new ImpuestoMapper();
        this.docSustentoMapper = new DocSustentoMapper();
    }

    @Override
    public ComprobanteRetencion convert(final ComprobanteRetencionDTO retencionDTO) {
        if (retencionDTO == null) {
            return null;
        }
        ComprobanteRetencion comprobanteRetencion = new ComprobanteRetencion();
        if (retencionDTO.getCampoAdicional() != null && !retencionDTO.getCampoAdicional().isEmpty()) {
            comprobanteRetencion.setCampoAdicional(getCampoAdicionalMapper().convertAll(retencionDTO.getCampoAdicional()));
        }
        comprobanteRetencion.setId(retencionDTO.getId());
        comprobanteRetencion.setVersion(retencionDTO.getVersion());
        comprobanteRetencion.setInfoCompRetencion(getInfoCompRetencionMapper().convert(retencionDTO.getInfoRetencion()));
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(retencionDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(retencionDTO)));
            comprobanteRetencion.setInfoTributaria(infoTributaria);
        }
        if (retencionDTO.getImpuesto() != null && !retencionDTO.getImpuesto().isEmpty()) {
            comprobanteRetencion.setImpuesto(getImpuestoMapper().convertAll(retencionDTO.getImpuesto()));
        }
        if (retencionDTO.getDocSustento() != null && !retencionDTO.getDocSustento().isEmpty()) {
            comprobanteRetencion.setDocSustento(getDocSustentoMapper().convertAll(retencionDTO.getDocSustento()));
        }
        return comprobanteRetencion;
    }

    @Override
    protected String getFechaEmision(final ComprobanteRetencionDTO comprobanteDTO) {
        return Optional.ofNullable(comprobanteDTO).map(ComprobanteRetencionDTO::getInfoRetencion).map(InfoRetencionDTO::getFechaEmision).orElse(null);
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

    public Mapper<DocSustentoDTO, DocSustento> getDocSustentoMapper() {
        return docSustentoMapper;
    }

    public void setDocSustentoMapper(Mapper<DocSustentoDTO, DocSustento> docSustentoMapper) {
        this.docSustentoMapper = docSustentoMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.COMPROBANTE_RETENCION);
    }

}