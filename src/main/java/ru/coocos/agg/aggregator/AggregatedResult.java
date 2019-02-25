package ru.coocos.agg.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AggregatedResult {

    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private Object value;

    @JsonProperty("messages")
    private List<Message> messageList;

    public AggregatedResult(String type, Object value, List<Message> messageList) {
        this.type = type;
        this.value = value;
        this.messageList = messageList;
    }
}
