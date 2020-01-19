package generate.pojo;

import com.alibaba.fastjson.JSON;
import generate.utils.Sm3Utils;

import java.util.Map;
import java.util.TreeMap;

public class SipwOper implements Comparable<SipwOper> {
	
	//付费通oper表字段
	private long serialId;
	private String bizChannel;
	private String bizCode;
	private String operTime;
	private String operCardNo;
	private String operCardType;
	private String operCardBin;
	private long transAmount;
	private String colMerchantId;
	private String scanType;
	private String terminalNo;
	private String payType;
	private String operStatus;
	private String respCode;
	private String operCustId;
	private String operCustType;
	private String colCustId;
	private String colCustType;

	// 默认TransFlow字段
	private long mx_sn;
	private String txn_dt;
	private String txn_tm;
	private long amt;
	private String chnl_no;
	private String txn_code;
	private String cust_id;
	private String cacc;
	private String org;
	private String peer_acc;
	private int ct_flag;
	private int dc_flag;
	private String dfp;
	private String ip;
	
	//自定义字段
	private String operName;
	private String operSex;
	private String operEmail;
	private String operTelephone;
	private String operLandline;
	private String operLon;
	private String operLat;
	private String operMac;
	private String operCommonNO;

	public static SipwOper parse(String str) {
		String[] conArr = str.split(",");
		if (conArr.length == 13) {
			SipwOper tf = new SipwOper();
			tf.setMx_sn(Long.parseLong(conArr[0]));
			tf.setTxn_dt(conArr[1]);
			tf.setTxn_tm(conArr[2]);
			tf.setAmt(Integer.parseInt(conArr[3]));
			tf.setChnl_no(conArr[4]);
			tf.setCust_id(conArr[6]);
			return tf;
		}
		return null;
	}

	@Override
	public String toString() {
		// StringBuilder sb = new StringBuilder();
		// sb.append(getMx_sn()).append(",").append(getTxn_dt()).append(",").append(getTxn_tm()).append(",")
		// .append(getAmt()).append(",").append(getChnl_no()).append(",").append(getTxn_code()).append(",")
		// .append(cust_id).append(",").append(cacc).append(",").append(org).append(",").append(peer_acc).append(",")
		// .append(ct_flag).append(",").append(dc_flag).append(",").append(dfp).append(",").append(ip);
		// return sb.toString();

		Map<Object, Object> map = new TreeMap<Object, Object>();
		// body的封装
		Map<Object, Object> bodyMap = new TreeMap<Object, Object>();

		bodyMap.put("bizChannel", getBizChannel());
		bodyMap.put("bizCode", getBizCode());
		bodyMap.put("serialId", getSerialId());
		bodyMap.put("operTime", getOperTime());
		bodyMap.put("operCardNo", getOperCardNo());
		bodyMap.put("operCardType", getOperCardType());
		bodyMap.put("transAmount", getTransAmount());
		bodyMap.put("colMerchantId", getColMerchantId());
		bodyMap.put("terminalNo", getTerminalNo());
		bodyMap.put("operStatus", getOperStatus());
		bodyMap.put("respCode", getRespCode());
		bodyMap.put("operCustId", getOperCustId());
		bodyMap.put("operCustType", getOperCustType());
		bodyMap.put("colCustId", getColCustId());
		bodyMap.put("colCustType", getColCustType());
		
		//自定义测试字段begin
		bodyMap.put("ip", getIp());
		bodyMap.put("operName", getOperName());
		bodyMap.put("operSex", getOperSex());
		bodyMap.put("operEmail", getOperEmail());
		bodyMap.put("operTelephone", getOperTelephone());
		bodyMap.put("operLandLline", getOperLandline());
		bodyMap.put("operLon", getOperLon());
		bodyMap.put("operLat", getOperLat());
		bodyMap.put("operMac", getOperMac());
		bodyMap.put("operCommonNo", getOperCommonNO());
		//end
        
		bodyMap.put("key", "fftbs31415");

		String bodyValue = JSON.toJSONString(bodyMap);
		// 加签
		String signature = Sm3Utils.encrypt(bodyValue);
		bodyMap.remove("key");
		String bodyStr = JSON.toJSONString(bodyMap);
		// header的封装
		Map<Object, Object> headerMap = new TreeMap<Object, Object>();
		headerMap.put("signType", "sm3");
		headerMap.put("timestamp", System.currentTimeMillis());
		headerMap.put("sign", signature);
		map.put("header", headerMap);
		map.put("body", bodyStr);
		String json = JSON.toJSONString(map);

		return json;
	}

	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(SipwOper o) {
		if (this.getSerialId() > o.getSerialId()) {
			return 1;
		}
		return this.getSerialId() == o.getSerialId() ? 0 : -1;
	}

