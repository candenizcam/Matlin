object LinearSolution {
    fun forwardBackwardsSubstitution(A: Matrix, b: Matrix): Matrix {
        val L: Matrix
        val U: Matrix
        Factorization.lu(A).also {
            L = it[0]
            U = it[1]
        }
        return forwardBackwardsSubstitution(L,U,b)


    }

    fun forwardBackwardsSubstitution(L: Matrix, U: Matrix, b: Matrix): Matrix {
        val n = L.shape.first
        val c = SpecialMatrix.zeros(Pair(n,1))
        for (i in 1..n){
            var a = b[i,1]
            for(j in 2..i){
                a -= L[i,j-1]*c[j-1,1]
            }
            c[i,1] = a/L[i,i]
        }

        val x = SpecialMatrix.zeros(Pair(n,1))
        for (i in n downTo 1 step 1){
            var a = c[i,1]
            for(j in (i+1)..n){
                a -= U[i,j]*x[j,1]
            }
            x[i,1] = a/U[i,i]

        }
        return x
    }

}