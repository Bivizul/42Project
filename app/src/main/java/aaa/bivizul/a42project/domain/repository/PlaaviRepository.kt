package aaa.bivizul.a42project.domain.repository

import aaa.bivizul.a42project.domain.model.Plaavi
import aaa.bivizul.a42project.domain.model.Plaavig
import retrofit2.Response

interface PlaaviRepository {

    suspend fun getPlaavig(plaavi: Plaavi): Response<Plaavig>

}