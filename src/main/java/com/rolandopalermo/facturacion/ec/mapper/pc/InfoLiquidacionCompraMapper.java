package com.rolandopalermo.facturacion.ec.mapper.pc;

import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.InfoLiquidacionCompraDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.PagoMapper;
import com.rolandopalermo.facturacion.ec.mapper.TotalImpuestoMapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.TotalImpuesto;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.InfoLiquidacionCompra;

public class InfoLiquidacionCompraMapper implements Mapper<InfoLiquidacionCompraDTO, InfoLiquidacionCompra> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public InfoLiquidacionCompraMapper() {
        this.totalImpuestoMapper = new TotalImpuestoMapper();
        this.pagoMapper = new PagoMapper();
    }

    @Override
    public InfoLiquidacionCompra convert(InfoLiquidacionCompraDTO infoLiquidacionCompraDTO) {
        if (infoLiquidacionCompraDTO == null) {
            return null;
        }
        InfoLiquidacionCompra infoLiquidacionCompra = new InfoLiquidacionCompra();
        infoLiquidacionCompra.setFechaEmision(infoLiquidacionCompraDTO.getFechaEmision());
        infoLiquidacionCompra.setDirEstablecimiento(infoLiquidacionCompraDTO.getDirEstablecimiento());
        infoLiquidacionCompra.setContribuyenteEspecial(infoLiquidacionCompraDTO.getContribuyenteEspecial());
        infoLiquidacionCompra.setObligadoContabilidad(infoLiquidacionCompraDTO.getObligadoContabilidad());
        infoLiquidacionCompra.setTipoIdentificacionProveedor(infoLiquidacionCompraDTO.getTipoIdentificacionProveedor());
        infoLiquidacionCompra.setRazonSocialProveedor(infoLiquidacionCompraDTO.getRazonSocialProveedor());
        infoLiquidacionCompra.setIdentificacionProveedor(infoLiquidacionCompraDTO.getIdentificacionProveedor());
        infoLiquidacionCompra.setDireccionProveedor(infoLiquidacionCompraDTO.getDireccionProveedor());
        infoLiquidacionCompra.setTotalSinImpuestos(infoLiquidacionCompraDTO.getTotalSinImpuestos());
        infoLiquidacionCompra.setTotalDescuento(infoLiquidacionCompraDTO.getTotalDescuento());
        infoLiquidacionCompra.setCodDocReembolso(infoLiquidacionCompraDTO.getCodDocReembolso());
        infoLiquidacionCompra.setTotalComprobantesReembolso(infoLiquidacionCompraDTO.getTotalComprobantesReembolso());
        infoLiquidacionCompra.setTotalBaseImponibleReembolso(infoLiquidacionCompraDTO.getTotalBaseImponibleReembolso());
        infoLiquidacionCompra.setTotalImpuestoReembolso(infoLiquidacionCompraDTO.getTotalImpuestoReembolso());
        if (infoLiquidacionCompraDTO.getTotalImpuesto() != null && !infoLiquidacionCompraDTO.getTotalImpuesto().isEmpty()) {
            infoLiquidacionCompra.setTotalImpuesto(getTotalImpuestoMapper().convertAll(infoLiquidacionCompraDTO.getTotalImpuesto()));
        }
        infoLiquidacionCompra.setImporteTotal(infoLiquidacionCompraDTO.getImporteTotal());
        infoLiquidacionCompra.setMoneda(infoLiquidacionCompraDTO.getMoneda());
        if (infoLiquidacionCompraDTO.getPago() != null && !infoLiquidacionCompraDTO.getPago().isEmpty()) {
            infoLiquidacionCompra.setPago(getPagoMapper().convertAll(infoLiquidacionCompraDTO.getPago()));
        }
        return infoLiquidacionCompra;
    }

    public Mapper<TotalImpuestoDTO, TotalImpuesto> getTotalImpuestoMapper() {
        return totalImpuestoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper) {
        this.totalImpuestoMapper = totalImpuestoMapper;
    }

    public Mapper<PagoDTO, Pago> getPagoMapper() {
        return pagoMapper;
    }

    public void setPagoMapper(Mapper<PagoDTO, Pago> pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

}