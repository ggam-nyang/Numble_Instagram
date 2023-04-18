package numble.instagram.controller

import numble.instagram.dto.request.PostPostingRequest
import numble.instagram.dto.response.PostPostingResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/postings")
class PostingController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("")
    fun createPosting(
        @RequestBody request: PostPostingRequest
    ): ResponseEntity<PostPostingResponse> {
        // get userId
        logger.info("Hello~ ")


        return ResponseEntity(PostPostingResponse(1, "temp", "temp"), HttpStatus.OK)
    }


}