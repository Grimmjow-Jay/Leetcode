package practice.lcp;

/**
 * LCP 03. 机器人大冒险
 *
 * <pre>
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，
 * 机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 *
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 *
 * 示例 2：
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 *
 * 示例 3：
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *
 * 限制：
 * 1. 2 <= command的长度 <= 1000
 * 2. command由U，R构成，且至少有一个U，至少有一个R
 * 3. 0 <= x <= 1e9, 0 <= y <= 1e9
 * 4. 0 <= obstacles的长度 <= 1000
 * 5. obstacles[i]不为原点或者终点
 * </pre>
 *
 * @author Grimm
 * @date 2021/4/14
 */
public class ProgrammableRobot {

    public static void main(String[] args) {
        String command = "URR";
        int[][] obstacles = {{4, 2}};
        int x = 3;
        int y = 2;
        boolean robot = robot(command, obstacles, x, y);
        System.out.println(robot);
    }

    private static boolean robot(String command, int[][] obstacles, int x, int y) {
        int rNum = 0;
        int uNum = 0;
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'R') {
                rNum++;
            } else {
                uNum++;
            }
        }

        if (!robot(command, rNum, uNum, x, y)) {
            return false;
        }

        for (int[] obstacle : obstacles) {
            if (obstacle[0] > x || obstacle[1] > y) {
                continue;
            }
            if (robot(command, rNum, uNum, obstacle[0], obstacle[1])) {
                return false;
            }
        }

        return true;
    }

    private static boolean robot(String command, int rNum, int uNum, int x, int y) {
        int count = Integer.min(x / rNum, y / uNum);
        x = x - (rNum * count);
        y = y - (uNum * count);

        int m = 0;
        int n = 0;
        for (int i = 0; i < command.length(); i++) {
            if (m == x && n == y) {
                return true;
            }

            char c = command.charAt(i);
            if (c == 'U') {
                n++;
            } else if (c == 'R') {
                m++;
            }

            if (m > x || n > y) {
                return false;
            }

        }

        return m == x && n == y;

    }

}
