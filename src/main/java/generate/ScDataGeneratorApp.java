package generate;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, DataSourceAutoConfiguration.class,
    JpaRepositoriesAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan
public class ScDataGeneratorApp {
    public static void main(String[] args) {
        try {
            new SpringApplicationBuilder().bannerMode(Mode.OFF).sources(ScDataGeneratorApp.class).run(args);
        } catch (BeanCreationException e) {
            System.err.println("请确认已正确配置该测试系统");
            e.printStackTrace();
        }
    }
}