	public long getSerialId() {
		return serialId;
	}

	public void setSerialId(long serialId) {
		this.serialId = serialId;
	}

	public String getBizChannel() {
		return bizChannel;
	}

	public void setBizChannel(String bizChannel) {
		this.bizChannel = bizChannel;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOperCardNo() {
		return operCardNo;
	}

	public void setOperCardNo(String operCardNo) {
		this.operCardNo = operCardNo;
	}

	public String getOperCardType() {
		return operCardType;
	}

	public void setOperCardType(String operCardType) {
		this.operCardType = operCardType;
	}

	public String getOperCardBin() {
		return operCardBin;
	}

	public void setOperCardBin(String operCardBin) {
		this.operCardBin = operCardBin;
	}

	public long getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(long transAmount) {
		this.transAmount = transAmount;
	}

	public String getColMerchantId() {
		return colMerchantId;
	}

	public void setColMerchantId(String colMerchantId) {
		this.colMerchantId = colMerchantId;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getOperCustId() {
		return operCustId;
	}

	public void setOperCustId(String operCustId) {
		this.operCustId = operCustId;
	}

	public String getOperCustType() {
		return operCustType;
	}

	public void setOperCustType(String operCustType) {
		this.operCustType = operCustType;
	}

	public String getColCustId() {
		return colCustId;
	}

	public void setColCustId(String colCustId) {
		this.colCustId = colCustId;
	}

	public String getColCustType() {
		return colCustType;
	}

	public void setColCustType(String colCustType) {
		this.colCustType = colCustType;
	}

	public long getMx_sn() {
		return mx_sn;
	}

	public void setMx_sn(long mx_sn) {
		this.mx_sn = mx_sn;
	}

	public String getTxn_dt() {
		return txn_dt;
	}

	public void setTxn_dt(String txn_dt) {
		this.txn_dt = txn_dt;
	}

	public String getTxn_tm() {
		return txn_tm;
	}

	public void setTxn_tm(String txn_tm) {
		this.txn_tm = txn_tm;
	}

	public long getAmt() {
		return amt;
	}

	public void setAmt(long amt) {
		this.amt = amt;
	}

	public String getChnl_no() {
		return chnl_no;
	}

	public void setChnl_no(String chnl_no) {
		this.chnl_no = chnl_no;
	}

	public String getTxn_code() {
		return txn_code;
	}

	public void setTxn_code(String txn_code) {
		this.txn_code = txn_code;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCacc() {
		return cacc;
	}

	public void setCacc(String cacc) {
		this.cacc = cacc;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getPeer_acc() {
		return peer_acc;
	}

	public void setPeer_acc(String peer_acc) {
		this.peer_acc = peer_acc;
	}

	public int getCt_flag() {
		return ct_flag;
	}

	public void setCt_flag(int ct_flag) {
		this.ct_flag = ct_flag;
	}

	public int getDc_flag() {
		return dc_flag;
	}

	public void setDc_flag(int dc_flag) {
		this.dc_flag = dc_flag;
	}

	public String getDfp() {
		return dfp;
	}

	public void setDfp(String dfp) {
		this.dfp = dfp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperSex() {
		return operSex;
	}

	public void setOperSex(String operSex) {
		this.operSex = operSex;
	}

	public String getOperEmail() {
		return operEmail;
	}

	public void setOperEmail(String operEmail) {
		this.operEmail = operEmail;
	}

	public String getOperTelephone() {
		return operTelephone;
	}

	public void setOperTelephone(String operTelephone) {
		this.operTelephone = operTelephone;
	}

	public String getOperLandline() {
		return operLandline;
	}

	public void setOperLandline(String operLandline) {
		this.operLandline = operLandline;
	}

	public String getOperLon() {
		return operLon;
	}

	public void setOperLon(String operLon) {
		this.operLon = operLon;
	}

	public String getOperLat() {
		return operLat;
	}

	public void setOperLat(String operLat) {
		this.operLat = operLat;
	}

	public String getOperMac() {
		return operMac;
	}

	public void setOperMac(String operMac) {
		this.operMac = operMac;
	}

	public String getOperCommonNO() {
		return operCommonNO;
	}

	public void setOperCommonNO(String operCommonNO) {
		this.operCommonNO = operCommonNO;
	}
	
	
}
