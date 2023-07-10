package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		testFindBySalary(100000, 200000);
	}
	
	private static void testFindBySalary(int min, int max) {
		List<EmployeeVo> list = new EmployeeDao().findBySalary(min, max);
		for(EmployeeVo vo : list) {
			System.out.println(min + ":" + vo.getSalary() + max + ":");
		}
	}

	public static void testFindByName(String keyword) {
		List<EmployeeVo> list = new EmployeeDao().findByName(keyword);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}
