package numble.instagram.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(name = "profile_image_url")
    var profileImageUrl: String = "",

    @Column(name = "nick_name")
    var nickName: String = "",

    // TODO: Follwers, Followings

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    var postings: MutableList<Posting> = arrayListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    var comments: MutableList<Comment> = arrayListOf(),

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    var replies: MutableList<Reply> = arrayListOf(),

    @Column(name = "activated")
    @JsonIgnore
    var isActivated: Boolean = false,

    // For auth
    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    val authorities: Set<Authority>? = null

) : BaseEntity()
