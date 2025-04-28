package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class FileSystemRunner {
  private final CLI cli = new CLI();

  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();

    for (String command : commands) {
      String result = cli.executeCommand(command);
      results.add(result);
    }

    return results;
  }
}
