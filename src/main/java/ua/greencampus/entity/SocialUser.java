package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "social_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "provider_id", "provider_user_id"}),
                @UniqueConstraint(columnNames = {"user_id", "provider_id", "rank"}),
                @UniqueConstraint(columnNames = {"user_id", "provider_id"}),
                @UniqueConstraint(columnNames = {"provider_id", "access_token"})
        })
@Getter
@Setter
public class SocialUser {

    @Id
    @SequenceGenerator(name = "social_user_seq", sequenceName = "social_users_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "social_user_seq")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "provider_id")
    private String providerId;

    @Column(name = "provider_user_id")
    private String providerUserId;

    @Column(nullable = false)
    private int rank;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false, name = "access_token")
    private String accessToken;

    @Column
    private String secret;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expire_time")
    private Long expireTime;

    @Column(name = "create_date")
    private Date createDate = new Date();

}
