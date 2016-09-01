package com.tennisfolks.dao;

import java.util.List;

import javax.ejb.Remote;
import com.tennisfolks.entities.Language;

@Remote
public interface LanguageDAO {
	
	public Language findByCode(String code);
	
	public List<Language> findAll();

}
