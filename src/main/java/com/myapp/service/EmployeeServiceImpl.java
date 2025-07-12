package com.myapp.service;

import com.myapp.dto.EmployeeDTO;
import com.myapp.entity.EmployeeEntity;
import com.myapp.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for handling business logic related to Employee operations.
 *
 * <p>This class is annotated with {@code @Service} to indicate that it is a Spring-managed
 * service component. This allows Spring's component scanning mechanism to automatically detect
 * and register the class as a bean in the application context.</p>
 *
 * <p>The {@code @Transactional} annotation ensures that all public methods within this class
 * are executed within a transactional context. This guarantees that any database operations
 * performed inside a method are atomic — either all changes are committed, or in case of failure,
 * all changes are rolled back to maintain data integrity.</p>
 *
 * <p>This class implements the {@link EmployeeService} interface and contains core logic for
 * creating, retrieving, updating, and deleting employee records.</p>
 *
 * @author Kiran Adhikari
 * @version 1.0
 * @since 2025-06-30
 */
@Service //Marks class as a Spring-managed service component
@Transactional //Ensures method executes inside a DB transaction
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Registers a new employee by saving their details to the database.
     *
     * <p>This method receives an {@link EmployeeDTO} object, which typically contains
     * user-submitted data from a registration form. It converts the DTO to an {@link EmployeeEntity}
     * instance using Spring's {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * method to map corresponding fields.</p>
     *
     * <p>Once the conversion is done, the method delegates the persistence operation to the
     * {@code employeeRepository.save()} method, which performs an insert operation if the
     * entity is new.</p>
     *
     * <p>Because this class is annotated with {@code @Transactional}, the save operation is
     * wrapped in a transaction. This ensures that if any error occurs during the save process,
     * changes will be rolled back to maintain data integrity.</p>
     *
     * @param employeeDTO the Data Transfer Object carrying employee registration information
     * @return employeeEntity.getEmployeeId(); This is the PK as employeeID
     */
    @Override
    public EmployeeDTO registerEmp(EmployeeDTO employeeDTO) {

        // Create a new entity instance to map form data into database-compatible format
        EmployeeEntity employeeEntity = new EmployeeEntity();

        // Copy form data (DTO) into entity fields for persistence
        BeanUtils.copyProperties(employeeDTO, employeeEntity);

        // Set current timestamp as Date of Entry (DOE)
        Timestamp timestamp = new Timestamp(new Date().getTime());
        employeeEntity.setDateOfEntry(timestamp);

        // Save the employee entity to the database using Spring Data JPA
        // employeeRepository.save(employeeEntity);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        EmployeeDTO employeeDTOWithPK = new EmployeeDTO();
        BeanUtils.copyProperties(savedEmployeeEntity, employeeDTOWithPK);
        return employeeDTOWithPK;

    }

    /**
     * Authenticates an employee by verifying their email and password.
     *
     * <p>This method searches the database for an employee record matching the given
     * {@code emailId} and {@code password}. If a match is found, the corresponding
     * {@link EmployeeEntity} is converted into an {@link EmployeeDTO} and returned.
     * Otherwise, {@code null} is returned.</p>
     *
     * <p>Note: Uses Spring Data JpaRepository’s custom method {@code findByEmailIdAndPassword()}
     * to retrieve the record as an {@link Optional} to avoid null pointer exceptions.</p>
     *
     * @param emailId  the email address entered by the user
     * @param password the password entered by the user
     * @return a fully populated {@link EmployeeDTO} if credentials are valid; otherwise {@code null}
     */
    @Override
    public EmployeeDTO authenticateUser(String emailId, String password) {
        // Query the database to check if a user exists with the provided email and password
        Optional<EmployeeEntity> optional = employeeRepository.findByEmailIdAndPassword(emailId, password);

        // Declares the DTO as null to handle cases where no matching user exists in the database,
        // since Optional may or may not contain a value when searching for a table row (null by default).
        EmployeeDTO employeeDTO = null;

        // If the user is found, convert the entity to DTO
        if (optional.isPresent()) {
            EmployeeEntity employeeEntity = optional.get();
            employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employeeEntity, employeeDTO);
            //return employeeDTO; // it is having values   NOT_NULL
        }  //else {

        //  Return the DTO if user exists, or null if not found
        return employeeDTO;
        //}
    }

    /**
     * Retrieves all employee records from the database and returns them as a list of DTOs.
     *
     * <p>This method uses {@code employeeRepository.findAll()} to fetch all records from the
     * employee table in the form of {@link EmployeeEntity} objects. It then iterates over the
     * retrieved list, converts each entity into an {@link EmployeeDTO} using {@link BeanUtils},
     * and adds them to a new list which is returned to the caller.</p>
     *
     * <p>This conversion ensures that database-level entities are transformed into presentation-friendly
     * DTOs suitable for use in the service or controller layers.</p>
     *
     * @return a list of {@link EmployeeDTO} objects containing employee details,
     * or an empty list if no records are found.
     */
    @Override
    public List<EmployeeDTO> findAllEmployees() {
        //  Fetches all employee records from the DB as entity objects
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        //  Prepares an empty list to hold DTOs
        List<EmployeeDTO> employeeDtoList = new ArrayList<>();

        //  Converts each EmployeeEntity to EmployeeDTO and adds to the list
        if (!employeeEntityList.isEmpty()) {
            for (EmployeeEntity tempEmployeeEntity : employeeEntityList) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                BeanUtils.copyProperties(tempEmployeeEntity, employeeDTO); //in the first iteration, first object added in blank arrayList
                employeeDtoList.add(employeeDTO); //1   +1   +  1  + 1  + 1    <===5 records added in the form of objects
            }
        }
        //  Returns the list of DTOs( it is collection of records in the form of objects )
        return employeeDtoList;
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId); // when you delete, there is nothing to return, so the method returns void
    }

    //Edit operation -- (tier 1) <---- get record from table -> for jsp page operation
    @Override
    public EmployeeDTO findEmployeeById(int employeeId) {
        // Checking whether the employee is present or not in DB for rest controller's, findEmployee method
        // and also JSP page reference, on this service layer, but to check employee before update, it can
        // be checked on the controller layer (-> for PUT/PATCH mapping -> v2)
        Optional<EmployeeEntity> optional = employeeRepository.findById(employeeId);

        EmployeeDTO employeeDTO = null;

        if (optional.isPresent()) {
            EmployeeEntity employeeEntity = optional.get();
             employeeDTO = new EmployeeDTO(); //we can work w/o declaring as null too, but not recommended
            employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employeeEntity, employeeDTO);
            return employeeDTO;
        }
        return employeeDTO;
    }

    // Edit operation---(tier 2) <----- save the updated data
    @Override
    public void registerEmpAfterUpdate(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeDTO, employeeEntity);
        employeeRepository.save(employeeEntity);

    }



//    @Override
//    public EmployeeDTO fetchRecordFromDBForUpdate(EmployeeDTO employeeDTO) {
//        // checking whether the employee is present or not in DB
//        // for v1 checking the presence of the employee on the service layer, instead of
//        // checking on the controller
//        Optional<EmployeeEntity> optional = employeeRepository.findById(employeeDTO.getEmployeeId());
//
//        if (optional.isPresent()) {
//            EmployeeEntity employeeEntity = optional.get();
//            BeanUtils.copyProperties(employeeDTO, employeeEntity);
//
//            EmployeeEntity updateEntity = employeeRepository.save(employeeEntity);
//
//            EmployeeDTO updatedDTO = new EmployeeDTO();
//            BeanUtils.copyProperties(updateEntity, updatedDTO);
//            return updatedDTO;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean deleteEmployeeRecord(Integer employeeId) {
//        if (employeeRepository.existsById(employeeId)) {
//            employeeRepository.deleteById(employeeId);
//            return true;
//        }
//        return false;
//    }
}

