package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;

public class FacturaDetalleMapper implements Mapper<FacturaDetalleDTO, FacturaDetalle> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;


    public FacturaDetalleMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper,
                                Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
        this.impuestoMapper = impuestoMapper;
    }

    @Override
    public FacturaDetalle convert(final FacturaDetalleDTO facturaDetalleDTO) {
        if (facturaDetalleDTO == null) {
            return null;
        }
        final FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setCodigoPrincipal(facturaDetalleDTO.getCodigoPrincipal());
        facturaDetalle.setCodigoAuxiliar(facturaDetalleDTO.getCodigoAuxiliar());
        facturaDetalle.setDescripcion(facturaDetalleDTO.getDescripcion());
        facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
        facturaDetalle.setPrecioUnitario(facturaDetalleDTO.getPrecioUnitario());
        facturaDetalle.setDescuento(facturaDetalleDTO.getDescuento());
        facturaDetalle.setPrecioTotalSinImpuesto(facturaDetalleDTO.getPrecioTotalSinImpuesto());
        facturaDetalle.getDetAdicional().addAll(getDetAdicionalMapper().convertAll(facturaDetalleDTO.getDetAdicional()));
        facturaDetalle.getImpuesto().addAll(getImpuestoMapper().convertAll(facturaDetalleDTO.getImpuesto()));
        return facturaDetalle;
    }

    protected Mapper<DetAdicionalDTO, DetAdicional> getDetAdicionalMapper() {
        return detAdicionalMapper;
    }

    public void setDetAdicionalMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
    }

    protected Mapper<ImpuestoDTO, Impuesto> getImpuestoMapper() {
        return impuestoMapper;
    }

    public void setImpuestoMapper(Mapper<ImpuestoDTO, Impuesto> impuestoMapper) {
        this.impuestoMapper = impuestoMapper;
    }

}
