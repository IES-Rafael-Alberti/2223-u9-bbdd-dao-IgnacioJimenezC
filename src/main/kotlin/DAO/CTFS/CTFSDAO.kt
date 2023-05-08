package DAO.CTFS

import DAO.CTFS.ENTITY.CTFSEntity
interface CTFSDAO {
    fun create(ctfs:CTFSEntity):CTFSEntity
    fun getAll(): List<CTFSEntity>
    fun getById(ctfid: Int): CTFSEntity?
    fun delete(ctfid:Int,groupId:Int)
}