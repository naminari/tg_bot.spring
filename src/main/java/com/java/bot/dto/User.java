package com.java.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    /**
     * user's tg_name
     */
    @JsonProperty("tg_name")
    private final String tg_name;
    /**
     * chat_id
     */
    @JsonProperty("chat_id")
    private final Long chat_id;

}