package hello.selector;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ImportSelectorTest {
    @Test
    void staticConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SelectorConfig.class);
        HelloBean helloBean = ac.getBean(HelloBean.class);
        assertThat(helloBean).isNotNull();
    }

    @Configuration
    @Import(HelloConfig.class)
    public static class StaticConfig{

    }

    @Configuration
    @Import(HelloImportSelector.class)
    public static class SelectorConfig{

    }
}
