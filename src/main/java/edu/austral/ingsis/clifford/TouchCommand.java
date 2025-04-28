package edu.austral.ingsis.clifford;

public class TouchCommand implements Command {
  private final FileSystem fileSystem;

  public TouchCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(String[] args) {
    if (args.length < 1) {
      return "file name not specified";
    }

    String fileName = args[0];

    if (fileName.contains("/") || fileName.contains(" ")) {
      return "invalid file name";
    }

    Directory currentDir = fileSystem.getCurrentDirectory();

    if (currentDir.findChild(fileName).isPresent()) {
      return "file already exists";
    }

    File newFile = new File(fileName, currentDir);
    currentDir.addItem(newFile);

    return "'" + fileName + "' file created";
  }
}