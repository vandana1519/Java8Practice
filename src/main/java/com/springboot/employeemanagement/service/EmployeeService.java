package com.springboot.employeemanagement.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.springboot.employeemanagement.model.Employee;

@FunctionalInterface
public interface EmployeeService {
	
	void addEmployee(Employee employee);

	default List<Integer> printNumber() {
		return IntStream.range(1, 100).boxed().collect(Collectors.toList());
	}
	
	public static Stream<LocalDate> getNextWeekBusinessDays() 
    {
		LocalDate startDate = LocalDate.now();

		//List<LocalDate> holidays = Arrays.asList(LocalDate.of(2021, 1, 26),LocalDate.of(2021, 1, 31));
		//Predicate<LocalDate> isHoliday = date -> holidays.isPresent() ? holidays.get().contains(date) : false;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
 
        long daysBetween = ChronoUnit.DAYS.between(startDate, startDate.plusDays(7));
 
        return Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
                .filter((isWeekend).negate());
    }

}
