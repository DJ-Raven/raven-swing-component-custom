package animator;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Simple animator based on ideas and concepts from "Filthy Rich Clients" book
 * and "Timing Framework" library.
 *
 * @author Karl Tauber
 */
public class Animator {

    private int duration;
    private int resolution = 1;
    private Interpolator interpolator;
    private final ArrayList<TimingTarget> targets = new ArrayList<>();
    private final Runnable endRunnable;
    private boolean running;
    private boolean hasBegun;
    private boolean timeToStop;
    private long startTime;
    private Timer timer;

    public Animator(int duration) {
        this(duration, null, null);
    }

    public Animator(int duration, TimingTarget target) {
        this(duration, target, null);
    }

    public Animator(int duration, TimingTarget target, Runnable endRunnable) {
        setDuration(duration);
        addTarget(target);
        this.endRunnable = endRunnable;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        throwExceptionIfRunning();
        if (duration <= 0) {
            throw new IllegalArgumentException();
        }
        this.duration = duration;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        throwExceptionIfRunning();
        if (resolution <= 0) {
            throw new IllegalArgumentException();
        }
        this.resolution = resolution;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        throwExceptionIfRunning();
        this.interpolator = interpolator;
    }

    public void addTarget(TimingTarget target) {
        if (target == null) {
            return;
        }

        synchronized (targets) {
            if (!targets.contains(target)) {
                targets.add(target);
            }
        }
    }

    public void removeTarget(TimingTarget target) {
        synchronized (targets) {
            targets.remove(target);
        }
    }

    public void start() {
        throwExceptionIfRunning();
        running = true;
        hasBegun = false;
        timeToStop = false;
        startTime = System.nanoTime() / 1000000;
        if (timer == null) {
            timer = new Timer(resolution, e -> {
                if (!hasBegun) {
                    begin();
                    hasBegun = true;
                }

                timingEvent(getTimingFraction());
            });
        } else {
            timer.setDelay(resolution);
        }
        timer.setInitialDelay(0);
        timer.start();
    }

    public void stop() {
        stop(false);
    }

    public void cancel() {
        stop(true);
    }

    private void stop(boolean cancel) {
        if (!running) {
            return;
        }

        if (timer != null) {
            timer.stop();
        }

        if (!cancel) {
            end();
        }

        running = false;
        timeToStop = false;
    }

    public void restart() {
        cancel();
        start();
    }

    public boolean isRunning() {
        return running;
    }

    private float getTimingFraction() {
        long currentTime = System.nanoTime() / 1000000;
        long elapsedTime = currentTime - startTime;
        timeToStop = (elapsedTime >= duration);
        float fraction = clampFraction((float) elapsedTime / duration);
        if (interpolator != null) {
            fraction = clampFraction(interpolator.interpolate(fraction));
        }
        return fraction;
    }

    private float clampFraction(float fraction) {
        if (fraction < 0) {
            return 0;
        }
        if (fraction > 1) {
            return 1;
        }
        return fraction;
    }

    private void timingEvent(float fraction) {
        synchronized (targets) {
            for (TimingTarget target : targets) {
                target.timingEvent(fraction);
            }
        }

        if (timeToStop) {
            stop();
        }
    }

    private void begin() {
        synchronized (targets) {
            for (TimingTarget target : targets) {
                target.begin();
            }
        }
    }

    private void end() {
        synchronized (targets) {
            for (TimingTarget target : targets) {
                target.end();
            }
        }

        if (endRunnable != null) {
            endRunnable.run();
        }
    }

    private void throwExceptionIfRunning() {
        if (isRunning()) {
            throw new IllegalStateException();
        }
    }

    @FunctionalInterface
    public interface TimingTarget {

        void timingEvent(float fraction);

        default void begin() {
        }

        default void end() {
        }
    }

    @FunctionalInterface
    public interface Interpolator {

        float interpolate(float fraction);
    }
}
