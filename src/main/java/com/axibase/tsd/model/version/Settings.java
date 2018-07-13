package com.axibase.tsd.model.version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    private String hostname;
    @JsonProperty("server.url")
    private String serverUrl;
}
