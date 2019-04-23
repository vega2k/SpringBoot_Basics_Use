package com.vega2k;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * application.properties 파일내에 똑같은 key값을 가진 property가 많은 경우에
 * 아래의 클래스와 같이 Properties 클래스를 작성할 수 있다.
 * @ConfigurationProperties 어노테이션을 사용할 때는 
 * spring-boot-configuration-processor를 의존관계를 설정하는 것을 권장
 */

@Component
@ConfigurationProperties("vega2k")
public class Vega2kProperties {
	private String name;
	private int age;
	private String fullName;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
