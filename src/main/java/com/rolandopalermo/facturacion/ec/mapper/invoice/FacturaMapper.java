package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.ReembolsoDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.TipoNegociableDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.ReembolsoDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.Retencion;
import com.rolandopalermo.facturacion.ec.modelo.factura.TipoNegociable;

import java.util.Optional;

public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO> implements Mapper<FacturaDTO, Factura> {

    private Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper;
    private Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper;
    private Mapper<FacturaRetencionDTO, Retencion> facturaRetencionMapper;
    private Mapper<TipoNegociableDTO, TipoNegociable> tipoNegociableMapper;
    private Mapper<ReembolsoDetalleDTO, ReembolsoDetalle> reembolsoDetalleMapper;

    public FacturaMapper() {
        this.infoFacturaMapper = new InfoFacturaMapper();
        this.facturaDetalleMapper = new FacturaDetalleMapper();
        this.facturaRetencionMapper = new RetencionMapper();
        this.tipoNegociableMapper = new TipoNegociableMapper();
        this.reembolsoDetalleMapper = new ReembolsoDetalleMapper();
    }

    @Override
    public Factura convert(final FacturaDTO facturaDTO) {
        if (facturaDTO == null) {
            return null;
        }
        final Factura factura = new Factura();
        factura.setId(facturaDTO.getId());
        factura.setVersion(facturaDTO.getVersion());
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(facturaDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(facturaDTO)));
            factura.setInfoTributaria(infoTributaria);
        }
        if (facturaDTO.getCampoAdicional() != null && !facturaDTO.getCampoAdicional().isEmpty()) {
            factura.setCampoAdicional(getCampoAdicionalMapper().convertAll(facturaDTO.getCampoAdicional()));
        }
        factura.setInfoFactura(getInfoFacturaMapper().convert(facturaDTO.getInfoFactura()));
        if (facturaDTO.getDetalle() != null && !facturaDTO.getDetalle().isEmpty()) {
            factura.setDetalle(getFacturaDetalleMapper().convertAll(facturaDTO.getDetalle()));
        }
        if (facturaDTO.getRetencion() != null) {
            factura.setRetencion(getFacturaRetencionMapper().convertAll(facturaDTO.getRetencion()));
        }
        if (facturaDTO.getTipoNegociable() != null) {
            factura.setTipoNegociable(getTipoNegociableMapper().convert(facturaDTO.getTipoNegociable()));
        }
        if (facturaDTO.getReembolsoDetalle() != null) {
            factura.setReembolsoDetalle(getReembolsoDetalleMapper().convertAll(facturaDTO.getReembolsoDetalle()));
        }
        return factura;
    }

    @Override
    protected String getFechaEmision(final FacturaDTO facturaDTO) {
        return Optional.ofNullable(facturaDTO)
                .map(FacturaDTO::getInfoFactura)
                .map(InfoFacturaDTO::getFechaEmision)
                .orElse(null);
    }

    protected Mapper<InfoFacturaDTO, InfoFactura> getInfoFacturaMapper() {
        return infoFacturaMapper;
    }

    public void setInfoFacturaMapper(Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper) {
        this.infoFacturaMapper = infoFacturaMapper;
    }

    protected Mapper<FacturaDetalleDTO, FacturaDetalle> getFacturaDetalleMapper() {
        return facturaDetalleMapper;
    }

    public void setFacturaDetalleMapper(Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper) {
        this.facturaDetalleMapper = facturaDetalleMapper;
    }

    public Mapper<FacturaRetencionDTO, Retencion> getFacturaRetencionMapper() {
        return facturaRetencionMapper;
    }

    public void setFacturaRetencionMapper(Mapper<FacturaRetencionDTO, Retencion> facturaRetencionMapper) {
        this.facturaRetencionMapper = facturaRetencionMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.FACTURA);
    }

    public Mapper<TipoNegociableDTO, TipoNegociable> getTipoNegociableMapper() {
        return tipoNegociableMapper;
    }

    public void setTipoNegociableMapper(Mapper<TipoNegociableDTO, TipoNegociable> tipoNegociableMapper) {
        this.tipoNegociableMapper = tipoNegociableMapper;
    }

    public Mapper<ReembolsoDetalleDTO, ReembolsoDetalle> getReembolsoDetalleMapper() {
        return reembolsoDetalleMapper;
    }

    public void setReembolsoDetalleMapper(Mapper<ReembolsoDetalleDTO, ReembolsoDetalle> reembolsoDetalleMapper) {
        this.reembolsoDetalleMapper = reembolsoDetalleMapper;
    }

}