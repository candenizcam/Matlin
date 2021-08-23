package matlin

/** This class handles matrix computations
 */
class  Matrix private constructor(){


    var shape: Pair<Int,Int> = Pair(0,0)
        private set
    var vals: MutableList<Double> = mutableListOf(0.0)
        private set
    val square: Boolean
        get() {
            return shape.first==shape.second
        }


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
        this.vals = values.toMutableList()
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
        vals = nvals
        shape = Pair(rows.size,vals.size/rows.size)
    }

    /** third constructor that handles list of lists input, inner lists are rows
     */
    public constructor(lists: List<List<Double>>, asRows:Boolean=true): this(){
        val vals = mutableListOf<Double>()
        val r: Int
        val c: Int
        if(asRows){
            r = lists.size
            c = lists[0].size
            for(i in 0 until r){
                for(j in 0 until c){
                    vals.add(lists[i][j])
                }
            }
            this.vals = vals
            this.shape = Pair(r,c)
        }else{
            c = lists.size
            r = lists[0].size
            for(i in 0 until r){
                for(j in 0 until c){
                    vals.add(lists[j][i])
                }
            }
            this.vals = vals
            this.shape = Pair(r,c)
        }
    }




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
        return Matrix(l,Pair(shape.first,other.shape.second))
    }

    /** get and set for 2d bracket access
     */
    operator fun get(row: Int,col: Int): Double {
        return vals[(row-1)*shape.second+col-1]
    }

    operator fun set(row: Int,col: Int, value: Double) {
        vals[(row-1)*shape.second+col-1] = value
    }

    /** returns the sliced matrix if null, it is computed to the end
     */
    fun slice(r1: Int?=null, r2: Int?=null,c1: Int?=null,c2: Int?=null): Matrix {
        ((r1?:1)..(r2?:shape.first)).map {i->
            val r = getRow(i)
            ((c1?:1)..(c2?:shape.second)).map {i2-> r[i2-1] }
        }.also {
            return Matrix(it)
        }
    }

    /** adjusts the output for printing
     */
    override fun toString(): String {
        var s = "matrix: \n"
        for (i in 1..shape.first){
            s += getRow(i).map {it-> "%.3f".format(it) }.joinToString()+"\n"
            //s += getRow(i).joinToString()+"\n"
        }
        return s
    }

    fun copy(): Matrix {
        return Matrix(vals, shape)
    }


    /** Row and Col. getters, starts from 1
     *
     */
    fun getRow(n: Int): List<Double> {
        if (n !in 1..shape.first){
            throw Exception("matrix does not have row $n")
        }
        val l= List(shape.second){index->
            get(n,index+1)
        }
        return l
    }

    fun getCol(n: Int): List<Double> {
        if (n !in 1..shape.second){
            throw Exception("matrix does not have column $n")
        }
        val l= List(shape.first){index->
            get(index+1,n)
        }
        return l
    }


    fun concatenate(other: Matrix, axis: Int = 0): Matrix {
        return when (axis) {
            0 -> {
                val nShape = Pair(shape.first+other.shape.first,shape.second)
                Matrix(vals+other.vals,nShape)
            }
            1 -> {
                val nShape = Pair(shape.first,shape.second+other.shape.second)
                val l = mutableListOf<Double>()
                for (i in 1..nShape.first){
                    l += getRow(i)+other.getRow(i)
                }
                Matrix(l,nShape)
            }
            else -> {
                throw Exception("axis must be 0 or 1")
            }
        }
    }




    /** Transpose of a matrix
     */
    fun T(): Matrix {
        return Matrix((1..shape.second).map {i-> getCol(i) })
    }
}