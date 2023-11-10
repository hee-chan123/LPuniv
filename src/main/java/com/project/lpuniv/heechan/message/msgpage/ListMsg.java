package com.project.lpuniv.heechan.message.msgpage;

import com.project.lpuniv.heechan.message.dto.Message;
import com.project.lpuniv.heechan.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMsg {

    @Autowired
    private MessageService messageService;

    private int size = 10;

    public MsgPage getRecMsgPage(int userNo, int pageNo){ //일반 Rec
        int total = messageService.msgRecCnt(userNo);
        List<Message> content = messageService.selectUserRecMsg((pageNo - 1) * size, size, userNo);
        return new MsgPage(total,pageNo, size, content);
    }

    public MsgPage getSenMsgPage(int userNo, int pageNo){ //일반 Sen
        int total = messageService.msgSenCnt(userNo);
        List<Message> content = messageService.selectUserSenMsg((pageNo - 1) * size, size, userNo);
        return new MsgPage(total,pageNo, size, content);
    }

    public MsgPage getRecycleMsgPage(int userNo, int pageNo){ //일반 Recylce
        int total = messageService.recycleMsgCnt(userNo);
        List<Message> content = messageService.recycleMsg((pageNo - 1) * size, size, userNo);
        return new MsgPage(total,pageNo, size, content);
    }
    public MsgPage getSearchRecMsgPage(int userNo, int pageNo, String searchInput, String searchOp, String div){ //검색 Rec
        int total = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);
        List<Message> content = messageService.searchMsg((pageNo - 1) * size, size, userNo, searchInput, searchOp, div);
        return new MsgPage(total,pageNo, size, content);
    }

    public MsgPage getSearchSenMsgPage(int userNo, int pageNo, String searchInput, String searchOp, String div){ //검색 Sen
        int total = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);
        List<Message> content = messageService.searchMsg((pageNo - 1) * size, size, userNo, searchInput, searchOp, div);
        return new MsgPage(total,pageNo, size, content);
    }

    public MsgPage getSearchRecycleMsgPage(int userNo, int pageNo, String searchInput, String searchOp, String div){ //검색 Recycle
        int total = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);
        List<Message> content = messageService.searchMsg((pageNo - 1) * size, size, userNo, searchInput, searchOp, div);
        return new MsgPage(total,pageNo, size, content);
    }
}
