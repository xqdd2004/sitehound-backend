package com.hyperiongray.sitehound.backend.test.model;

import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.service.crawler.Constants;

public class MetadataTestFactory {

    @Deprecated
    private static Metadata getMetadata_broadcrawl(){
        Metadata metadata = new Metadata();
        metadata.setWorkspace("test-workspace");
        metadata.setUow("uow-123435");
        metadata.setCrawlEntityType(Constants.CrawlEntityType.GOOGLE);
        metadata.setJobId("568a8649132ad21be932d3c2");
        metadata.setCrawlType(Constants.CrawlType.BROADCRAWL);
        metadata.setnResults(10);
//        metadata.setCallbackQueue("callbackqueue");
//        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
        metadata.setKeywordSourceType(Constants.KeywordSourceType.FETCHED);
        return metadata;
    }

    public static Metadata getMetadataKeywords(){
        Metadata metadata = new Metadata();
        metadata.setWorkspace("test-workspace");
        metadata.setJobId("568a8649132ad21be932d3c2");
        metadata.setCrawlEntityType(Constants.CrawlEntityType.BING);
        metadata.setCrawlType(Constants.CrawlType.KEYWORDS);
        metadata.setUow("uow-12344321");
        metadata.setnResults(10);
//        metadata.setCallbackQueue("callbackqueue");
//        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setStrTimestamp(Long.toString(System.currentTimeMillis()));
        metadata.setKeywordSourceType(Constants.KeywordSourceType.FETCHED);
        return metadata;
    }
}
