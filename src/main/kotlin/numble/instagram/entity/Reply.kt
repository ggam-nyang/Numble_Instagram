package numble.instagram.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "replys")
class Reply(
    @ManyToOne
    @JoinColumn(name = "comment_id")
    var comment: Comment,

    var content: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var author: User,

) : BaseEntity()
