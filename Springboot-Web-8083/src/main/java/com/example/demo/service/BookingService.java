package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingDao;
import com.example.demo.dao.RoomDao;
import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	public List<BookingMeetingRoomDto> findAllBookings() {
		return bookingDao.findAllBookings();
	}
	
	public Integer addBooking(Integer roomId, Integer userId, String bookingDate) {
		BookingMeetingRoom booking = new BookingMeetingRoom();
		booking.setRoomId(roomId);
		booking.setUserId(userId);
		booking.setBookingDate(bookingDate);
		return bookingDao.addBooking(booking);
	}
	
	public Integer deleteBooking(Integer bookingId) {
		return bookingDao.deleteBooking(bookingId);
	}

	public Integer updateBookingUserId(Integer bookingId, Integer userId) {
		return bookingDao.updateBookingUserId(bookingId, userId);
	}


	public List<MeetingRoom> findAllRooms() {
		return bookingDao.findAllRooms();
	}


	public Optional<MeetingRoom> getRoom(Integer roomId) {
		return bookingDao.getRoom(roomId);
	}
	
	
	
	
	
}
