package com.rolandopalermo.facturacion.ec.mapper.bol;

import com.rolandopalermo.facturacion.ec.dto.v1.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.bol.GuiaDetallesDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaDetalles;

public class GuiaDetalleMapper implements Mapper<GuiaDetallesDTO, GuiaDetalles> {

    private Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper;

    public GuiaDetalleMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
    }

    @Override
    public GuiaDetalles convert(final GuiaDetallesDTO guiaDetallesDTO) {
        if (guiaDetallesDTO == null) {
            return null;
        }
        final GuiaDetalles detalle = new GuiaDetalles();
        detalle.setCodigoInterno(guiaDetallesDTO.getCodigoInterno());
        detalle.setCodigoAdicional(guiaDetallesDTO.getCodigoAdicional());
        detalle.setDescripcion(guiaDetallesDTO.getDescripcion());
        detalle.setCantidad(guiaDetallesDTO.getCantidad());
        detalle.getDetAdicional().addAll(getDetAdicionalMapper().convertAll(guiaDetallesDTO.getDetAdicional()));
        return detalle;
    }

    protected Mapper<DetAdicionalDTO, DetAdicional> getDetAdicionalMapper() {
        return detAdicionalMapper;
    }

    public void setDetAdicionalMapper(Mapper<DetAdicionalDTO, DetAdicional> detAdicionalMapper) {
        this.detAdicionalMapper = detAdicionalMapper;
    }

}