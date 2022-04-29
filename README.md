# jpa-basic
jpa-basic실습
# JPA (Java Persistence API)
## JPA란?
- JPA는 자바 진영에서 **ORM(Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스의 모음**
## ORM?
- **ORM(Object Relational Mapping)**
	- **자체적으로 쿼리문 생성(SQL문 불필요)**
		-   SQL Query가 아닌 자바 코드(메서드)로 데이터 조작이 가능
		-   물론, SQL Query도 사용할 수 있지만, @Query와 같은 Annotation으로 코드에서 사용할 수 있음
	- 장점:
		- SQL 중심적인 개발이 아닌 Method를 통해서 DB를 조작할 수 있어, 개발자는 객체 모델을 이용해서 비즈니스 로직을 구성하는데만 집중할 수 있음. (개발자가 CRUD용 쿼리를 따로 작성할 필요가 없다) 
	- 단점: 
		-  통계 쿼리처럼 매우 복잡한 SQL을 작성하기에는 적합하지 않다 (즉, 결국 SQL문을 써야할 수도 있음)
		- 프로젝트의 규모가 크고 복잡해서 설계가 잘못된 경우, 속도 저하 및 일관성을 무너뜨리는 문제점이 생길 수 있음
- **↔ SQL Mapper** (OR Mapper와는 상반되게 SQL문이 필요하다. 즉, 쿼리문이 자동생성되지 못함
	- 대표적인 프레임워크: iBatis, MyBatis

## HIBERNATE
- **Hibernate**: JPA를 구현한 ORM 프레임워크(**JPA인터페이스의 구현체라고 생각하면 된다.**)

## Hibernate 설정
```java
//※ application.yml
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/wisestudy?characterEncoding=UTF-8&serverTimezone=Asia/Seoul
spring.datasource.username=wisestudy
spring.datasource.password=wise$tudy
spring.datasource.initialization-mode=always
spring.datasource.dbcp2.validation-query=SELECT 1

spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.open-in-view=false
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true

spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true

logging.level.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.BasicBinder=TRACE
```
※출처: [느루.log](https://velog.io/@chyin370/ToyProject-JPAHibernate-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0) <br>

-   `datasource`: JDBC 드라이버 설정, driver-class, DB 접속 정보 설정
-   `jpa`: JPA 관련 설정, O-R 매핑을 위한 DB 플랫폼 정보 설정, 테이블 생성 관련 설정
-   `jpa.hibernate`: Hibernate 구현체에 대한 설정, DDL 사용, SQL 로그 출력 형식 설정 등
-   `logging.lovel.org.hibernate`: Hibernate 런타임 로그 관련 설정


## @Annotation
-   `@Entity`: 테이블에 대응되는 도메인 클래스를 의미, JPA가 동작할 때, 데이터를 다루는 작업 단위이기도 함
	-  기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자)
-   `@Table`: 테이블을 생성할 때 사용할 정보를 기술할 수 있음, 기술하지 않으면 클래스 명으로 테이블을 생성
-   `@Id`:  **Primary Key**를 지정
-   `@GeneratedValue`: Primary Key 컬럼의 데이터를 삽입할 때, 사용할 전략,  **GenerationType.IDENTITY**로 지정 시 PK 생성 전략을 데이터베이스에 위임
-   `@Enumerated`: Enum 타입을 사용시 지정


## JPA 구동방식
1. Persistence 클래스 시작
2. 설정 정보 조회
	- 스프링 부트 없이 순수하게 JPA,하이버네이트 사용시 resources폴더안에 META-INF폴더 생성 후 persistence.xml 파일을 생성해서 직접 사용해야합니다. 
	```
	//persistence.xml 
	 <?xml version="1.0" encoding="UTF-8"?>  
	 <persistence version="2.2"  
	  xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">  
	 <persistence-unit name="hello">  
	 <properties>  <!-- 필수 속성 -->  
	 <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>  
	 <property name="javax.persistence.jdbc.user" value="sa"/>  
	 <property name="javax.persistence.jdbc.password" value=""/>  
	 <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>  
	 <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>  
	<!--            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect"/>-->  
	 <!--dialect: 이런식으로 사용하면 해당 SQL 데이터베이스 방언 지원-->  
	  
	 <!-- 옵션 -->  
	 <property name="hibernate.show_sql" value="true"/>  
	 <property name="hibernate.format_sql" value="true"/>  
	 <property name="hibernate.use_sql_comments" value="true"/>  
	 <!--<property name="hibernate.jdbc.batch_size" value="10"/>-->  
	 <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->  </properties>  
	 </persistence-unit></persistence>
	```
	- 스프링 부트를 통해서 JPA를 사용하게 되면 스프링 부트가 제공하는 application.yml을 참고해서 사용합니다.
3. 설정 정보를 참고하여 EntityManagerFactory 생성
4. EntityManager 생성
