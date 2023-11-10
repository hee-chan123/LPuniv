package com.project.lpuniv.heechan.message.controller;

import com.project.lpuniv.heechan.message.dto.Message;
import com.project.lpuniv.heechan.message.msgpage.ListMsg;
import com.project.lpuniv.heechan.message.msgpage.MsgPage;
import com.project.lpuniv.heechan.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ListMsg listMsg;

    @GetMapping("/main")
    public String test(){
        return "heechan/message/test";
    }

    @GetMapping("/message/recmsg") //받은메시지 함
    public String recMsg(@RequestParam(value = "searchInput", required = false) String searchInput, @RequestParam(value = "searchOp", required = false) String searchOp,
                         @RequestParam(value = "div", required = false) String div, @RequestParam(value = "pageNo", required = false) String pageNoVal,
                         Model model){
        int pageNo = 1;
        if(pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        int userNo = 1;
        if(searchInput != null && searchOp != null && div != null){
            MsgPage msgPage = listMsg.getSearchRecMsgPage(userNo, pageNo, searchInput, searchOp, div);
            int msgCount = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        } else {
            MsgPage msgPage = listMsg.getRecMsgPage(userNo, pageNo);
            //위 아래 코드 세션으로 유저 번호 넘기는 걸로 수정해야 함
            int msgCount = messageService.msgRecCnt(userNo);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        }
        return "heechan/message/recmsg";
    }

    @GetMapping("/message/senmsg") //보낸메시지 함
    public String senMsg(@RequestParam(value = "searchInput", required = false) String searchInput, @RequestParam(value = "searchOp", required = false) String searchOp,
                         @RequestParam(value = "div", required = false) String div, @RequestParam(value = "pageNo", required = false) String pageNoVal,
                         Model model){
        int pageNo = 1;
        if(pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        int userNo = 1;
        if(searchInput != null && searchOp != null && div != null){
            MsgPage msgPage = listMsg.getSearchSenMsgPage(userNo, pageNo, searchInput, searchOp, div);
            int msgCount = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        } else{
            MsgPage msgPage = listMsg.getSenMsgPage(userNo, pageNo);
            //위 아래 코드 세션으로 유저 번호 넘기는 걸로 수정해야 함
            int msgCount = messageService.msgSenCnt(userNo);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        }
        return "heechan/message/senmsg";
    }

    @GetMapping("/message/recycle") //휴지통
    public String recycle(@RequestParam(value = "searchInput", required = false) String searchInput, @RequestParam(value = "searchOp", required = false) String searchOp,
                          @RequestParam(value = "div", required = false) String div, @RequestParam(value = "pageNo", required = false) String pageNoVal,
                          Model model){
        int pageNo = 1;
        if(pageNoVal != null){
            pageNo = Integer.parseInt(pageNoVal);
        }

        int userNo = 1;
        if(searchInput != null && searchOp != null && div != null){
            MsgPage msgPage = listMsg.getSearchRecycleMsgPage(userNo, pageNo, searchInput, searchOp, div);
            int msgCount = messageService.searchMsgCnt(userNo, searchInput, searchOp, div);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        } else {
            MsgPage msgPage = listMsg.getRecycleMsgPage(userNo, pageNo);
            //위 아래 코드 세션으로 유저 번호 넘기는 걸로 수정해야 함
            int msgCount = messageService.msgRecCnt(userNo);

            model.addAttribute("msgPage", msgPage);
            model.addAttribute("msgCount", msgCount);
        }
        return "heechan/message/recycle";
    }

    @GetMapping("/message/msgview") //메시지 상세내용
    public String view(@RequestParam("msgId") int msgId, Model model){
        Message message = messageService.selectMsg(msgId);
        int userNO = 1; //세션으로 userNo받는걸로 수정 해야 함

        if(message.getReadFlag() == 0){
            messageService.readMsg(msgId);
        }

        model.addAttribute("message", message);
        model.addAttribute("userNO", userNO);
        return "heechan/message/msgview";
    }

    @GetMapping("/message/writeform") //메시지 작성
    public String write(){
        return "heechan/message/msgwrite";
    }

    @PostMapping("/message/recdel") //받은 쪽지 삭제
    public String recDel(@RequestParam("msgId") int msgId){
        Message msg = messageService.selectMsg(msgId);
        messageService.recDel(msgId);

        if(msg.getRecDel() == 2 && msg.getSenDel() == 2){ //보낸사람, 받은사람 모두 쪽지를 삭제 했을 때 DB에서도 삭제
            messageService.msgDel(msgId);
        }

        return "heechan/message/recmsg";
    }

    @PostMapping("/message/sendel") //보낸 쪽지 삭제
    public String senDel(@RequestParam("msgId") int msgId){
        Message msg = messageService.selectMsg(msgId);
        messageService.senDel(msgId);

        if(msg.getRecDel() == 2 && msg.getSenDel() == 2){ //보낸사람, 받은사람 모두 쪽지를 삭제 했을 때 DB에서도 삭제
            messageService.msgDel(msgId);
        }

        return "heechan/message/senmsg";
    }

    @PostMapping("/message/recyclerecmsg") //받은 쪽지 복구
    public String recycleRecMsg(@RequestParam("msgId") int msgId){
        messageService.recycleRecMsg(msgId);

        return "heechan/message/recycle";
    }

    @PostMapping("/message/recyclesenmsg") //보낸 쪽지 복구
    public String recycleSenMsg(@RequestParam("msgId") int msgId){
        messageService.recycleSenMsg(msgId);

        return "heechan/message/recycle";
    }
}
