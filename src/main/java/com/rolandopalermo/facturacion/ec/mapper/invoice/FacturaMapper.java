package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;

import java.util.Optional;

public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO> implements Mapper<FacturaDTO, Factura> {

    private Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper;
    private Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper;


    public FacturaMapper() {
        this.infoFacturaMapper = new InfoFacturaMapper();
        this.facturaDetalleMapper = new FacturaDetalleMapper();
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

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.FACTURA);
    }

}