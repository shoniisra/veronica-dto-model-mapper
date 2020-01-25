package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.invoice.DestinoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.factura.Destino;

public class DestinoMapper implements Mapper<DestinoDTO, Destino> {

    @Override
    public Destino convert(DestinoDTO destinoDTO) {
        if(destinoDTO == null) {
            return null;
        }
        Destino destino = new Destino();
        destino.setMotivoTraslado(destinoDTO.getMotivoTraslado());
        destino.setDocAduaneroUnico(destinoDTO.getDocAduaneroUnico());
        destino.setCodEstabDestino(destinoDTO.getCodEstabDestino());
        destino.setRuta(destinoDTO.getRuta());
        return destino;
    }

}