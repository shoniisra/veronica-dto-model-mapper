package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;

public class InfoFacturaMapper implements Mapper<InfoFacturaDTO, InfoFactura> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public InfoFacturaMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper,
                             Mapper<PagoDTO, Pago> pagoMapper) {
        this.totalImpuestoMapper = totalImpuestoMapper;
        this.pagoMapper = pagoMapper;
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
        infoFactura.getTotalImpuesto().addAll(getTotalImpuestoMapper().convertAll(infoFacturaDTO.getTotalImpuesto()));
        infoFactura.setPropina(infoFacturaDTO.getPropina());
        infoFactura.setImporteTotal(infoFacturaDTO.getImporteTotal());
        infoFactura.setMoneda(infoFacturaDTO.getMoneda());
        infoFactura.getPago().addAll(getPagoMapper().convertAll(infoFacturaDTO.getPagos()));
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