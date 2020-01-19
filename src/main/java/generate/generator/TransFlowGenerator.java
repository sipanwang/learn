package generate.generator;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import generate.conf.FlowCodes;
import generate.pojo.TransFlow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;

import static generate.generator.Global.generateUtils;

public class TransFlowGenerator {
    private Logger logger = LoggerFactory.getLogger(TransFlowGenerator.class);
    private long start;
    private int currentNo = 0;
    private HashFunction hf = Hashing.murmur3_128();

    public TransFlowGenerator() {
    }

    public TransFlowGenerator(long time) {
        this.start = time;
    }

    public TransFlow next() {
        // 交易时间
        long transTime = (long) ((getNormalGaussian() + 1) * Global.HALF_DAY_MILSECONDS) + start;
        return next(transTime);
    }

    public TransFlow next(long transTime) {

        double r = Global.RANDOM.nextDouble();
        int specType = -1;
        if (r <= Global.RATIO) {
            //get spec key
            specType = Global.RANDOM.nextInt(Global.specTypes.length);
        }
        TransFlow tf = new TransFlow();
        Date date = new Date(transTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);

        //交易时间
        tf.setTxn_tm(String.format("%02d%02d%02d", hours, minute, seconds));
        //交易日期
        tf.setTxn_dt(String.format("%04d%02d%02d", year, month, day));
        ++currentNo;
        //流水号
        tf.setMx_sn(Long.parseLong(String.format("%s%08d", tf.getTxn_dt(), currentNo)));
        tf.setAmt(getValueFromGauss(Global.AMT_MIN, Global.AMT_MAX));
        tf.setOrg(String.format("%04d", getValueFromGauss(Global.ORG_MIN, Global.ORG_MAX)));

        tf.setCacc(Long.toString(getValueFromGauss(Global.CACC_MIN, Global.CACC_MAX)));
        tf.setPeer_acc(Long.toString(getValueFromGauss(Global.PEER_CC_MIN, Global.PEER_CC_MAX)));
        tf.setCust_id(String.format("%014d", getValueFromGauss(Global.CUST_ID_MIN, Global.CUST_ID_MAX)));
        Hasher hasher = hf.newHasher();
        hasher.putString(tf.getCust_id(), Charset.forName("UTF-8"));
        tf.setDfp(hasher.hash().toString());
        tf.setIp(generateIp(tf.getCust_id()));
        tf.setIp(generateUtils.getRandomIp());
        //fill spec dem key

        if (specType != -1) {
            switch (specType) {
            case 0:
                if (Global.SpecIps != null)
                    tf.setIp(Global.SpecIps[Global.RANDOM.nextInt(Global.SpecIps.length)]);
                break;
            case 1:
                if (Global.Specdfps != null)
                    tf.setDfp(Global.Specdfps[Global.RANDOM.nextInt(Global.Specdfps.length)]);
                break;
            case 2:
                if (Global.SpecAcc != null)
                    tf.setCacc(Global.SpecAcc[Global.RANDOM.nextInt(Global.SpecAcc.length)]);
                break;
            case 3:
                if (Global.SpecPeerAcc != null)
                    tf.setPeer_acc(Global.SpecPeerAcc[Global.RANDOM.nextInt(Global.SpecPeerAcc.length)]);
                break;
            case 4:
                if (Global.SpecCustId != null)
                    tf.setCust_id(Global.SpecCustId[Global.RANDOM.nextInt(Global.SpecCustId.length)]);
                break;
            default:
            }
        }

        tf.setAmt(getValueFromGauss(Global.AMT_MIN, Global.AMT_MAX));
        tf.setTxn_code(FlowCodes.TXN_CODES[Global.RANDOM.nextInt(FlowCodes.TXN_CODES.length)]);
        if (Global.TXN_SPECCODES_MAP.containsKey(tf.getTxn_code())) {
            tf.setChnl_no(FlowCodes.CHNL_SPEC_CODES[Global.RANDOM.nextInt(FlowCodes.CHNL_SPEC_CODES.length)]);
        } else {
            tf.setChnl_no(FlowCodes.CHNL_CODES[Global.RANDOM.nextInt(FlowCodes.CHNL_CODES.length)]);
        }
        tf.setCt_flag(FlowCodes.CF_FLAGS[Global.RANDOM.nextInt(FlowCodes.CF_FLAGS.length)]);
        tf.setDc_flag(FlowCodes.DC_FLAGS[Global.RANDOM.nextInt(FlowCodes.DC_FLAGS.length)]);

        return tf;
    }

    public String generateIp(String custId) {
        long c = Long.parseLong(custId);
        long p1 = (c % 100) * 2 % 254;
        long p2 = (c / 100 % 100) * 2 % 254;
        long p3 = (c / 10000 % 100) * 2 % 254;
        long p4 = (c / 1000000 % 100) * 2 % 254;
        return String.format("%d.%d.%d.%d", p1 == 0 ? 1 : p1, p2, p3, p4 == 0 ? 1 : p4);
    }

    /**
     * 返回一个[-1, 1] 的数字，它们符合高斯分布<br>
     */
    public Double getNormalGaussian() {
        double doubleTemp;
        do {
            doubleTemp = Global.RANDOM.nextGaussian();
        } while (Math.abs(doubleTemp) > 3.0);

        return doubleTemp / 3;
    }

    public long getValueFromGauss(long min, long max) {
        return (long) ((getNormalGaussian() + 1) * (max - min) / 2) + min;
    }

    /**
     * 得到一个交易时间，它们符合高斯分布<br>
     */
    public long getTransTime() {
        return (long) ((getNormalGaussian() + 1) * Global.HALF_DAY_MILSECONDS) + start;
    }
}
