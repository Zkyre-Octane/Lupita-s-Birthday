package com.example;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {

    // this is a private and static hashmap to store the birthdays
    private static HashMap<String, String> birthdayMap = new HashMap<>();

    // Read JSON file and parse into a list of BirthdayData objects
    public static JSONArray parseJsonFile(String fileName) {
       // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

    JSONArray birthdayArr = null;

    // Read JSON file
    try (FileReader reader = new FileReader(fileName)) {
      Object obj = jsonParser.parse(reader);

      birthdayArr = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayArr;
    }
 
    // students do not have to change this function
  public static void initializeMap(final String pathToFile) {
    JSONArray jsonData = parseJsonFile(pathToFile);

    // loop over list
    String birthday, name;
    JSONObject obj;
    for (Integer i = 0; i < jsonData.size(); i++) {
      // parse the object and pull out the name and birthday
      obj = (JSONObject) jsonData.get(i);
      birthday = (String) obj.get("birthday");
      name = (String) obj.get("name");

      // add the name and birthday in to a hashmap
      birthdayMap.put(name, birthday);

      // print the names and birthdays
      System.out.println("name = " + name);
      System.out.println("birthday = " + birthday);
    }
  }

  public static void main(final String[] args) {
    String pathToFile =
      "D:/dd/Lupita's  Birthday/demo/src/main/java/com/example/birthdayOnlyForTesting.json";

    // initialize the hash map
    initializeMap(pathToFile);

    // read user input from keyboard
    System.out.println("Reading user input into a string");

    // get user input
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name:");
    String name = input.nextLine();

    // Search for the birthday
    String birthday = birthdayMap.get(name);
    if (birthday != null) {
        System.out.println("Birthday: " + birthday);
    } else {
        System.out.println("Birthday unknown.");
    }

    // close the scanner
    input.close();
  }
}