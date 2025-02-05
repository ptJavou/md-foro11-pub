package br.com.javamoon.domain.draw;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.javamoon.domain.cjm_user.Auditorship;
import br.com.javamoon.domain.cjm_user.CJM;
import br.com.javamoon.domain.soldier.Army;

public interface DrawRepository extends JpaRepository<Draw, Integer>{

	Draw findByProcessNumber(String processNumber);
	
	@Query("from Draw d join d.soldiers s where s.id = :soldierId and d.justiceCouncil.id = 2")
	List<Draw> findBySoldierJusticeCouncil(@Param("soldierId") Integer soldierId);
	
	@Query("from Draw d where d.cjmUser.auditorship.cjm = :cjm")
	List<Draw> findByCJM(@Param("cjm") CJM cjm);
	
	@Query("from Draw d where d.cjmUser.auditorship.cjm = :cjm and d.army = :army")
	List<Draw> findByCJMAndArmy(@Param("cjm") CJM cjm, @Param("army") Army army);
	
	List<Draw> findByJusticeCouncil(JusticeCouncil justiceCouncil);
	
	@Query("from Draw d where d.cjmUser.auditorship = :auditorship and d.justiceCouncil = :justiceCouncil")
	List<Draw> findByAuditorshipJusticeCouncil(@Param("auditorship") Auditorship auditorship, @Param("justiceCouncil") JusticeCouncil justiceCouncil);
	
	List<Draw> findByProcessNumberIgnoreCaseContaining(String processNumber);
	
	@Query("from Draw d join d.soldiers sd where sd.id = :soldierId and d.finished = false and d.justiceCouncil.name = 'CEJ'")
	List<Draw> findUnfinishedBySoldierAndCJM(@Param("soldierId") Integer soldierId);
	
	@Query("from Draw d where d.cjmUser.auditorship.id = :auditorshipId and d.justiceCouncil.id = 2 and d.finished = false")
	List<Draw> findUnfinishedByAuditorship(@Param("auditorshipId") Integer auditorshipId);
	
	@Query("select count(d) from Draw d join d.soldiers s where s.id = :soldierId")
	Integer countDrawBySoldier(@Param("soldierId") Integer soldierId);
	
	@Query("from Draw d join d.soldiers s where s.id = :soldierId and (d.creationDate between :startDate and :endDate)")
	List<Draw> findBySoldierBetweenDates(@Param("soldierId") Integer soldierId, 
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
