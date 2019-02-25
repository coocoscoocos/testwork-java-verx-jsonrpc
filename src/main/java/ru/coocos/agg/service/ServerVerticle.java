package ru.coocos.agg.service;

import com.github.arteam.simplejsonrpc.server.JsonRpcServer;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import ru.coocos.agg.aggregator.CountAggregator;

public class ServerVerticle extends AbstractVerticle {

    private JsonRpcServer rpcServer = new JsonRpcServer();
    private RpcService rpcService = new RpcService(new CountAggregator());

    private int port;

    public ServerVerticle(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/rpc").handler(this::handleRpc);
        vertx.createHttpServer().requestHandler(router).listen(port);
    }

    private void handleRpc(RoutingContext routingContext) {
        String response = rpcServer.handle(routingContext.getBodyAsString(), rpcService);
        routingContext.response()
                .end(response);
    }

}
