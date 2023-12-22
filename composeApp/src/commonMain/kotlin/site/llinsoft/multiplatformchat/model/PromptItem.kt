package site.llinsoft.multiplatformchat.model

import io.github.skeptick.libres.images.Image
import site.llinsoft.multiplatformchat.Res
import site.llinsoft.multiplatformchat.db.PromptEntity

data class PromptItem(
    val id: Long,
    val type: PromptType,
    val prompt: String,
    val model: ApiModel,
    val temperature: Float,
)

fun PromptItem.toPromptEntity(): PromptEntity {
    return PromptEntity(
        this.id,
        this.type.name,
        this.prompt,
        this.model.model,
        this.temperature.toDouble()
    )
}

fun PromptEntity.toPromptItem(): PromptItem {
    return PromptItem(
        this.id,
        PromptType.valueOf(this.type),
        this.prompt,
        this.model.makeApiModel(),
        this.temperature.toFloat()
    )
}

private fun String.makeApiModel(): ApiModel {
    return when (this) {
        ApiModel.GPT4.model -> ApiModel.GPT4
        ApiModel.GPT35.model -> ApiModel.GPT35
        else -> ApiModel.GPT35
    }
}

enum class PromptType(
    val icon: Image,
) {
    TRANSLATE(
        icon = Res.image.ic_translate
    ),
    CODE(
        icon = Res.image.ic_code
    ),
    CHAT(
        icon = Res.image.ic_chat
    ),
    LETTER(
        icon = Res.image.ic_mail
    )
}
