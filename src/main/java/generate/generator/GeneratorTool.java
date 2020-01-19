package generate.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratorTool {
    @PostConstruct
    private void run() throws InterruptedException {
        logger.info("================================================");
        logger.info("generate mode");
        logger.info("================================================");
        try {
            init();
            int taskCount = (int) ((endTime - startTime) / Global.DAY_MILSECONDS);
            int taskCompleted = 0;
            for (long i = startTime; i < endTime; i += Global.DAY_MILSECONDS) {
                //Runnable task = new TransFlowETL(i, tfCount);
                //task.run();
                //调用自定义ETL
                Runnable task = new SipwOperETL(i, tfCount);
                task.run();
                taskCompleted++;
            }
            logger.info("{} file to generate, {} file completed", taskCount, taskCompleted);
        } catch (Throwable e) {
            logger.error("run etl failed.", e);
        }
        logger.info("completed...");
        System.exit(0);
    }

    /**
     * 初始化系统资源，如开始结束时间 <br>
     */
    private void init() throws ParseException {

        Date date = sdf.parse(start);
        startTime = date.getTime();
        date = sdf.parse(end);
        endTime = date.getTime() + Global.DAY_MILSECONDS;
    }

    private long startTime;
    private long endTime;

    @Value("${sc.time.start: }")
    private String start;

    @Value("${sc.time.end: }")
    private String end;

    @Value("${generator.record.perDay: 1000}")
    private int tfCount;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Logger logger = LoggerFactory.getLogger(GeneratorTool.class);
}
