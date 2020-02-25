package controllers;

import entities.Employee;
import helpers.PropertiesHelper;
import builders.UrlBuilder;
import io.restassured.response.Response;

import java.net.URL;

public class EmployeeController extends ApiController {

    private static EmployeeController employeeControllerInstance;
    private Response response;

    private EmployeeController() {
        super(new UrlBuilder().addDomain(PropertiesHelper.getValueByKey("url.base")).addPathStep(
                PropertiesHelper.getValueByKey("url.employee.table")).build());
    }

    public static EmployeeController GetEmployeeControllerInstance() {
        if (employeeControllerInstance == null)
            employeeControllerInstance = new EmployeeController();
        return employeeControllerInstance;
    }

    public Response createEmployee(String employee) {
        response = requestSpecification.when().body(employee).and().post(url);
        return response;
    }

    public Response getResponse() {
        return response;
    }

    public Response getEmployeeById(Employee employee) {
        URL idUrl = new UrlBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep(PropertiesHelper.getValueByKey("url.employee.table"))
                .addPathStep(String.valueOf(employee.getEmp_no()))
                .build();
        response = requestSpecification.when().get(idUrl);
        return response;
    }

    public Response DeleteEmployeeById(Employee employee) {
        URL idUrl = new UrlBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep(PropertiesHelper.getValueByKey("url.employee.table"))
                .addPathStep(String.valueOf(employee.getEmp_no()))
                .build();
        response = requestSpecification.when().delete(idUrl);
        return response;
    }


}
