package edu.austral.ingsis.clifford;

public interface FileSystemItem {
  String getName();
  boolean isDirectory();
  String getPath();
}