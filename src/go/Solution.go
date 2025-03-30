package main

// https://leetcode.com/problems/partition-labels/submissions/1590841099/?envType=daily-question&envId=2025-03-30
func partitionLabels(s string) []int {
	result := []int{}
	lastMap := make(map[rune]int)

	for i, char := range s {
		lastMap[char] = i
	}

	start := 0
	end := 0

	for i, char := range s {
		if lastMap[char] > end {
			end = lastMap[char]
		}

		if i == end {
			result = append(result, end-start+1)
			start = end + 1
		}
	}

	return result
}
