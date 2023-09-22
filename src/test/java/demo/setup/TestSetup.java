package demo.setup;

import demo.utilies.ConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class TestSetup {

   @Before
    public void setUp()  {

        ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

        RestAssured.baseURI = configProperties.getBaseURI();
        //RestAssured.basePath = configProperties.getBasePath();

    }

    @After
    public void tearDown() {

    }
}
