package com.rolandopalermo.facturacion.ec.mapper.cm;

import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.cm.InfoNotaCreditoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.TotalImpuestoNotaCreditoMapper;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.InfoNotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.TotalImpuesto;

public class InfoNotaCreditoMapper implements Mapper<InfoNotaCreditoDTO, InfoNotaCredito> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoNotaCreditoMapper;

    public InfoNotaCreditoMapper() {
        this.totalImpuestoNotaCreditoMapper = new TotalImpuestoNotaCreditoMapper();
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
        return infoNotaCredito;
    }

    protected Mapper<TotalImpuestoDTO, TotalImpuesto> getTotalImpuestoMapper() {
        return totalImpuestoNotaCreditoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoNotaCreditoMapper) {
        this.totalImpuestoNotaCreditoMapper = totalImpuestoNotaCreditoMapper;
    }

}