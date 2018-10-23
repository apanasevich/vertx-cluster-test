package ru.mail.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingVerticle extends AbstractVerticle {
  @NotNull
  private static final Logger log = LoggerFactory.getLogger(LoggingVerticle.class);

  @Override
  public void start() {
    vertx.eventBus().consumer("logging", (Handler<Message<String>>) event -> log.info(event.body()));
  }
}