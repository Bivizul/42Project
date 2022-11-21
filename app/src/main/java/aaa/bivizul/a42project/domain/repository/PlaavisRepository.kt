package aaa.bivizul.a42project.domain.repository

import aaa.bivizul.a42project.domain.model.Plaavis
import retrofit2.Response

interface PlaavisRepository {

    suspend fun getPlaavis() : Response<List<Plaavis>>

}