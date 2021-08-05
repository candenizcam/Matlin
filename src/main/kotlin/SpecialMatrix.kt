import kotlin.math.cos
import kotlin.math.sin

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

    fun zeros(len: Int): Matrix {
        return full(0.0,Pair(len,len))
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

    /** Generates a rotation matrix, default is 2d
     *
     */
    fun rotationMatrix2d(rad: Double): Matrix {
        val c = cos(rad)
        val s = sin(rad)
        return Matrix(listOf(c,-s,s,c), Pair(2,2))



    }

    /** Axis inputs are 1: x, 2: y, 3:z
     *
     */
    fun rotationMatrix3d(rad: Double, axis: Int): Matrix {
        val c = cos(rad)
        val s = sin(rad)
        val a = axis-1
        return identity(3).also {m->
            m[(a+1).mod(3)+1,(a+1).mod(3)+1]=c
            m[(a+2).mod(3)+1,(a+2).mod(3)+1]=c
            m[(a+1).mod(3)+1,(a+2).mod(3)+1]=-s
            m[(a+2).mod(3)+1,(a+1).mod(3)+1]=s
        }
    }

    /** Returns a swap matrix that swaps first to second given value
     * if multiplied from left it swaps rows, if multiplied from right it swaps columns
     */
    fun swapMatrix(n:Int, first:Int, second: Int): Matrix {

        return if(first==second){
            identity(n)
        } else{
            identity(n).also {m->
                m[first,first]=0.0
                m[first,second]=1.0
                m[second,first]=1.0
                m[second,second]=0.0
            }
        }


    }



}