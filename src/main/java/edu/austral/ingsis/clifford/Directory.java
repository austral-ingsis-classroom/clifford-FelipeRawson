package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Directory implements FileSystemItem {
  private final String name;
  private final Directory parent;
  private final List<FileSystemItem> children = new ArrayList<>();

  public Directory(String name) {
    this.name = name;
    this.parent = null;
  }

  //Constructor para directorios no root
  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  public void addItem(FileSystemItem item) {
    children.add(item);
  }

  public void removeItem(FileSystemItem item) {
    children.remove(item);
  }

  public List<FileSystemItem> getChildren() {
    return new ArrayList<>(children);
  }

  public Optional<FileSystemItem> findChild(String name) {
    return children.stream()
        .filter(item -> item.getName().equals(name))
        .findFirst();
  }

  public Directory getParent() {
    return parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Override
  public String getPath() {
    if (parent == null) {
      return "";
    }
    String parentPath = parent.getPath();
    if (parentPath.isEmpty()) {
      return "/" + name;
    }
    return parentPath + "/" + name;
  }
}