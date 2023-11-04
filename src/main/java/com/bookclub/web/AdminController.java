package com.bookclub.web;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;

@Controller
@RequestMapping("/monthly-books")
public class AdminController {

	BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

	@Autowired
	public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
		this.bookOfTheMonthDao = bookOfTheMonthDao;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String showBookOfTheMonth(Model model) {
		model.addAttribute("books", bookOfTheMonthDao.list("999"));
		return "monthly-books/list";
	}
	@GetMapping(value="/new")
	public String bookOfTheMonthForm(Model model) {
		System.out.println("new!!!!");
		model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
		model.addAttribute("months", getMonths());
		return "monthly-books/new";
	}
	 @RequestMapping(value="/addBookOfTheMonth",method = RequestMethod.POST)
	  public String addBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult bindingResult,
	      Model model) {
	    System.out.println(bookOfTheMonth.toString());

	    if (bindingResult.hasErrors()) {
	      model.addAttribute("months", getMonths());
	      return "monthly-books/new";
	    }

	    bookOfTheMonthDao.add(bookOfTheMonth);

	    return "redirect:/monthly-books";
	  }
	@RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
	  public String removeBookOfTheMonth(@PathVariable String id) {

		bookOfTheMonthDao.remove(id);

	    return "redirect:/monthly-books";
	  }
	private Map<Integer, String> getMonths(){
		Map<Integer, String> months = new HashMap<Integer, String>();
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
		return months;
	}

}