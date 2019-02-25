package ru.coocos.agg.aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Входящее сообщение
 */
public class Message {

    @JsonProperty("id_sample")
    private String idSample;

    @JsonProperty("num_id")
    private String numId;

    @JsonProperty("id_location")
    private String idLocation;

    @JsonProperty("id_signal_par")
    private String idSignalPar;

    @JsonProperty("id_detected")
    private String idDetected;

    @JsonProperty("id_clas_det")
    private String idClasDet;

    @JsonCreator
    public Message(
            @JsonProperty("id_sample") String idSample,
            @JsonProperty("num_id") String numId,
            @JsonProperty("id_location") String idLocation,
            @JsonProperty("id_signal_par") String idSignalPar,
            @JsonProperty("id_detected") String idDetected,
            @JsonProperty("id_clas_det") String idClasDet
    ) {
        this.idSample = idSample;
        this.numId = numId;
        this.idLocation = idLocation;
        this.idSignalPar = idSignalPar;
        this.idDetected = idDetected;
        this.idClasDet = idClasDet;
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

    public String getIdDetected() {
        return idDetected;
    }

    public String getIdClasDet() {
        return idClasDet;
    }
}
