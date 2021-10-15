package br.com.javamoon.domain.draw;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.javamoon.domain.soldier.Army;
import br.com.javamoon.domain.soldier.Soldier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DRAW_LIST")
public class DrawList {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	@CreationTimestamp
	@Column(name = "creation_date", nullable = false)
	private LocalDate creationDate;
	
	@UpdateTimestamp
	@Column(name = "update_date", nullable = false)
	private LocalDate updateDate;
	
	@Column(name = "quarter_year", nullable = false)
	private String quarterYear;
	
	@ManyToOne
	@JoinColumn(name = "army_id", nullable = false)
	private Army army;
	
	@ManyToMany
	@JoinTable(
			name = "DRAW_LIST_HAS_SOLDIER",
			joinColumns = @JoinColumn(name = "draw_list_id"),
			inverseJoinColumns = @JoinColumn(name = "soldier_id"))
	private Set<Soldier> soldiers = new HashSet<Soldier>(0);
}