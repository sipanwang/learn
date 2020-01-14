package com.sipw.loader;

import com.sipw.utils.ExcelReader;
import com.sipw.utils.ExcelWriter;
import com.sipw.vo.ExcelDataVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Component
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
//@ConfigurationProperties(prefix = "poi.excle")
public class ExcelLoader implements CommandLineRunner {

    @Value("${poi.excel.readPath}")
    private String readPath;

    @Value("${poi.excel.exportPath}")
    private String exportPath;

    private static Logger logger = Logger.getLogger(ExcelLoader.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("excel脱敏开始" + "\n" + "Excel读取路径是" + readPath + "\n" + "Excel输出路径是" + exportPath + "\n");
        load();
    }

    public void load() {
        // 读取Excel文件内容
        List<ExcelDataVO> readResult = ExcelReader.readExcel(readPath);
        logger.info("excel内容为"+readResult);

        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(readResult);

        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            File exportFile = new File(exportPath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportPath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            logger.warning("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
                logger.info("Excle转换完成");
            } catch (IOException e) {
                logger.warning("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }

    }
}





