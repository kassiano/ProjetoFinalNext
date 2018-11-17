package br.com.honeyinvestimentos.meusinvestimentos.fp

//Uma função "pipe" que permite realizar um pipeline de funções
infix inline fun <P1, R> P1.pipe(t: (P1) -> R): R = t(this)
