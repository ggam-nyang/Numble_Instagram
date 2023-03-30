package numble.instagram.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "postings")
class Posting(
    var content: String,

    @Column(name = "image_url")
    var imageUrl: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var author: User,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "posting")
    var comments: MutableList<Comment>

) : BaseEntity()
