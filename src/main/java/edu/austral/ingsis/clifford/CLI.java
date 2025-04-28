package edu.austral.ingsis.clifford;

import java.util.HashMap;
import java.util.Map;

public class CLI {
  private final FileSystem fileSystem;
  private final Map<String, Command> commands;

  public CLI() {
    this.fileSystem = new FileSystem();
    this.commands = new HashMap<>();
    initCommands();
  }

  private void initCommands() {
    commands.put("ls", new LsCommand(fileSystem));
    commands.put("cd", new CdCommand(fileSystem));
    commands.put("touch", new TouchCommand(fileSystem));
    commands.put("mkdir", new MkdirCommand(fileSystem));
    commands.put("rm", new RmCommand(fileSystem));
    commands.put("pwd", new PwdCommand(fileSystem));
  }

  public String executeCommand(String input) {
    String[] parts = input.trim().split("\\s+", 2);
    String commandName = parts[0];

    Command command = commands.get(commandName);
    if (command == null) {
      return "unknown command: " + commandName;
    }

    String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];
    return command.execute(args);
  }

  public FileSystem getFileSystem() {
    return fileSystem;
  }
}
