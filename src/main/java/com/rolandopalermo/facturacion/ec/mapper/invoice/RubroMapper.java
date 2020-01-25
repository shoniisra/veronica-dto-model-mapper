package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.RubroDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Rubro;

public class RubroMapper implements Mapper<RubroDTO, Rubro> {

    @Override
    public Rubro convert(RubroDTO rubroDTO) {
        if (rubroDTO == null) {
            return null;
        }
        Rubro rubro = new Rubro();
        rubro.setConcepto(rubroDTO.getConcepto());
        rubro.setTotal(rubroDTO.getTotal());
        return rubro;
    }

}