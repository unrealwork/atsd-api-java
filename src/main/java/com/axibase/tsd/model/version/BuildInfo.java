package com.axibase.tsd.model.version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildInfo {
    private String revisionNumber;
    private String buildNumber;
    private String buildId;
    private String hbaseVersion;
}
