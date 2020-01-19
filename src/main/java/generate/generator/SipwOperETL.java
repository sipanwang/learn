package generate.generator;

import generate.pojo.SipwOper;
import generate.pojo.TransFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SipwOperETL implements Runnable {
    private Logger logger = LoggerFactory.getLogger(SipwOperETL.class);
    private File fout = null;
    private FileOutputStream fos = null;
    private BufferedWriter bw = null;
    private int tfCount;
    private int processCount = 0;
    private long startTime;
    private String postFix;
    private long[] transTimeArr;

    private SipwOperGenerator tfg;

    public SipwOperETL(long startTime, int tfCount) throws FileNotFoundException {
        this.startTime = startTime;
        this.tfCount = tfCount;
        this.transTimeArr = new long[tfCount];
        tfg = new SipwOperGenerator(startTime);
        postFix = Global.SDF.format(new Date(startTime));
        fout = new File(Global.outFilePath + postFix + ".txt");
        fos = new FileOutputStream(fout);
        bw = new BufferedWriter(new OutputStreamWriter(fos));
    }

    /*
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        formatTransTime();
        long point1 = System.currentTimeMillis();
        try {
            load();
            long point3 = System.currentTimeMillis();
            logger.info("{}, total:[{}ms], transTime:[{}ms],  load:[{}ms].", Global.SDF.format(new Date(startTime)),
                point3 - start, point1 - start, point3 - point1);
        } catch (Throwable e) {
            logger.error("{} failed.{}", Global.SDF.format(new Date(startTime)), e);
        }
    }

    /**
     * 生成交易时间，并排序 <br>
     *
     * @since
     */
    private void formatTransTime() {
        for (int i = 0; i < tfCount; i++) {
            transTimeArr[i] = tfg.getTransTime();
        }
        Arrays.sort(transTimeArr);
    }

    private void load() throws IOException {
        SipwOper temp;
        List<Object> tfList = new ArrayList<Object>(Global.writeBath);
        for (int i = 0; i < tfCount; i++) {
            temp = tfg.next(transTimeArr[i]);
            tfList.add(temp);
            if (tfList.size() % 1 == 0) {
                for (Object obj : tfList) {
                    bw.write(obj.toString() + "\n");
                    processCount++;
                }
                tfList.clear();
            }
        }
        bw.close();
    }
}
