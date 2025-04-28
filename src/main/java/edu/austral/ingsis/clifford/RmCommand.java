package edu.austral.ingsis.clifford;

import java.util.Optional;

public class RmCommand implements Command {
  private final FileSystem fileSystem;

  public RmCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length < 1) {
      return "path not specified";
    }

    boolean recursive = false;
    String path = null;

    for (String arg : args) {
      if (arg.equals("--recursive")) {
        recursive = true;
      } else if (path == null) {
        path = arg;
      }
    }

    if (path == null) {
      return "path not specified";
    }

    Directory currentDir = fileSystem.getCurrentDirectory();
    Optional<FileSystemItem> item = currentDir.findChild(path);

    if (item.isEmpty()) {
      return "'" + path + "' does not exist";
    }

    if (item.get().isDirectory() && !recursive) {
      return "cannot remove '" + path + "', is a directory";
    }

    currentDir.removeItem(item.get());
    return "'" + path + "' removed";
  }
}