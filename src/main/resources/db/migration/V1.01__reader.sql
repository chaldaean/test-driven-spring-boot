CREATE TABLE reader (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY,
	name varchar(255) not null,
	age int not null,
	favourite_author varchar(255)
);