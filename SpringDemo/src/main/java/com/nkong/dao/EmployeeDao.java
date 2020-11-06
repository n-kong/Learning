package com.nkong.dao;

import com.nkong.bean.Department;
import com.nkong.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employeeMap = null;

    @Autowired
    private DepartmentDao departmentDao;
    static {
        employeeMap = new HashMap<>();
        employeeMap.put(1001, new Employee(101, "教学部", "111@qq.com", 1, new Department(101, "教学部")));
        employeeMap.put(1002, new Employee(102, "教学部", "222@qq.com", 0, new Department(102, "市场部")));
        employeeMap.put(1003, new Employee(103, "教学部", "333@qq.com", 0, new Department(103, "体育部")));
        employeeMap.put(1004, new Employee(104, "教学部", "444@qq.com", 1, new Department(104, "运营部")));
        employeeMap.put(1005, new Employee(105, "教学部", "555@qq.com", 0, new Department(105, "后勤部")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employeeMap.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employeeMap.values();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeMap.get(id);
    }

    public void remove(Integer id) {
        employeeMap.remove(id);
    }
}
