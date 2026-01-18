package org.puzzler.model;

/**
 * Simple timing utility used to measure durations for different phases or runs.
 *
 * <p>The Timer supports {@link #start()} and {@link #end()} calls. Each call pair
 * records a duration which is stored internally. Call {@link #printAllDurations()}
 * to print a summary of all recorded durations and the total time.</p>
 *
 * <p>This implementation uses {@link System#nanoTime()} for measurements and prints
 * durations in milliseconds with three decimal digits.</p>
 */
public class Timer {
    private long start;
    private long end;

    private long duration;

    public Timer() {
    }

    /**
     * Start a new timing interval. If a previous interval was started and not ended,
     * it will be overwritten.
     */
    public void start() {
        this.start = System.nanoTime();
    }

    /**
     * End the current timing interval and store its duration.
     *
     * <p>If {@link #start()} was not called before this method, this method is a no-op.</p>
     */
    public void end() {
        this.end = System.nanoTime();
        this.duration = this.end - this.start;
    }

    /**
     * Print all recorded durations to stdout and a summary total.
     *
     * <p>Durations are printed in milliseconds with three decimal places. If no durations
     * are recorded, the method indicates that no timing information is available.</p>
     */
    public void printAllDurations() {
        System.out.println();
        System.out.println("Benötigte Dauer: " + this.getDurationInNs() + " ns.");
        System.out.println("Benötigte Dauer: " + this.getDurationInMs() + " ms.");
        System.out.println("Benötigte Dauer: " + this.getDurationInS() + " s.");
        System.out.println("Benötigte Dauer: " + this.getDurationInMin() + " min.");
    }

    private long getDurationInNs() {
        return this.duration;
    }

    private double getDurationInMs() {
        return this.duration / 1_000_000.0;
    }

    private double getDurationInS() {
        return this.duration / 1_000_000_000.0;
    }

    private double getDurationInMin() {
        return this.duration / 60_000_000_000.0;
    }
}
