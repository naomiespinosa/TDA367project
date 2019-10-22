package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class MomentTest {

  // TODO KOLLA HUR MAN SKRIVER IN DATUM NÄR MAN SKAPAR EN TODO
  @Test
  public void momentToString() {
    String str = "2019-11-11";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(str, formatter);
    Moment moment = new Moment("tenta", dateTime);
    assertEquals("Den 11 NOVEMBER 2019: tenta", moment.toString());
  }

  @Test
  public void momentDeadline() {
    String str = "2019-11-11";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(str, formatter);
    Moment moment = new Moment("tenta", dateTime);

    assertEquals(dateTime, moment.getDeadline());
  }

  // getname är private egentligen
  @Test
  public void momentName() {
    String str = "2019-11-11";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(str, formatter);
    Moment moment = new Moment("tenta", dateTime);
    assertEquals("tenta", moment.getName());
  }

  @Test
  public void momentCompare() {
    String str1 = "2019-11-11";
    String str2 = "2019-12-12";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime1 = LocalDate.parse(str1, formatter);
    LocalDate dateTime2 = LocalDate.parse(str2, formatter);
    Moment moment1 = new Moment("tenta", dateTime1);
    Moment moment2 = new Moment("tenta", dateTime2);

    assertEquals(-1, moment2.byDate.compare(moment1, moment2));
  }
}
