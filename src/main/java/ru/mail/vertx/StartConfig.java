package ru.mail.vertx;

import org.jetbrains.annotations.Nullable;
import picocli.CommandLine;

public final class StartConfig {
  @Nullable
  @CommandLine.Option(names = {"-h"}, description = "Host")
  private String host;

  public StartConfig() {
  }

  @Nullable
  public String host() {
    return host;
  }
}
