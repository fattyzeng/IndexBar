package com.mystudy.ui;

import java.io.Serializable;

/**
 * this is a model class for descripting person infos
 * and it implements serializable and comparable
 * @author Administrator
 *
 */
public class Person implements Serializable,Comparable<Person>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the person's name
	 */
	private String name;
	/**
	 * the person's pinyin
	 */
	private String pinYin;
	/**
	 * get person's  name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * set person's name  
	 * @param name set person's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get pinyin character
	 * @return  pinyin character
	 */
	public String getPinYin() {
		return pinYin;
	}
	/**
	 * set pinyin character
	 * @param pinYin  the parameter is  pinyin character 
	 */
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	/**
	 * the function that beyond pinyin comparation 
	 */
	@Override
	public int compareTo(Person another) {
		return this.pinYin.compareTo(another.pinYin);
	}
	
	
}
