object MatrixInversion {
    fun inversion(m: Matrix): Matrix {
        return if(m.square){
            squareInvert(m)
        }else{
            throw Exception("Matrix must be square")
        }
    }

    private fun squareInvert(m: Matrix): Matrix {
        val n = m.shape.first
        val I = SpecialMatrix.identity(n)
        var lu = Factorization.lu(m)

        var l = mutableListOf<Double>()
        for(i in 1..n){
            val e = I.slice(c1=i,c2=i)
            val newList = LinearSolution.forwardBackwardsSubstitution(lu[0],lu[1],e)
            l += newList.getCol(1)
        }
        return Matrix(l,shape = Pair(n,n))
    }
}