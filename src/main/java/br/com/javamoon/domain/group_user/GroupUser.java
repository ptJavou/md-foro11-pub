package br.com.javamoon.domain.group_user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.javamoon.domain.cjm_user.CJM;
import br.com.javamoon.domain.soldier.Army;
import br.com.javamoon.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Getter
@Setter
@Entity
@Table(name="GROUP_USER")
public class GroupUser extends User{

	@ToString.Exclude
	@NotNull(message = "É necessário selecionar uma força armada.")
	@ManyToOne
	@JoinColumn(name = "army_id", nullable = false)
	private Army army;
	
	@ToString.Exclude
	@NotNull(message = "È necessário selecionar uma unidade")
	@ManyToOne
	@JoinColumn(nullable = false)
	private CJM cjm;
}
