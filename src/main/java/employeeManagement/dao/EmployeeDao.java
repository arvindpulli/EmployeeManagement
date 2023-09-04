package employeeManagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import employeeManagement.dto.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private EntityManager em;
	
	public void saveEmployee(Employee e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
	
	public List<Employee> getAllEmployees(){
		Query query=em.createQuery("select e from Employee e");
		List<Employee>employees=query.getResultList();
		return employees;
	}

	public void deleteEmployee(int id) {
		Employee e=em.find(Employee.class, id);
		
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
	}
	
	public void updateEmployee(Employee e, int id) {
		Employee emp=em.find(Employee.class, id);
		
		e.setId(id);
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
		
	}
	
	public Employee findEmployee(int id) {
		Employee emp=em.find(Employee.class, id);
		if(emp!=null) {
			return emp;
		}else
			return null;
	}
}
