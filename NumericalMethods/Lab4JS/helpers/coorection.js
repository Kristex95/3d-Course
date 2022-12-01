export const testCorrection = (result, system) =>{ 
    const correction = []
    for (let i = 0; i < system.length; i++) {
        correction.push(system[i](...result))
    }
    return correction
}