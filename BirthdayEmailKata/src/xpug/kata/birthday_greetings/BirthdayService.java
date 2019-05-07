package xpug.kata.birthday_greetings;

import java.io.IOException;
import java.text.ParseException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {
	int numberOfGreetingsSent;
	EmployeeRepository repository;
	EmailService emailService;

	public BirthdayService(EmployeeRepository repository, EmailService emailService) {
		this.repository = repository;
		this.emailService = emailService;
	}

	public void sendGreetings(OurDate ourDate) throws AddressException, MessagingException, IOException, ParseException {
		numberOfGreetingsSent = 0;
		for (Employee employee : this.repository.getAllEmployee()) {
			if (employee.isBirthday(ourDate)) {
				this.emailService.sendMessage("sender@here.com", employee);
				numberOfGreetingsSent++;
			}
		}
		
	}

	public static void main(String[] args) {
		EmployeeRepository repository = new FileEmployeeRepository("employee_data.txt");
		EmailService mailService = new SMTPMailService("localhost", 25); 
		BirthdayService service = new BirthdayService(repository,mailService);
		try {
			service.sendGreetings(new OurDate("2008/10/08"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int quantityOfGreetingsSent() {
		return numberOfGreetingsSent;
	}
}
