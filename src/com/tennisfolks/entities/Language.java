package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Language
 *
 */
@Entity
@Table(name = "language")
public class Language implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;
	
	private String name;

	public Language() {
		super();
	}

	public Language(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
