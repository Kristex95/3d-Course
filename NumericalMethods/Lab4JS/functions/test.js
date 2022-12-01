import { newtonMethod, newtonMethodModified, relaxationMethod } from "../helpers/algoritm.js"

const createFi = (i) => (...xi) => {
    const sumOfSquares = xi.reduce((acc, x, index) => acc + (index === i ? Math.pow(x, 3) : Math.pow(x, 2)) , 0)
    const sumOfNumbersSquares = xi.reduce((acc, x, index) => acc + (index === i ? Math.pow(index + 1, 3) : Math.pow(index + 1, 2)), 0)
    return sumOfSquares - sumOfNumbersSquares;
}

const generateDerivativeFunctionsMatrix = (size) => {
    const matrix = []
    for (let i = 0; i < size; i++) {
        const row = []
        for (let j = 0; j < size; j++) {
            if (i === j) {
                row.push((...xi) => 3 * Math.pow(xi[j], 2))
            } else {
                row.push((...xi) => 2 * xi[j])
            }
        }
        matrix.push(row)
    }
    return matrix
}

export const testMain = () => {
    const size = 20
    const system = Array(size).fill(0).map((_, i) => createFi(i))
    const derivativeFunctionsMatrix = generateDerivativeFunctionsMatrix(size)
    const x0 = Array(size).fill(0).map((_, i) => i + 1.2)
    const eps = 0.0001
    const maxIter = 100
    const x = newtonMethod(system, derivativeFunctionsMatrix, x0, eps, maxIter)
    const xModified = newtonMethodModified(system, derivativeFunctionsMatrix, x0, eps, maxIter)
    const xRelax = relaxationMethod(system, derivativeFunctionsMatrix, x0, eps, maxIter)
    console.log("Newton method: ", x)
    
    console.log("Newton modified method: ", xModified)
    
    console.log("Relaxation  method: ", xRelax)
    
}
