package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.CompensacionReembolsoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.DetalleImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.ReembolsoDetalleDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.invoice.CompensacionReembolsoMapper;
import com.rolandopalermo.facturacion.ec.mapper.invoice.DetalleImpuestoMapper;
import com.rolandopalermo.facturacion.ec.modelo.DetalleImpuesto;
import com.rolandopalermo.facturacion.ec.modelo.ReembolsoDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.CompensacionReembolso;

public class ReembolsoDetalleMapper implements Mapper<ReembolsoDetalleDTO, ReembolsoDetalle> {

    private Mapper<DetalleImpuestoDTO, DetalleImpuesto> detalleImpuestoMapper;
    private Mapper<CompensacionReembolsoDTO, CompensacionReembolso> compensacionReembolsoMapper;

    public ReembolsoDetalleMapper() {
        this.detalleImpuestoMapper = new DetalleImpuestoMapper();
        this.compensacionReembolsoMapper = new CompensacionReembolsoMapper();
    }

    @Override
    public ReembolsoDetalle convert(ReembolsoDetalleDTO reembolsoDetalleDTO) {
        if (reembolsoDetalleDTO == null) {
            return null;
        }
        ReembolsoDetalle reembolsoDetalle = new ReembolsoDetalle();
        reembolsoDetalle.setTipoIdentificacionProveedorReembolso(reembolsoDetalleDTO.getTipoIdentificacionProveedorReembolso());
        reembolsoDetalle.setIdentificacionProveedorReembolso(reembolsoDetalleDTO.getIdentificacionProveedorReembolso());
        reembolsoDetalle.setCodPaisPagoProveedorReembolso(reembolsoDetalleDTO.getCodPaisPagoProveedorReembolso());
        reembolsoDetalle.setTipoProveedorReembolso(reembolsoDetalleDTO.getTipoProveedorReembolso());
        reembolsoDetalle.setCodDocReembolso(reembolsoDetalleDTO.getCodDocReembolso());
        reembolsoDetalle.setEstabDocReembolso(reembolsoDetalleDTO.getEstabDocReembolso());
        reembolsoDetalle.setPtoEmiDocReembolso(reembolsoDetalleDTO.getPtoEmiDocReembolso());
        reembolsoDetalle.setSecuencialDocReembolso(reembolsoDetalleDTO.getSecuencialDocReembolso());
        reembolsoDetalle.setFechaEmisionDocReembolso(reembolsoDetalleDTO.getFechaEmisionDocReembolso());
        reembolsoDetalle.setNumeroautorizacionDocReemb(reembolsoDetalleDTO.getNumeroautorizacionDocReemb());
        if (reembolsoDetalleDTO.getDetalleImpuesto() != null) {
            reembolsoDetalle.setDetalleImpuesto(getDetalleImpuestoMapper().convertAll(reembolsoDetalleDTO.getDetalleImpuesto()));
        }
        if (reembolsoDetalleDTO.getCompensacionReembolso() != null) {
            reembolsoDetalle.setCompensacionReembolso(getCompensacionReembolsoMapper().convertAll(reembolsoDetalleDTO.getCompensacionReembolso()));
        }
        return reembolsoDetalle;
    }

    public Mapper<DetalleImpuestoDTO, DetalleImpuesto> getDetalleImpuestoMapper() {
        return detalleImpuestoMapper;
    }

    public void setDetalleImpuestoMapper(Mapper<DetalleImpuestoDTO, DetalleImpuesto> detalleImpuestoMapper) {
        this.detalleImpuestoMapper = detalleImpuestoMapper;
    }

    public Mapper<CompensacionReembolsoDTO, CompensacionReembolso> getCompensacionReembolsoMapper() {
        return compensacionReembolsoMapper;
    }

    public void setCompensacionReembolsoMapper(Mapper<CompensacionReembolsoDTO, CompensacionReembolso> compensacionReembolsoMapper) {
        this.compensacionReembolsoMapper = compensacionReembolsoMapper;
    }

}