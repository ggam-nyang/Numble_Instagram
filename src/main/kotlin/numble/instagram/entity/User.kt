package numble.instagram.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(name = "profile_image_url")
    var profileImageUrl: String,

    @Column(name = "nick_name")
    var nickName: String,

    // TODO: Follwers, Followings

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var postings: MutableList<Posting>,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var comments: MutableList<Comment>,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var replies: MutableList<Reply>,
) : BaseEntity()
