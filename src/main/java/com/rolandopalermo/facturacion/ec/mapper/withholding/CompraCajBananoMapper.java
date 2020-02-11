package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.withholding.CompraCajBananoDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.retencion.CompraCajBanano;

public class CompraCajBananoMapper implements Mapper<CompraCajBananoDTO, CompraCajBanano> {

    @Override
    public CompraCajBanano convert(CompraCajBananoDTO compraCajBananoDTO) {
        if (compraCajBananoDTO == null) {
            return null;
        }
        CompraCajBanano compraCajBanano = new CompraCajBanano();
        compraCajBanano.setNumCajBan(compraCajBananoDTO.getNumCajBan());
        compraCajBanano.setPrecCajBan(compraCajBananoDTO.getPrecCajBan());
        return compraCajBanano;
    }

}