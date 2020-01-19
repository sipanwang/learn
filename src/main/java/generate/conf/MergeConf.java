package generate.conf;

import generate.generator.MergeTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = { "sc.work.mode" }, havingValue = "merge")
public class MergeConf {
    @Bean
    public MergeTool generatorTool() {
        return new MergeTool();
    }
}