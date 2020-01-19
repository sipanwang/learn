package generate.conf;

import generate.generator.GeneratorTool;
import generate.generator.Global;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = { "sc.work.mode" }, havingValue = "generate")
public class GeneratorConf {
    @Bean
    public GeneratorTool generatorTool() {
        Global.RANDOM.setSeed(System.currentTimeMillis());
        return new GeneratorTool();
    }
}
