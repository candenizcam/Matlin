/** This object contains various special matrices as shorthand
 *
 */
object SpecialMatrix {
    fun full(n: Double, shape: Pair<Int,Int>): Matrix{
        return Matrix(List(shape.first*shape.second){n},shape)
    }

    fun zeros(shape: Pair<Int,Int>): Matrix {
        return full(0.0,shape)
    }

    fun identity(shape: Pair<Int,Int>): Matrix{
        if(shape.first!=shape.second){
            throw Exception("The shape of an identity matrix must be a square")
        }
        val m = zeros(shape = shape)
        for(i in 1..shape.first){
            m[i,i] = 1.0
        }
        return m
    }

    fun identity(len: Int): Matrix {
        return identity(Pair(len,len))
    }



}