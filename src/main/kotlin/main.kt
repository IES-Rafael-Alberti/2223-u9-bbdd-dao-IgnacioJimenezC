import kotlinx.cli.*





fun main(args: Array<String>) {
    /*TODO() Las participaciones deberían de ser el resultado de una query */
    val participaciones = listOf(Ctf(1, 1, 3), Ctf(1, 2, 101), Ctf(2, 2, 3), Ctf(2, 1, 50), Ctf(2, 3, 1), Ctf(3, 1, 50), Ctf(3, 3, 5))
    val mejoresCtfByGroupId = calculaMejoresResultados(participaciones)
    println(mejoresCtfByGroupId)


    val parser = ArgParser("un9pe")
    val input by parser.option(ArgType.String, shortName = "a", description = "Añade un participante, sintaxis: -a <ctfsid> <grupoid> <puntuacion>").multiple()
    val delete by parser.option(ArgType.Int, shortName = "d", description = "Borra un grupo, sintaxis: -d <ctfif> <grupoid>").multiple()
    val listado by parser.option(ArgType.Int, shortName = "l", description = "lista el grupo seleccionado, sintaxis: -l <grupoid>").multiple()
    parser.parse(args)

}


private fun calculaMejoresResultados(participaciones: List<Ctf>): MutableMap<Int, Pair<Int, Ctf>> {
    val participacionesByCTFId = participaciones.groupBy { it.id }
    var participacionesByGrupoId = participaciones.groupBy { it.grupoId }
    val mejoresCtfByGroupId = mutableMapOf<Int, Pair<Int, Ctf>>()

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