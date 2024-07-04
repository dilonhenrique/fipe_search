package com.fipe_search.fipe.repository;

import java.util.Comparator;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fipe_search.fipe.models.CodeName;
import com.fipe_search.fipe.models.CodeNameData;
import com.fipe_search.fipe.models.ModelsList;
import com.fipe_search.fipe.models.Vehicle;
import com.fipe_search.fipe.models.VehicleData;
import com.fipe_search.fipe.utils.JsonParser;
import com.fipe_search.fipe.utils.Requester;

public class FipeRepo {

  private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
  private JsonParser parser = new JsonParser();
  private String type;
  private int brandCode;
  private int modelCode;

  public List<CodeName> searchBrandsByType(String type) {
    this.type = type;

    String response = Requester.run(BASE_URL + this.type + "/marcas");
    var codes = parser.fromJSON(response, new TypeReference<List<CodeNameData>>() {
    });
    return codes.stream().map(i -> new CodeName(i)).sorted(Comparator.comparingInt(CodeName::getCode)).toList();
  }

  public List<CodeName> searchModelsByBrand(int brandModel) {
    this.brandCode = brandModel;

    String response = Requester.run(BASE_URL + this.type + "/marcas/" + this.brandCode + "/modelos");
    var codes = parser.fromJSON(response, new TypeReference<ModelsList>() {
    });
    return codes.models().stream().map(i -> new CodeName(i)).sorted(Comparator.comparingInt(CodeName::getCode))
        .toList();
  }

  public List<CodeNameData> searchYearsByModel(int modelCode) {
    this.modelCode = modelCode;

    String response = Requester
        .run(BASE_URL + this.type + "/marcas/" + this.brandCode + "/modelos/" + this.modelCode + "/anos");
    var codes = parser.fromJSON(response, new TypeReference<List<CodeNameData>>() {
    });
    return codes.stream().sorted(Comparator.comparing(CodeNameData::code)).toList();
  }

  public Vehicle getPricingByYear(String year) {
    String response = Requester
        .run(BASE_URL + this.type + "/marcas/" + this.brandCode + "/modelos/" + this.modelCode + "/anos/" + year);
    var vehicleData = parser.fromJSON(response, new TypeReference<VehicleData>() {
    });
    return new Vehicle(vehicleData);
  }

}
