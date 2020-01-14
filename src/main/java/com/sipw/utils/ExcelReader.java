package com.sipw.utils;

import cn.com.bsfit.frms.utils.EncodesUtils;
import com.sipw.vo.ExcelDataVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 10:21
 * Description: 读取Excel内容
 */
@Component
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
public class ExcelReader {

    private static String secretKey;

    private static Logger logger = Logger.getLogger(ExcelReader.class.getName()); // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileType 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<ExcelDataVO> readExcel(String fileName) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // 获取Excel文件
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                logger.warning("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<ExcelDataVO> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 读取Excel文件内容
     * @param file 上传的Excel文件
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<ExcelDataVO> readExcel(MultipartFile file) {

        Workbook workbook = null;

        try {
            // 获取Excel后缀名
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty() || fileName.lastIndexOf(".") < 0) {
                logger.warning("解析Excel失败，因为获取到的Excel文件名非法！");
                return null;
            }
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

            // 获取Excel工作簿
            workbook = getWorkbook(file.getInputStream(), fileType);

            // 读取excel中的数据
            List<ExcelDataVO> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + file.getOriginalFilename() + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }


    /**
     * 解析Excel数据
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<ExcelDataVO> parseExcel(Workbook workbook) {
       List<ExcelDataVO> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warning("读取第一行标题栏！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                ExcelDataVO resultData = convertRowToData(row);
                if (null == resultData) {
                    logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }

    /**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     *
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static ExcelDataVO convertRowToData(Row row) {
        ExcelDataVO resultData = new ExcelDataVO();

        Cell cell;
        int cellNum = 0;

        // 获取id
        cell = row.getCell(cellNum++);
        String id = convertCellValueToString(cell);
        resultData.setId(Integer.parseInt(id));

        // 获取渠道
        cell = row.getCell(cellNum++);
        String channels = convertCellValueToString(cell);

        // 获取流水号
        cell = row.getCell(cellNum++);
        String operId = convertCellValueToString(cell);
        resultData.setOperId(operId);

        // 获取主体号 (操作 / 支付方)>需要解密
        cell = row.getCell(cellNum++);
        String payCustId = convertCellValueToString(cell);
        resultData.setPayCustId(decrypt(payCustId));

        //获取主体类型
        cell = row.getCell(cellNum++);
        String payCustType = convertCellValueToString(cell);
        resultData.setPayCustType(payCustType);

        //获取主体名称
        cell = row.getCell(cellNum++);
        String payCustName = convertCellValueToString(cell);
        resultData.setPayCustName(payCustName);

        //获取风险级别
        cell = row.getCell(cellNum++);
        String riskLevel = convertCellValueToString(cell);
        resultData.setRiskLevel(Integer.parseInt(riskLevel));

        //获取风险类型
        cell = row.getCell(cellNum++);
        String riskType = convertCellValueToString(cell);
        resultData.setRiskType(riskType);

        //获取业务类型
        cell = row.getCell(cellNum++);
        String bizCode = convertCellValueToString(cell);
        resultData.setBizCode(bizCode);

        //获取交易金额
        cell = row.getCell(cellNum++);
        String transVol = convertCellValueToString(cell);
        resultData.setTransVol(Long.parseLong(transVol));

        //获取收款方主体号>需要解密
        cell = row.getCell(cellNum++);
        String recCustId = convertCellValueToString(cell);
        resultData.setRecCustId(decrypt(recCustId));

        //获取收款方类型
        cell = row.getCell(cellNum++);
        String recCustType = convertCellValueToString(cell);
        resultData.setRecCustType(recCustType);

        //获取收款方名称>需要解密
        cell = row.getCell(cellNum++);
        String recCustName = convertCellValueToString(cell);
        resultData.setRecCustName(decrypt(recCustName));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //获取操作 (交易) 时间
        cell = row.getCell(cellNum++);
        String transTime = convertCellValueToString(cell);
        try {
            resultData.setTransTime(sdf.parse(transTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取创建时间
        cell = row.getCell(cellNum++);
        String createTime = convertCellValueToString(cell);
        try {
            resultData.setCreateTime(sdf.parse(createTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取更新时间
        cell = row.getCell(cellNum++);
        String updateTime = convertCellValueToString(cell);
        try {
            resultData.setUpdateTime(sdf.parse(updateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取验证策略
        cell = row.getCell(cellNum++);
        String verifyStrategy = convertCellValueToString(cell);
        resultData.setVerifyStrategy(verifyStrategy);

        //获取名单策略
        cell = row.getCell(cellNum++);
        String namelistStrategy = convertCellValueToString(cell);
        resultData.setNamelistStrategy(namelistStrategy);

        //获取规则代码
        cell = row.getCell(cellNum++);
        String ruleCode = convertCellValueToString(cell);
        resultData.setRuleCode(ruleCode);

        //获取运营机构
        cell = row.getCell(cellNum++);
        String operOrg = convertCellValueToString(cell);
        resultData.setOperOrg(operOrg);

        //获取处理机构
        cell = row.getCell(cellNum++);
        String handleOrg = convertCellValueToString(cell);
        resultData.setHandleOrg(handleOrg);

        //获取处理结果
        cell = row.getCell(cellNum++);
        String riskQualitative = convertCellValueToString(cell);
        resultData.setRiskQualitative(riskQualitative);

        //获取核查状态
        cell = row.getCell(cellNum++);
        String status = convertCellValueToString(cell);
        resultData.setStatus(status);

        //获取受理人员
        cell = row.getCell(cellNum++);
        String operUser = convertCellValueToString(cell);
        resultData.setOperUser(operUser);

        //获取来源
        cell = row.getCell(cellNum++);
        String source = convertCellValueToString(cell);
        resultData.setSource(source);

        return resultData;
    }

    //加密
    public String encrypt(String source) {
        return EncodesUtils.encodeBase64(EncodesUtils.aesEncrypt(source.getBytes(Charset.forName("UTF-8")), EncodesUtils.decodeBase64(secretKey)));
    }

    //解密
    public static String decrypt(String source) {

        return EncodesUtils.decrypt(source, secretKey);
    }

    //给静态变量注入@value,生成set方法后需删除static
   @Value("${poi.excel.secretKey}")
    public void setSecretKey(String secretKey) {
        ExcelReader.secretKey = secretKey;
    }
}
