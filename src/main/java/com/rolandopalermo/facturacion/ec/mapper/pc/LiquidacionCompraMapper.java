package com.rolandopalermo.facturacion.ec.mapper.pc;

import com.rolandopalermo.facturacion.ec.common.types.DocumentEnum;
import com.rolandopalermo.facturacion.ec.dto.v1.ReembolsoDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.InfoLiquidacionCompraDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.LiquidacionCompraDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.LiquidacionCompraDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.pc.MaquinaFiscalDTO;
import com.rolandopalermo.facturacion.ec.mapper.AbstractComprobanteMapper;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.ReembolsoDetalleFacturaMapper;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.ReembolsoDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.ReembolsoDetalleFactura;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.InfoLiquidacionCompra;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.LiquidacionCompra;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.LiquidacionCompraDetalle;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.MaquinaFiscal;

import java.util.Optional;

public class LiquidacionCompraMapper extends AbstractComprobanteMapper<LiquidacionCompraDTO> implements Mapper<LiquidacionCompraDTO, LiquidacionCompra> {

    private Mapper<InfoLiquidacionCompraDTO, InfoLiquidacionCompra> infoLiquidacionCompraMapper;
    private Mapper<LiquidacionCompraDetalleDTO, LiquidacionCompraDetalle> liquidacionCompraDetalleMapper;
    private Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> reembolsoDetalleMapper;
    private Mapper<MaquinaFiscalDTO, MaquinaFiscal> maquinaFiscalMapper;

    public LiquidacionCompraMapper() {
        this.infoLiquidacionCompraMapper = new InfoLiquidacionCompraMapper();
        this.liquidacionCompraDetalleMapper = new LiquidacionCompraDetalleMapper();
        this.reembolsoDetalleMapper = new ReembolsoDetalleFacturaMapper();
        this.maquinaFiscalMapper = new MaquinaFiscalMapper();
    }

    @Override
    protected String getFechaEmision(LiquidacionCompraDTO liquidacionCompraDTO) {
        return Optional.ofNullable(liquidacionCompraDTO)
                .map(LiquidacionCompraDTO::getInfoLiquidacionCompra)
                .map(InfoLiquidacionCompraDTO::getFechaEmision)
                .orElse(null);
    }

    @Override
    public LiquidacionCompra convert(LiquidacionCompraDTO liquidacionCompraDTO) {
        if (liquidacionCompraDTO == null) {
            return null;
        }
        LiquidacionCompra liquidacionCompra = new LiquidacionCompra();

        liquidacionCompra.setId(liquidacionCompraDTO.getId());
        liquidacionCompra.setVersion(liquidacionCompraDTO.getVersion());
        final InfoTributaria infoTributaria = getInfoTributariaMapper().convert(liquidacionCompraDTO.getInfoTributaria());
        if (infoTributaria != null) {
            infoTributaria.setClaveAcceso(getClaveAcceso(infoTributaria, getFechaEmision(liquidacionCompraDTO)));
            liquidacionCompra.setInfoTributaria(infoTributaria);
        }
        if (liquidacionCompraDTO.getCampoAdicional() != null && !liquidacionCompraDTO.getCampoAdicional().isEmpty()) {
            liquidacionCompra.setCampoAdicional(getCampoAdicionalMapper().convertAll(liquidacionCompraDTO.getCampoAdicional()));
        }
        liquidacionCompra.setInfoLiquidacionCompra(getInfoLiquidacionCompraMapper().convert(liquidacionCompraDTO.getInfoLiquidacionCompra()));
        if (liquidacionCompraDTO.getDetalle() != null && !liquidacionCompraDTO.getDetalle().isEmpty()) {
            liquidacionCompra.setDetalle(getLiquidacionCompraDetalleMapper().convertAll(liquidacionCompraDTO.getDetalle()));
        }
        if (liquidacionCompraDTO.getReembolsoDetalle() != null && !liquidacionCompraDTO.getReembolsoDetalle().isEmpty()) {
            liquidacionCompra.setReembolsoDetalle(getReembolsoDetalleMapper().convertAll(liquidacionCompraDTO.getReembolsoDetalle()));
        }
        liquidacionCompra.setMaquinaFiscal(getMaquinaFiscalMapper().convert(liquidacionCompraDTO.getMaquinaFiscal()));
        return liquidacionCompra;
    }

    @Override
    public boolean supports(DocumentEnum tipoDocumento) {
        return tipoDocumento.equals(DocumentEnum.LIQUIDACION_COMPRAS);
    }

    public Mapper<InfoLiquidacionCompraDTO, InfoLiquidacionCompra> getInfoLiquidacionCompraMapper() {
        return infoLiquidacionCompraMapper;
    }

    public void setInfoLiquidacionCompraMapper(Mapper<InfoLiquidacionCompraDTO, InfoLiquidacionCompra> infoLiquidacionCompraMapper) {
        this.infoLiquidacionCompraMapper = infoLiquidacionCompraMapper;
    }

    public Mapper<LiquidacionCompraDetalleDTO, LiquidacionCompraDetalle> getLiquidacionCompraDetalleMapper() {
        return liquidacionCompraDetalleMapper;
    }

    public void setLiquidacionCompraDetalleMapper(Mapper<LiquidacionCompraDetalleDTO, LiquidacionCompraDetalle> liquidacionCompraDetalleMapper) {
        this.liquidacionCompraDetalleMapper = liquidacionCompraDetalleMapper;
    }

    public Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> getReembolsoDetalleMapper() {
        return reembolsoDetalleMapper;
    }

    public void setReembolsoDetalleMapper(Mapper<ReembolsoDetalleDTO, ReembolsoDetalleFactura> reembolsoDetalleMapper) {
        this.reembolsoDetalleMapper = reembolsoDetalleMapper;
    }

    public Mapper<MaquinaFiscalDTO, MaquinaFiscal> getMaquinaFiscalMapper() {
        return maquinaFiscalMapper;
    }

    public void setMaquinaFiscalMapper(Mapper<MaquinaFiscalDTO, MaquinaFiscal> maquinaFiscalMapper) {
        this.maquinaFiscalMapper = maquinaFiscalMapper;
    }

}