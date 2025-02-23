package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.AccessKeyBuilder;
import com.rolandopalermo.facturacion.ec.common.DateUtils;
import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ComprobanteDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComprobanteMapper<DTO extends ComprobanteDTO> {

    protected Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper;
    protected Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;

    public AbstractComprobanteMapper() {
        this.infoTributariaMapper = new InfoTributariaMapper();
        this.campoAdicionalMapper = new CampoAdicionalMapper();
    }

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

    public Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return infoTributariaMapper;
    }

    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        this.infoTributariaMapper = infoTributariaMapper;
    }

    public Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
    }

}