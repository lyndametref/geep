package net.madscientists.geep.core.model

import java.time.LocalDateTime

data class Attachment(
    val id: String,
    val recordId: String,
    val attachmentType: String,
    val uri: String,
    val label: String? = null,
    val capturedAt: LocalDateTime? = null
)
