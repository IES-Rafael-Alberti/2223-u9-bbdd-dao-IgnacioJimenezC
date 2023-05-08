package DAO.GRUPOS

import DAO.GRUPOS.entity.GroupsEntity
import javax.sql.DataSource

class GroupDAOH2(private var baseDATOS:DataSource):GroupDAO {
    override fun getById(id: Int): GroupsEntity? {
        val sql = "SELECT * FROM GRUPOS WHERE GRUPOID = ?"
        return baseDATOS.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    GroupsEntity(
                        grupoid = rs.getInt("GRUPOID"),
                        grupoDesc = rs.getString("GRUPODESC"),
                        mejorCtfId = rs.getInt("MEJORPOSCTFID")
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getAll(): List<GroupsEntity> {
        val sql = "SELECT * FROM GRUPOS"
        return baseDATOS.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val users = mutableListOf<GroupsEntity>()
                while (rs.next()) {
                    users.add(
                        GroupsEntity(
                            grupoid = rs.getInt("GRUPOID"),
                            grupoDesc = rs.getString("GRUPODESC"),
                            mejorCtfId = rs.getInt("MEJORPOSCTFID")
                        )
                    )
                }
                users
            }
        }
    }
}