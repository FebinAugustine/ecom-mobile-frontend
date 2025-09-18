package com.ecom.core.data.dto

import com.ecom.core.domain.model.Reply
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyDto(
    @SerialName("_id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("review_id")
    val reviewId: String,
    val reply: String
)

fun ReplyDto.toDomain(): Reply {
    return Reply(
        id = this.id,
        userId = this.userId,
        reviewId = this.reviewId,
        reply = this.reply
    )
}
