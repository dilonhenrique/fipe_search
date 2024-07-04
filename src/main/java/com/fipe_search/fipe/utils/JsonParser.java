package com.fipe_search.fipe.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
  private ObjectMapper mapper = new ObjectMapper();

  public <T> T fromJSON(String json, TypeReference<T> type) {
    try {
      return mapper.readValue(json, type);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
