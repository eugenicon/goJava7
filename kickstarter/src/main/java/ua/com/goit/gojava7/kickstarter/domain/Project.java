package ua.com.goit.gojava7.kickstarter.domain;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Project {

    private int id;
    private String name;
    private long goalSum;
    private long balanceSum;
    private Date startDate;
    private Date endDate;
    private int categoryId;
    private String description;
    private String videoUrl;
    private String author;
    private List<Question> questions = new ArrayList<Question>();

    public Project() {
        // default bean constructor
    }
    
    public Project(int id, String name, String author, int categoryId) {
        this(name, author, categoryId);
        this.id = id;
    }

    public Project(String name, String author, int categoryId) {
        this.name = name;
        this.categoryId = categoryId;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGoalSum() {
        return goalSum;
    }

    public void setGoalSum(long goalSum) {
        this.goalSum = goalSum;
    }

    public long getBalanceSum() {
        return balanceSum;
    }

    public void setBalanceSum(long balanceSum) {
        this.balanceSum = balanceSum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long daysLeft() {
        long ms = 0;
        if (getEndDate() != null) {
            ms = getEndDate().getTime() - System.currentTimeMillis(); 
        }
        return ms / (1000L * 60L * 60L * 24L);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project \"" + name.substring(0, Math.min(15, name.length())) + "..." + "\" by " + author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
