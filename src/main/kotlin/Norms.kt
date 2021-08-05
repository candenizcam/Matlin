import kotlin.math.abs

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
}