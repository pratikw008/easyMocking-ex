package com.app.service;

import java.util.List;

import com.app.model.Contact;

public interface IContactService {
	
	public Contact findContactById(Integer id);
	
	public List<String> getAllContactNames();
}
