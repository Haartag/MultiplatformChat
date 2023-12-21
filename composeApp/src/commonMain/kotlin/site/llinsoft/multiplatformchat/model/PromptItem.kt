package site.llinsoft.multiplatformchat.model

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
    //val icon: Int,
) {
    TRANSLATE(
        //icon = R.drawable.baseline_translate_24
    ),
    CODE(
        //icon = R.drawable.baseline_code_24
    ),
    CHAT(
        //icon = R.drawable.baseline_chat_24
    ),
    LETTER(
        //icon = R.drawable.baseline_mail_24
    )
}
