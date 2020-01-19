package generate.generator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MergeTool {
    private Logger logger = LoggerFactory.getLogger(MergeTool.class);
    private PrintWriter writer = null;
    private final String DATE_PATTERN = "yyyyMMddHHmmssSSS";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    private long writeCount = 0;
    private String currentDate = null;

    private int minDateIndex(ArrayList<Date> dateList) {
        Date minDate = dateList.get(0);
        int minIndex = 0;
        for (int i = 1; i < dateList.size(); i++) {
            Date date = dateList.get(i);
            if (minDate != null && minDate.after(date)) {
                minDate = dateList.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    @PostConstruct
    private void run() throws IOException, ParseException {
        logger.info("================================================");
        logger.info("merge mode");
        logger.info("================================================");
        //read file from path
        long start = System.currentTimeMillis();
        File dir = new File(Global.sourceFilepath);
        File[] filesList = dir.listFiles();

        if (filesList.length < 2) {
            logger.warn("2 or more files can be merged, ignore...");
        }
        File targetFile = new File(Global.targetFile);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        writer = new PrintWriter(Global.targetFile, "UTF-8");

        ArrayList<BufferedReader> readerIndex = new ArrayList<BufferedReader>();
        ArrayList<String> lineList = new ArrayList<String>();
        ArrayList<Date> dateList = new ArrayList<Date>();
        for (File file : filesList) {
            // 2) Create BufferedReader list with respect to the file.
            BufferedReader br1 = new BufferedReader(new FileReader(file));
            readerIndex.add(br1);
            // 3) Read the 1st line of each line and store it in another list.
            String line = br1.readLine();
            lineList.add(line);
            // 4) Store the date from the 1st line of each file.
            //String date = line.substring(17, 32);
            JSONObject json = JSONObject.parseObject(line);
            JSONObject body = json.getJSONObject("body");
            String date = body.getString("operTime");
            Date convertedDate = sdf.parse(date);
            dateList.add(convertedDate);
        }
        int index = readerIndex.size();
        // 5) While BufferedReader's size is not zero then,
        while (index > 0) {
            // 6) Take the index of minimum date from dateList
            writeCount++;
            int indexMin = minDateIndex(dateList);
            // 7) Get the Line with the index you got in the previous step (lineToWrite).
            String lineToWrite = lineList.get(indexMin);
            writeLine(lineToWrite);
            // 8) Get the buffered reader with the index.
            BufferedReader br1 = readerIndex.get(indexMin);
            if (br1 != null) {
                //  9) If the BR is not null then read the line.
                String line = br1.readLine();
                if (line != null) {
                    // 10)If line is not equal to null then remove the lineList from the index and add the line to index.
                    lineList.remove(indexMin);
                    lineList.add(indexMin, line);
                    // 11)If the line length is greater than 23 (yyyy-MM-dd HH:mm:ss,SSS) then take the first 23 String from the line.
                    //String date = line.substring(17, 32);
                    JSONObject json = JSONObject.parseObject(line);
                    JSONObject body = json.getJSONObject("body");
                    String date = body.getString("operTime");
                    Date convertedDate = sdf.parse(date);
                    dateList.remove(indexMin);
                    dateList.add(indexMin, convertedDate);
                } else {
                    //If line is null then remove the min indexed line from lineList,dateList,BufferedReader list. Do BufferedReader--.
                    lineList.remove(indexMin);
                    dateList.remove(indexMin);
                    readerIndex.remove(indexMin);
                    br1.close();
                    index--;
                }
            }
        }
        logger.info("合并文件数量："+writeCount);
        writer.close();
        long end = System.currentTimeMillis();
        logger.info("completed total:[{}]ms", end - start);
        System.exit(0);
    }

    private void writeLine(String line) {
        StringBuilder sb = new StringBuilder();
        //String date = line.substring(17, 25);
        JSONObject json = JSONObject.parseObject(line);
        JSONObject body = json.getJSONObject("body");
        String date = body.getString("operTime");
        if (currentDate == null || !currentDate.equals(date)) {
            writeCount = 0;
            currentDate = date;
        }
        writeCount++;
        //sb.append(currentDate).append(String.format("%08d", writeCount)).append(line.substring(16));
        //writer.println(sb.toString());
        writer.println(line);
    }
}
