package model;

import model.Min5a;

public class TestData {
  static String name = "Sten och Stanley";
  static String pwd  = "Margareta";
  static int number  = 42;

  public static void insertUsers(int size, Min5a model) {
    for (int i = 0; i < size; i++)
      model.addUser(number + i, name, pwd);
  }
}
