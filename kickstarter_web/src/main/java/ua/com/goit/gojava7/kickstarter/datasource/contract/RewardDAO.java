package ua.com.goit.gojava7.kickstarter.datasource.contract;

import java.util.List;

import ua.com.goit.gojava7.kickstarter.domain.Reward;

public interface RewardDAO extends DataSource<Reward>{
    
     List<Reward> getByProject(Long projectId);
     
}
