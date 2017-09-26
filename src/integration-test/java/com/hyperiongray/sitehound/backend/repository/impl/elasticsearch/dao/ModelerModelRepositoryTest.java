package com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.dao;

import com.hyperiongray.sitehound.backend.TestConfiguration;
import com.hyperiongray.sitehound.backend.kafka.api.dto.dd.modeler.output.DdModelerOutput;
import com.hyperiongray.sitehound.backend.repository.impl.elasticsearch.api.ModelerModelDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by tomas on 19/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ModelerModelRepositoryTest {

    @Autowired
    private ElasticsearchModelerModelRepository modelerModelRepository = new ElasticsearchModelerModelRepository();

    private String id = "59c27d45e91b2e11edbaf859";
    private String model = "YmlnIG1vZGVsbC4uZC5hZGY";
    private String quality = "big modell..d.adf";

    @Test
    public void crud() throws Exception {
        modelerModelRepository.delete(id);
        DdModelerOutput ddModelerOutput = new DdModelerOutput();
        ddModelerOutput.setWorkspaceId(id);
        ddModelerOutput.setModel(model);
        ddModelerOutput.setQuality(quality);
        modelerModelRepository.save(ddModelerOutput);

        ModelerModelDto savedModel = modelerModelRepository.get(id);
        Assert.assertEquals(model, savedModel.getModel());

        modelerModelRepository.delete(id);

        ModelerModelDto savedModelAfterDelete = modelerModelRepository.get(id);
        Assert.assertNull(savedModelAfterDelete);

        modelerModelRepository.save(ddModelerOutput);

    }


    @Test
    public void getTest() throws IOException {
//        NotFoundError: TransportError(404, u'{"_index":"modeler","_type":"model","_id":"59676bc6166f1c7543990bf3","found":false}')
        ModelerModelDto model = modelerModelRepository.get(id);
        System.out.println(model);
    }
}