package com.rolandopalermo.facturacion.ec.mapper.pc;

import com.rolandopalermo.facturacion.ec.dto.v1.pc.MaquinaFiscalDTO;
import com.rolandopalermo.facturacion.ec.mapper.Mapper;
import com.rolandopalermo.facturacion.ec.modelo.liquidacion.MaquinaFiscal;

public class MaquinaFiscalMapper implements Mapper<MaquinaFiscalDTO, MaquinaFiscal> {

    @Override
    public MaquinaFiscal convert(MaquinaFiscalDTO maquinaFiscalDTO) {
        if (maquinaFiscalDTO == null) {
            return null;
        }
        MaquinaFiscal maquinaFiscal = new MaquinaFiscal();
        maquinaFiscal.setMarca(maquinaFiscalDTO.getMarca());
        maquinaFiscal.setModelo(maquinaFiscalDTO.getModelo());
        maquinaFiscal.setSerie(maquinaFiscalDTO.getSerie());
        return maquinaFiscal;
    }

}