package com.rolandopalermo.facturacion.ec.mapper.dm;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.CampoAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.InfoTributariaDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.InfoNotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.MotivoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.NotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.InfoNotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.Motivo;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;

import java.util.Optional;

public class NotaDebitoMapper extends AbstractComprobanteMapper<NotaDebitoDTO> implements Mapper<NotaDebitoDTO, NotaDebito> {

    private Mapper<InfoTributariaDTO, InfoTributaria> InfoTributariaMapper;
    private Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper;
    private Mapper<InfoNotaDebitoDTO, InfoNotaDebito> infoNotaDebitoMapper;
    private Mapper<MotivoDTO, Motivo> motivoMapper;

    public NotaDebitoMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper,
                            Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper,
                            Mapper<InfoNotaDebitoDTO, InfoNotaDebito> infoNotaDebitoMapper,
                            Mapper<MotivoDTO, Motivo> motivoMapper) {
        InfoTributariaMapper = infoTributariaMapper;
        this.campoAdicionalMapper = campoAdicionalMapper;
        this.infoNotaDebitoMapper = infoNotaDebitoMapper;
        this.motivoMapper = motivoMapper;
    }

    @Override
    public NotaDebito convert(final NotaDebitoDTO notaDebitoDTO) {
        if (notaDebitoDTO == null) {
            return null;
        }
        final NotaDebito notaDebito = new NotaDebito();
        notaDebito.setId(notaDebitoDTO.getId());
        notaDebito.setVersion(notaDebitoDTO.getVersion());
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(notaDebitoDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(notaDebitoDTO)));
            notaDebito.setInfoTributaria(infoTributaria);
        }
        notaDebito.getCampoAdicional().addAll(getCampoAdicionalMapper().convertAll(notaDebitoDTO.getCampoAdicional()));
        notaDebito.setInfoNotaDebito(getInfoNotaDebitoMapper().convert(notaDebitoDTO.getInfoNotaDebito()));
        notaDebito.getMotivo().addAll(getMotivoMapper().convertAll(notaDebitoDTO.getMotivo()));
        return notaDebito;
    }

    protected Mapper<InfoTributariaDTO, InfoTributaria> getInfoTributariaMapper() {
        return InfoTributariaMapper;
    }

    public void setInfoTributariaMapper(Mapper<InfoTributariaDTO, InfoTributaria> infoTributariaMapper) {
        InfoTributariaMapper = infoTributariaMapper;
    }

    protected Mapper<CampoAdicionalDTO, CampoAdicional> getCampoAdicionalMapper() {
        return campoAdicionalMapper;
    }

    public void setCampoAdicionalMapper(Mapper<CampoAdicionalDTO, CampoAdicional> campoAdicionalMapper) {
        this.campoAdicionalMapper = campoAdicionalMapper;
    }

    @Override
    protected String getFechaEmision(final NotaDebitoDTO notaDebitoDTO) {
        return Optional.ofNullable(notaDebitoDTO).map(NotaDebitoDTO::getInfoNotaDebito)
                .map(InfoNotaDebitoDTO::getFechaEmision).orElse(null);
    }

    protected Mapper<InfoNotaDebitoDTO, InfoNotaDebito> getInfoNotaDebitoMapper() {
        return infoNotaDebitoMapper;
    }

    public void setInfoNotaDebitoMapper(Mapper<InfoNotaDebitoDTO, InfoNotaDebito> infoNotaDebitoMapper) {
        this.infoNotaDebitoMapper = infoNotaDebitoMapper;
    }

    public Mapper<MotivoDTO, Motivo> getMotivoMapper() {
        return motivoMapper;
    }

    public void setMotivoMapper(Mapper<MotivoDTO, Motivo> motivoMapper) {
        this.motivoMapper = motivoMapper;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.NOTA_DEBITO);
    }

}