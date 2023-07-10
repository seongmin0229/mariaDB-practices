package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class HRMain02 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("min : max > ");
		int min = scanner.nextInt();
		int max = scanner.nextInt();
		List<EmployeeVo> list = new EmployeeDao().findBySalary(min, max);
		for(EmployeeVo vo : list) {
			System.out.println(vo.getNo() + " : " + vo.getFirstName() + " : " + vo.getSalary());
		}
	

	}

}
