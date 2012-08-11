package org.uji.agile.contactsbook.tests.integration;

import org.junit.Before;
import org.junit.Test;
import org.uji.agile.contactsbook.ContactsBook;
import org.uji.agile.contactsbook.Phone;
import org.uji.agile.contactsbook.PhoneService;
import org.uji.agile.contactsbook.PhoneValidator;
import static org.mockito.Mockito.*;

public class ContactsBookTest {

	private PhoneService mockPhoneService;
	private PhoneValidator mockPhoneValidator;
	
	@Before
	public void setUp() {
		mockPhoneService = mock(PhoneService.class);
		ContactsBook.setPhoneService(mockPhoneService);
		
		mockPhoneValidator = mock(PhoneValidator.class);
		ContactsBook.setPhoneValidator(mockPhoneValidator);
		
		when(mockPhoneValidator.validate(Phone.create("606912312"))).thenReturn(true);
		
	}
	
	@Test
	public void callIsAbleToCallUsingAPhoneCallerService() {
		ContactsBook.call("606912312");
		verify(mockPhoneService).call(Phone.create("606912312"));
	}
	
	@Test
	public void callValidatesThePhoneNumber() {
		ContactsBook.call("606912312");
		verify(mockPhoneValidator).validate(Phone.create("606912312"));
	}
	
	@Test
	public void callDoesntExecuteTheCallMethodOnPhoneServiceWhenThePhoneNumberIsNotValid() {
		String phonenumber = "123412332"; 
		ContactsBook.call(phonenumber);
		verify(mockPhoneValidator).validate(Phone.create(phonenumber));
		verify(mockPhoneService, never()).call(Phone.create(phonenumber));
	}
	
}
