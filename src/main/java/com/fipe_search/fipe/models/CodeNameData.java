package com.fipe_search.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CodeNameData(@JsonAlias("codigo") String code, @JsonAlias("nome") String name) {
}
