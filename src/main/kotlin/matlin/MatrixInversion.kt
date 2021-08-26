package matlin

object MatrixInversion {
    fun inversion(A: Matrix, pivot: Boolean=true): Matrix {
        return if(A.square){
            if(Determinant.det(A)!=0.0){
                throw Exception("Matlin.Matrix is not invertible, det(A) is 0")
            }
            if(pivot){
                pivotSquareInvert(A)
            }else{
                squareInvert(A)
            }

        }else{
            throw Exception("Matlin.Matrix must be square")
        }
    }

    private fun pivotSquareInvert(m: Matrix): Matrix {
        val n = m.shape.first

        var lu = Factorization.lu(m, partialPivot = true)
        if(!invertible(lu[2],true)){
            throw Exception("Matlin.Matrix not invertible")
        }

        val I = SpecialMatrix.identity(n)

        var l = mutableListOf<Double>()
        for(i in 1..n){
            val e = I.slice(c1=i,c2=i)
            val newList = LinearSolution.forwardBackwardsSubstitution(lu[1], lu[2], e)
            l += newList.getCol(1)
        }
        return Matrix(l,shape = Pair(n,n)).T()*lu[0]
    }

    private fun squareInvert(m: Matrix): Matrix {
        val n = m.shape.first
        val I = SpecialMatrix.identity(n)
        var lu = Factorization.lu(m)
        if(!invertible(lu[1],true)){
            throw Exception("Matlin.Matrix not invertible")
        }

        var l = mutableListOf<Double>()
        for(i in 1..n){
            val e = I.slice(c1=i,c2=i)
            val newList = LinearSolution.forwardBackwardsSubstitution(lu[0], lu[1], e)
            l += newList.getCol(1)
        }
        return Matrix(l,shape = Pair(n,n)).T()
    }

    fun pseudoinverse(m: Matrix): Matrix {
        return (m* inversion(m.T()*m)).T()
    }


    private fun invertible(u: Matrix, isU: Boolean): Boolean {
        var m = 1.0
        for(i in 1..u.shape.first){
            m*=(u)[i,i]
        }
        return m!=0.0
    }



    fun invertible(m: Matrix): Boolean {
        val lu = Factorization.lu(m, partialPivot = true)
        return invertible(lu[2],isU = true)
    }


}