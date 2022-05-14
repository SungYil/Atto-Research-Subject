# Atto-Research-Subject


### 프로젝트 개요
> 호스트들의 Alive 상태 체크 및 모니터링 API 서버 개발
> 다수의 호스트를 관리하는 네트워크 관제 프로그램으로 호스트를 등록하고
> 등록한 호스트의 상태를 모니터링 하는 서버

------
### 사용 언어 및 라이브러리
JAVA 11

SPRING BOOT 2.7.0

Mapstruct 1.4.1

lombok 0.2.0

JPA 2.7.0

mariadb 3.0.4

gradle 7.4.1

-------
### API 문서

Method|HTTP request|Description
---|---|---|
GET   | /hosts            | host 리스트 조회
GET   | /hosts/{hostId}   | hostsId와 일치하는 host 조회
POST  | /hosts            | host 등록
PUT   | /hosts/{hostId}   | hostId와 일치하는 host 수정
DELETE| /hosts/{hostId}| hostId와 일치하는 host 삭제
GET   | /hosts/{hostId}/host-alive-history | hostId와 일치하는 host의 Alive상태 조회
GET   | /hosts/host-alive-history | host의 Alive 상태 조회
POST  | /hosts/{hostId}/host-alive-history | hostId와 일치하는 host의 Alive상태 확인 후 등록
--------------------------------

##### GET   /hosts    host 리스트 조회 

###### Responses

Code 200 조회 성공

Example Value
```
   {
    "id" : 0,
    "ip" : "string",
    "name" : "string",
    "createdAt" : "string",
    "updatedAt" : "string"
   }
```

------------------
##### GET   /hosts/{hostsId}    hostId와 일치하는 host 조회

###### Responses
Code 200 조회 성공

Example Value
```
   {
    "id" : 0,
    "ip" : "string",
    "name" : "string",
    "createdAt" : "string",
    "updatedAt" : "string"
   }
```

Code 404 조회 실패

Example Value
```
{
  "message" : "Id가 일치하는 Host가 없습니다"
}
```

##### POST  /hosts    host 등록
###### Parameter
formdata
```
{
"ip" : "string",
"name" : "string"
}
```

###### Responses
Code 200 등록 성공

Example Value
```
{
"id" : 0,
"ip" : "string",
"name" : "string",
"createdAt" : "string",
"updatedAt" : "string"
}
```

Code 400 등록 실패

Example Value
```
{
"message" : "ip주소가 유효하지 않습니다" or "host의 개수가 100개 입니다" or "이름이 중복됩니다" or "ip가 중복됩니다"
{
```
------------------
##### PUT /hosts/{hostsId}    hostId와 일치하는 host 수정
###### Parameter
formdata
```
{
"ip" : "string",
"name" : "string",
}
###### Responses
Code 200 수정 성공

Example Value
```
{
"id" : 0,
"ip" : "string",
"name" : "string",
"createdAt" : "string",
"updatedAt" : "string"
}

Code 400 수정 실패
```
{
" message" : "이름이 중복됩니다" or "ip가 중복됩니다" or "ip주소가 유효하지 않습니다"
}
```

Code 404 수정 실패
```
{
"message" : "일치하는 id가 없습니다."
}
```
------------
##### DELETE  /hosts/{hostId} hostId와 일치하는 host 삭제


###### Responses
Code 404 삭제 실패
```
{
"message" : "일치하는 id가 없습니다"
}
```
---------------
##### GET /hosts/{hostId}/host-alive-history  hostId와 일치하는 host의 Alive 상태조회

###### Responses
Code 200 조회 성공
```
{
"hostId" : 0,
"ip" : "string",
"name" : "string",
"state" : "string",
"checkTime" : "string"
}
```

Code 404 조회 실패
```
{
"message" : "일치하는 id가 없습니다"
}
```

------------
##### GET /hosts/host-alive-history host의 Alive 상태 조회

###### Responses
Code 200 조회 성공
```
{
"hostId" : 0,
"ip" : "string",
"name" : "string",
"state" : "string",
"checkTime" : "string"
}
```

-----------------
##### POST  /hosts/{hostId}/host-alive-history  hostId와 일치하는 host의 Alive 상태 확인 후 등록
###### Responses
Code 200 등록 성공
```
{
"hostId" : 0,
"ip" : "string",
"name" : "string",
"state" : "string",
"checkTime" : "string"
}
```
Code 404 등록 실패
```
{
"message" : "일치하는 id가 없습니다"
}
```
