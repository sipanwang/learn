##简介
该工具基于Java开发，用于付费通国密SM3加密报文造数，包括数据生成与合并两类功能

###数据生成功能

####造数逻辑
- 帐号：在6210990000000001到6210991000000000之间生成
- 交易流水号：YYYYMMDD00000001到YYYYMMDD10000000之间顺序生成，其中YYYYMMDD与交易日期保持一致，每日10000000的流水
- 交易码中备注为“新一代电子银行”的交易，对应的渠道号为06或07
- 交易渠道为06或07的时候，随机生成设备编号和IP地址（设备编号/IP地址由CUST_ID决定）
- 所有交易流水按照交易成功处理

####数据分布
- 交易时间、交易金额、交易客户号、交易账号、交易对手账号、交易机构服从独立的正态分布
- 交易时间和流水号保持同序

####密集造数
- 支持指定特定维度进行密集造数。包括：交易客户号、交易账号、交易对手账号、设备ID、设备IP地址
- 密集造数的原理就是利用Java随机数原理，随机生成符合正态分布（高斯分布）的随机数据，通过if判断，在指定区间生成大量相同的数据。普通随机数不符合正态分布。

####数据格式(CSV)
- 交易流水号,交易日期,交易时间,交易金额,交易渠道,交易码,交易客户号,交易账号/卡号,交易机构,交易对手账号,现转标志,借贷标志,设备ID,设备IP地址

###数据合并功能
- 将多个时间有序的csv文本合并成为一个时间有序的csv文件。

##使用说明
####数据生成
修改主要配置项
> sc.work.mode, 设置为generate模式
<br/>
> sc.time.start, 数据的起始日期
<br/>
> sc.time.end, 数据的最晚日期
<br/>
> generator.record.perDay, 每天的数据量
<br/>
> generator.record.filepath, 数据生成的目录

```
java -jar sc-data-generator-1.0.0.jar
```
生成的文件将在filepath中，文件名为YYYY-MM-DD.csv，每个文件包含一天的数据，流水数量由perDay指定

####数据合并
修改主要配置项
> sc.work.mode, 设置为merge模式
<br/>
> merge.sourceFilepath, 需要被合并的原始文件目录
<br/>
> merge.targetFile, 合并后文件路径名

```
java -jar sc-data-generator-1.0.0.jar
```
合并的文件路径为targetFile

##配置解读
具体使用方式，可参考样例配置文件：application.yml
> generator.spec ,可配置的密集维度数据的维度列表
<br/>
> generator.data, 维度的取值范围

##工作原理
ScDataGeneratorApp为启动类，启动后在GeneratorTool类扫描@PostConstruct注解，执行run()和init()方法，之后调用SipwOperETL(i, tfCount)方法
这是自己定义的接口类，SipwOperETL继承了Runnable接口，会自动执行run()方法，run()里面定义了load()方法，load里面加载了SipwOperGenerator.next(long transTime)方法,
这个方法里面定义了随机数的生成方式，主要有Global.RANDOM.nextInt(),Global.RANDOM.nextDouble(),Global.RANDOM.nextGaussian();

1、RANDOM.nextInt(0,100)，随机生成均匀分布的在[0, 100)范围内的随机数，若想生成[1,100]的随机数应该怎么做，使用RANDOM.nextInt(0,100)+1即可。
2、RANDOM.nextDouble(0,100)同理是随机生成均匀分布的[0, 100)范围的double数
3、RANDOM.nextGaussian(0,,100)同理是生成满足符合正态分布的[0, 100)范围的随机数

此外我还写了一个GenerateUtils随机数生成工具类，包括随机生成姓名,性别,身份证号,邮箱,手机号,座机号,ip,经度,纬度,mac地址,通用号码，可自定义增加

SipwOperGenerator.next(long transTime)方法中对SipwOper类的属性进行了赋值，因为SipwOper类重写了toString()方法，因此set属性的时候便会自动封装成加签的报文。
SipwOper tf = new SipwOper();

数据生成完最后就是SipwOperETL的tfList.add(temp);放到数组里写入文件，end..
