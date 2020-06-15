package com.app.service.impl;

import java.util.List;

import com.app.custom.exception.NoDataFoundException;
import com.app.dao.ContactDao;
import com.app.model.Contact;
import com.app.service.IContactService;

public class ContactServiceImpl implements IContactService {
	
	private ContactDao contactDao;
	
	public ContactServiceImpl(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Override
	public Contact findContactById(Integer id) {
		Contact contactFromDb = contactDao.findById(id);
		if(contactFromDb == null) {
			throw new NoDataFoundException("No Contact Found");
		}
		return contactFromDb;
	}
	
	@Override
	public List<String> getAllContactNames() {
		List<String> names = contactDao.findByContactName();
		if(names.isEmpty()) {
			throw new NoDataFoundException("List is Empty");
		}
		return names;
	}
}
