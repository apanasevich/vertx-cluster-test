package ru.mail.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

public enum Start {
  ;
  @NotNull
  private static final Logger log = LoggerFactory.getLogger(Start.class);

  public static void main(@NotNull String[] args) {

    final StartConfig startConfig = CommandLine.populateCommand(new StartConfig(), args);
    final VertxOptions options = new VertxOptions()
        .setClusterHost(startConfig.host())
        .setClusterPublicHost(startConfig.host())
        .setClusterManager(new HazelcastClusterManager());

    Vertx.clusteredVertx(options, res -> {
      if (res.succeeded()) {
        final Vertx vertx = res.result();
        vertx.deployVerticle(new HttpControllerVerticle());
        vertx.deployVerticle(new LoggingVerticle());
        log.info("Cluster node started at " + startConfig.host());
      } else {
        log.error("Start failed!");
      }
    });
  }
}
