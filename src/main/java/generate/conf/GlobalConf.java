package generate.conf;

import generate.generator.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class GlobalConf {

    private @Value("${generator.ratio: 0.1}")
    double ratio;

    private @Value("${generator.data.amt.min: 1}")
    long amtMin;

    private @Value("${generator.data.amt.max: 10000}")
    long amtMax;

    private @Value("${generator.data.cacc.min: 6210990000000001}")
    long caccMin;

    private @Value("${generator.data.cacc.max: 6210990800000000}")
    long caccMax;

    private @Value("${generator.data.peerCc.min: 6210990300000001}")
    long pccMin;

    private @Value("${generator.data.peerCc.max: 6210991000000000}")
    long pccMax;

    private @Value("${generator.data.org.min: 1}")
    long orgMin;

    private @Value("${generator.data.org.max: 9999}")
    long orgMax;

    private @Value("${generator.data.custID.min: 0}")
    long cidMin;

    private @Value("${generator.data.custID.max: 10000000}")
    long cidMax;

    private @Value("${generator.spec.ip:}")
    String specIps;

    private @Value("${generator.spec.dfp:}")
    String specDfps;

    private @Value("${generator.spec.acc:}")
    String specAcc;

    private @Value("${generator.spec.peer_acc:}")
    String specPeerAcc;

    private @Value("${generator.spec.cust_id:}")
    String specCustID;
    
    private @Value("${generator.spec.bizChannel:}")
    String specBizChannel;
    
    private @Value("${generator.spec.bizCode:}")
    String specBizCode;
    
    private @Value("${generator.spec.operCardType:}")
    String specOperCardType;
    
    private @Value("${generator.spec.respCode:}")
    String specRespCode;

    private @Value("${generator.record.filepath}")
    String outFilePath;

    private @Value("${merge.sourceFilepath}")
    String sourceFilepath;

    private @Value("${merge.targetFile}")
    String targetFile;

    @PostConstruct
    public void init() {
        //set spec dem
        if (!StringUtils.isEmpty(specIps)) {
            Global.setSpecIps(specIps.split(","));
        }
        if (!StringUtils.isEmpty(specDfps)) {
            Global.setSpecdfps(specDfps.split(","));
        }
        if (!StringUtils.isEmpty(specAcc)) {
            Global.setSpecAcc(specAcc.split(","));
        }
        if (!StringUtils.isEmpty(specPeerAcc)) {
            Global.setSpecPeerAcc(specPeerAcc.split(","));
        }
        if (!StringUtils.isEmpty(specCustID)) {
            Global.setSpecCustId(specCustID.split(","));
        }
        if (!StringUtils.isEmpty(specBizChannel)) {
            Global.setSpecBizChannel(specBizChannel.split(","));
        }
        if (!StringUtils.isEmpty(specBizCode)) {
            Global.setSpecBizCode(specBizCode.split(","));
        }
        if (!StringUtils.isEmpty(specOperCardType)) {
            Global.setSpecOperCardType(specOperCardType.split(","));
        }
        if (!StringUtils.isEmpty(specRespCode)) {
            Global.setSpecRespCode(specRespCode.split(","));
        }
        Global.setRatio(ratio);
        Global.setAmtMin(amtMin);
        Global.setAmtMax(amtMax);
        Global.setCaccMax(caccMax);
        Global.setCaccMin(caccMin);
        Global.setPeerCcMax(pccMax);
        Global.setPeerCcMin(pccMin);
        Global.setOrgMax(orgMax);
        Global.setOrgMin(orgMin);
        Global.setCustIdMax(cidMax);
        Global.setCustIdMin(cidMin);
        File dirs = new File(outFilePath);

        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        Global.setOutFilePath(outFilePath);
        //set codes map
        for (String s : FlowCodes.SPEC_CODES) {
            Global.TXN_SPECCODES_MAP.put(s, true);
        }
        if (!StringUtils.isEmpty(sourceFilepath)) {
            Global.sourceFilepath = sourceFilepath;
        }
        Global.targetFile = targetFile;
    }
}
