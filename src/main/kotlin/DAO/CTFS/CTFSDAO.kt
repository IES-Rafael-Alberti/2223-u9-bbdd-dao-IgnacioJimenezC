package DAO.CTFS

import DAO.CTFS.ENTITY.CTFSEntity
interface CTFSDAO {
    fun create(ctfid: Int,groupId: Int,points:Int):CTFSEntity
    fun getAll(): List<CTFSEntity>
    fun getById(ctfid: Int): CTFSEntity?
    fun update(groupId: Int):CTFSEntity
    fun delete(ctfid:Int,groupId:Int)
}