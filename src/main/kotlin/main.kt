import DAO.CTFS.ENTITY.CTFSEntity
import kotlinx.cli.*

fun main(args: Array<String>) {

    val participaciones = listOf(CTFSEntity(1, 1, 3), CTFSEntity(1, 2, 101), CTFSEntity(2, 2, 3), CTFSEntity(2, 1, 50), CTFSEntity(2, 3, 1), CTFSEntity(3, 1, 50), CTFSEntity(3, 3, 5))
    val mejoresCtfByGroupId = calculaMejoresResultados(participaciones)
    println(mejoresCtfByGroupId)


    val parser = ArgParser("un9pe")
    val input by parser.option(ArgType.Int, shortName = "a", description = "Añade un participante, sintaxis: -a <ctfsid> <grupoid> <puntuacion>").multiple()
    println(input)

    val delete by parser.option(ArgType.Int, shortName = "d", description = "Borra un grupo, sintaxis: -d <ctfif> <grupoid>").multiple()
    val listado by parser.option(ArgType.Int, shortName = "l", description = "lista el grupo seleccionado, sintaxis: -l <grupoid>").multiple()
    parser.parse(args)

}


private fun calculaMejoresResultados(participaciones: List<CTFSEntity>): MutableMap<Int, Pair<Int, CTFSEntity>> {
    val participacionesByCTFId = participaciones.groupBy { it.id }
    var participacionesByGrupoId = participaciones.groupBy { it.grupoId }
    val mejoresCtfByGroupId = mutableMapOf<Int, Pair<Int, CTFSEntity>>()

    participacionesByCTFId.values.forEach { ctfs ->
        val ctfsOrderByPuntuacion = ctfs.sortedBy { it.puntuacion }.reversed()
        participacionesByGrupoId.keys.forEach { grupoId ->
            val posicionNueva = ctfsOrderByPuntuacion.indexOfFirst { it.grupoId == grupoId }
            if (posicionNueva >= 0) {
                val posicionMejor = mejoresCtfByGroupId.getOrDefault(grupoId, null)
                if (posicionMejor != null) {
                    if (posicionNueva < posicionMejor.first)
                        mejoresCtfByGroupId.set(grupoId, Pair(posicionNueva, ctfsOrderByPuntuacion.get(posicionNueva)))
                } else
                    mejoresCtfByGroupId.set(grupoId, Pair(posicionNueva, ctfsOrderByPuntuacion.get(posicionNueva)))

            }
        }
    }
    return mejoresCtfByGroupId
}