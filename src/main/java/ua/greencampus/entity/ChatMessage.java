package ua.greencampus.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "chat_message")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
