import { newtonMethod, newtonMethodModified } from "../helpers/algoritm.js"

export const f1 = (x, y) => x - 0.5 * Math.sin((x - y) / 2)
export const f2 = (x, y) => y - 0.5 * Math.cos((x + y) / 2)

export const f1x = (x, y) => 1 - 0.25 * Math.cos((x - y) / 2)
export const f1y = (x, y) => 0.25 * Math.cos((x - y) / 2)
export const f2x = (x, y) => 0.25 * Math.sin((x + y) / 2)
export const f2y = (x, y) => 1 + 0.25 * Math.sin((x + y) / 2)

export const lectureMain = () => {
    const x0 = [0, 0]
    const eps = 0.0001
    const maxIter = 100
    const system = [f1, f2]
    const derivativeFunctionsMatrix = [[f1x, f1y], [f2x, f2y]]
    const x = newtonMethod(system, derivativeFunctionsMatrix, x0, eps, maxIter)
    const xModified = newtonMethodModified(system, derivativeFunctionsMatrix, x0, eps, maxIter)
    console.log("Newton method: ", x)
    console.log("Newton modified method: ", xModified)
}