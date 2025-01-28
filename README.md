# 일정 관리 앱 calendar

### API 명세서

![image](https://github.com/user-attachments/assets/0c3879b7-d2fa-4b9e-8333-cc328dd4817c)


### ERD

![image](https://github.com/user-attachments/assets/c1489ef6-574d-4f41-96b1-fe9554c7ac4e)

# 기능

### 일정 등록

- /api/schedules 에 POST 요청

<details>
  <summary>Request</summary>
  
  ```
    {
      "todo" : "할 일",
      "userId" : "1",
      "password" : "aaa"
    }
  ```
- todo -> 할 일
- userId -> 유저 식별자
- password -> 비밀번호

</details>

<details>
<summary>Response</summary>
  
성공
```
{
  "id": 48,
  "todo": "할 일",
  "username": "이름 업테이트",
  "createdDate": "2025-01-28 14:37:11",
  "updatedDate": "2025-01-28 14:37:11"
}
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일

실패
```
{
  "todo": "할 일은 필수 입력 값입니다."
}
```
- todo는 필수로 입력 해야됩니다.

```
{
  "todo": "최대 200자 까지 허용됩니다."
}
```
- todo는 최대 200자 까지 입력이 가능합니다.

```
{
  "password": "비밀번호는 필수 입력 값입니다."
}
```
- password는 필수로 입력 해야됩니다.

```
{
  "message": "userId에 해당하는 유저가 없습니다."
}
```
- 존재하지 않는 유저의 userId를 전달하면 오류가 발생합니다.
</details>




