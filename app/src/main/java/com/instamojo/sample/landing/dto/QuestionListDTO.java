package com.instamojo.sample.landing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionListDTO {

    @JsonProperty("quota_max")
    public String quotaMax;

    public List<ItemDTO> items;
}
