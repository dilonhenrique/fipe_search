package com.fipe_search.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleData(
    @JsonAlias("Valor") String price,
    @JsonAlias("Marca") String brand,
    @JsonAlias("Modelo") String model,
    @JsonAlias("AnoModelo") int year,
    @JsonAlias("Combustivel") String fuel) {
}
