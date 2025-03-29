package main

// https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/submissions/1589662417/?envType=daily-question&envId=2025-03-28
// 4방향 이동을 위한 방향 벡터
var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}} // 상, 하, 좌, 우

type Cell struct {
	row, col int
}

func maxPoints(grid [][]int, queries []int) []int {
	m, n := len(grid), len(grid[0])
	k := len(queries)
	answer := make([]int, k)

	// 쿼리마다 BFS 수행
	for i, query := range queries {
		visited := make(map[Cell]bool)

		queue := []Cell{{0, 0}}

		if grid[0][0] >= query {
			answer[i] = 0
			continue
		}

		visited[Cell{0, 0}] = true
		points := 1

		for len(queue) > 0 {
			current := queue[0]
			queue = queue[1:] // 큐에서 제거

			for _, dir := range directions {
				newRow, newCol := current.row+dir[0], current.col+dir[1]

				// 범위 내에 있음
				if newRow >= 0 && newRow < m && newCol >= 0 && newCol < n {
					// 새 셀의 값이 쿼리보다 작다면
					if grid[newRow][newCol] < query {
						newCell := Cell{newRow, newCol}

						if !visited[newCell] {
							visited[newCell] = true
							points++
							queue = append(queue, newCell)
						}
					}
				}
			}
		}
		answer[i] = points
	}

	return answer
}
