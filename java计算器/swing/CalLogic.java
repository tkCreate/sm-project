package swing;

import java.util.ArrayDeque;
import java.util.Queue;

public class CalLogic {
	protected static double cal(String s) {
		Queue<Character> q = new ArrayDeque<>();
		for (char c : s.toCharArray())
			q.offer(c);

		q.offer(' ');
		return calculate(q);

	}

	protected static double calculate(Queue<Character> q) {
		double num = 0, prev = 0, sum = 0;
		char prevOp = '+'; // 初始化，0+0=0
		boolean f = false;
		int cnt = 0;
		while (!q.isEmpty()) {
			char c = q.poll();
			if (c >= '0' && c <= '9') {
				num = num * 10 + c - '0';
				if (f)
					cnt++;
			} else if (c == '.')
				f = true;
			else if (c == '(')
				num = calculate(q);
			else {
				f = false;
				while (cnt > 0) {
					num /= 10;
					cnt--;
				}
				switch (prevOp) { // 遇到c='$'或')'最后会再来一次
				case '+':
					sum += prev;
					prev = num;
					break;

				case '-':
					sum += prev;
					prev = -num;
					break;

				case '*':
					prev *= num;
					break;

				case '/':
					prev /= num;
					break;

				case '%':
					prev %= num;
					break;
				}
				if (c == ')')
					break;
				prevOp = c;
				num = 0;
			}
		}

		return sum + prev;
	}
}
