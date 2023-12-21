package site.llinsoft.multiplatformchat.model

enum class ApiModel(
    val model: String,
    val label: String
) {
    GPT35(
        model = "gpt-3.5-turbo-1106",
        label = "gpt 3.5"
    ),
    GPT4(
        model = "gpt-4-1106-preview",
        label = "gpt 4"
    )
}