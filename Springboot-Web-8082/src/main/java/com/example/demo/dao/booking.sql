drop table if exists bookingmeetingroom;
drop table if exists meetingroom;

-- 建立 meetingroom
create table if not exists meetingroom(
	roomId int primary key,
    roomName varchar(50) not null unique,
    roomSize int not null default 1
);

-- 建立 bookingmeetingroom
create table if not exists bookingmeetingroom(
	bookingId int auto_increment primary key,
    roomId int not null,
    userId int not null,
    bookingDate varchar(50) not null,
    createDate timestamp default current_timestamp,
    foreign key (roomId) references meetingroom(roomId),
    foreign key (userId) references User(id),
    constraint unique_roomId_and_bookingDate unique(roomId, bookingDate)
);

-- 建立範例資料
insert into meetingroom(roomId, roomName, roomSize) values(101, '101(S)', 10);
insert into meetingroom(roomId, roomName, roomSize) values(102, '102(M)', 25);
insert into meetingroom(roomId, roomName, roomSize) values(203, '203(L)', 50);
insert into meetingroom(roomId, roomName, roomSize) values(404, '404(2L)', 100);

insert into bookingmeetingroom(roomId, userId, bookingDate) values(101, 1, '2024-05-20');
insert into bookingmeetingroom(roomId, userId, bookingDate) values(102, 2, '2024-05-20');



