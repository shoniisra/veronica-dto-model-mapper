package com.rolandopalermo.facturacion.ec.mapper.dm;

import com.rolandopalermo.facturacion.ec.dto.CompensacionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.InfoNotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.mapper.CompensacionMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.PagoMapper;
import com.rolandopalermo.facturacion.ec.modelo.Compensacion;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.InfoNotaDebito;

public class InfoNotaDebitoMapper implements Mapper<InfoNotaDebitoDTO, InfoNotaDebito> {

    private Mapper<ImpuestoDTO, Impuesto> impuestoNotaDebitoMapper;
    private Mapper<CompensacionDTO, Compensacion> compensacionMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public InfoNotaDebitoMapper() {
        this.impuestoNotaDebitoMapper = new ImpuestoMapper();
        this.pagoMapper = new PagoMapper();
        this.compensacionMapper = new CompensacionMapper();
    }

    @Override
    public InfoNotaDebito convert(final InfoNotaDebitoDTO infoNotaDebitoDTO) {
        final InfoNotaDebito infoNotaDebito = new InfoNotaDebito();
        infoNotaDebito.setFechaEmision(infoNotaDebitoDTO.getFechaEmision());
        infoNotaDebito.setDirEstablecimiento(infoNotaDebitoDTO.getDirEstablecimiento());
        infoNotaDebito.setContribuyenteEspecial(infoNotaDebitoDTO.getContribuyenteEspecial());
        infoNotaDebito.setObligadoContabilidad(infoNotaDebitoDTO.getObligadoContabilidad());
        infoNotaDebito.setRise(infoNotaDebitoDTO.getRise());
        infoNotaDebito.setTipoIdentificacionComprador(infoNotaDebitoDTO.getTipoIdentificacionComprador());
        infoNotaDebito.setRazonSocialComprador(infoNotaDebitoDTO.getRazonSocialComprador());
        infoNotaDebito.setIdentificacionComprador(infoNotaDebitoDTO.getIdentificacionComprador());
        infoNotaDebito.setTotalSinImpuestos(infoNotaDebitoDTO.getTotalSinImpuestos());
        infoNotaDebito.setCodDocModificado(infoNotaDebitoDTO.getCodDocModificado());
        infoNotaDebito.setNumDocModificado(infoNotaDebitoDTO.getNumDocModificado());
        infoNotaDebito.setFechaEmisionDocSustento(infoNotaDebitoDTO.getFechaEmisionDocSustento());
        if (infoNotaDebitoDTO.getImpuesto() != null && !infoNotaDebitoDTO.getImpuesto().isEmpty()) {
            infoNotaDebito.setImpuesto(getTotalImpuestoMapper().convertAll(infoNotaDebitoDTO.getImpuesto()));
        }
        if (infoNotaDebitoDTO.getPagos() != null && !infoNotaDebitoDTO.getPagos().isEmpty()) {
            infoNotaDebito.setPago(getPagoMapper().convertAll(infoNotaDebitoDTO.getPagos()));
        }
        if (infoNotaDebitoDTO.getCompensacion() != null && !infoNotaDebitoDTO.getCompensacion().isEmpty()) {
            infoNotaDebito.setCompensacion(getCompensacionMapper().convertAll(infoNotaDebitoDTO.getCompensacion()));
        }
        infoNotaDebito.setValorTotal(infoNotaDebitoDTO.getValorTotal());
        return infoNotaDebito;
    }

    protected Mapper<ImpuestoDTO, Impuesto> getTotalImpuestoMapper() {
        return impuestoNotaDebitoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<ImpuestoDTO, Impuesto> impuestoNotaDebitoMapper) {
        this.impuestoNotaDebitoMapper = impuestoNotaDebitoMapper;
    }

    public Mapper<CompensacionDTO, Compensacion> getCompensacionMapper() {
        return compensacionMapper;
    }

    public void setCompensacionMapper(Mapper<CompensacionDTO, Compensacion> compensacionMapper) {
        this.compensacionMapper = compensacionMapper;
    }

    public Mapper<PagoDTO, Pago> getPagoMapper() {
        return pagoMapper;
    }

    public void setPagoMapper(Mapper<PagoDTO, Pago> pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

}