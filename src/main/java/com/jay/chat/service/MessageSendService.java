package com.jay.chat.service;

import com.jay.chat.dto.MessageBodyDTO;
import com.jay.chat.entity.User;

/**
 * @author Jay Yang
 * @date 2022/1/18
 */
public interface MessageSendService {

    /**
     * send message
     *
     * @param from        发送人
     * @param to          接收人
     * @param messageBody 消息体
     */
    void send(User from, User to, MessageBodyDTO messageBody);

}
