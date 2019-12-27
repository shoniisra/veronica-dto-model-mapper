package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;

import java.util.Optional;

public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO> implements Mapper<FacturaDTO, Factura> {

    private Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper;
    private Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;
    private Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper;
    private Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper;


    public FacturaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper,
                         Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper,
                         Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper,
                         Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper) {
        this.infoTributariaMapper = infoTributariaMapper;
        this.campoAdicionalMapper = campoAdicionalMapper;
        this.infoFacturaMapper = infoFacturaMapper;
        this.facturaDetalleMapper = facturaDetalleMapper;
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
        factura.getCampoAdicional().addAll(getCampoAdicionalMapper().convertAll(facturaDTO.getCampoAdicional()));
        factura.setInfoFactura(getInfoFacturaMapper().convert(facturaDTO.getInfoFactura()));
        factura.getDetalle().addAll(getFacturaDetalleMapper().convertAll(facturaDTO.getDetalle()));
        return factura;
    }

    @Override
    protected String getFechaEmision(final FacturaDTO facturaDTO) {
        return Optional.ofNullable(facturaDTO)
                .map(FacturaDTO::getInfoFactura)
                .map(InfoFacturaDTO::getFechaEmision)
                .orElse(null);
    }

    protected Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return infoTributariaMapper;
    }

    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        this.infoTributariaMapper = infoTributariaMapper;
    }

    protected Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
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

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.FACTURA);
    }

}