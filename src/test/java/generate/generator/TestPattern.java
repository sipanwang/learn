package generate.generator;

import java.util.regex.Pattern;

/*
 * 正则表达式测试类
 * date:2019/11/19
 * */
public class TestPattern {
	public static void main(String[] args) {
		String content = "(10.23,12.38)";
		//验证经纬度
		String pattern = "^([(]{1}([0-9]+\\.?[0-9]+|[0-9]+\\.?[0-9]*),([0-9]+\\.?[0-9]+|[0-9]+\\.?[0-9]*)[)]){1}$";
		boolean isMatch = Pattern.matches(pattern, content);
		String a = "3131sasfasd".replaceAll("\\d{2}", "Z");
	    String b = "3131sasfasd".replaceAll("^\\d{2}", "Z");//仅替换字符串开头的两个数字
		System.out.println(isMatch);
		System.out.println(a);
		System.out.println(b);
	}
}
