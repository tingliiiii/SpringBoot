SELECT b.bookingId, b.roomId, b.userId, b.bookingDate, b.createDate,
	   r.roomId, r.roomName, r.roomSize, 
       u.id, u.name

FROM bookingmeetingroom b
LEFT JOIN meetingroom r ON b.roomId = r.roomId
LEFT JOIN user u ON b.userId = u.id;