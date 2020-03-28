package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.withholding.CompraCajBananoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.DividendosDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.retencion.CompraCajBanano;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Dividendos;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Retencion;

public class RetencionMapper extends AbstractComprobanteMapper<RetencionDTO> implements Mapper<RetencionDTO, Retencion> {

    private Mapper<DividendosDTO, Dividendos> dividendosMapper;
    private Mapper<CompraCajBananoDTO, CompraCajBanano> compraCajBananoMapper;

    public RetencionMapper() {
        this.dividendosMapper = new DividendosMapper();
        this.compraCajBananoMapper = new CompraCajBananoMapper();
    }

    @Override
    public Retencion convert(RetencionDTO retencionDTO) {
        if (retencionDTO == null) {
            return null;
        }
        Retencion retencion = new Retencion();
        retencion.setCodigo(retencionDTO.getCodigo());
        retencion.setCodigoRetencion(retencionDTO.getCodigoRetencion());
        retencion.setBaseImponible(retencionDTO.getBaseImponible());
        retencion.setPorcentajeRetener(retencionDTO.getPorcentajeRetener());
        retencion.setValorRetenido(retencionDTO.getValorRetenido());
        if (retencionDTO.getDividendos() != null) {
            retencion.setDividendos(getDividendosMapper().convert(retencionDTO.getDividendos()));
        }
        if (retencionDTO.getCompraCajBanano() != null) {
            retencion.setCompraCajBanano(getCompraCajBananoMapper().convert(retencionDTO.getCompraCajBanano()));
        }
        return retencion;
    }

    public Mapper<DividendosDTO, Dividendos> getDividendosMapper() {
        return dividendosMapper;
    }

    public void setDividendosMapper(Mapper<DividendosDTO, Dividendos> dividendosMapper) {
        this.dividendosMapper = dividendosMapper;
    }

    public Mapper<CompraCajBananoDTO, CompraCajBanano> getCompraCajBananoMapper() {
        return compraCajBananoMapper;
    }

    public void setCompraCajBananoMapper(Mapper<CompraCajBananoDTO, CompraCajBanano> compraCajBananoMapper) {
        this.compraCajBananoMapper = compraCajBananoMapper;
    }

}
