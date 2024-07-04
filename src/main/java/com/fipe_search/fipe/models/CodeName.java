package com.fipe_search.fipe.models;

public class CodeName {

  private Integer code;
  private String name;

  public CodeName(CodeNameData payload) {
    this.code = Integer.parseInt(payload.code());
    this.name = payload.name();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
