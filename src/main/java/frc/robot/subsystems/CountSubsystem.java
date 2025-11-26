package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CountSubsystem extends SubsystemBase{
    int count;
    int index;

    public CountSubsystem(int _index) {
        count = 0;
        index = _index;
        System.out.printf("Subsystem %d initialized\n", index);
    }

    public void reset() {
        count = 0;
    }

    public void run() {
        ++count;
        if (count % 5 == index)
            System.out.printf("Subsystem %d counts: %d\n", index, count);
    }

    public void stop() {
        System.out.printf("Subsystem %d stopped running\n", index);
    }

    public boolean condition() {
        return count > index * 100;
    }
}
