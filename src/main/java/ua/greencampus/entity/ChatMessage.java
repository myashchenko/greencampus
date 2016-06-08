package ua.greencampus.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @SequenceGenerator(name = "chat_message_seq_gen", sequenceName = "chat_message_seq", allocationSize = 1)
    @GeneratedValue(generator = "chat_message_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @Column(name = "text", nullable = false, length = 4000)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_dialog_id")
    private ChatDialog dialog;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "send_date", nullable = false)
    private Calendar sendDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getSendDate() {
        return sendDate;
    }

    public void setSendDate(Calendar sendDate) {
        this.sendDate = sendDate;
    }

    public ChatDialog getDialog() {
        return dialog;
    }

    public void setDialog(ChatDialog dialog) {
        this.dialog = dialog;
    }

}
