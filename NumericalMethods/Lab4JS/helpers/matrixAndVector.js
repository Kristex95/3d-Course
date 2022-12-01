export const vectorSubtraction = (x, y) => {
    const z = []
    for (let i = 0; i < x.length; i++) {
        z.push(x[i] - y[i])
    }
    return z
}

export const multiplyMatrixByVector = (A, x) => {
    const b = []
    for (let i = 0; i < A.length; i++) {
        b.push(0)
        for (let j = 0; j < A.length; j++) {
            b[i] += A[i][j] * x[j]
        }
    }
    return b
}

export const GaussMethod = (A, b) => {
    const n = b.length
    const x = []
    for (let i = 0; i < n; i++) {
        x.push(0)
    }
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (j != i) {
                const m = A[j][i] / A[i][i]
                for (let k = 0; k < n; k++) {
                    A[j][k] -= m * A[i][k]
                }
                b[j] -= m * b[i]
            }
        }
    }
    for (let i = 0; i < n; i++) {
        x[i] = b[i] / A[i][i]
    }
    return x
}


export const calculateNorm = (x) => {
    let norm = Number.MIN_VALUE
    for (let i = 0; i < x.length; i++) {
        if (Math.abs(x[i]) > norm) {
            norm = Math.abs(x[i])
        }
    }
    return norm
}


export const inverse = (A) => {
    const n = A.length
    const E = []
    for (let i = 0; i < n; i++) {
        E.push([])
        for (let j = 0; j < n; j++) {
            if (i == j) {
                E[i].push(1)
            } else {
                E[i].push(0)
            }
        }
    }
    for (let i = 0; i < n; i++) {
        const m = A[i][i]
        for (let j = 0; j < n; j++) {
            A[i][j] /= m
            E[i][j] /= m
        }
        for (let j = 0; j < n; j++) {
            if (j != i) {
                const m = A[j][i]
                for (let k = 0; k < n; k++) {
                    A[j][k] -= m * A[i][k]
                    E[j][k] -= m * E[i][k]
                }
            }
        }
    }
    return E
}

export const generateMatrix = (n) => {
    const matrix = [];
    for (let i = 0; i < n; i++) {
        matrix.push([]);
        for (let j = 0; j < n; j++) {
            matrix[i].push(0);
        }
    }
    return matrix;
};