package aaa.bivizul.a42project.data.repository

import aaa.bivizul.a42project.data.network.PlaaviService
import aaa.bivizul.a42project.domain.model.Plaavi
import aaa.bivizul.a42project.domain.model.Plaavig
import aaa.bivizul.a42project.domain.repository.PlaaviRepository
import retrofit2.Response
import javax.inject.Inject

class PlaaviRepositoryImpl @Inject constructor(val plaaviService: PlaaviService):PlaaviRepository {

    override suspend fun getPlaavig(plaavi: Plaavi) : Response<Plaavig> {
        return plaaviService.getPlaavi(plaavi = plaavi)
    }

}