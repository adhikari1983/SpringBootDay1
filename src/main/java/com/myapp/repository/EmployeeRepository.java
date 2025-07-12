package com.myapp.repository;

import com.myapp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

   /// @Override
   /// boolean existsById(Integer integer);
    ///@Override
   /// void delete(EmployeeEntity employeeEntity);
    /// These findAll(), save() is already present in the JpaRepository interface
    /// So, it is an optional, or even better not to declare here
    /// List<EmployeeEntity> findAll();

 /*
 *                  CrudRepository<T, ID>        e.g. save(), findById(), deleteById(), existsById()
 *                  ↑
 *                  PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID>
 *                  ↑
 *                  JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>

 *    - JpaRepository and its parents provide generic CRUD methods like save(), findById(), deleteById(), etc.
 * <p>
 *    - For custom queries based on your entity fields, you declare method signatures in your repository interface
 *      following Spring Data JPA's naming conventions.
 * <p>
 *   - Then Spring Data JPA implements those methods automatically at runtime based on the method name — no explicit
 *     implementation or SQL needed.
 *     For example:
 *     I did write the method signature :
 *                                    Optional<EmployeeEntity> findByEmailIdAndPassword(String emailId, String password);
 *     myself in the repository interface — that's the only thing I wrote.
 *     But I didn't write the method's actual code or SQL query — Spring Data JPA generates the implementation
 *     automatically at runtime based on the method name.
 * <p>
 *     Spring Data JPA parses the method name findByEmailIdAndPassword:
     *     findBy             → tells Spring Data to create a query method.
     *     EmailIdAndPassword → tells it which fields to filter on (matching exactly fields in your EmployeeEntity).
 * <p>
 *     IMPORTANT NOTE TO follow Spring Data JPA method naming conventions:
 *     Method names must start with keywords like findBy, readBy, getBy, etc., followed directly by the entity’s field names.
 *     So, findByEmailIdAndPassword is valid.
 *     But something like canYouFindByEmailIdAndPassword or any other arbitrary prefix won’t work — Spring Data JPA won’t
 *     recognize it as a query method and will throw a runtime error.
*     ..........................FURTHER ELABORATION HOW IT WORKS BEHIND THE SCENE....................................
     * <p>
     * Method names must start with keywords like findBy, readBy, getBy, etc., it means:
     * <p>
     * - Spring Data JPA provides a framework that understands these prefixes as instructions.
     * <p>
     * - Behind the scenes, Spring Data JPA has built-in logic that recognizes these keywords and parses the method name
     *   to generate the appropriate database query automatically.
     * <p>
     * - You are not calling a concrete method already implemented in a library. Instead, Spring Data JPA interprets
     *   your method name at runtime and dynamically creates the code (proxy implementation) that runs the query.
     * <p>
     * So:
     * - You declare a method like findByEmailIdAndPassword in your repository interface.
     * <p>
     * - Spring Data JPA’s internal machinery scans that method name, breaks it down
     *   (e.g., findBy + EmailId + And + Password).
     * <p>
     * - It builds a query equivalent to:
     *   SELECT * FROM employee_entity WHERE email_id = ? AND password = ?
     * <p>
     * - Then runs that query when you call the method.
     * <p>
     * It’s a bit like magic under the hood:
     * - You write the method signature.
     * <p>
     * - Spring Data JPA uses proxy objects, reflection, and query generation to create the actual implementation dynamically.
     * <p>
     * This saves you from writing SQL or JPQL yourself for simple queries.
*/

    /**
     * Finds an employee entity by matching the given email ID and password.
     *
     * @param emailId the email ID of the employee to search for
     * @param password the password of the employee to search for
     * @return an Optional containing the EmployeeEntity if a matching record is found;
     *         otherwise, an empty Optional
     */
    Optional<EmployeeEntity> findByEmailIdAndPassword(String emailId, String password);

}
