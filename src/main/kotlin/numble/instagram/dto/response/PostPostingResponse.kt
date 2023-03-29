package numble.instagram.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PostPostingResponse(
    val id: Long,
    @JsonProperty("image_url") val imageUrl: String,
    val content: String
)
