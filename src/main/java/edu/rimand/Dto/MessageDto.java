package edu.rimand.Dto;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import edu.rimand.domain.util.MessageHelper;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageDto {

    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }
}
