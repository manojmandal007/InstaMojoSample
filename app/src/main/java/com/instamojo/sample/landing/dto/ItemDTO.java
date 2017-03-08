package com.instamojo.sample.landing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDTO {

    @JsonProperty("creation_date")
    public String creationDate;

    public List<String> tags;

    public String title;

    public String link;

    public String score;

    @JsonProperty("answer_count")
    public String answerCount;

    public OwnerDTO owner;

    @JsonProperty("question_id")
    public String questionId;

    @JsonProperty("user_id")
    public int userId;

}
