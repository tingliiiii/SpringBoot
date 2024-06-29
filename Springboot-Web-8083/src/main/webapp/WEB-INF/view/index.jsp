<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- Spring Form 表單標籤 -->
    <%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>

        <!DOCTYPE html>
        <html>

            <head>
                <meta charset="UTF-8">
                <title>Booking MeetingRoom</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
                <style>
                    h3 {
                        margin: 0 0 20px;
                    }

                    .table {
                        background-color: transparent;
                        --bs-table-bg: transparent;
                    }
                </style>
            </head>

            <body style="padding: 50px; background-color: #eee;">
                <table class="table">
                    <tr>
                        <!-- 預約表單 -->
                        <td>
                            <h3>預約表單</h3>
                            <sp:form modelAttribute="bookingMeetingRoomDto" method="post" action="/booking"
                                target="resultFrame">
                                <div class="row">
                                    <div class="col-auto">
                                        <label class="col-form-label">會議室</label>
                                    </div>
                                    <div class="col-auto">
                                        <sp:select path="roomId" items="${ rooms }" itemValue="roomId"
                                            itemLabel="roomName" class="form-select" />
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                    <div class="col-auto">
                                        <label class="col-form-label">預約人</label>
                                    </div>
                                    <div class="col-auto">
                                        <sp:select path="userId" items="${ users }" itemValue="id" itemLabel="name"
                                            class="form-select" />
                                    </div>
                                </div>
                                <p></p>
                                <div class="row">
                                    <div class="col-auto">
                                        <label class="col-form-label">預約日</label>
                                    </div>
                                    <div class="col-auto">
                                        <sp:input type="date" path="bookingDate" class="form-control" />
                                    </div>
                                </div>
                                <p></p>
                                <button type="submit" class="btn btn-primary">預約</button>
                            </sp:form>
                        </td>
                        <!-- 回應結果 -->
                        <td rowspan="4">
                            <iframe name="resultFrame" style="border: 0px" width="600px" height="1000px"></iframe>
                        </td>
                    </tr>
                    <tr>
                        <!-- 取消預約 -->
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <!-- 查詢預約 -->
                        <td valign="top"></td>
                    </tr>
                    <tr>
                        <!-- 新增會議室 -->
                        <td valign="top"></td>
                    </tr>
                </table>
            </body>

        </html>