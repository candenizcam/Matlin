import kotlin.random.Random
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    /*
    val m1 = Matrix(listOf(1.0,2.3,5.0))
    val m2 = Matrix("1;2.3;5")

    val m = m1+m2

     */

    //val m = Matrix("2,0,1;0,3,0;1,0,5")


    val n= 10
    val l = List(n*n) { Random.nextDouble(-1000.0,1000.0)}
    val mSing = Matrix(l,Pair(n,n))
    val m = mSing //.T()*mSing

    //val m = Matrix("4,15,2;22,1,3;5,2,10")
    //val l2= List(10) { Random.nextDouble(-10.0,10.0)}
    //val x1 =  Matrix(l2,Pair(10,1))

    //println(m)
    println("LU")
    //val t = Factorization.doolitle(m)

    val t = Factorization.lu(m)

    println(Norms.frobenius(m-(t[0]*t[1])))

    //val x1 = Matrix("1;2;3")
    //val b = m*x1
    //val x2= LinearSolution.forwardBackwardsSubstitution(m,b)
    //println(Norms.vectorNorm(x2-x1))
    val testNo = 1000
    /*
    val t2 = measureNanoTime {
        for(i in 1..testNo){
            MatrixInversion.inversion(m)
        }

    }
    println("average in %.3f ms".format(t2*1e-6/testNo))
     */
    //println("not pivot")
    println("with pivot")
    val m3= MatrixInversion.inversion(m)
    val s3 = m3*m-SpecialMatrix.identity(m.shape.first)
    println(Norms.frobenius(s3))
















    /*
    println(t.first)
    println(t.second)
    println(t.first*t.second)

    println((m*-1.0)+(t.first*t.second))

     */
    /*
    val t = Factorization.lu(m)
    println(t.first)
    println(t.second)
    println(t.third)

     */










    //println("Program arguments: ${m.vals}, ${m.getCol(1)}, ${m[3,1]}")
    //println(m.slice())
    //println(m.slice(null,2,2,null))
}