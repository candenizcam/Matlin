fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    /*
    val m1 = Matrix(listOf(1.0,2.3,5.0))
    val m2 = Matrix("1;2.3;5")

    val m = m1+m2

     */

    val m = Matrix("1,1,0;2,0,1;1.5,3,1")



    println("Program arguments: ${m.vals}, ${m.getCol(1)}, ${m[3,1]}")
    println(m.slice())
    println(m.slice(null,2,2,null))
}