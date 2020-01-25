package com.rolandopalermo.facturacion.ec.mapper.invoice;

import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.CompensacionDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.invoice.InfoFacturaDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.PagoMapper;
import com.rolandopalermo.facturacion.ec.mapper.TotalImpuestoMapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.TotalImpuesto;
import com.rolandopalermo.facturacion.ec.modelo.factura.Compensacion;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;

public class InfoFacturaMapper implements Mapper<InfoFacturaDTO, InfoFactura> {

    private Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper;
    private Mapper<CompensacionDTO, Compensacion> compensacionMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public InfoFacturaMapper() {
        this.totalImpuestoMapper = new TotalImpuestoMapper();
        this.pagoMapper = new PagoMapper();
        this.compensacionMapper = new CompensacionMapper();
    }

    @Override
    public InfoFactura convert(final InfoFacturaDTO infoFacturaDTO) {
        if (infoFacturaDTO == null) {
            return null;
        }
        final InfoFactura infoFactura = new InfoFactura();
        infoFactura.setComercioExterior(infoFacturaDTO.getComercioExterior());
        infoFactura.setIncoTermFactura(infoFacturaDTO.getIncoTermFactura());
        infoFactura.setLugarIncoTerm(infoFacturaDTO.getLugarIncoTerm());
        infoFactura.setPaisOrigen(infoFacturaDTO.getPaisOrigen());
        infoFactura.setPuertoEmbarque(infoFacturaDTO.getPuertoEmbarque());
        infoFactura.setPuertoDestino(infoFacturaDTO.getPuertoDestino());
        infoFactura.setPaisDestino(infoFacturaDTO.getPaisDestino());
        infoFactura.setPaisAdquisicion(infoFacturaDTO.getPaisAdquisicion());
        infoFactura.setTotalSubsidio(infoFacturaDTO.getTotalSubsidio());
        infoFactura.setIncoTermTotalSinImpuestos(infoFacturaDTO.getIncoTermTotalSinImpuestos());
        infoFactura.setCodDocReembolso(infoFacturaDTO.getCodDocReembolso());
        infoFactura.setTotalComprobantesReembolso(infoFacturaDTO.getTotalComprobantesReembolso());
        infoFactura.setTotalBaseImponibleReembolso(infoFacturaDTO.getTotalBaseImponibleReembolso());
        infoFactura.setTotalImpuestoReembolso(infoFacturaDTO.getTotalImpuestoReembolso());
        infoFactura.setFleteInternacional(infoFacturaDTO.getFleteInternacional());
        infoFactura.setSeguroInternacional(infoFacturaDTO.getSeguroInternacional());
        infoFactura.setGastosAduaneros(infoFacturaDTO.getGastosAduaneros());
        infoFactura.setGastosTransporteOtros(infoFacturaDTO.getGastosTransporteOtros());
        infoFactura.setFechaEmision(infoFacturaDTO.getFechaEmision());
        infoFactura.setDirEstablecimiento(infoFacturaDTO.getDirEstablecimiento());
        infoFactura.setContribuyenteEspecial(infoFacturaDTO.getContribuyenteEspecial());
        infoFactura.setObligadoContabilidad(infoFacturaDTO.getObligadoContabilidad());
        infoFactura.setTipoIdentificacionComprador(infoFacturaDTO.getTipoIdentificacionComprador());
        infoFactura.setGuiaRemision(infoFacturaDTO.getGuiaRemision());
        infoFactura.setRazonSocialComprador(infoFacturaDTO.getRazonSocialComprador());
        infoFactura.setIdentificacionComprador(infoFacturaDTO.getIdentificacionComprador());
        infoFactura.setDireccionComprador(infoFacturaDTO.getDireccionComprador());
        infoFactura.setTotalSinImpuestos(infoFacturaDTO.getTotalSinImpuestos());
        infoFactura.setTotalDescuento(infoFacturaDTO.getTotalDescuento());
        if (infoFacturaDTO.getTotalImpuesto() != null && !infoFacturaDTO.getTotalImpuesto().isEmpty()) {
            infoFactura.setTotalImpuesto(getTotalImpuestoMapper().convertAll(infoFacturaDTO.getTotalImpuesto()));
        }
        if (infoFacturaDTO.getPagos() != null && !infoFacturaDTO.getPagos().isEmpty()) {
            infoFactura.setPago(getPagoMapper().convertAll(infoFacturaDTO.getPagos()));
        }
        if (infoFacturaDTO.getCompensacion() != null && !infoFacturaDTO.getCompensacion().isEmpty()) {
            infoFactura.setCompensacion(getCompensacionMapper().convertAll(infoFacturaDTO.getCompensacion()));
        }
        infoFactura.setPropina(infoFacturaDTO.getPropina());
        infoFactura.setImporteTotal(infoFacturaDTO.getImporteTotal());
        infoFactura.setMoneda(infoFacturaDTO.getMoneda());
        infoFactura.setPlaca(infoFacturaDTO.getPlaca());
        infoFactura.setValorRetIva(infoFacturaDTO.getValorRetIva());
        infoFactura.setValorRetRenta(infoFacturaDTO.getValorRetRenta());
        return infoFactura;
    }

    protected Mapper<TotalImpuestoDTO, TotalImpuesto> getTotalImpuestoMapper() {
        return totalImpuestoMapper;
    }

    public void setTotalImpuestoMapper(Mapper<TotalImpuestoDTO, TotalImpuesto> totalImpuestoMapper) {
        this.totalImpuestoMapper = totalImpuestoMapper;
    }

    protected Mapper<PagoDTO, Pago> getPagoMapper() {
        return pagoMapper;
    }

    public void setPagoMapper(Mapper<PagoDTO, Pago> pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

    public Mapper<CompensacionDTO, Compensacion> getCompensacionMapper() {
        return compensacionMapper;
    }

    public void setCompensacionMapper(Mapper<CompensacionDTO, Compensacion> compensacionMapper) {
        this.compensacionMapper = compensacionMapper;
    }

}