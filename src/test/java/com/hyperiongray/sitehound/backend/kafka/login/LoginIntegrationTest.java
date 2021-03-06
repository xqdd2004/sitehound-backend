package com.hyperiongray.sitehound.backend.kafka.login;

import com.hyperiongray.framework.JsonMapper;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginInputDto;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.login.input.DdLoginResultDto;
import com.hyperiongray.sitehound.backend.model.DdLoginInput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.ElasticsearchDatabaseClient;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.MongoRepository;
import com.hyperiongray.sitehound.backend.repository.impl.mongo.dd.DdLoginRepository;
import com.hyperiongray.sitehound.backend.service.aquarium.AquariumAsyncClient;
import com.hyperiongray.sitehound.backend.service.crawler.excavator.ExcavatorSearchService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.bing.BingCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.crawler.searchengine.google.GoogleCrawlerBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginInputBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.login.DdLoginResultBrokerService;
import com.hyperiongray.sitehound.backend.service.dd.modeler.input.DdModelerInputService;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpClientConnector;
import com.hyperiongray.sitehound.backend.service.httpclient.HttpProxyClientImpl;
import com.hyperiongray.sitehound.backend.service.nlp.tika.TikaService;
import com.hyperiongray.sitehound.backend.test.kafka.KafkaTestConfiguration;
import com.hyperiongray.sitehound.backend.test.kafka.producer.Producer;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 7/09/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
//@ActiveProfiles("integration-test")
@Ignore
public class LoginIntegrationTest {

    private static final String LOGIN_INPUT_TOPIC = "dd-login-input";
    private static final String LOGIN_RESULT_TOPIC = "dd-login-result";

    @ClassRule public static KafkaEmbedded loginInputEmbeddedKafka = new KafkaEmbedded(1, true, LOGIN_INPUT_TOPIC);
    @ClassRule public static KafkaEmbedded loginResultEmbeddedKafka = new KafkaEmbedded(1, true, LOGIN_RESULT_TOPIC);


    @Autowired
    private DdLoginInputBrokerService ddLoginInputBrokerService;

    @Autowired
    private DdLoginResultBrokerService ddLoginResultBrokerService;

    @Autowired private Producer producer;

    @Autowired private DdLoginRepository ddLoginRepository;


    @MockBean
    AquariumAsyncClient aquariumAsyncClient;
    @MockBean
    MongoRepository mongoRepository;
    @MockBean
    GoogleCrawlerBrokerService googleCrawlerBrokerService;
    @MockBean
    BingCrawlerBrokerService bingCrawlerBrokerService;
    @MockBean
    DdModelerInputService ddModelerInputService;
    @MockBean
    HttpProxyClientImpl httpProxyClient;
    @MockBean
    HttpClientConnector httpClientConnector;
    @MockBean
    TikaService tikaService;
    @MockBean
    ElasticsearchDatabaseClient elasticsearchDatabaseClient;
    @MockBean
    ExcavatorSearchService excavatorSearchService;

    @Test
    public void testTemplate() {

        String workspaceId = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        String jobId = "bbbbbbbbbbbbbbbbbbbbbbb";
        String domain = "test-example-" + System.currentTimeMillis() + ".com";
        String url = "http://test-example.com/login";
        String screenshot = "test-example-snapshot57ea86a9d11ff300054a351.....afazzz9";
        String result = "success";
        List<String> keys = Lists.newArrayList("txtUser", "txtPassword");
        Map<String, String> keyValues = Maps.newHashMap("txtUser", "");
        keyValues.put("txtPassword", "");

        DdLoginInputDto ddLoginInputDto = new DdLoginInputDto();
        ddLoginInputDto.setWorkspaceId(workspaceId);
        ddLoginInputDto.setJobId(jobId);
        ddLoginInputDto.setUrl(url);
        ddLoginInputDto.setScreenshot(screenshot);
        ddLoginInputDto.setDomain(domain);
        ddLoginInputDto.setKeys(keys);

        JsonMapper<DdLoginInputDto> loginInputJsonMapper = new JsonMapper();
        String loginInput="";
        try {
            loginInput = loginInputJsonMapper.toJson(ddLoginInputDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        producer.produce(LOGIN_INPUT_TOPIC, loginInputEmbeddedKafka, ddLoginInputBrokerService, loginInput);

        DdLoginInput ddLoginInputSaved = ddLoginRepository.getByDomain(domain);
        Assert.assertEquals(workspaceId, ddLoginInputSaved.getWorkspaceId());
        Assert.assertEquals(jobId, ddLoginInputSaved.getJobId());
        Assert.assertEquals(domain, ddLoginInputSaved.getDomain());
        Assert.assertEquals(url, ddLoginInputSaved.getUrl());
        Assert.assertEquals(keys, ddLoginInputSaved.getKeysOrder());
//        Assert.assertEquals(screenshot, ddLoginInputSaved.getScreenshot());

        DdLoginResultDto ddLoginResultDto = new DdLoginResultDto();
        ddLoginResultDto.setId(ddLoginInputSaved.getId());
        ddLoginResultDto.setResult(result);

        JsonMapper<DdLoginResultDto> loginResultJsonMapper = new JsonMapper();
        String loginResult = "";
        try {
            loginResult = loginResultJsonMapper.toJson(ddLoginResultDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        producer.produce(LOGIN_RESULT_TOPIC, loginResultEmbeddedKafka, ddLoginResultBrokerService, loginResult);

        DdLoginInput ddLoginResultSaved = ddLoginRepository.getByDomain(domain);
        Assert.assertEquals(workspaceId, ddLoginResultSaved.getWorkspaceId());
        Assert.assertEquals(jobId, ddLoginResultSaved.getJobId());
        Assert.assertEquals(domain, ddLoginResultSaved.getDomain());
        Assert.assertEquals(url, ddLoginResultSaved.getUrl());
        Assert.assertEquals(keys, ddLoginResultSaved.getKeysOrder());
        Assert.assertEquals(result, ddLoginResultSaved.getResult());
    }
}
