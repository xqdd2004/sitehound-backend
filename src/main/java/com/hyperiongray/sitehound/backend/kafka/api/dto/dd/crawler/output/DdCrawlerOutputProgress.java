package com.hyperiongray.sitehound.backend.kafka.api.dto.dd.crawler.output;

import com.hyperiongray.framework.kafka.dto.KafkaDto;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tomas on 2/10/16.
 */
public class DdCrawlerOutputProgress  extends KafkaDto {

    private String id;

    private String progress;

    @JsonProperty("percentage_done")
    private Double percentageDone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Double getPercentageDone() {
        return percentageDone;
    }

    public void setPercentageDone(Double percentageDone) {
        this.percentageDone = percentageDone;
    }


    @Override
    public String toString() {
        return "DdCrawlerOutputProgress{" +
                "id='" + id + '\'' +
                ", progress='" + progress + '\'' +
                ", percentageDone=" + percentageDone +
                '}';
    }
}
