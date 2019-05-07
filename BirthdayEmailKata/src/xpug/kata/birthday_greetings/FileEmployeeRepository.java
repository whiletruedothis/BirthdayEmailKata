package xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {
    String fileName;
    List<Employee> employeeList = new ArrayList<Employee>();

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> getAllEmployee() throws IOException, ParseException {

        BufferedReader in = new BufferedReader(new FileReader(this.fileName));
		String str = "";
        str = in.readLine(); 
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0],employeeData[2], employeeData[3]);
            employeeList.add(employee);
        }
        return employeeList;
	}


}