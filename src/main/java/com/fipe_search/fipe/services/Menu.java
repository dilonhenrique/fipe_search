package com.fipe_search.fipe.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

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
    // TODO: validate type
    // TODO: clean terminal each step
    ListBrandsByType(type);
  }

  private void ListBrandsByType(String type) {
    brandList = fipe.searchBrandsByType(type);
    brandList.stream().forEach(i -> System.out.println("> " + i.getCode() + ": " + i.getName()));

    ChooseBrand();
  }

  private void ChooseBrand() {
    System.out.println("Digite o código da marca ou pesquise:");
    handleSelectCode(brandList, this::ListModelsByBrand, this::ChooseBrand);
  }

  private void ListModelsByBrand(CodeName brand) {
    modelList = fipe.searchModelsByBrand(brand.getCode());
    modelList.stream().forEach(i -> System.out.println("> " + i.getCode() + ": " + i.getName()));

    ChooseModel();
  }

  private void ChooseModel() {
    System.out.println("Digite o código do modelo ou pesquise:"); // optional search
    handleSelectCode(modelList, this::ListPricingByModel, this::ChooseModel);
  }

  private void ListPricingByModel(CodeName model) {
    var years = fipe.searchYearsByModel(model.getCode());
    years
        .stream()
        .map(i -> fipe.getPricingByYear(i.code()))
        .forEach(i -> System.out.println("> " + i.getModel() + " (" + i.getYear() + "): " + i.getPrice()));
  }

  private void handleSelectCode(List<CodeName> list, Consumer<CodeName> nextMethod, Runnable fallbackMethod) {
    String search = input.nextLine();
    int code;

    try {
      code = Integer.parseInt(search);

      Optional<CodeName> selected = list.stream().filter(i -> i.getCode() == code).findFirst();

      if (selected.isPresent()) {
        nextMethod.accept(selected.get());
      } else {
        System.out.println("Código não encontrado!\n");
        fallbackMethod.run();
      }
    } catch (NumberFormatException e) {
      list
          .stream()
          .filter(i -> i.getName().toLowerCase().contains(search.toLowerCase()))
          .forEach(i -> System.out.println("> " + i.getCode() + ": " + i.getName()));
      fallbackMethod.run();
    }
  }

}
