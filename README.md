

## 동시성 제어 - 쿠폰 발급
정해진 수량만 쿠폰 발급되도록 코드를 작성해보자 
<br>

1. Mysql에서 제공하는 비관적 락을 사용한 동시성 제어   
퍼포먼스 테스트 (JMeter)  
임의의 유저 이메일을 CSV요청 정보에 담고 특정 쿠폰을 발급   
조건 : 500Thread, Ramp-up periods: 1, 쿠폰 수량: 180개

- Aggregate Report
![Aggregate Report](img.png) 

- TPS 수치  
<img src="../../../Desktop/쿠폰 발급 요청 TPS.png" width="400" height="200"/>

--- 아쉬운 점 ---  
데이터베이스가 Innodb엔진이라 수량 수정하는 부분에 대해서 row락이 걸리지만 단일 수정하는 사항이 단일 row이기때문에 사실상 테이블락과 다를게 없다고 생각한다.  
쿠폰 잔여 수량 측정하는 코드를 수정해야할 것 같다.
