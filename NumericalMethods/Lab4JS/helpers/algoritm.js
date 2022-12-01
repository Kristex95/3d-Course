import {GaussMethod, calculateNorm, generateMatrix, inverse, multiplyMatrixByVector, vectorSubtraction} from "./matrixAndVector.js"

// Calculates Jacobi Matrix for system
export const Jacobi = (derivativeFunctionsMatrix, xi) => {
    const matrix = []
    for (let i = 0; i < derivativeFunctionsMatrix.length; i++) {
        const row = []
        for (let j = 0; j < derivativeFunctionsMatrix[i].length; j++) {
            row.push(derivativeFunctionsMatrix[i][j](...xi))
        }
        matrix.push(row)
    }
    return matrix
}

export const F = (system, xi) => {
    return system.map(f => f(...xi))
}


export const nextXNewton = (x, z) => {
    const nextX = []
    for (let i = 0; i < x.length; i++) {
        nextX.push(x[i] - z[i])
    }
    return nextX
}

const nextXNewtonModified = (x, AInv, Fi) => {
    const z = multiplyMatrixByVector(AInv, Fi)
    return vectorSubtraction(x, z)
}

export const newtonMethod = (system, derivativeFunctionsMatrix, x0, eps, maxIter) => {
    let xi = [...x0]
    let i = 0
    let norm = Number.MAX_VALUE
    while (norm > eps && i < maxIter) {
        const Ai = Jacobi(derivativeFunctionsMatrix, xi)
        const Fi = F(system, xi)
        const zi = GaussMethod(Ai, Fi)
        norm = calculateNorm(zi)
        xi = nextXNewton(xi, zi)
        i++
    }
    console.log(i)
    return xi
}

export const newtonMethodModified = (system, derivativeFunctionsMatrix, x0, eps, maxIter) => {
    let xcur = [...x0]
    let xprev = [...x0]
    let i = 0
    let norm = Number.MAX_VALUE
    const A0 = Jacobi(derivativeFunctionsMatrix, xprev)
    const A0Inv = inverse(A0)
    while (norm > eps && i < maxIter) {
        const Fi = F(system, xcur)
        xcur = nextXNewtonModified(xprev, A0Inv, Fi)
        norm = calculateNorm(vectorSubtraction(xcur, xprev))
        xprev = [...xcur]
        i++
    }
    console.log(i)
    return xcur
}

const calculateMatrixNorm = (matrix) => {
    let max = Number.MIN_VALUE
    for (let i = 0; i < matrix.length; i++) {
        let sum = 0
        for (let j = 0; j < matrix[i].length; j++) {
            sum += Math.abs(matrix[i][j])
        }
        if (sum > max) {
            max = sum
        }
    }
    return max
}

export const calculateTau = (derivativeFunctionsMatrix, x) => {
    const matrix = Jacobi(derivativeFunctionsMatrix, x)
    const norm = calculateMatrixNorm(matrix)
    return 2 / norm
}

const multiplyVector = (vector, number) => {
    return vector.map(x => x * number)
}


export const relaxationMethod = (system, derivativeFunctionsMatrix, x0, eps, maxIter) => {
    let xcur = [...x0]
    let xprev = [...x0]
    let i = 0
    let norm = Number.MAX_VALUE
    while (norm > eps && i < maxIter) {
        const Fi = F(system, xcur)
        xprev = xcur
        xcur = vectorSubtraction(xprev, multiplyVector(Fi, calculateTau(derivativeFunctionsMatrix, xprev)))
        norm = calculateNorm(vectorSubtraction(xcur, xprev))
        i++
    }
    console.log(i)
    return xcur
}
