package ua.com.goit.gojava7.kickstarter.domain;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import ua.com.goit.gojava7.kickstarter.domain.Question;

public class QuestionTest {
	
    @Test
    public void testConstructor() {
        
        int projectId = 1;
        Question question = new Question(projectId , "q1","a1");
        
        assertThat(question.getProjectId(), is(projectId));
         
    }
    
    @Test
    public void testBean() {
        assertThat(Question.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanToString()
        ));
         
    }

}
