-- query 순서
-- 1. from : 접근 테이블을 확인
-- 2. where : 조건에 맞는 row를 선택(임시 테이블)
-- 3. 집계 : (임시 테이블 -> 결과 테이블)
-- 4. 출력

-- 예제 : 사번이 10060인 사원이 받은 평균 연봉은?
select avg(salary)
from salaries
where emp_no = '10060';

-- 4) group by에 참여하교 있는 컬럼은 select 절에 올 수 있다.
-- 예제 : 사원 별 평균 연봉은?
select emp_no, avg(salary)
from salaries
group by emp_no;

-- 5) having
-- 집계결과(결과 테이블)에서 row를 선택해야 하는 경우
-- 이미 where절은 실행이 되었기 때문에 having절에서 조건을 주어야 한다.
-- 예제 : 평균 월급이 60000 달러 이상인 직원의 사번과 평균 월급을 출력하세요.
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) > 60000;

-- 6) order by
--    order by는 항상 맨 마지막 출력 전에 한다.
select emp_no, avg(salary)
from salaries
group by emp_no
having avg(salary) > 60000
order by avg(salary) asc;

-- 주의)
-- 예제) 사번이 10060인 직원의 급여 평균, 급여 총합

-- 문법적인 오류
-- 의미적으로 맞다 : where절 때문에
select avg(salary), sum(salary)
from salaries
where emp_no = '10060';

-- 문법적으로 옳다
select emp_no, avg(salary), sum(salary)
from salaries
group by emp_no
having emp_no = '10060';