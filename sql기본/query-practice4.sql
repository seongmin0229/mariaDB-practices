--
-- inner join
--

-- 예제1) 현재, 근무하고 있는 직원의 이름과 직책을 출력하세요.
select a.emp_no, a.first_name, b.title
from employees a, titles b
where a.emp_no = b.emp_no					-- join 조건(n-1)
and b.to_date = '9999-01-01';				-- row 선택 조건

-- 예제2) 현재, 근무하고 있는 직원의 이름과 직책을 출력하되, 여성 엔지니어만 출력하세요
select e.emp_no, e.first_name, t.title, e.gender
from employees e, titles t
where e.emp_no = t.emp_no and t.title = 'Engineer' and gender = 'F' and t.to_date = '9999-01-01';

--
-- ANSI/ISO SQL1999 JOIN 표준문법
--

-- 1) natural join
-- 조인 대상이 되는 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 명시하지 않고 암묵적으로 조인이 된다.
select a.first_name, b.title
from employees a natural join titles b
where b.to_date = '9999-01-01';

-- 2) join ~ using

-- natural join의 문제점
select count(*)
from salaries a natural join titles b
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

-- join ~ using
select count(*)
from salaries a join titles b using(emp_no)
where a.to_date = '9999-01-01'
and b.to_date = '9999-01-01';

-- join ~ on
-- 예제) 현재, 직책별 평균 연봉을 큰 순서대로 출력하세요.
select b.title, avg(a.salary)
from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by b.title
order by avg(a.salary) desc;

-- 실습문제 1
-- 현재, 직원들의 근무 부서를 출력해 보세요.
-- 사번, 직원 이름, 부서명 순으로 출력하세요.
select e.emp_no, e.first_name, d.dept_name
from employees e, departments d, dept_emp de
where e.emp_no = de.emp_no and de.dept_no = d.dept_no and de.to_date = '9999-01-01';

-- 실습문제 2
-- 현재, 직책 별 평균 연봉과 직원수를 구하되 직원수가 100명 이상인 직책만 출력 하세요.
select t.title, avg(salary), count(*)
from salaries s, titles t
where s.emp_no = t.emp_no and s.to_date = '9999-01-01' and t.to_date = '9999-01-01'
group by t.title
having count(*) >= 100;

-- 실습문제 3
-- 현재, 부서별로 직책이 Engineer인 직원들에 대해서만 평균 급여를 구하세요.
-- 부서이름, 평균급여 순으로 출력
select d.dept_name, avg(s.salary)
from departments d, dept_emp de, titles t, salaries s
where d.dept_no = de.dept_no
	and de.emp_no = t.emp_no
    and de.emp_no = s.emp_no
    and de.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and t.to_date = '9999-01-01'
    and t.title = 'Engineer'
group by d.dept_name
order by avg(s.salary) desc;