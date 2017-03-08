package com.instamojo.sample.detail.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by manoj on 8/3/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailDTO {
    public List<QuestionDetailDTO> items;
}
