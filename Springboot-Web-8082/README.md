## 會議室預訂系統（Web API）
以SSR（Server-Side Rendering）方式，建立會議室預訂系統，功能包括預約會議室、取消預約及查詢預約。

### 會議室資訊 (Room)
| roomId | roomName | roomSize |
|--------|----------|----------|
| 101    | 101(S)   | 10       |
| 102    | 102(M)   | 25       |

### 預約紀錄 (BookingRoom)
| bookingId | roomId | userId | bookingDate |
|-----------|--------|--------|-------------|
| 1         | 101    | 1      | 2023-12-04  |
| 2         | 102    | 2      | 2023-12-05  |
| 3         | 102    | 3      | 2023-12-06  |

### 功能
1. **預約會議室**
    - **路徑**：`/booking/bookRoom`
    - **參數**：`roomId`（會議室ID）、`userId`（使用者ID）、`date`（預訂日期）
    - **返回**：預訂成功（會得到預約號碼 bookingId）或失敗
    - **範例**：
      ```sh
      curl "http://localhost:8082/booking/bookRoom?roomId=101&userId=1&date=2023-12-04"
      ```

2. **取消預約**
    - **路徑**：`/booking/cancelBooking/{bookingId}`
    - **參數**：`bookingId`（預訂ID）
    - **返回**：取消成功或失敗的消息
    - **範例**：
      ```sh
      curl "http://localhost:8082/booking/cancelBooking/1"
      ```

3. **查看所有預約**
    - **路徑**：`/booking/viewBookings`
    - **返回**：所有預約的列表
    - **範例**：
      ```sh
      curl "http://localhost:8082/booking/viewBookings"
      ```

4. **修改預約人**
    - **路徑**：`/booking/{bookingId}/updateUserId`
    - **參數**：`userId`（使用者ID）
    - **返回**：是否變更成功
    - **範例**：
      ```sh
      curl "http://localhost:8082/booking/1/updateUserId?userId=2"
      curl "http://localhost:8082/booking/2/updateUserId?userId=3"
      ```
