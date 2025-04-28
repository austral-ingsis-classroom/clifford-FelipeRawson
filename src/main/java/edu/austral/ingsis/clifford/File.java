package edu.austral.ingsis.clifford;

public final class File implements FileSystemItem {
  private final String name;
  private final Directory parent;

  public File(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String getPath() {
    return parent.getPath() + "/" + name;
  }
}