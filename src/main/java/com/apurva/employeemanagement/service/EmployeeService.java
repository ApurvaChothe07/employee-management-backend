package com.apurva.employeemanagement.service;

import com.apurva.employeemanagement.dto.EmployeeRequestDTO;
import com.apurva.employeemanagement.dto.EmployeeResponseDTO;
import com.apurva.employeemanagement.entity.Employee;
import com.apurva.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.apurva.employeemanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET all employees
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    // GET employee by ID
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        ));

        return convertToResponseDTO(employee);
    }

    // CREATE employee
    public EmployeeResponseDTO createEmployee(
            EmployeeRequestDTO requestDTO) {

        Employee employee = convertToEntity(requestDTO);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return convertToResponseDTO(savedEmployee);
    }

    // UPDATE employee
    public EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO requestDTO) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        ));

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setPhone(requestDTO.getPhone());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setDesignation(requestDTO.getDesignation());
        employee.setSalary(requestDTO.getSalary());

        Employee updatedEmployee =
                employeeRepository.save(employee);

        return convertToResponseDTO(updatedEmployee);
    }

    // DELETE employee
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        ));

        employeeRepository.delete(employee);
    }

    // DTO → Entity
    private Employee convertToEntity(
            EmployeeRequestDTO requestDTO) {

        Employee employee = new Employee();

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setPhone(requestDTO.getPhone());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setDesignation(requestDTO.getDesignation());
        employee.setSalary(requestDTO.getSalary());

        return employee;
    }

    // Entity → Response DTO
    private EmployeeResponseDTO convertToResponseDTO(
            Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getSalary()
        );
    }
}