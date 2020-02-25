package steps;

import builders.EmployeeBuilder;
import controllers.EmployeeController;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import entities.Employee;
import helpers.JsonHelper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.*;

public class EmployeesSteps {
    private Employee employee;
    private Response response;
    private EmployeeController employeeControllerInstance = EmployeeController.GetEmployeeControllerInstance();

    private Random random = new Random();

    @Given("^A new employee needs to be created$")
    public void aNewEmployeeNeedsToBeCreated() {
        employee = new EmployeeBuilder().withId(random.nextInt(10000) + 520000).withBirthDate(new Date(random.nextInt(10) + 1990,
                random.nextInt(12) + 1, random.nextInt(20) + 1)).withHireDate(new Date(random.nextInt(10) + 2010,
                random.nextInt(12) + 1, random.nextInt(20) + 1)).withFirstName("Name").withLastName("Test").withGender("F").create();
    }

    @When("^The user send a request to create the employee$")
    public void theUserSendARequestToCreateTheEmployee() {
        response = employeeControllerInstance.createEmployee(JsonHelper.objectToJson(employee));
    }

    @Given("^The user have the employee data$")
    public void theUserHaveTheEmployeeData(DataTable employeeData) {
        List<Map<String, String>> data = employeeData.asMaps(String.class, String.class);
        employee = new EmployeeBuilder().withId(Integer.parseInt(data.get(0).get("id")))
                .withFirstName(data.get(0).get("first_name")).withLastName(data.get(0).get("last_name")).create();
    }

    @When("^The user send a request to get the employee by its id$")
    public void theUserSendARequestToGetTheEmployeeByItsId(){
        response = employeeControllerInstance.getEmployeeById(employee);
    }

    @And("^The response contains the employee$")
    public void theResponseContainsTheEmployee(){
        Employee[] employeeList = JsonHelper.responseToEmployeeArray(response);
        Assert.assertThat(String.format("Error: The employee id returned is not %s", employee.getEmp_no()),
                employeeList[0].getEmp_no(), Matchers.equalTo(employee.getEmp_no()));
        Assert.assertThat(String.format("Error: The employee name returned is not %s", employee.getFirst_name()),
                employeeList[0].getFirst_name(), Matchers.equalTo(employee.getFirst_name()));
        Assert.assertThat(String.format("Error: The employee  last name returned is not %s", employee.getLast_name()),
                employeeList[0].getLast_name(), Matchers.equalTo(employee.getLast_name()));
    }

    @When("^The user send a request to delete the employee by its id$")
    public void theUserSendARequestToDeleteTheEmployeeByItsId() {
        response = employeeControllerInstance.DeleteEmployeeById(employee);
    }

    @And("^The response deletes the employee$")
    public void theResponseDeletesTheEmployee() {

        Employee[] employeeList = JsonHelper.responseToEmployeeArray(response);
        Assert.assertThat(String.format("Error: The employee id returned is not %s", employee.getEmp_no()),
                employeeList[0].getEmp_no(), Matchers.equalTo(employee.getEmp_no()));

    }
}