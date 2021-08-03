import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    /*
    val m1 = Matrix(listOf(1.0,2.3,5.0))
    val m2 = Matrix("1;2.3;5")

    val m = m1+m2

     */

    val m = Matrix("1,2,3;2,0,1;1.5,3,1")

    println(m[2,3])

    measureNanoTime {  }



    val t1 = measureNanoTime {
        for(i in 1..10000){
            //m.set1(1,2,15.3)
        }
    }

    val t2 = measureNanoTime {
        for(i in 1..10000){
            //m.set2(1,2,15.3)
        }
    }

    val t3 = measureNanoTime {
        for(i in 1..10000){
            m.set(1,2,15.3)
        }
    }

    val t4 = measureNanoTime {
        for(i in 1..10000){
            m.set(1,2,15.3)
            m[2,3]=-15.3
        }
    }

    println(m)




    println("t1: $t1, \nt2: $t2, \nt3: $t3\nt4: $t4")



    println(SpecialMatrix.identity(5).concatenate(SpecialMatrix.identity(5)))

    println(SpecialMatrix.identity(5).concatenate(SpecialMatrix.identity(5),axis = 1))





    //println("Program arguments: ${m.vals}, ${m.getCol(1)}, ${m[3,1]}")
    //println(m.slice())
    //println(m.slice(null,2,2,null))
}