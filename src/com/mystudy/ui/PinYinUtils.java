package com.mystudy.ui;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * this is a tool class that get person's name pinyin character
 * @author Administrator
 *
 */
public class PinYinUtils {

	/**
	 * this is a function that get person's name hanyu pinyin through pinyin4j.jar
	 * @param inputString the input parameter  of person's name  
	 * @return
	 */
	public static String getChinesePinYin(String inputString) {
		HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		StringBuilder sb=new StringBuilder();
        char[] chars=inputString.toCharArray();
        for (char c : chars) {
			if (Character.isWhitespace(c)) {
				continue;
			}
			if (c>-127&&c<=128) {
				sb.append(c);
			}else {
				try {
					 String  s=PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					 sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
			
		}
		return sb.toString();
	}
}
