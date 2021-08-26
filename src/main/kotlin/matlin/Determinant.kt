package matlin

import matlin.Factorization.partialPivotLU

object Determinant {
    fun det(A: Matrix): Double{
        var P: Matrix
        val L: Matrix
        val U: Matrix
        partialPivotLU(A).also {
            P = it[0]
            L = it[1]
            U = it[2]
        }
        val n = P.shape.first
        var m1 = 1.0;
        var m2 = 1;
        for (i in 1..n){
            m1 *= U[i,i]

            if(P[i,i]==0.0){
                var j: Int
                for (j in i+1..n){
                    if(P[j,i]==1.0){
                        P=SpecialMatrix.swapMatrix(n,i,j)*P
                        m2 *=-1
                        break
                    }
                }
            }

        }
        return m1*m2
    }


}