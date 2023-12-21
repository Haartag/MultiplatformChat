package site.llinsoft.multiplatformchat.utils

import site.llinsoft.multiplatformchat.db.PromptEntity
import site.llinsoft.multiplatformchat.model.ApiModel
import site.llinsoft.multiplatformchat.model.ChatTemperature
import site.llinsoft.multiplatformchat.model.PromptType


object DefaultPrompts {

    val defaults = listOf(
        PromptEntity(
            1L,
            PromptType.TRANSLATE.name,
            "I want you to act as an English translator, spelling corrector and improver. " +
                    "I will speak to you in any language and you will detect the language, " +
                    "translate it and answer in the corrected and improved version of my text, " +
                    "in English. Keep the meaning same, but make them more literary. " +
                    "I want you to only reply the correction, the improvements and nothing else, " +
                    "do not write explanations.",
            ApiModel.GPT35.model,
            ChatTemperature.MED.index.toDouble()
        ),
        PromptEntity(
            2L,
            PromptType.CODE.name,
            "Act as a coding tutor. I'll give you a topic and language, and you make a study " +
                    "plan to help me master it, with links to tutorials and video resources.",
            ApiModel.GPT4.model,
            ChatTemperature.MIN.index.toDouble()
        ),
        PromptEntity(
            3L,
            PromptType.LETTER.name,
            "Act as a email writer. I will give you a letter and a short statement for " +
                    "response, and you write me a short (5-7 sentences) response. Make it is more " +
                    "business-oriented and appropriate to put in the email.",
            ApiModel.GPT35.model,
            ChatTemperature.HI.index.toDouble()
        ),
        PromptEntity(
            4L,
            PromptType.CHAT.name,
            "Act as an English teacher. We're going to talk casual. You will check my answers " +
                    "for correct use of English and if it is sub-optimal, you will write a " +
                    "corrected version as Revised: {revised sentence}. Then you will keep " +
                    "the conversation going by asking questions.",
            ApiModel.GPT35.model,
            ChatTemperature.TOP.index.toDouble()
        ),
        PromptEntity(
            5L,
            PromptType.CODE.name,
            "Answer me with some short greating. Add +++ at the start.",
/*            "Act as professional android developer. You must help me with my kotlin " +
                    "multiplatform app. I will ask you some questions, and you must provide " +
                    "detailed answer. If it contains several steps, make it step-by-step. " +
                    "Use code samples, if needed.",*/
            ApiModel.GPT4.model,
            ChatTemperature.MIN.index.toDouble()
        )
    )
}