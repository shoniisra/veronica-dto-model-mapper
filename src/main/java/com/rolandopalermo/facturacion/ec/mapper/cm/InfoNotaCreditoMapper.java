package com.rolandopalermo.facturacion.ec.mapper.cm;

import com.rolandopalermo.facturacion.ec.dto.CompensacionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.InfoNotaCreditoDTO;
import com.rolandopalermo.facturacion.ec.mapper.CompensacionMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.TotalImpuestoNotaCreditoMapper;
import com.rolandopalermo.facturacion.ec.modelo.Compensacion;
import com.rolandopalermo.facturacion.ec.modelo.TotalImpuesto;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.InfoNotaCredito;

public class InfoNotaCreditoMapper implements Mapper<InfoNotaCreditoDTO, InfoNotaCredito> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoNotaCreditoMapper;
    private Mapper<CompensacionDTO, Compensacion> compensacionMapper;

    public InfoNotaCreditoMapper() {
        this.totalImpuestoNotaCreditoMapper = new TotalImpuestoNotaCreditoMapper();
        this.compensacionMapper = new CompensacionMapper();
    }

    @Override
    public InfoNotaCredito convert(final InfoNotaCreditoDTO infoNotaCreditoDTO) {
        final InfoNotaCredito infoNotaCredito = new InfoNotaCredito();
        infoNotaCredito.setFechaEmision(infoNotaCreditoDTO.getFechaEmision());
        infoNotaCredito.setDirEstablecimiento(infoNotaCreditoDTO.getDirEstablecimiento());
        infoNotaCredito.setContribuyenteEspecial(infoNotaCreditoDTO.getContribuyenteEspecial());
        infoNotaCredito.setObligadoContabilidad(infoNotaCreditoDTO.getObligadoContabilidad());
        infoNotaCredito.setRise(infoNotaCreditoDTO.getRise());
        infoNotaCredito.setTipoIdentificacionComprador(infoNotaCreditoDTO.getTipoIdentificacionComprador());
        infoNotaCredito.setRazonSocialComprador(infoNotaCreditoDTO.getRazonSocialComprador());
        infoNotaCredito.setIdentificacionComprador(infoNotaCreditoDTO.getIdentificacionComprador());
        infoNotaCredito.setTotalSinImpuestos(infoNotaCreditoDTO.getTotalSinImpuestos());
        infoNotaCredito.setMoneda(infoNotaCreditoDTO.getMoneda());
        infoNotaCredito.setCodDocModificado(infoNotaCreditoDTO.getCodDocModificado());
        infoNotaCredito.setNumDocModificado(infoNotaCreditoDTO.getNumDocModificado());
        infoNotaCredito.setFechaEmisionDocSustento(infoNotaCreditoDTO.getFechaEmisionDocSustento());
        infoNotaCredito.setValorModificacion(infoNotaCreditoDTO.getValorModificacion());
        infoNotaCredito.setMotivo(infoNotaCreditoDTO.getMotivo());
        infoNotaCredito.setTotalImpuesto(getTotalImpuestoMapper().convertAll(infoNotaCreditoDTO.getTotalImpuesto()));
        if (infoNotaCreditoDTO.getCompensacion() != null && !infoNotaCreditoDTO.getCompensacion().isEmpty()) {
            infoNotaCredito.setCompensacion(getCompensacionMapper().convertAll(infoNotaCreditoDTO.getCompensacion()));
        }
        return infoNotaCredito;
    }

    protected Mapper<TotalImpuestoDTO, TotalImpuesto> getTotalImpuestoMapper() {
        return totalImpuestoNotaCreditoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoNotaCreditoMapper) {
        this.totalImpuestoNotaCreditoMapper = totalImpuestoNotaCreditoMapper;
    }

    public Mapper<CompensacionDTO, Compensacion> getCompensacionMapper() {
        return compensacionMapper;
    }

    public void setCompensacionMapper(Mapper<CompensacionDTO, Compensacion> compensacionMapper) {
        this.compensacionMapper = compensacionMapper;
    }
}