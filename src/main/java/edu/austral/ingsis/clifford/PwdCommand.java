package edu.austral.ingsis.clifford;

public class PwdCommand implements Command {
  private final FileSystem fileSystem;

  public PwdCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    return fileSystem.getCurrentPath();
  }
}