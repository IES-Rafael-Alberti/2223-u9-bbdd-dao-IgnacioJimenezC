package ConnectionFactory

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object ConnectionFactory {
    private const val URL = "jdbc:h2:./ctfs.mv.db"
    private const val USERNAME = "user"
    private const val PASSWORD = "user"

    fun conectarse(): Connection?{
        var conexion : Connection? = null
        try {
            Class.forName("org.h2.Driver")
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD)
        } catch (e: SQLException){
            println(e)
        }
        return conexion
    }
}