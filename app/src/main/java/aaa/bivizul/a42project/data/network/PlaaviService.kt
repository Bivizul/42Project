package aaa.bivizul.a42project.data.network

import aaa.bivizul.a42project.domain.model.Plaavi
import aaa.bivizul.a42project.domain.model.Plaavis
import aaa.bivizul.a42project.domain.model.Plaavig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaaviService {

    @GET("42Project/plaavis.json")
    suspend fun getPlaavis(): Response<List<Plaavis>>

    @POST("42Project/plaavi.php")
    suspend fun getPlaavi(@Body plaavi: Plaavi): Response<Plaavig>

}