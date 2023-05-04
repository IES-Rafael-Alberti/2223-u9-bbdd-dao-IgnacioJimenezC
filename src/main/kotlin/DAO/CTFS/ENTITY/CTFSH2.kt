package DAO.CTFS.ENTITY

import java.util.*
import javax.sql.DataSource

class CTFSH2(private var fuenteDeDator:DataSource) {
    /*TODO() terminar el ctfs DAO*/
    override fun create(ctfid: Int,groupId: Int,points:Int): CTFSEntity {
        val sql = "INSERT INTO tuser (id, name, email) VALUES (?, ?, ?)"
        return fuenteDeDator.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.id.toString())
                stmt.setString(2, user.name)
                stmt.setString(3, user.email)
                stmt.executeUpdate()
                user
            }
        }
    }

    override fun getById(id: UUID): UserEntity? {
        val sql = "SELECT * FROM tuser WHERE id = ?"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    UserEntity(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        email = rs.getString("email")
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): List<UserEntity> {
        val sql = "SELECT * FROM tuser"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val users = mutableListOf<UserEntity>()
                while (rs.next()) {
                    users.add(
                        UserEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            email = rs.getString("email")
                        )
                    )
                }
                users
            }
        }
    }

    override fun update(user: UserEntity):UserEntity {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, user.name)
                stmt.setString(2, user.email)
                stmt.setString(3, user.id.toString())
                stmt.executeUpdate()
                user
            }
        }
    }

    override fun delete(id: UUID) {
        val sql = "DELETE FROM tuser WHERE id = ?"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                stmt.executeUpdate()
            }
        }
    }
}