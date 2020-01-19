package generate.utils;

import java.math.BigDecimal;
import java.util.Random;

import generate.generator.Global;

/**
 * 
 * @author sipw 
 * 说明：随机数生成工具类，包括随机生成姓名,性别,身份证号,邮箱,手机号,座机号,ip,经度,纬度,mac地址,通用号码，可自定义增加
 *
 */
public class GenerateUtils {
	
	//邮箱枚举值
	public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	//姓氏枚举值
	public static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
	//女名枚举值
	public static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
	//男名枚举值
	public static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
	//邮箱后缀枚举值
	public static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn"
			.split(",");
	//手机号前缀枚举值
	public static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
			.split(",");
	//座机号前缀枚举值
	public static String[] landlineFirst = "6666,6800,6882,7277,7777,7853,8675,8888,8957"
			.split(",");
	public static int sex = getNum(0,1);
	public static String name_sex = "";
	
	
	
	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/***
	 * 
	 * 随机生成Email
	 * @param lMin
	 *            最小长度4
	 * @param lMax
	 *            最大长度15
	 */
	public static String getEmail() {
		int length = getNum(4,15);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = (int) (Math.random() * base.length());
			sb.append(base.charAt(number));
		}
		sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
		return sb.toString();
	}



	/***
	 * 
	 * 随机生成手机号码
	 */
	public static String getTelephone() {
		int index = getNum(0, telFirst.length - 1);
		String first = telFirst[index];
		String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
		String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
		return first + second + thrid;
	}

	/***
	 * 
	 * 随机生成8位电话号码
	 */
	public static String getLandline() {
		int index = getNum(0, landlineFirst.length - 1);
		String first = landlineFirst[index];
		String second = String.valueOf(getNum(0, 9000) + 1000);
		return first + second;
	}

	/***
	 * 
	 * 随机生成中文姓名
	 */
	public static String getChineseName() {
		int index = getNum(0, firstName.length() - 1);
		String first = firstName.substring(index, index + 1);
		String str = "";
		int length = boy.length();
		if (sex == 0) {
			str = girl;
			length = str.length();
		} else {
			str = boy;
			length = str.length();
		}
		index = getNum(0, length - 1);
		String second = str.substring(index, index + 1);
		int hasThird = getNum(0, 1);
		String third = "";
		if (hasThird == 1) {
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;
	}
	
	/***
	 * 
	 * 对应性别
	 */
	public static String getSex(){
		if (sex == 0) {
			name_sex = "女";
		} else {
			name_sex = "男";
		}
		return name_sex;
	}

	// 随机生成ip算法
	public static String getRandomIp() {
		// ip范围
		int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
				{ 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
				{ 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
				{ 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
				{ 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
				{ -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
				{ -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
				{ -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
				{ -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
				{ -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
		};

		int index = Global.RANDOM.nextInt(10);
		String ip = generateIp(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
		return ip;
	}

	public static String generateIp(int ip) {
		int[] b = new int[4];
		String x = "";

		b[0] = (int) ((ip >> 24) & 0xff);
		b[1] = (int) ((ip >> 16) & 0xff);
		b[2] = (int) ((ip >> 8) & 0xff);
		b[3] = (int) (ip & 0xff);
		x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "."
				+ Integer.toString(b[3]);

		return x;
	}
	
/**
 * 随机生成经纬度
 * @param MinLon 112.1212
 * @param MaxLon 134.1212
 * @param MinLat 43.12
 * @param MaxLat 65.123
 * @return
 */
	//经度
    public static String getRandomLon() {
    	double MinLon = 112.1212;
    	double MaxLon = 134.1212;
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
        return lon;
    }
    //纬度
    public static String getRandomLat() {
    	double MinLat = 43.12;
    	double MaxLat = 65.123; 
        BigDecimal db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        return lat;
    }
    
    /**
     * 随机生成mac地址
     */
    private static String SEPARATOR_OF_MAC = ":";
    
    public static String getRandomMac() {
        Random random = new Random();
        String[] mac = {String.format("%02x", 0x52), String.format("%02x", 0x54), String.format("%02x", 0x00), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff)), String.format("%02x", random.nextInt(0xff))};
        return String.join(SEPARATOR_OF_MAC, mac);
    }

    /***
	 * 
	 * 随机生成通用号码
	 */
	public static String getCommonNo() {
		String comno1 = String.valueOf(getNum(1, 89999999) + 10000000);
		String comno2 = String.valueOf(getNum(1, 89999999) + 10000000);
		return comno1+comno2;
	}
	public static void main(String[] args) {
		//身份证生成类,建议用通用号码生成
		IdCardRandom idm = new IdCardRandom();
		//生成姓名
		System.out.println(getChineseName());
		//生成性别
		System.out.println(getSex());
		//生成身份证号
		System.out.println(idm.generate());
		//生成邮箱
		System.out.println(getEmail());
		//生成手机号
		System.out.println(getTelephone());
		//生成座机号
		System.out.println(getLandline());
		//生成ip
		System.out.println(getRandomIp());
		//生成经度
		System.out.println(getRandomLon());
		//生成纬度
		System.out.println(getRandomLat());
		//生成mac地址
		System.out.println(getRandomMac());
		//生成通用号码
		System.out.println(getCommonNo());
		
		System.out.println(String.format("%s%08d%s", 1232132, 77777,546445));
	}
}
