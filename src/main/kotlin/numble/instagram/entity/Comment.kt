package numble.instagram.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "comments")
class Comment(
    @ManyToOne
    @JoinColumn(name = "posting_id")
    var posting: Posting,

    var content: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var author: User,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "comment")
    var replies: MutableList<Reply>

) : BaseEntity()
