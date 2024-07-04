package com.fipe_search.fipe.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelsList(@JsonAlias("modelos") List<CodeNameData> models) {
}
