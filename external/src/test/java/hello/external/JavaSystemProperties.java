package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class JavaSystemProperties {

    public static void main(String[] args){
        Properties properties = System.getProperties();
        for(Object key : properties.keySet()){
            log.info("prop = {} = {}", key, properties.get(key));
        }

        String url =  System.getProperty("url");
        log.info("url = {}", url);
    }
}
