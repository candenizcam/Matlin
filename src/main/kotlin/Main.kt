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

    val m = Matrix("1,2,3;4,5,6;7,8,9")
    //val l = List(100) { Random.nextDouble(-10.0,10.0)}
    //val m = Matrix(l,Pair(10,10))

    //println(m)
    println("LU")
    //val t = Factorization.doolitle(m)

    val t = Factorization.lu(m)

    println(Norms.frobenius(m-(t.first*t.second)))





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