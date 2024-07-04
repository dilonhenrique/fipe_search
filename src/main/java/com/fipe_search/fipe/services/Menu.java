package com.fipe_search.fipe.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fipe_search.fipe.models.CodeName;
import com.fipe_search.fipe.repository.FipeRepo;

public class Menu {

  private Scanner input = new Scanner(System.in);
  private FipeRepo fipe = new FipeRepo();
  private List<CodeName> brandList;
  private List<CodeName> modelList;

  public void Show() {
    ChooseType();
  }

  private void ChooseType() {
    System.out.println("Que tipo de veículo você deseja buscar?");
    System.out.println("> Carros");
    System.out.println("> Motos");
    System.out.println("> Caminhões");

    String type = input.nextLine();
    // validate type
    ListBrandsByType(type);
  }

  private void ListBrandsByType(String type) {
    brandList = fipe.searchBrandsByType(type);
    brandList.stream().forEach(i -> System.out.println("> " + i.getCode() + ": " + i.getName()));

    ChooseBrand();
  }

  private void ChooseBrand() {
    System.out.println("Digite o código da marca:");
    String code = input.nextLine();

    Optional<CodeName> selectedBrand = brandList.stream().filter(i -> i.getCode() == Integer.parseInt(code))
        .findFirst();

    if (selectedBrand.isPresent()) {
      ListModelsByBrand(selectedBrand.get());
    } else {
      System.out.println("Código não encontrado!\n");
      ChooseBrand();
    }
  }

  private void ListModelsByBrand(CodeName brand) {
    modelList = fipe.searchModelsByBrand(brand.getCode());
    modelList.stream().forEach(i -> System.out.println("> " + i.getCode() + ": " + i.getName()));

    ChooseModel();
  }

  private void ChooseModel() {
    System.out.println("Digite o código do modelo:");
    String search = input.nextLine();

    Optional<CodeName> selectedModel = modelList.stream().filter(i -> i.getCode() == Integer.parseInt(search))
        .findFirst();

    if (selectedModel.isPresent()) {
      ListPricingByModel(selectedModel.get());
    } else {
      System.out.println("Código não encontrado!\n");
      ChooseModel();
    }
  }

  private void ListPricingByModel(CodeName model) {
    System.out.println(model.getName());
  }

}
