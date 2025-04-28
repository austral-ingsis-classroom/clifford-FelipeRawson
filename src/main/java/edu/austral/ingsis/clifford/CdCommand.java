package edu.austral.ingsis.clifford;

import java.util.Optional;

public class CdCommand implements Command {
  private final FileSystem fileSystem;

  public CdCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length < 1) {
      return "path not specified";
    }

    String path = args[0];
    Optional<Directory> targetDir = fileSystem.navigateTo(path);

    if (targetDir.isEmpty()) {
      return "'" + path + "' directory does not exist";
    }

    fileSystem.setCurrentDirectory(targetDir.get());
    return "moved to directory '"
        + (targetDir.get() == fileSystem.getRoot() ? "/" : targetDir.get().getName())
        + "'";
  }
}
