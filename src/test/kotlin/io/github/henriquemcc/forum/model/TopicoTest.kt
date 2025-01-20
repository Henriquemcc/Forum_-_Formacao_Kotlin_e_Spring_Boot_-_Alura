package io.github.henriquemcc.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo Kotlin basico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}