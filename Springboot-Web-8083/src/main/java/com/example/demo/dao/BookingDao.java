package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;

public interface BookingDao extends RoomDao {
	List<BookingMeetingRoomDto> findAllBookings();
	Integer addBooking(BookingMeetingRoom bookingMeetingRoom);
	Integer deleteBooking(Integer bookingId);
	Integer updateBookingUserId(Integer bookingId, Integer userId);
}
