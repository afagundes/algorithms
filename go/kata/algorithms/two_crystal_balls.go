package algorithms

import "math"

func TwoCrystalBalls(distances []bool) int {
	size := float64(len(distances))
	step := int(math.Floor(math.Sqrt(size)))

	i := step
	for ; i < len(distances); i += step {
		if distances[i] {
			break
		}
	}

	i -= step
	for j := 0; j <= step && i < len(distances); {
		if distances[i] {
			return i
		}

		i++
		j++
	}

	return -1
}
