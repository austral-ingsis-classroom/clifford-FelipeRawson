package edu.austral.ingsis.clifford;

import java.util.Optional;

public class FileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    this.root = new Directory("");
    this.currentDirectory = root;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }

  public Directory getRoot() {
    return root;
  }

  public String getCurrentPath() {
    if (currentDirectory == root) {
      return "/";
    }
    return currentDirectory.getPath();
  }

  public Optional<Directory> navigateTo(String path) {
    if (path.isEmpty() || path.equals(".")) {
      return Optional.of(currentDirectory);
    }

    if (path.equals("..")) {
      return Optional.ofNullable(currentDirectory.getParent()).or(() -> Optional.of(root));
    }

    Directory startDir;
    String[] components;

    if (path.startsWith("/")) {
      startDir = root;
      path = path.substring(1);
    } else {
      startDir = currentDirectory;
    }

    components = path.split("/");

    Directory currentDir = startDir;
    for (String component : components) {
      if (component.isEmpty() || component.equals(".")) {
        continue;
      }

      if (component.equals("..")) {
        if (currentDir.getParent() != null) {
          currentDir = currentDir.getParent();
        }
        continue;
      }

      Optional<FileSystemItem> item = currentDir.findChild(component);

      if (item.isEmpty()) {
        return Optional.empty();
      }

      if (!item.get().isDirectory()) {
        return Optional.empty();
      }

      currentDir = (Directory) item.get();
    }

    return Optional.of(currentDir);
  }
}
