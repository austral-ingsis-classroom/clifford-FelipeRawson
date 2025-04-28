package edu.austral.ingsis.clifford;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LsCommand implements Command {
  private final FileSystem fileSystem;

  public LsCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    Directory currentDir = fileSystem.getCurrentDirectory();
    List<FileSystemItem> items = currentDir.getChildren();

    if (items.isEmpty()) {
      return "";
    }

    String orderFlag = extractOrderFlag(args);

    if (orderFlag != null) {
      if (orderFlag.equals("asc")) {
        items.sort(Comparator.comparing(FileSystemItem::getName));
      } else if (orderFlag.equals("desc")) {
        items.sort(Comparator.comparing(FileSystemItem::getName).reversed());
      }
    }

    return items.stream().map(FileSystemItem::getName).collect(Collectors.joining(" "));
  }

  private String extractOrderFlag(String[] args) {
    for (String arg : args) {
      if (arg.startsWith("--ord=")) {
        return arg.substring(6);
      }
    }
    return null;
  }
}
