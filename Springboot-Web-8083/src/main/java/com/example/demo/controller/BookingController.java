package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.model.po.User;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String index(@ModelAttribute BookingMeetingRoomDto bookingMeetingRoomDto, Model model) {
		List<BookingMeetingRoomDto> dtos = bookingService.findAllBookings();
		List<MeetingRoom> rooms = bookingService.findAllRooms();
		List<User> users = userService.findAllUsers();
		model.addAttribute("dtos",dtos);
		model.addAttribute("rooms",rooms);
		model.addAttribute("users", users);
		return "index";
	}
	
	@PostMapping
	public String add(@ModelAttribute BookingMeetingRoom booking, Model model) {
		try {
			Integer rowcount = bookingService.addBooking(booking.getRoomId(), booking.getUserId(), booking.getBookingDate());
			String message = "add "+(rowcount==1?"success":"fail");
			model.addAttribute("message", message);
		} catch (Exception e) {
			String message = "add error: ";
			if(e.getMessage().contains("unique_roomId_and_bookingDate")) {
				message += "this meeting room has been reserved on that day";
			} else {
				message += e.getMessage();
			}
			model.addAttribute("message", message);
		}	
		return "result";
	}
	
	@DeleteMapping
	@ResponseBody
	public String delete(@PathVariable Integer bookingId, Model model) {
		Integer rowcount = bookingService.deleteBooking(bookingId);
		String message = "delete "+(rowcount==1?"success":"fail");
		return message;
	}
	
	
}
