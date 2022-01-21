package com.jay.chat.dto;

import com.jay.chat.constant.MessageType;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Jay Yang
 * @date 2022/1/18
 */
@Getter
public class MessageDTO {

    private String id;

    private MessageType type;

    private String body;

    private LocalDateTime createTime;

}
