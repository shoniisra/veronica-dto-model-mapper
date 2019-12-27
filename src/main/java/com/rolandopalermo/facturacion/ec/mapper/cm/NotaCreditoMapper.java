package com.rolandopalermo.facturacion.ec.mapper.cm;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.InfoNotaCreditoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.NotaCreditoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.NotaCreditoDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.Detalle;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.InfoNotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;

import java.util.Optional;

public class NotaCreditoMapper extends AbstractComprobanteMapper<NotaCreditoDTO> implements Mapper<NotaCreditoDTO, NotaCredito> {

    private Mapper<InfoTributariaDTO, InfoTributaria> InfoTributariaMapper;
    private Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;
    private Mapper<InfoNotaCreditoDTO, InfoNotaCredito> infoNotaCreditoMapper;
    private Mapper<NotaCreditoDetalleDTO, Detalle> notaCreditoDetalleMapper;

    public NotaCreditoMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper,
                             Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper,
                             Mapper<InfoNotaCreditoDTO, InfoNotaCredito> infoNotaCreditoMapper,
                             Mapper<NotaCreditoDetalleDTO, Detalle> notaCreditoDetalleMapper) {
        InfoTributariaMapper = infoTributariaMapper;
        this.campoAdicionalMapper = campoAdicionalMapper;
        this.infoNotaCreditoMapper = infoNotaCreditoMapper;
        this.notaCreditoDetalleMapper = notaCreditoDetalleMapper;
    }

    @Override
    public NotaCredito convert(final NotaCreditoDTO notaCreditoDTO) {
        if (notaCreditoDTO == null) {
            return null;
        }
        final NotaCredito notaCredito = new NotaCredito();
        notaCredito.setId(notaCreditoDTO.getId());
        notaCredito.setVersion(notaCreditoDTO.getVersion());
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(notaCreditoDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(notaCreditoDTO)));
            notaCredito.setInfoTributaria(infoTributaria);
        }
        notaCredito.getCampoAdicional().addAll(getCampoAdicionalMapper().convertAll(notaCreditoDTO.getCampoAdicional()));
        notaCredito.setInfoNotaCredito(getInfoNotaCreditoMapper().convert(notaCreditoDTO.getInfoNotaCredito()));
        notaCredito.setDetalle(getNotaCreditoDetalleMapper().convertAll(notaCreditoDTO.getDetalle()));
        return notaCredito;
    }

    protected Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return InfoTributariaMapper;
    }

    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        InfoTributariaMapper = infoTributariaMapper;
    }

    protected Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
    }

    @Override
    protected String getFechaEmision(final NotaCreditoDTO notaCreditoDTO) {
        return Optional.ofNullable(notaCreditoDTO).map(NotaCreditoDTO::getInfoNotaCredito)
                .map(InfoNotaCreditoDTO::getFechaEmision).orElse(null);
    }

    protected Mapper<InfoNotaCreditoDTO, InfoNotaCredito> getInfoNotaCreditoMapper() {
        return infoNotaCreditoMapper;
    }

    public void setInfoNotaCreditoMapper(Mapper<InfoNotaCreditoDTO, InfoNotaCredito> infoNotaCreditoMapper) {
        this.infoNotaCreditoMapper = infoNotaCreditoMapper;
    }

    protected Mapper<NotaCreditoDetalleDTO, Detalle> getNotaCreditoDetalleMapper() {
        return notaCreditoDetalleMapper;
    }

    public void setNotaCreditoDetalleMapper(Mapper<NotaCreditoDetalleDTO, Detalle> notaCreditoDetalleMapper) {
        this.notaCreditoDetalleMapper = notaCreditoDetalleMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.NOTA_CREDITO);
    }

}