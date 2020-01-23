package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.CompensacionReembolsoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.CompensacionReembolso;

public class CompensacionReembolsoMapper implements Mapper<CompensacionReembolsoDTO, CompensacionReembolso> {

    @Override
    public CompensacionReembolso convert(CompensacionReembolsoDTO compensacionReembolsoDTO) {
        if (compensacionReembolsoDTO == null) {
            return null;
        }
        CompensacionReembolso compensacionReembolso = new CompensacionReembolso();
        compensacionReembolso.setCodigo(compensacionReembolsoDTO.getCodigo());
        compensacionReembolso.setTarifa(compensacionReembolsoDTO.getTarifa());
        compensacionReembolso.setValor(compensacionReembolsoDTO.getValor());
        return compensacionReembolso;
    }

}