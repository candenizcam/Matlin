import SpecialMatrix.identity
import SpecialMatrix.swapMatrix
import SpecialMatrix.zeros
import kotlin.math.abs

object Factorization {

    /** Applies doolitle algorithm to a matrix to get LU factorization
     * returns a pair, first is L second is U
     *
     */
    fun lu(m: Matrix): Pair<Matrix, Matrix> {
        if(!m.square){
            throw Exception("input of lu must be a square matrix")
        }
        val n = m.shape.first
        val L = zeros(n)
        val U = zeros(n)
        for (i in 1..n){
            for (k in i..n){
                var sum = 0.0
                for (j in 1 until i){
                    sum += (L[i,j]*U[j,k])
                }
                U[i,k] = m[i,k]- sum
            }
            for (k in i..n){
                if(i==k){
                    L[i,i] = 1.0
                }else{
                    var sum = 0.0
                    for (j in 1 until i){
                        sum += (L[k,j]*U[j,i])
                    }
                    L[k,i] = (m[k,i]-sum)/U[i,i]
                }
            }
        }
        return Pair(L, U)

    }

    /** First does partial pivoting and then applies lu
     * returns a triple with permutation matrix P and LU such that PA=LU
     */
    fun partialPivotLU(m: Matrix): Triple<Matrix, Matrix, Matrix> {
        if(!m.square){
            throw Exception("input of partialPivotLU must be a square matrix")
        }
        val n = m.shape.first
        var A = m.copy()
        var P = identity(n)
        for(k in 1..n){
            var ind = k
            var maxVal = 0.0
            for (i in k..n){
                val thisVal = abs(A[i,k])
                if(thisVal>maxVal){
                    maxVal = thisVal
                    ind = i
                }
            }
            if(k!=ind){
                val p = swapMatrix(n,k,ind)
                P = p*P
                A = P*m
            }
        }
        lu(P*m).also {
            return Triple(P,it.first,it.second)
        }
    }
}