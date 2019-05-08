package com.swb.fuel;

import com.swb.fuel.models.FuelConsumption;
import com.swb.fuel.utilities.MultipartParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiParserTest {

    @Autowired
    ServletContext context;

    @Test
    public void fuelConsumptionIsParsedCorrectly() throws IOException, ParseException {

        Path path = Paths.get(ResourceUtils.getFile("sample.txt").toURI());

        String filename = "sample.txt";
        String originalFilename = "sample.txt";
        String contentType = "text/plain";

        MockMultipartFile mockMultipartFile = new MockMultipartFile(filename, originalFilename, contentType, Files.readAllBytes(path));
        List<FuelConsumption> fcList = MultipartParser.fuelConsumptions(mockMultipartFile);

        Assert.assertTrue(fcList.size() == 2);
        Assert.assertTrue(fcList.get(0).getDriverId().equals("D00007"));
    }
}
