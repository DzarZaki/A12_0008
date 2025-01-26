import com.example.finalpam.model.Instruktur
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface InstrukturService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @POST("instruktur/tambahinstruktur.php")
    suspend fun insertInstruktur(@Body instruktur: Instruktur)

    @GET("instruktur/bacainstruktur.php")
    suspend fun getAllInstruktur(): List<Instruktur>

    @GET("instruktur/bacainstruktur1.php")
    suspend fun getInstrukturById(@Query("id_instruktur") idInstruktur: String): Instruktur

    @PUT("instruktur/editinstruktur.php")
    suspend fun updateInstruktur(@Query("id_instruktur") idInstruktur: String, @Body instruktur: Instruktur)

    @DELETE("instruktur/hapusinstruktur.php")
    suspend fun deleteInstruktur(@Query("id_instruktur") idInstruktur: String): Response<Void>
}
