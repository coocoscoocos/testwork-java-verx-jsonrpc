package ru.coocos.agg;

import io.vertx.core.Vertx;
import ru.coocos.agg.service.ServerVerticle;

public class Aggregator {

    private final static int PORT = 8080;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle(PORT));
    }
}
