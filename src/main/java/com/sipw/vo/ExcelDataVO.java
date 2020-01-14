package com.sipw.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Author: sipw
 * Date: 2019-12-26
 * Description: 读取Excel时，封装读取的每一行的数据，vo为checklist对象
 */
//@Repository

//定义单例模式的bean
@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
public class ExcelDataVO {

    //核查单id
    private Integer id;
    //渠道
    private String channels;
    //流水号
    private String operId;
    //主体号 (操作 / 支付方)
    private String payCustId;
    //主体类型
    private String payCustType;
    //主体名称
    private String payCustName;
    //风险级别
    private Integer riskLevel;
    //风险类型
    private String riskType;
    //业务类型
    private String bizCode;
    //交易金额
    private Long transVol;
    //收款方主体号
    private String recCustId;
    //收款方类型
    private String recCustType;
    //收款方名称
    private String recCustName;
    //操作 (交易) 时间
    private Date transTime;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //验证策略
    private String verifyStrategy;
    //名单策略
    private String namelistStrategy;
    //规则代码
    private String ruleCode;
    //运营机构
    private String operOrg;
    //处理机构
    private String handleOrg;
    //处理结果
    private String riskQualitative;
    //核查状态
    private String status;
    //受理人员
    private String operUser;
    //来源
    private String source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getPayCustId() {
        return payCustId;
    }

    public void setPayCustId(String payCustId) {
        this.payCustId = payCustId;
    }

    public String getPayCustType() {
        return payCustType;
    }

    public void setPayCustType(String payCustType) {
        this.payCustType = payCustType;
    }

    public String getPayCustName() {
        return payCustName;
    }

    public void setPayCustName(String payCustName) {
        this.payCustName = payCustName;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Long getTransVol() {
        return transVol;
    }

    public void setTransVol(Long transVol) {
        this.transVol = transVol;
    }

    public String getRecCustId() {
        return recCustId;
    }

    public void setRecCustId(String recCustId) {
        this.recCustId = recCustId;
    }

    public String getRecCustType() {
        return recCustType;
    }

    public void setRecCustType(String recCustType) {
        this.recCustType = recCustType;
    }

    public String getRecCustName() {
        return recCustName;
    }

    public void setRecCustName(String recCustName) {
        this.recCustName = recCustName;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVerifyStrategy() {
        return verifyStrategy;
    }

    public void setVerifyStrategy(String verifyStrategy) {
        this.verifyStrategy = verifyStrategy;
    }

    public String getNamelistStrategy() {
        return namelistStrategy;
    }

    public void setNamelistStrategy(String namelistStrategy) {
        this.namelistStrategy = namelistStrategy;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getOperOrg() {
        return operOrg;
    }

    public void setOperOrg(String operOrg) {
        this.operOrg = operOrg;
    }

    public String getHandleOrg() {
        return handleOrg;
    }

    public void setHandleOrg(String handleOrg) {
        this.handleOrg = handleOrg;
    }

    public String getRiskQualitative() {
        return riskQualitative;
    }

    public void setRiskQualitative(String riskQualitative) {
        this.riskQualitative = riskQualitative;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
