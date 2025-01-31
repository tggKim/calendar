# 일정 관리 앱 calendar

### 개발 환경
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
</div>

### API 명세서

![image](https://github.com/user-attachments/assets/12d5d269-cadf-46ef-9087-d0505aa18128)


### ERD

![image](https://github.com/user-attachments/assets/c1489ef6-574d-4f41-96b1-fe9554c7ac4e)

# 기능

## 일정 등록

**/api/schedules POST 요청**

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

## 일정 단건 조회

**/api/schedules/{id} GET 요청**

<details>
<summary>Request</summary>
  
- PathVariable로 일정의 식별자인 id를 명시하면 된다.

</details>

<details>
<summary>Response</summary>

성공
```
{
  "id": 1,
  "todo": "할 일",
  "username": "이름 업테이트",
  "createdDate": "2000-04-29 00:00:00",
  "updatedDate": "2025-05-11 00:00:00"
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
  "message": "id에 해당하는 일정이 없습니다."
}
```
- 존재하지 않는 유저를 조회하면 오류가 발생합니다.
</details>

## 일정 목록 조회

**/api/schedules GET 요청**

<details>
<summary>Request</summary>
  
/api/schedules 로 GET 요청을 하면 된다.

</details>

<details>
<summary>Response</summary>

성공
```
[
    {
        "id": 1,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2000-04-29 00:00:00",
        "updatedDate": "2025-05-11 00:00:00"
    },
    {
        "id": 2,
        "todo": "할 일2",
        "username": "이름 업테이트",
        "createdDate": "2000-04-30 00:00:00",
        "updatedDate": "2025-05-12 00:00:00"
    },
    {
        "id": 3,
        "todo": "할 일3",
        "username": "이름 업테이트",
        "createdDate": "2000-04-27 00:00:00",
        "updatedDate": "2025-05-13 00:00:00"
    },
    {
        "id": 48,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2025-01-28 14:37:11",
        "updatedDate": "2025-01-28 14:37:11"
    }
]
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일

일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>

<hr>

**/api/schedules/userId=[유저의 userId] GET 요청**

<details>
<summary>Request</summary>
  
- 파라미터로 유저의 식별자인 userId를 명시하면 된다.

</details>

<details>
<summary>Response</summary>

성공
```
[
    {
        "id": 1,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2000-04-29 00:00:00",
        "updatedDate": "2025-05-11 00:00:00"
    },
    {
        "id": 2,
        "todo": "할 일2",
        "username": "이름 업테이트",
        "createdDate": "2000-04-30 00:00:00",
        "updatedDate": "2025-05-12 00:00:00"
    },
    {
        "id": 3,
        "todo": "할 일3",
        "username": "이름 업테이트",
        "createdDate": "2000-04-27 00:00:00",
        "updatedDate": "2025-05-13 00:00:00"
    },
    {
        "id": 48,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2025-01-28 14:37:11",
        "updatedDate": "2025-01-28 14:37:11"
    }
]
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일
- userId에 해당하는 일정들을 리스트로 반환합니다.

userId에 해당하는 일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>

<hr>

**/api/schedules/updatedDate=[일정의 수정일] GET 요청**

<details>
<summary>Request</summary>
  
- 파라미터로 유저의 수정일을 yyyy-mm-dd 형식으로 명시하면 된다..

</details>

<details>
<summary>Response</summary>

성공
```
[
    {
        "id": 5,
        "todo": "할 일5",
        "username": "업데이트2",
        "createdDate": "2000-05-02 00:00:00",
        "updatedDate": "1999-05-11 00:00:00"
    },
    {
        "id": 6,
        "todo": "할 일5",
        "username": "업데이트2",
        "createdDate": "2000-05-03 00:00:00",
        "updatedDate": "1999-05-11 00:00:00"
    }
]
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일
- updatedDate에 해당하는 일정들을 리스트로 반환합니다.

실패
```
{
    "message": "날짜 형식이 올바르지 않습니다."
}
```
- yyyy-mm-dd 형식으로 입력하지 않으면 오류가 발생합니다.

해당하는 일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>

<hr>

**/api/schedules/sort=[정렬의 기준이 되는 필드].[asc/desc/ASC/DESC] GET 요청**

<details>
<summary>Request</summary>
  
- 파라미터로 정렬의 기준이되는 필드와 방향을 설정하면 된.

</details>

<details>
<summary>Response</summary>

성공
```
[
    {
        "id": 3,
        "todo": "할 일3",
        "username": "이름 업테이트",
        "createdDate": "2000-04-27 00:00:00",
        "updatedDate": "2025-05-13 00:00:00"
    },
    {
        "id": 2,
        "todo": "할 일2",
        "username": "이름 업테이트",
        "createdDate": "2000-04-30 00:00:00",
        "updatedDate": "2025-05-12 00:00:00"
    },
    {
        "id": 1,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2000-04-29 00:00:00",
        "updatedDate": "2025-05-11 00:00:00"
    },
    {
        "id": 4,
        "todo": "할 일4",
        "username": "tgg",
        "createdDate": "2000-05-01 00:00:00",
        "updatedDate": "2025-02-12 00:00:00"
    },
    {
        "id": 48,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2025-01-28 14:37:11",
        "updatedDate": "2025-01-28 14:37:11"
    },
    {
        "id": 5,
        "todo": "할 일5",
        "username": "업데이트2",
        "createdDate": "2000-05-02 00:00:00",
        "updatedDate": "1999-05-11 00:00:00"
    },
    {
        "id": 6,
        "todo": "할 일5",
        "username": "업데이트2",
        "createdDate": "2000-05-03 00:00:00",
        "updatedDate": "1999-05-11 00:00:00"
    }
]
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일
- 현재 예시에서는 api/schedules.sort=updatedDate.desc로 요청한 결과이다. 수정일을 기준으로 내림차순 정렬된 것을 볼 수 있다.

실패
```
{
    "message": "정렬 형식이 올바르지 않습니다."
}
```
- [정렬의 기준이 되는 필드].[asc/desc/ASC/DESC] 형식으로 입력하지 않으면 오류가 발생합니다.
- id, todo, createdDate, updatedDate가 정렬의 기준이 될 수 있습니다.

해당하는 일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>

<hr>

**/api/schedules/page=[페이지 번호]&size=[한 페이지당 크기]**

<details>
<summary>Request</summary>
  
- page와 size 두 개의 파라미터를 모두 입력해야 됩니다.

</details>

<details>
<summary>Response</summary>

성공
```
[
    {
        "id": 1,
        "todo": "할 일",
        "username": "이름 업테이트",
        "createdDate": "2000-04-29 00:00:00",
        "updatedDate": "2025-05-11 00:00:00"
    },
    {
        "id": 2,
        "todo": "할 일2",
        "username": "이름 업테이트",
        "createdDate": "2000-04-30 00:00:00",
        "updatedDate": "2025-05-12 00:00:00"
    },
    {
        "id": 3,
        "todo": "할 일3",
        "username": "이름 업테이트",
        "createdDate": "2000-04-27 00:00:00",
        "updatedDate": "2025-05-13 00:00:00"
    }
]
```
- id -> 일정 식별자
- todo -> 할 일
- username -> 유저 이름
- createDate -> 일정 생성일
- updatedDate -> 일정 수정일
- 현재 예시에서는 /api/schedules?page=1&size=3로 요청한 결과입니다.

실패
```
{
    "message": "페이징 형식이 올바르지 않습니다. size는 -1보다 크고, page는 1이상 입니다."
}
```
- page와 size파라미터를 모두 입력하지 않으면 페이징에 대한 요청은 무시됩니다.
- size는 0이상이어야 하며 page는 1이상 이어야 합니다.

해당하는 일정이 없을 경우
```
[]
```
- 일정이 존재하지 않으면 빈 리스트를 반환합니다.
</details>

<hr>

**파라미터를 조합해서 일정 목록 얻기**

- 위에서 설명한 파라미터들을 섞어서 사용이 가능합니다

- 예로 /api/schedules?page=1&size=2&updatedDate=1999-05-11&userid=3&sort=id.desc 처럼 모든 파라미터를 사용할 수도 있고 원하는 파라미터를 적용해서 자신만의 기준으로 목록을 가져올 수 있습니다.

## 일정 수정

**/api/schedules/{id} PATCH 요청**

<details>
  <summary>Request</summary>
  
  ```
  {
    "todo" : "할 일",
    "username" : "수정 이름",
    "password" : "aaa"
  }
  ```
- todo -> 할 일
- username -> 유저 이름
- password -> 비밀번호

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "id": 1,
    "todo": "할일 업데이트",
    "username": "이름 수정",
    "createdDate": "2000-04-29 00:00:00",
    "updatedDate": "2025-01-28 19:23:48"
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
    "message": "id에 해당하는 일정이 없습니다."
}
```
- 존재하지 않는 일정의 id를 입력하면 오류가 발생합니다. 

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
    "message": "비밀번호가 잘못되었습니다."
}
```
- 비밀번호가 일치하지 않으면 오류가 발생합니다.
</details>

## 일정 삭제

**/api/schedules/{id} POST 요청**

<details>
<summary>Request</summary>
  
```
{
  "password" : "비밀번호"
}
```
- password -> 비밀번호

</details>

<details>
<summary>Response</summary>
  
성공
- 204 No Content가 응답으로 돌아옵니다.

실패
```
{
    "message": "id에 해당하는 일정이 없습니다."
}
```
- 존재하지 않는 일정의 id를 입력하면 오류가 발생합니다. 

```
{
  "password": "비밀번호는 필수 입력 값입니다."
}
```
- password는 필수로 입력 해야됩니다.

```
{
  "message": "비밀번호가 잘못되었습니다."
}
```
- 비밀번호가 일치하지 않으면 오류가 발생합니다.
</details>

## 유저 등록

**/api/users POST 요청**

<details>
<summary>Request</summary>
  
```
{
    "username" : "새로운 유저",
    "email" : "rl123456@hanmail.net"
}
```
- username -> 유저 이름
- email -> 유저 이메일

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 124,
    "username": "새로운 유저1",
    "email": "rl123456@hanmail.net",
    "createdDate": "2025-01-30 13:18:39",
    "updatedDate": "2025-01-30 13:18:39"
}
```
- userId -> 유저 식별자
- username -> 유저  이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유 수정일

실패
```
{
    "email": "이메일 형식에 맞지 않습니다."
}
```
- 이메일 형식에 맞지 않으면 오류가 발생합니다.
</details>

## 유저 단건 조회

**/api/users{userId} GET 요청**

<details>
<summary>Request</summary>
  
PathVariable로 userId를 명시하면 됩니다.

</details>

<details>
<summary>Response</summary>
  
성공
```
{
    "userId": 125,
    "username": "새로운 유저1",
    "email": "rle@hanmail.net",
    "createdDate": "2025-01-30 13:32:06",
    "updatedDate": "2025-01-30 13:32:06"
}
```
- userId -> 유저 식별자
- username -> 유저  이름
- email -> 유저 이메일
- createDate -> 유저 생성일
- updatedDate -> 유 수정일

실패
```
{
    "message": "userId에 해당하는 유저가 없습니다."
}
```
- 존재하지 않는 유저의 userId를 입력하면 오류가 발생합니다.
</details>
