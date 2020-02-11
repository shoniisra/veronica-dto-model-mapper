package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.ReembolsoDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaRetencionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoSustitutivaGuiaRemisionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.RubroDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.TipoNegociableDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.ReembolsoDetalleFacturaMapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.ReembolsoDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoSustitutivaGuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.factura.ReembolsoDetalleFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.Retencion;
import com.rolandopalermo.facturacion.ec.modelo.factura.Rubro;
import com.rolandopalermo.facturacion.ec.modelo.factura.TipoNegociable;

import java.util.Optional;

public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO> implements Mapper<FacturaDTO, Factura> {

    private Mapper<InfoFacturaDTO, InfoFactura> infoFacturaMapper;
    private Mapper<FacturaDetalleDTO, FacturaDetalle> facturaDetalleMapper;
    private Mapper<FacturaRetencionDTO, Retencion> facturaRetencionMapper;
    private Mapper<TipoNegociableDTO, TipoNegociable> tipoNegociableMapper;
    private Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> reembolsoDetalleMapper;
    private Mapper<InfoSustitutivaGuiaRemisionDTO, InfoSustitutivaGuiaRemision> infoSustitutivaGuiaRemisionMapper;
    private Mapper<RubroDTO, Rubro> rubroMapper;

    public FacturaMapper() {
        this.infoFacturaMapper = new InfoFacturaMapper();
        this.facturaDetalleMapper = new FacturaDetalleMapper();
        this.facturaRetencionMapper = new RetencionMapper();
        this.tipoNegociableMapper = new TipoNegociableMapper();
        this.reembolsoDetalleMapper = new ReembolsoDetalleFacturaMapper();
        this.infoSustitutivaGuiaRemisionMapper = new InfoSustitutivaGuiaRemisionMapper();
        this.rubroMapper = new RubroMapper();
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
        if (facturaDTO.getInfoSustitutivaGuiaRemision() != null) {
            factura.setInfoSustitutivaGuiaRemision(getInfoSustitutivaGuiaRemisionMapper().convert(facturaDTO.getInfoSustitutivaGuiaRemision()));
        }
        if (facturaDTO.getRubro() != null && !facturaDTO.getRubro().isEmpty()) {
            factura.setRubro(getRubroMapper().convertAll(facturaDTO.getRubro()));
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

    public Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> getReembolsoDetalleMapper() {
        return reembolsoDetalleMapper;
    }

    public void setReembolsoDetalleMapper(Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> reembolsoDetalleMapper) {
        this.reembolsoDetalleMapper = reembolsoDetalleMapper;
    }

    public Mapper<InfoSustitutivaGuiaRemisionDTO, InfoSustitutivaGuiaRemision> getInfoSustitutivaGuiaRemisionMapper() {
        return infoSustitutivaGuiaRemisionMapper;
    }

    public void setInfoSustitutivaGuiaRemisionMapper(Mapper<InfoSustitutivaGuiaRemisionDTO, InfoSustitutivaGuiaRemision> infoSustitutivaGuiaRemisionMapper) {
        this.infoSustitutivaGuiaRemisionMapper = infoSustitutivaGuiaRemisionMapper;
    }

    public Mapper<RubroDTO, Rubro> getRubroMapper() {
        return rubroMapper;
    }

    public void setRubroMapper(Mapper<RubroDTO, Rubro> rubroMapper) {
        this.rubroMapper = rubroMapper;
    }

}