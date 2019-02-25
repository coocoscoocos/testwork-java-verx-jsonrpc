package ru.coocos.agg.service;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcParam;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcService;
import ru.coocos.agg.aggregator.AggregatedResult;
import ru.coocos.agg.aggregator.IAggregator;
import ru.coocos.agg.aggregator.Message;

import java.util.Collection;
import java.util.List;

@JsonRpcService
public class RpcService {

    private IAggregator aggregator;

    public RpcService(IAggregator aggregator) {
        this.aggregator = aggregator;
    }

    @JsonRpcMethod
    public String add(@JsonRpcParam("message") Message message) {
        aggregator.add(message);
        return "ok";
    }

    @JsonRpcMethod
    public List<AggregatedResult> getResult() {
        return aggregator.getResultList();
    }

    @JsonRpcMethod
    public List<AggregatedResult> batchProcess(@JsonRpcParam("messages") Collection<Message> messages) throws IllegalAccessException, InstantiationException {
        IAggregator aggregator = this.aggregator.getClass().newInstance();
        messages.forEach(aggregator::add);
        return aggregator.getResultList();
    }

}
