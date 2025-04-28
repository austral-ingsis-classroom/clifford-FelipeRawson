package edu.austral.ingsis.clifford;

public class MkdirCommand implements Command {
  private final FileSystem fileSystem;

  public MkdirCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length < 1) {
      return "directory name not specified";}

    String dirName = args[0];

    if (dirName.contains("/") || dirName.contains(" ")) {
      return "invalid directory name";}

    Directory currentDir = fileSystem.getCurrentDirectory();

    Directory newDir = new Directory(dirName, currentDir);
    currentDir.addItem(newDir);

    return "'" + dirName + "' directory created";
  }
}