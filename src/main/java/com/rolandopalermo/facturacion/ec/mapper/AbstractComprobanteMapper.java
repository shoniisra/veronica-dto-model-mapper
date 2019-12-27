package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.AccessKeyBuilder;
import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.dto.v1.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import org.apache.commons.lang.RandomStringUtils;

public abstract class AbstractComprobanteMapper<DTO extends ComprobanteDTO> {

    protected InfoTributaria buildInfoTributaria(DTO dto) {
        InfoTributaria InfoTributaria = new InfoTributaria();
        InfoTributaria.setAmbiente(dto.getInfoTributaria().getAmbiente());
        InfoTributaria.setTipoEmision(dto.getInfoTributaria().getTipoEmision());
        InfoTributaria.setRazonSocial(dto.getInfoTributaria().getRazonSocial());
        InfoTributaria.setNombreComercial(dto.getInfoTributaria().getNombreComercial());
        InfoTributaria.setRuc(dto.getInfoTributaria().getRuc());
        InfoTributaria.setCodDoc(dto.getInfoTributaria().getCodDoc());
        InfoTributaria.setEstab(dto.getInfoTributaria().getEstab());
        InfoTributaria.setPtoEmi(dto.getInfoTributaria().getPtoEmi());
        InfoTributaria.setSecuencial(dto.getInfoTributaria().getSecuencial());
        InfoTributaria.setDirMatriz(dto.getInfoTributaria().getDirMatriz());
        return InfoTributaria;
    }

    protected String getClaveAcceso(final InfoTributaria infoTributaria, final String fechaEmision) {
        final StringBuilder sb = new StringBuilder(infoTributaria.getEstab());
        sb.append(infoTributaria.getPtoEmi());
        final String serie = sb.toString();
        final String codigoNumerico = RandomStringUtils.randomNumeric(8);
        return AccessKeyBuilder.builder()
                .fechaEmision(DateUtils.getFechaFromStringddMMyyyy(fechaEmision))
                .ambiente(infoTributaria.getAmbiente())
                .numeroComprobante(infoTributaria.getSecuencial())
                .ruc(infoTributaria.getRuc())
                .serie(serie)
                .tipoComprobante(infoTributaria.getCodDoc())
                .tipoEmision(infoTributaria.getTipoEmision())
                .codigoNumerico(codigoNumerico)
                .build()
                .generarClaveAcceso();

    }

    protected abstract String getFechaEmision(final DTO comprobanteDTO);

}