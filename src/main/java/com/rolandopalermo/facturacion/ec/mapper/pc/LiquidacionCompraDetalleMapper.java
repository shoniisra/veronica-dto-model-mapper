package com.rolandopalermo.facturacion.ec.mapper.pc;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.LiquidacionCompraDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.DetAdicionalMapper;
import com.rolandopalermo.facturacion.ec.mapper.ImpuestoMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.LiquidacionCompraDetalle;

public class LiquidacionCompraDetalleMapper implements Mapper<LiquidacionCompraDetalleDTO, LiquidacionCompraDetalle> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;
    private Mapper<ImpuestoDTO, Impuesto> impuestoMapper;

    public LiquidacionCompraDetalleMapper() {
        this.detAdicionalMapper = new DetAdicionalMapper();
        this.impuestoMapper = new ImpuestoMapper();
    }

    @Override
    public LiquidacionCompraDetalle convert(LiquidacionCompraDetalleDTO liquidacionCompraDetalleDTO) {
        if (liquidacionCompraDetalleDTO == null) {
            return null;
        }
        LiquidacionCompraDetalle liquidacionCompraDetalle = new LiquidacionCompraDetalle();
        liquidacionCompraDetalle.setCodigoPrincipal(liquidacionCompraDetalleDTO.getCodigoPrincipal());
        liquidacionCompraDetalle.setCodigoAuxiliar(liquidacionCompraDetalleDTO.getCodigoAuxiliar());
        liquidacionCompraDetalle.setDescripcion(liquidacionCompraDetalleDTO.getDescripcion());
        liquidacionCompraDetalle.setUnidadMedida(liquidacionCompraDetalleDTO.getUnidadMedida());
        liquidacionCompraDetalle.setCantidad(liquidacionCompraDetalleDTO.getCantidad());
        liquidacionCompraDetalle.setPrecioUnitario(liquidacionCompraDetalleDTO.getPrecioUnitario());
        liquidacionCompraDetalle.setPrecioSinSubsidio(liquidacionCompraDetalleDTO.getPrecioSinSubsidio());
        liquidacionCompraDetalle.setDescuento(liquidacionCompraDetalleDTO.getDescuento());
        liquidacionCompraDetalle.setPrecioTotalSinImpuesto(liquidacionCompraDetalleDTO.getPrecioTotalSinImpuesto());
        if (liquidacionCompraDetalleDTO.getDetAdicional() != null && !liquidacionCompraDetalleDTO.getDetAdicional().isEmpty()) {
            liquidacionCompraDetalle.setDetAdicional(getDetAdicionalMapper().convertAll(liquidacionCompraDetalleDTO.getDetAdicional()));
        }
        if (liquidacionCompraDetalleDTO.getImpuesto() != null && !liquidacionCompraDetalleDTO.getImpuesto().isEmpty()) {
            liquidacionCompraDetalle.setImpuesto(getImpuestoMapper().convertAll(liquidacionCompraDetalleDTO.getImpuesto()));
        }
        return liquidacionCompraDetalle;
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