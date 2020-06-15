package com.app.dao;

import java.util.List;

import com.app.model.Contact;

public interface ContactDao {
	
	public Contact findById(Integer id);
	
	public List<String> findByContactName();
}
