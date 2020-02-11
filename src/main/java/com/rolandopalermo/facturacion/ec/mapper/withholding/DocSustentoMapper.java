package com.rolandopalermo.facturacion.ec.mapper.withholding;

import com.rolandopalermo.facturacion.ec.dto.v1.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.ReembolsoDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.DocSustentoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.ImpuestoDocSustentoDTO;
import com.rolandopalermo.facturacion.ec.dto.v1.withholding.RetencionDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.mapper.PagoMapper;
import com.rolandopalermo.facturacion.ec.mapper.ReembolsoDetalleComprobanteRetencionMapper;
import com.rolandopalermo.facturacion.ec.modelo.Pago;
import com.rolandopalermo.facturacion.ec.modelo.retencion.DocSustento;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ImpuestoDocSustento;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ReembolsoDetalleComprobanteRetencion;
import com.rolandopalermo.facturacion.ec.modelo.retencion.Retencion;

public class DocSustentoMapper implements Mapper<DocSustentoDTO, DocSustento> {

    private Mapper<ImpuestoDocSustentoDTO, ImpuestoDocSustento> impuestoDocSustentoMapper;
    private Mapper<RetencionDTO, Retencion> retencionMapper;
    private Mapper<ReembolsoDetalleDTO, ReembolsoDetalleComprobanteRetencion> reembolsoDetalleMapper;
    private Mapper<PagoDTO, Pago> pagoMapper;

    public DocSustentoMapper() {
        this.impuestoDocSustentoMapper = new ImpuestoDocSustentoMapper();
        this.retencionMapper = new RetencionMapper();
        this.reembolsoDetalleMapper = new ReembolsoDetalleComprobanteRetencionMapper();
        this.pagoMapper = new PagoMapper();
    }

    @Override
    public DocSustento convert(DocSustentoDTO docSustentoDTO) {
        if (docSustentoDTO == null) {
            return null;
        }
        DocSustento docSustento = new DocSustento();
        docSustento.setCodSustento(docSustentoDTO.getCodSustento());
        docSustento.setCodDocSustento(docSustentoDTO.getCodDocSustento());
        docSustento.setNumDocSustento(docSustentoDTO.getNumDocSustento());
        docSustento.setFechaEmisionDocSustento(docSustentoDTO.getFechaEmisionDocSustento());
        docSustento.setFechaRegistroContable(docSustentoDTO.getFechaRegistroContable());
        docSustento.setNumAutDocSustento(docSustentoDTO.getNumAutDocSustento());
        docSustento.setPagoLocExt(docSustentoDTO.getPagoLocExt());
        docSustento.setTipoRegi(docSustentoDTO.getTipoRegi());
        docSustento.setPaisEfecPago(docSustentoDTO.getPaisEfecPago());
        docSustento.setAplicConvDobTrib(docSustentoDTO.getAplicConvDobTrib());
        docSustento.setPagExtSujRetNorLeg(docSustentoDTO.getPagExtSujRetNorLeg());
        docSustento.setPagoRegFis(docSustentoDTO.getPagoRegFis());
        docSustento.setTotalComprobantesReembolso(docSustentoDTO.getTotalComprobantesReembolso());
        docSustento.setTotalBaseImponibleReembolso(docSustentoDTO.getTotalBaseImponibleReembolso());
        docSustento.setTotalImpuestoReembolso(docSustentoDTO.getTotalImpuestoReembolso());
        docSustento.setTotalSinImpuestos(docSustentoDTO.getTotalSinImpuestos());
        docSustento.setImporteTotal(docSustentoDTO.getImporteTotal());
        if (docSustentoDTO.getImpuestoDocSustento() != null && !docSustentoDTO.getImpuestoDocSustento().isEmpty()) {
            docSustento.setImpuestoDocSustento(getImpuestoDocSustentoMapper().convertAll(docSustentoDTO.getImpuestoDocSustento()));
        }
        if (docSustentoDTO.getRetencion() != null && !docSustentoDTO.getRetencion().isEmpty()) {
            docSustento.setRetencion(getRetencionMapper().convertAll(docSustentoDTO.getRetencion()));
        }
        if (docSustentoDTO.getReembolsoDetalle() != null && !docSustentoDTO.getReembolsoDetalle().isEmpty()) {
            docSustento.setReembolsoDetalle(getReembolsoDetalleMapper().convertAll(docSustentoDTO.getReembolsoDetalle()));
        }
        if (docSustentoDTO.getPago() != null && !docSustentoDTO.getPago().isEmpty()) {
            docSustento.setPago(getPagoMapper().convertAll(docSustentoDTO.getPago()));
        }
        return docSustento;
    }

    public Mapper<ImpuestoDocSustentoDTO, ImpuestoDocSustento> getImpuestoDocSustentoMapper() {
        return impuestoDocSustentoMapper;
    }

    public void setImpuestoDocSustentoMapper(Mapper<ImpuestoDocSustentoDTO, ImpuestoDocSustento> impuestoDocSustentoMapper) {
        this.impuestoDocSustentoMapper = impuestoDocSustentoMapper;
    }

    public Mapper<RetencionDTO, Retencion> getRetencionMapper() {
        return retencionMapper;
    }

    public void setRetencionMapper(Mapper<RetencionDTO, Retencion> retencionMapper) {
        this.retencionMapper = retencionMapper;
    }

    public Mapper<ReembolsoDetalleDTO, ReembolsoDetalleComprobanteRetencion> getReembolsoDetalleMapper() {
        return reembolsoDetalleMapper;
    }

    public void setReembolsoDetalleMapper(Mapper<ReembolsoDetalleDTO, ReembolsoDetalleComprobanteRetencion> reembolsoDetalleMapper) {
        this.reembolsoDetalleMapper = reembolsoDetalleMapper;
    }

    public Mapper<PagoDTO, Pago> getPagoMapper() {
        return pagoMapper;
    }

    public void setPagoMapper(Mapper<PagoDTO, Pago> pagoMapper) {
        this.pagoMapper = pagoMapper;
    }

}