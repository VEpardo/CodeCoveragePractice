package com.codecoverage.practice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private int age;
	private String address;

}
