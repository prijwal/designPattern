
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

interface Robot {
    void showMe(String color);
}

class SmallRobot implements Robot {
    private final String robotTypeCreated = "A small robot created";
    
    public SmallRobot() {
        System.out.print(robotTypeCreated);
    }
    
    @Override
    public void showMe(String color) {
        System.out.print(" with " + color + " color");
    }
}

class LargeRobot implements Robot {
    private final String robotTypeCreated = "A large robot created";

    public LargeRobot() {
        System.out.print(robotTypeCreated);
    }

    @Override
    public void showMe(String color) {
        System.out.print(" with " + color + " color");
    }
}

class FixedSizeRobot implements Robot {
    private final String robotTypeCreated = "A robot with a fixed size created";

    public FixedSizeRobot() {
        System.out.print(robotTypeCreated);
    }

    @Override
    public void showMe(String color) {
        System.out.print(" with blue (default) color");
    }
}

class RobotFactory {
    static Map<String, Robot> robotFactory = new HashMap<>();
    
    public int totalObjectsCreated() {
        return robotFactory.size();
    }

    public static synchronized Robot getRobotFromFactory(String robotType) throws Exception {
        Robot robot = robotFactory.get(robotType);
        if (robot == null) {
            switch (robotType) {
                case "small":
                    robot = new SmallRobot();
                    break;
                case "large":
                    robot = new LargeRobot();
                    break;
                case "fixed":
                    robot = new FixedSizeRobot();
                    break;
                default:
                    throw new Exception("Unknown robot type");
            }
            robotFactory.put(robotType, robot);
        }
        return robot;
    }
}

public class Flyweight{

    public static void main(String[] args) throws Exception {
        RobotFactory robotFactory = new RobotFactory();
        Robot myRobot;

        for (int i = 0; i < 3; i++) {
            myRobot = RobotFactory.getRobotFromFactory("small");
            Thread.sleep(1000);
            myRobot.showMe(getRandomColor());
        }
        System.out.println("\nDistinct robots created: " + robotFactory.totalObjectsCreated());

        for (int i = 0; i < 5; i++) {
            myRobot = RobotFactory.getRobotFromFactory("large");
            Thread.sleep(1000);
            myRobot.showMe(getRandomColor());
        }
        System.out.println("\nDistinct robots created: " + robotFactory.totalObjectsCreated());

        for (int i = 0; i < 4; i++) {
            myRobot = RobotFactory.getRobotFromFactory("fixed");
            Thread.sleep(1000);
            myRobot.showMe(getRandomColor());
        }
        System.out.println("\nDistinct robots created: " + robotFactory.totalObjectsCreated());
    }

    static String getRandomColor() {
        return new Random().nextInt() % 2 == 0 ? "red" : "green";
    }
}

