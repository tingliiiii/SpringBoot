package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.BookingMeetingRoomDto;
import com.example.demo.model.po.BookingMeetingRoom;
import com.example.demo.model.po.MeetingRoom;
import com.example.demo.model.po.User;

@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BookingMeetingRoomDto> findAllBookings() {
		String sql = "SELECT b.bookingId, b.roomId, b.userId, b.bookingDate, b.createDate, "
				+ "	r.roomId, r.roomName, r.roomSize, u.id, u.name "
				+ "FROM bookingmeetingroom b "
				+ "LEFT JOIN meetingroom r ON b.roomId = r.roomId "
				+ "LEFT JOIN user u ON b.userId = u.id";
		
		// 自定義對應邏輯規則（從上表中欄位對應Dto物件屬性）
		RowMapper<BookingMeetingRoomDto> mapper = new RowMapper<BookingMeetingRoomDto>() {
			
			@Override
			public BookingMeetingRoomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				// 逐筆逐項將每一個欄位資料抓出
				Integer bookingId = rs.getInt("bookingId");
				Integer roomId = rs.getInt("roomId");
				Integer userId = rs.getInt("userId");
				String bookingDate = rs.getString("bookingDate");
				Timestamp createDate = rs.getTimestamp("createDate");
				String roomName = rs.getString("roomName");
				Integer roomSize = rs.getInt("roomSize");
				String name = rs.getString("name"); // user name
				
				// 注入資料
				MeetingRoom meetingRoom = new MeetingRoom(roomId, roomName, roomSize);
				User user = new User(userId, name);
				
				// DTO
				BookingMeetingRoomDto dto = new BookingMeetingRoomDto();
				dto.setBookingId(bookingId);
				dto.setRoomId(roomId);
				dto.setUserId(userId);
				dto.setBookingDate(bookingDate);
				dto.setCreateDate(createDate);
				dto.setMeetingRoom(meetingRoom);
				dto.setUser(user);
				return dto;
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public Integer addBooking(BookingMeetingRoom bookingMeetingRoom) {
		String sql = "INSERT INTO bookingmeetingroom(roomId, userId, bookingDate) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, bookingMeetingRoom.getRoomId(), bookingMeetingRoom.getUserId(), bookingMeetingRoom.getBookingDate());
	}

	@Override
	public Integer deleteBooking(Integer bookingId) {
		String sql = "DELETE FROM bookingmeetingroom WHERE bookingId=?";
		return jdbcTemplate.update(sql, bookingId);
	}

	@Override
	public Integer updateBookingUserId(Integer bookingId, Integer userId) {
		String sql = "UPDATE bookingmeetingroom SET userId = ? WHERE bookingId = ?";
		return jdbcTemplate.update(sql, userId, bookingId);
	}

	@Override
	public List<MeetingRoom> findAllRooms() {
		String sql = "SELECT roomId, roomName, roomSize FROM meetingroom";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MeetingRoom.class));
	}

	@Override
	public Optional<MeetingRoom> getRoom(Integer roomId) {
		String sql = "SELECT roomId, roomName, roomSize FROM meetingroom WHERE roomId = ?";
		try {
			MeetingRoom meetingRoom = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MeetingRoom.class), roomId);
			return Optional.of(meetingRoom);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	@Override
	public Integer addRoom(MeetingRoom room) {
		String sql = "INSERT INTO meetingroom(roomId, roomName, roomSize) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new BeanPropertyRowMapper<>(MeetingRoom.class), room.getRoomId(), room.getRoomName(), room.getRoomSize());
	}
}
