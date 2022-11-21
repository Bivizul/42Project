package aaa.bivizul.a42project.domain.model


import androidx.annotation.Keep

@Keep
data class Plaavis(
    val id: Int,
    val plaaviDesc: String,
    val plaaviImage: String,
    val plaaviTitle: String
)