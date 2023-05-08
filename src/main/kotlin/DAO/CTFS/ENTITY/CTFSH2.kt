package DAO.CTFS.ENTITY

import DAO.CTFS.CTFSDAO
import java.util.*
import javax.sql.DataSource

class CTFSH2(private var fuenteDeDator:DataSource):CTFSDAO {
    /*TODO() terminar el ctfs DAO*/
    override fun create(ctfs:CTFSEntity): CTFSEntity {
        val sql = "INSERT INTO CTFS (CTFID, GRUPOID, PUNTUACION) VALUES (?, ?, ?)"
        return fuenteDeDator.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, ctfs.id)
                stmt.setInt(2, ctfs.grupoId)
                stmt.setInt(3, ctfs.puntuacion)
                stmt.executeUpdate()
                ctfs
            }
        }
    }

    override fun getById(id: Int): CTFSEntity? {
        val sql = "SELECT * FROM CTFS WHERE CTFID = ?"
        return fuenteDeDator.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    CTFSEntity(
                        id = rs.getInt("CTFID"),
                        grupoId = rs.getInt("GRUPOID"),
                        puntuacion = rs.getInt("PUNTUACION")
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): List<CTFSEntity> {
        val sql = "SELECT * FROM CTFS"
        return fuenteDeDator.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val ctfss = mutableListOf<CTFSEntity>()
                while (rs.next()) {
                    ctfss.add(
                        CTFSEntity(
                            id = rs.getInt("CTFID"),
                            grupoId = rs.getInt("GRUPOID"),
                            puntuacion = rs.getInt("PUNTUACION")
                        )
                    )
                }
                ctfss
            }
        }
    }

    override fun delete(ctfsId: Int,grupoID:Int) {
        val sql = "DELETE FROM CTFS WHERE CTFID = ? AND GRUPOID = ?"
        fuenteDeDator.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, ctfsId)
                stmt.setInt(2,grupoID)
                stmt.executeUpdate()
            }
        }
    }
}