package com.project.lpuniv.heechan.message.msgpage;
import com.project.lpuniv.heechan.message.dto.Message;
import lombok.Getter;

import java.util.List;

@Getter
public class MsgPage {

    private int total;
    private int currentPage;
    private List<Message> content;
    private int totalPages;
    private int startPage;
    private int endPage;

    public MsgPage(int total, int currentPage, int size, List<Message> content) {
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;
        if (total == 0) {
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            totalPages = total / size;
            if (total % size > 0) {
                totalPages++;
            }
            int modVal = currentPage % 5;
            startPage = currentPage / 5 * 5 + 1;
            if (modVal == 0) startPage -= 5;

            endPage = startPage + 4;
            if (endPage > totalPages) endPage = totalPages;
        }
    }


    public boolean hasNoArticles() {
        return total == 0;
    }

    public boolean hasArticles() {
        return total > 0;
    }
}