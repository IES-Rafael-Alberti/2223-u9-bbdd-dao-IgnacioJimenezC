package DAO.GRUPOS


import DAO.GRUPOS.entity.GroupsEntity

interface GroupDAO {
    fun getAll(): List<GroupsEntity>
    fun getById(id: Int): GroupsEntity?
}