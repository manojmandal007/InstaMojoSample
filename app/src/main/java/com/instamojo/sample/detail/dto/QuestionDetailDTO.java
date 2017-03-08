package com.instamojo.sample.detail.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.instamojo.sample.common.dto.BaseItem;
import com.instamojo.sample.common.view.ViewType;
import com.instamojo.sample.landing.dto.OwnerDTO;

/**
 * Created by manoj on 8/3/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDetailDTO extends BaseItem {

    public OwnerDTO owner;

    @JsonProperty("title")
    public String questionTitle;

    @JsonProperty("body")
    public String body;

    public String header;

    public QuestionDetailDTO(ViewType type) {
        super(type);
    }

    public QuestionDetailDTO() {
    }


}
