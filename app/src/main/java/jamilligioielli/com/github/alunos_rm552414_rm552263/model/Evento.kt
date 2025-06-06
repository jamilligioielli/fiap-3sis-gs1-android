package jamilligioielli.com.github.alunos_rm552414_rm552263.model

import java.util.Date

class Evento(
    val nomeLocal: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val dataEvento: Date,
    val noPessoasAfetadas: Int
)