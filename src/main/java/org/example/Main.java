package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Solution.readInputAndSaveToDataStructure("src/main/resources/input.txt");
    System.out.println(Solution.getXMASOccurrencesFromData());
  }
}

class Solution {
  static List<String> data = new ArrayList<>();
  public static void readInputAndSaveToDataStructure(String path) {
    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while((line = br.readLine()) != null) {
        data.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  static boolean matchesForward(String string, String neededMatch, int index) {
    return string.substring(index, index+4).equals(neededMatch);
  }

  static boolean matchesDownward(String neededMatch, int i, int j) {
    String lineOne = data.get(i);
    String lineTwo = data.get(i+1);
    String lineThree = data.get(i+2);
    String lineFour = data.get(i+3);
    return ("" + lineOne.charAt(j) + lineTwo.charAt(j) + lineThree.charAt(j) + lineFour.charAt(j)).equals(neededMatch);
  }

  static boolean matchesDiagonalRight(String neededMatch, int i, int j) {
    String lineOne = data.get(i);
    String lineTwo = data.get(i+1);
    String lineThree = data.get(i+2);
    String lineFour = data.get(i+3);
    return ("" + lineOne.charAt(j) + lineTwo.charAt(j+1) + lineThree.charAt(j+2) + lineFour.charAt(j+3)).equals(neededMatch);
  }

  static boolean matchesDiagonalLeft(String neededMatch, int i, int j) {
    String lineOne = data.get(i);
    String lineTwo = data.get(i+1);
    String lineThree = data.get(i+2);
    String lineFour = data.get(i+3);   
    return ("" + lineOne.charAt(j) + lineTwo.charAt(j-1) + lineThree.charAt(j-2) + lineFour.charAt(j-3)).equals(neededMatch);
  }

  public static int getXMASOccurrencesFromData() {
    int timeXMAS = 0;
    String xmas = "XMAS";
    String xmasBackwards = "SAMX";
    for(int i = 0; i < data.size(); i++) {
      String line = data.get(i);
      for (int j = 0; j < line.length(); j++) {
        if(line.charAt(j) == 'X') {
          if (j+3 <= line.length()-1) {
            if (matchesForward(line, xmas, j)) timeXMAS++;
          }
          if(i+3 <= data.size()-1) {
            if (matchesDownward(xmas, i, j)) timeXMAS++;
          }
          if (j+3 <= line.length()-1 && i+3 <= data.size()-1) {
            if (matchesDiagonalRight(xmas, i, j)) timeXMAS++;
          }
          if(j-3 >= 0 && i+3 <= data.size()-1){
            if(matchesDiagonalLeft(xmas, i, j)) timeXMAS++;
          }
        } else if (line.charAt(j) == 'S') {
          if (j+3 <= line.length()-1) {
            if (matchesForward(line, xmasBackwards, j)) timeXMAS++;
          }
          if(i+3 <= data.size()-1){
            if(matchesDownward(xmasBackwards, i, j)) timeXMAS++;
          }
          if (j+3 <= line.length()-1 && i+3 <= data.size()-1) {
            if(matchesDiagonalRight(xmasBackwards, i, j)) timeXMAS++;
          }
          if(j-3 >= 0 && i+3 <= data.size()-1){
            if(matchesDiagonalLeft(xmasBackwards, i, j)) timeXMAS++;
          }
        }
      }
    }
    return timeXMAS;
  }
}