package io.github.henriquemcc.forum.model

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.jvm.Transient

@Entity
data class Topico(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val curso: Curso? = null,

    @ManyToOne
    val autor: Usuario? = null,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList(),

    var dataAlteracao: LocalDate? = null
): Serializable {
    @JvmField
    @Transient
    val serialVersionUID: Long = 1L
}
