package ru.coocos.agg.aggregator;

import java.util.List;

public interface IAggregator {
    void add(Message message);
    List<AggregatedResult> getResultList();
}
