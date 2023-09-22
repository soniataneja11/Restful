package demo.utilies;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "file:./src/test/resources/config.properties"
})
public interface ConfigProperties extends Config {
    @Key("baseURI")
    public String getBaseURI();

    //@Key("basePath")
    //public String getBasePath();

}
