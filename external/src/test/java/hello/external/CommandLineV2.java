package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Properties;

@Slf4j
public class CommandLineV2 {

    public static void main(String[] args){
        for (String arg : args){
            log.info("arg = {}", arg);
        }

        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("source args = {}", List.of(appArgs.getSourceArgs()));
    }
}
