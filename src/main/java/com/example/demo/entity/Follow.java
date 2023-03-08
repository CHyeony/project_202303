package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
	indexes = {
		@Index(name = "idx_from_id", columnList = "from_id"),
		@Index(name = "idx_to_id", columnList = "to_id")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "follow_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "from_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private UserAccount from;

	@ManyToOne
	@JoinColumn(name = "to_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private UserAccount to;

	@Builder
	public Follow(Long id, UserAccount from, UserAccount to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}
}
