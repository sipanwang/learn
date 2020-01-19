package generate.generator;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import generate.utils.GenerateUtils;

public class Global {
    public static long DAY_MILSECONDS = 24L * 3600 * 1000;

    public static long HALF_DAY_MILSECONDS = 12L * 3600 * 1000;

    public static final Random RANDOM = new Random();
    
    public static final GenerateUtils generateUtils = new GenerateUtils();

    public static double RATIO = 0.1;

    public static long AMT_MIN = 1;
    public static long AMT_MAX = 100000;

    public static long CACC_MIN = 6210990000000001L;
    public static long CACC_MAX = 6210990800000000L;
    public static long PEER_CC_MIN = 6210990300000001L;
    public static long PEER_CC_MAX = 6210991000000000L;
    public static long ORG_MIN = 1;
    public static long ORG_MAX = 9999;
    public static long CUST_ID_MIN = 0;
    public static long CUST_ID_MAX = 1000000;

    public static Map<String, Boolean> TXN_SPECCODES_MAP = new HashMap<String, Boolean>();
    public static String[] specTypes = { "ip", "dfp", "acc", "peer_acc", "custID", "bizChannel","bizCode","operCardType","respCode" };
    public static String[] SpecIps = null;
    public static String[] Specdfps = null;
    public static String[] SpecAcc = null;
    public static String[] SpecPeerAcc = null;
    public static String[] SpecCustId = null;
    public static String[] SpecBizChannel = null;
    public static String[] SpecBizCode = null;
    public static String[] SpecOperCardType = null;
    public static String[] SpecRespCode = null;
    public static String outFilePath = "./data/";
    public static int writeBath = 10;

    ///merge
    public static String sourceFilepath = null;
    public static String targetFile = "./data/target.merged";

    public static void setWriteBath(int batch) {
        writeBath = batch;
    }

    public static void setOutFilePath(String outFilePath) {
        Global.outFilePath = outFilePath;
    }

    public static void setSpecIps(String[] specIps) {
        SpecIps = specIps;
    }

    public static void setSpecdfps(String[] specdfps) {
        Specdfps = specdfps;
    }

    public static void setSpecAcc(String[] specAcc) {
        SpecAcc = specAcc;
    }

    public static void setSpecPeerAcc(String[] specPeerAcc) {
        SpecPeerAcc = specPeerAcc;
    }

    public static void setSpecCustId(String[] specCustId) {
        SpecCustId = specCustId;
    }	
    
    public static String[] getSpecBizChannel() {
		return SpecBizChannel;
	}

	public static void setSpecBizChannel(String[] specBizChannel) {
		Global.SpecBizChannel = specBizChannel;
	}

	public static String[] getSpecBizCode() {
		return SpecBizCode;
	}

	public static void setSpecBizCode(String[] specBizCode) {
		Global.SpecBizCode = specBizCode;
	}

	public static String[] getSpecOperCardType() {
		return SpecOperCardType;
	}

	public static void setSpecOperCardType(String[] specOperCardType) {
		Global.SpecOperCardType = specOperCardType;
	}

	public static String[] getSpecRespCode() {
		return SpecRespCode;
	}

	public static void setSpecRespCode(String[] specRespCode) {
		Global.SpecRespCode = specRespCode;
	}

	public static void setAmtMin(long amtMin) {
        Global.AMT_MIN = amtMin;
    }

    public static void setAmtMax(long amtMax) {
        Global.AMT_MAX = amtMax;
    }

    public static void setCaccMin(long caccMin) {
        CACC_MIN = caccMin;
    }

    public static void setCaccMax(long caccMax) {
        CACC_MAX = caccMax;
    }

    public static void setPeerCcMin(long peerCcMin) {
        PEER_CC_MIN = peerCcMin;
    }

    public static void setPeerCcMax(long peerCcMax) {
        PEER_CC_MAX = peerCcMax;
    }

    public static void setOrgMin(long orgMin) {
        ORG_MIN = orgMin;
    }

    public static void setOrgMax(long orgMax) {
        ORG_MAX = orgMax;
    }

    public static void setCustIdMin(long custIdMin) {
        CUST_ID_MIN = custIdMin;
    }

    public static void setCustIdMax(long custIdMax) {
        CUST_ID_MAX = custIdMax;
    }

    public static void setRatio(double ratio) {
        RATIO = ratio;
    }

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
}
