package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.DetAdicionalMapper;
import com.rolandopalermo.facturacion.ec.mapper.ImpuestoMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;

public class FacturaDetalleMapper implements Mapper<FacturaDetalleDTO, FacturaDetalle> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;


    public FacturaDetalleMapper() {
        this.detAdicionalMapper = new DetAdicionalMapper();
        this.impuestoMapper = new ImpuestoMapper();
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
        facturaDetalle.setUnidadMedida(facturaDetalleDTO.getUnidadMedida());
        facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
        facturaDetalle.setPrecioUnitario(facturaDetalleDTO.getPrecioUnitario());
        facturaDetalle.setPrecioSinSubsidio(facturaDetalleDTO.getPrecioSinSubsidio());
        facturaDetalle.setDescuento(facturaDetalleDTO.getDescuento());
        facturaDetalle.setPrecioTotalSinImpuesto(facturaDetalleDTO.getPrecioTotalSinImpuesto());
        if (facturaDetalleDTO.getDetAdicional() != null && !facturaDetalleDTO.getDetAdicional().isEmpty()) {
            facturaDetalle.setDetAdicional(getDetAdicionalMapper().convertAll(facturaDetalleDTO.getDetAdicional()));
        }
        if (facturaDetalleDTO.getImpuesto() != null && !facturaDetalleDTO.getImpuesto().isEmpty()) {
            facturaDetalle.setImpuesto(getImpuestoMapper().convertAll(facturaDetalleDTO.getImpuesto()));
        }
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
