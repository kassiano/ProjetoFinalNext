package br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils


val calcInvestiment:(Double, Int)-> Double = { initialPrice ,quantity->
    initialPrice * quantity
}

val calcPostition: (Double, Int)->Double = { currentPrice, quantity->
    currentPrice * quantity
}

val calcYield: (Double, Double)->Double = { currentPrice, initialPrice->
    (currentPrice / initialPrice) - 1
}
