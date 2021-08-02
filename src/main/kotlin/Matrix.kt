
/** This class handles matrix computations
 */
class  Matrix private constructor(){


    var shape: Pair<Int,Int> = Pair(0,0)
        private set
    var vals: List<Double> = listOf(0.0)
        private set

    /** Secondary constructor for list
     * This constructor takes a list of doubles and a shape pair, this is the default scheme
     */
    public constructor(values: List<Double>, shape: Pair<Int, Int>? = null): this() {

        if (shape!=null){
            if (shape.first*shape.second!= values.size){
                throw Exception("incorrect shape for matrix")
            }
        }
        this.shape = shape ?: Pair(values.size,1)
        this.vals = values
    }

    /** Secondary constructor for string
     * This is the constructor that takes a string as input, it has a simple notation which must be followed exactly
     * rows are seperated by ; and values on a row by ,
     * ex: "1,2,3;0,1,2;0,0,1" upper triangle 3x3 matrix
     */
    public constructor(string: String) : this() {
        val nvals = mutableListOf<Double>()
        val rows = string.split(";")
        val nlist = mutableListOf<Int>()
        var n1=0
        var n2 = 0
        rows.forEach {
            it.split(",").forEach {it2->
                nvals.add(it2.toDouble())
                n1+=1
            }
            if (n2==0){
                n2=n1
            }else if(n2!=n1){
                throw Exception("incorrect shape for matrix")
            }
            n1 = 0
        }
        vals = nvals.toList()
        shape = Pair(rows.size,vals.size/rows.size)
    }

    /** third constructor that handles list of lists input, inner lists are rows
     */
    public constructor(lists: List<List<Double>>): this(lists.joinToString(separator = ";") { it ->
        it.joinToString(
            separator = ","
        )
    })


    /** Operators:
     */
    operator fun plus(other: Matrix): Matrix {
        if (this.shape!=other.shape){
            throw Exception("matrix shapes do not match")
        }
        return Matrix(vals.mapIndexed { index, d ->  other.vals[index]+d},shape)
    }

    operator fun minus(other: Matrix): Matrix {
        if (this.shape!=other.shape){
            throw Exception("matrix shapes do not match")
        }
        return Matrix(vals.mapIndexed { index, d ->  other.vals[index]-d},shape)
    }

    operator fun times(other: Double): Matrix {
        return Matrix(vals.map { d ->  d*other},shape)
    }

    operator fun times(other: Matrix): Matrix {
        if (this.shape.second!=other.shape.first){
            throw Exception("matrix shapes do not match")
        }
        val rowLeft = shape.first
        val rowRight = other.shape.first
        val colLeft = shape.second
        val colRight = other.shape.second


        val l = mutableListOf<Double>()
        var s = ""

        for (i in 1..shape.first){
            for (j in 1..other.shape.second){
                var n=0.0
                val r = getRow(i)
                val c = other.getCol(j)
                for (k in 0 until other.shape.first){
                    n += r[k]*c[k]
                }
                l.add(n)
            }
        }
        return Matrix(l,Pair(other.shape.second,shape.first))
    }

    /** adjusts the output for printing
     */
    override fun toString(): String {
        var s = "matrix: \n"
        for (i in 1..shape.first){
            s += getRow(i).joinToString()+"\n"
        }
        return s
    }


    /** Row and Col. getters, starts from 1
     *
     */
    fun getRow(n: Int): List<Double> {
        if (n !in 1..shape.first){
            throw Exception("matrix does not have row $n")
        }
        return (0 until shape.second).map {i-> vals[i+(n-1)*shape.second] }
    }

    fun getCol(n: Int): List<Double> {
        if (n !in 1..shape.second){
            throw Exception("matrix does not have column $n")
        }
        return (0 until shape.first).map {i-> vals[i*shape.second+n-1] }
    }


    /** Transpose of a matrix
     */
    fun T(): Matrix {
        return Matrix((1..shape.second).map {i-> getCol(i) })
    }
}