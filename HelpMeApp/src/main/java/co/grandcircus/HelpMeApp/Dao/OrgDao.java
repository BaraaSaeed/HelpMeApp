package co.grandcircus.HelpMeApp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import co.grandcircus.HelpMeApp.model.Org;

public interface OrgDao extends JpaRepository<Org, Long> {

	
}
