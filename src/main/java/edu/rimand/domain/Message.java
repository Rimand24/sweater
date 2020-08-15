package edu.rimand.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more then 2048)")
    private String text;

    @Length(max = 255, message = "Message too long (more then 255)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Message() {
    }

    public Message(User user, String text, String tag) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName (){
        return author != null ? author.getUsername() : "<none>";
    }

}