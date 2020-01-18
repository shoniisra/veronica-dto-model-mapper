package com.rolandopalermo.facturacion.ec.mapper.cm;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.NotaCreditoDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.DetAdicionalMapper;
import com.rolandopalermo.facturacion.ec.mapper.ImpuestoMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.Detalle;

public class NotaCreditoDetalleMapper implements Mapper<NotaCreditoDetalleDTO, Detalle> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;

    public NotaCreditoDetalleMapper() {
        this.detAdicionalMapper = new DetAdicionalMapper();
        this.impuestoMapper = new ImpuestoMapper();
    }

    @Override
    public Detalle convert(final NotaCreditoDetalleDTO notaCreditoDetalleDTO) {
        if (notaCreditoDetalleDTO == null) {
            return null;
        }
        final Detalle detalle = new Detalle();
        detalle.setCodigoInterno(notaCreditoDetalleDTO.getCodigoInterno());
        detalle.setCodigoAdicional(notaCreditoDetalleDTO.getCodigoAdicional());
        detalle.setDescripcion(notaCreditoDetalleDTO.getDescripcion());
        detalle.setCantidad(notaCreditoDetalleDTO.getCantidad());
        detalle.setPrecioUnitario(notaCreditoDetalleDTO.getPrecioUnitario());
        detalle.setDescuento(notaCreditoDetalleDTO.getDescuento());
        detalle.setPrecioTotalSinImpuesto(notaCreditoDetalleDTO.getPrecioTotalSinImpuesto());
        detalle.setDetAdicional(getDetAdicionalMapper().convertAll(notaCreditoDetalleDTO.getDetAdicional()));
        detalle.setImpuesto(getImpuestoMapper().convertAll(notaCreditoDetalleDTO.getImpuesto()));
        return detalle;
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