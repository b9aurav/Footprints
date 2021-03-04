package com.msu.footprints.models;

import java.io.Serializable;

public class Event implements Serializable{
    private String documentId;
    private String Title, Description, Summary, Rules, Rounds;
    private String Provide, Instruction, GenInstruction, Criteria, Specification, Presentation, Abstract, Submission, Requirements;
    private String Fees, RegisterLink, ImageURL, TeamSize;
    private boolean Category, RuleRound;

    public Event(){
    }

    public Event(String ImageURL, String title, String summary, String des){
        this.ImageURL = ImageURL;
        this.Title = title;
        this.Summary = summary;
        this.Description = des;
    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String title){
        Title = title;
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(String description){
        Description = description;
    }

    public String getSummary(){
        return Summary;
    }

    public void setSummary(String summary){
        Summary = summary;
    }

    public String getRules(){
        return Rules;
    }

    public void setRules(String rules){
        Rules = rules;
    }

    public String getRounds(){
        return Rounds;
    }

    public void setRounds(String rounds){
        Rounds = rounds;
    }

    public String getProvide(){
        return Provide;
    }

    public void setProvide(String provide){
        Provide = provide;
    }

    public String getInstruction(){
        return Instruction;
    }

    public void setInstruction(String instruction){
        Instruction = instruction;
    }

    public String getGenInstruction(){
        return GenInstruction;
    }

    public void setGenInstruction(String genInstruction){
        GenInstruction = genInstruction;
    }

    public String getCriteria(){
        return Criteria;
    }

    public void setCriteria(String criteria){
        Criteria = criteria;
    }

    public String getSpecification(){
        return Specification;
    }

    public void setSpecification(String specification){
        Specification = specification;
    }

    public String getFees(){
        return Fees;
    }

    public void setFees(String fees){
        Fees = fees;
    }

    public String getRegisterLink(){
        return RegisterLink;
    }

    public void setRegisterLink(String registerLink){
        RegisterLink = registerLink;
    }

    public String getImageURL(){
        return ImageURL;
    }

    public void setImageURL(String imageURL){
        ImageURL = imageURL;
    }

    public String getTeamSize(){
        return TeamSize;
    }

    public void setTeamSize(String teamSize){
        TeamSize = teamSize;
    }

    public boolean isCategory(){
        return Category;
    }

    public void setCategory(boolean category){
        Category = category;
    }

    public boolean isRuleRound(){
        return RuleRound;
    }

    public void setRuleRound(boolean ruleRound){
        RuleRound = ruleRound;
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId = documentId;
    }

    public String getPresentation(){
        return Presentation;
    }

    public void setPresentation(String presentation){
        Presentation = presentation;
    }

    public String getAbstract(){
        return Abstract;
    }

    public void setAbstract(String anAbstract){
        Abstract = anAbstract;
    }

    public String getSubmission(){
        return Submission;
    }

    public void setSubmission(String submission){
        Submission = submission;
    }

    public String getRequirements(){
        return Requirements;
    }

    public void setRequirements(String requirements){
        Requirements = requirements;
    }
}
