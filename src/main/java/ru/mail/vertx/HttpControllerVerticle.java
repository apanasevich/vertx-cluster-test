package ru.mail.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class HttpControllerVerticle extends AbstractVerticle {
  @NotNull
  private static final Logger log = LoggerFactory.getLogger(HttpControllerVerticle.class);


  @Override
  public void start(@NotNull Future<Void> startFuture) {
    final HttpServer httpServer = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    router.post("/message").handler(event -> {
      final List<String> message = event.queryParam("m");
      vertx.eventBus().publish("logging", String.join(",", message));
      event.response().end("ok");
    });

    httpServer.requestHandler(router::accept).listen(18888, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        log.info("HTTP server started on http://localhost:18888");
      } else {
        startFuture.fail(http.cause());
      }
    });
  }
}
