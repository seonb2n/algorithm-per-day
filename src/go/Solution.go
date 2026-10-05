package main

// https://leetcode.com/problems/score-of-parentheses/?envType=daily-question&envId=2026-10-05
func scoreOfParentheses(s string) int {
    i := 0

    var dfs func() int
    dfs = func() int {
        score := 0

        for i < len(s) && s[i] != ')' {
            i++ // '(' 건너뜀
            inner := dfs()
            i++ // ')' 건너뜀
            
            if inner == 0 {
                score++ // "()" 를 만난 것
            } else {
                score += 2 * inner // "(A)" 를 만난 것
            }
        }

        return score
    }

    return dfs()
}