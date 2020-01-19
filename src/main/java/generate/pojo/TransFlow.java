package generate.pojo;

public class TransFlow implements Comparable<TransFlow> {

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

    public static TransFlow parse(String str) {
        String[] conArr = str.split(",");
        if (conArr.length == 13) {
            TransFlow tf = new TransFlow();
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

    public boolean hasNullFild() {
        return this.getCacc() == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMx_sn()).append(",").append(getTxn_dt()).append(",").append(getTxn_tm()).append(",")
            .append(getAmt()).append(",").append(getChnl_no()).append(",").append(getTxn_code()).append(",")
            .append(cust_id).append(",").append(cacc).append(",").append(org).append(",").append(peer_acc).append(",")
            .append(ct_flag).append(",").append(dc_flag).append(",").append(dfp).append(",").append(ip);
        return sb.toString();
    }

    /*
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TransFlow o) {
        if (this.getMx_sn() > o.getMx_sn()) {
            return 1;
        }
        return this.getMx_sn() == o.getMx_sn() ? 0 : -1;
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
}
