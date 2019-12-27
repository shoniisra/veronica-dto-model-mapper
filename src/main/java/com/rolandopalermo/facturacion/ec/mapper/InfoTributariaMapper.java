package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;

public class InfoTributariaMapper implements Mapper<InfoTributariaDTO, InfoTributaria> {

    @Override
    public InfoTributaria convert(final InfoTributariaDTO dto) {
        final InfoTributaria infoTributaria = new InfoTributaria();
        if (dto == null) {
            return null;
        }
        infoTributaria.setAmbiente(dto.getAmbiente());
        infoTributaria.setTipoEmision(dto.getTipoEmision());
        infoTributaria.setRazonSocial(dto.getRazonSocial());
        infoTributaria.setNombreComercial(dto.getNombreComercial());
        infoTributaria.setRuc(dto.getRuc());
        infoTributaria.setCodDoc(dto.getCodDoc());
        infoTributaria.setEstab(dto.getEstab());
        infoTributaria.setPtoEmi(dto.getPtoEmi());
        infoTributaria.setSecuencial(dto.getSecuencial());
        infoTributaria.setDirMatriz(dto.getDirMatriz());
        return infoTributaria;
    }

}