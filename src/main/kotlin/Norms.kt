import kotlin.math.abs
import kotlin.math.sqrt

object Norms {
    fun frobenius(m: Matrix): Double {
        var s = 0.0
        val n = m.shape.first
        for(i in 1..n ){
            for(j in 1..n ){
                s += abs(m[i,j])
            }
        }
        return s

    }


    /** Returns the vector norm of an input vector depending on the type
     *
     */
    fun vectorNorm(m: Matrix, kind: String="2"): Double{
        if(m.shape.second!=1){
            throw Exception("Invalid input, m is not a vector")
        }
        return when (kind) {
            "1" -> {
                var n=0.0
                m.getCol(1).forEach {
                    n+=abs(it)
                }
                n
            }
            "2" -> { //also called euclidean
                sqrt((m.T()*m)[1,1])
            }
            "inf" -> {
                m.getCol(1).maxByOrNull { abs(it) }!!
            }
            else -> {
                throw Exception("invalid vector norm kind, try 1,2 or inf")
            }
        }
    }
}