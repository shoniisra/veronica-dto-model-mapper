package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.PagoMapper;
import com.rolandopalermo.facturacion.ec.mapper.TotalImpuestoMapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;

public class InfoFacturaMapper implements Mapper<InfoFacturaDTO, InfoFactura> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public InfoFacturaMapper() {
        this.totalImpuestoMapper = new TotalImpuestoMapper();
        this.pagoMapper = new PagoMapper();
    }

    @Override
    public InfoFactura convert(final InfoFacturaDTO infoFacturaDTO) {
        final InfoFactura infoFactura = new InfoFactura();
        infoFactura.setFechaEmision(infoFacturaDTO.getFechaEmision());
        infoFactura.setDirEstablecimiento(infoFacturaDTO.getDirEstablecimiento());
        infoFactura.setContribuyenteEspecial(infoFacturaDTO.getContribuyenteEspecial());
        infoFactura.setObligadoContabilidad(infoFacturaDTO.getObligadoContabilidad());
        infoFactura.setTipoIdentificacionComprador(infoFacturaDTO.getTipoIdentificacionComprador());
        infoFactura.setGuiaRemision(infoFacturaDTO.getGuiaRemision());
        infoFactura.setRazonSocialComprador(infoFacturaDTO.getRazonSocialComprador());
        infoFactura.setIdentificacionComprador(infoFacturaDTO.getIdentificacionComprador());
        infoFactura.setDireccionComprador(infoFacturaDTO.getDireccionComprador());
        infoFactura.setTotalSinImpuestos(infoFacturaDTO.getTotalSinImpuestos());
        infoFactura.setTotalDescuento(infoFacturaDTO.getTotalDescuento());
        if (infoFacturaDTO.getTotalImpuesto() != null && !infoFacturaDTO.getTotalImpuesto().isEmpty()) {
            infoFactura.setTotalImpuesto(getTotalImpuestoMapper().convertAll(infoFacturaDTO.getTotalImpuesto()));
        }
        if (infoFacturaDTO.getPagos() != null && !infoFacturaDTO.getPagos().isEmpty()) {
            infoFactura.setPago(getPagoMapper().convertAll(infoFacturaDTO.getPagos()));
        }
        infoFactura.setPropina(infoFacturaDTO.getPropina());
        infoFactura.setImporteTotal(infoFacturaDTO.getImporteTotal());
        infoFactura.setMoneda(infoFacturaDTO.getMoneda());
        infoFactura.setValorRetIva(infoFacturaDTO.getValorRetIva());
        infoFactura.setValorRetRenta(infoFacturaDTO.getValorRetRenta());
        return infoFactura;
    }

    protected Mapper<TotalImpuestoDTO, TotalImpuesto> getTotalImpuestoMapper() {
        return totalImpuestoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper) {
        this.totalImpuestoMapper = totalImpuestoMapper;
    }

    protected Mapper<PagoDTO, Pago> getPagoMapper() {
        return pagoMapper;
    }

    public void setPagoMapper(Mapper<PagoDTO, Pago> pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

}