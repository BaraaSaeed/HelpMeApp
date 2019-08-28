package co.grandcircus.HelpMeApp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.HelpMeApp.model.OrgSelection;

public interface OrgSelectionDao extends JpaRepository<OrgSelection, Long> {

	List<OrgSelection> findAllNameByKeyWords(String keyWord);
	
	List<OrgSelection> findAllByName(String name);
	
	List<OrgSelection> findAllByNameAndKeyWords(String name, String keyWords);

}
