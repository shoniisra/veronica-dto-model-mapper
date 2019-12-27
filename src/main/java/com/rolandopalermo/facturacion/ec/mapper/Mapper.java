package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.interfaces.Supportable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Mapper<SOURCE, TARGET> extends Supportable {

    TARGET convert(SOURCE source);

    default List<TARGET> convertAll(final List<SOURCE> sources) {
        List<TARGET> targets = new ArrayList<>();
        Optional.ofNullable(sources).ifPresent(list -> list.stream().map(this::convert).forEach(targets::add));
        return targets;
    }

}