package com.axibase.tsd.model.version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateInfo {
    private String localDate;
    private String currentDate;
    private TimeZoneInfo timeZone;
    private String startDate;
    private Long currentTime;
}
