package aaa.bivizul.a42project.data.repository

import aaa.bivizul.a42project.data.network.PlaaviService
import aaa.bivizul.a42project.domain.model.Plaavis
import aaa.bivizul.a42project.domain.repository.PlaavisRepository
import retrofit2.Response
import javax.inject.Inject

class PlaavisRepositoryImpl @Inject constructor(val plaaviService: PlaaviService) :
    PlaavisRepository {

    override suspend fun getPlaavis(): Response<List<Plaavis>> {
        return plaaviService.getPlaavis()
    }
}