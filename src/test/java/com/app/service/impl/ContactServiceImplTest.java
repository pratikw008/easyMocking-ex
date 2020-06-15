package com.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.app.custom.exception.NoDataFoundException;
import com.app.dao.ContactDao;
import com.app.model.Contact;

public class ContactServiceImplTest {
	
	private static ContactServiceImpl serviceImpl = null;
	
	private static Contact actualContact = null;
	
	@BeforeClass
	public static void init() {
		
		actualContact = Contact.builder().contactId(101).contactName("ABC").contactNumber(12345678).build();
		
		//Create Mock Object For ContactDao impl
		ContactDao contactDao = EasyMock.createMock(ContactDao.class);
		
		//Create Behaviour for findById method which is called by findContactById_01 method
		EasyMock.expect(contactDao.findById(101))
				.andReturn(actualContact);
		
		//Create Behaviour for findById method which is called by findContactById_02 method
		EasyMock.expect(contactDao.findById(102)).andReturn(null);
		
		List<String> names = Arrays.asList("A","B","C","D","E");
		
		//Create Behaviour for findByContactNames method called by testGetAllContactNames_01 method
		EasyMock.expect(contactDao.findByContactName()).andReturn(names).andThrow(new NoDataFoundException());

		//Create Behaviour for findByContactNames method called by testGetAllContactNames_02 method
		//EasyMock.expect(contactDao.findByContactName()).andReturn(null);
		
		//Activate Behaviour
		EasyMock.replay(contactDao);
		
		//Create Service Object To Test
		serviceImpl = new ContactServiceImpl(contactDao);
	}
	
	@Test
	@Ignore
	public void findContactById_01() {
		Contact expectedContact = serviceImpl.findContactById(101);
		
		assertEquals(expectedContact, actualContact);
	}
	
	@Test(expected = NoDataFoundException.class)
	public void findContactById_02() {
		//Only call this method coz we mention in @Test that we are expecting Exception
		serviceImpl.findContactById(102);
		
		//Contact expectedContact = serviceImpl.findContactById(102);
		//assertNull(expectedContact);
	}
	
	@Test
	//@Ignore
	public void testGetAllContactNames_01(){
		List<String> names = serviceImpl.getAllContactNames();
		assertNotNull(names);
	}
	
	@Test(expected = NoDataFoundException.class)
	public void testGetAllContactNames_02() {
		serviceImpl.getAllContactNames();
	}
}
