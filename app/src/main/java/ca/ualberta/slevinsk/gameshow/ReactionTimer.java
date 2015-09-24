package ca.ualberta.slevinsk.gameshow;

import java.util.Date;
import java.util.Random;

/**
 * Created by john on 15-09-19.
 */
public class ReactionTimer implements Comparable<ReactionTimer> {
    private Date startDate;
    private Date endDate;
    private Long targetTime;

    private final Integer minTargetMillis = 10;
    private final Integer maxTargetMillis = 2000;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Long targetTime) {
        this.targetTime = targetTime;
    }

    public void randomizeTargetTime() {
        Random r = new Random();

        Integer l = r.nextInt(maxTargetMillis - minTargetMillis) + minTargetMillis;
        setTargetTime(l.longValue());
    }

    public ReactionTimer(){
        Date now = new Date();
        setStartDate(now);
        setEndDate(now);
        randomizeTargetTime();
    }

    public Long start(){
        setStartDate(new Date());
        return getStartDate().getTime();
    }

    public Long stop(){
        setEndDate(new Date());
        return getEndDate().getTime();
    }

    /**
     *
     * @return the amount of time that has passed, in milliseconds
     */
    public Long getElapsedTime(){
        return Math.max(getEndDate().getTime() - getStartDate().getTime(), 0);
    }

    /**
     *
     * @return the amount of time that has passed after the target
     */
    public Long targetDelta() {
        return getElapsedTime() - getTargetTime();
    }


    @Override
    public int compareTo(ReactionTimer another) {
        return targetDelta().compareTo(another.targetDelta());
    }


}
