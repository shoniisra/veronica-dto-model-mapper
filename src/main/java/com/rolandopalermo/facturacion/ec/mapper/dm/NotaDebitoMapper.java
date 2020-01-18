package com.rolandopalermo.facturacion.ec.mapper.dm;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.InfoNotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.MotivoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.dm.NotaDebitoDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.InfoNotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.Motivo;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;

import java.util.Optional;

public class NotaDebitoMapper extends AbstractComprobanteMapper<NotaDebitoDTO> implements Mapper<NotaDebitoDTO, NotaDebito> {

    private Mapper<InfoNotaDebitoDTO, InfoNotaDebito> infoNotaDebitoMapper;
    private Mapper<MotivoDTO, Motivo> motivoMapper;

    public NotaDebitoMapper() {
        this.infoNotaDebitoMapper = new InfoNotaDebitoMapper();
        this.motivoMapper = new MotivoMapper();
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
        if (notaDebitoDTO.getCampoAdicional() != null && !notaDebitoDTO.getCampoAdicional().isEmpty()) {
            notaDebito.setCampoAdicional(getCampoAdicionalMapper().convertAll(notaDebitoDTO.getCampoAdicional()));
        }
        notaDebito.setInfoNotaDebito(getInfoNotaDebitoMapper().convert(notaDebitoDTO.getInfoNotaDebito()));
        if (notaDebitoDTO.getMotivo() != null && !notaDebitoDTO.getMotivo().isEmpty()) {
            notaDebito.setMotivo(getMotivoMapper().convertAll(notaDebitoDTO.getMotivo()));
        }

        return notaDebito;
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