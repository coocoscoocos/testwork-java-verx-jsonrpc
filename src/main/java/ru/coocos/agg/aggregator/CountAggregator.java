package ru.coocos.agg.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Реализация агрегатора входящий сообщений
 *
 * Эмуляция бизнес логики приложения
 */
public class CountAggregator implements IAggregator {

    private final String TYPE = "count";

    /**
     * Ключ для агрегации, состоит из части полей message
     */
    private static class Item {

        @JsonProperty("id_sample")
        private String idSample;

        @JsonProperty("num_id")
        private String numId;

        @JsonProperty("id_location")
        private String idLocation;

        @JsonProperty("id_signal_par")
        private String idSignalPar;

        @JsonProperty("count")
        private long count = 0;

        public Item(String idSample, String numId, String idLocation, String idSignalPar) {
            this.idSample = idSample;
            this.numId = numId;
            this.idLocation = idLocation;
            this.idSignalPar = idSignalPar;
        }

        public String getIdSample() {
            return idSample;
        }

        public String getNumId() {
            return numId;
        }

        public String getIdLocation() {
            return idLocation;
        }

        public String getIdSignalPar() {
            return idSignalPar;
        }

        public void setCount(long count) {
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(idSample, item.idSample) &&
                    Objects.equals(numId, item.numId) &&
                    Objects.equals(idLocation, item.idLocation) &&
                    Objects.equals(idSignalPar, item.idSignalPar);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idSample, numId, idLocation, idSignalPar);
        }
    }

    // Сообщения сгруппированные по ключу
    // ключ - часть часть полей входящих сообщений
    private ConcurrentMap<Item, List<Message>> itemMessagesMap = new ConcurrentHashMap<>();

    @Override
    public void add(Message message) {
        Item item = new Item(message.getIdSample(), message.getNumId(), message.getIdLocation(), message.getIdSignalPar());
        itemMessagesMap.putIfAbsent(item, new ArrayList<>());
        itemMessagesMap.get(item).add(message);
    }

    @Override
    synchronized public List<AggregatedResult> getResultList() {
        ConcurrentMap<Item, List<Message>> tempMap = itemMessagesMap;
        itemMessagesMap = new ConcurrentHashMap<>();
        return tempMap.entrySet().stream()
                .map(e -> {
                    List<Message> messageList = e.getValue();
                    int count = messageList.size();
                    Item item = e.getKey();
                    item.setCount(count);
                    return new AggregatedResult(TYPE, item, messageList);
                })
                .collect(Collectors.toList());
    }
}
