package ca.ualberta.slevinsk.gameshow;
/**
 * Copyright 2015 John Slevinsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Date;
import java.util.Random;

/**
 * Reaction Timer implementation.
 * Can be started, stopped, queried for how much time has elapsed.
 * Also responsible for randomly choosing a target reaction time, stored internally.
 */
public class ReactionTimer implements Comparable<ReactionTimer> {
    private Long startDate;
    private Long endDate;
    private Long targetTime;

    private final Integer minTargetMillis = 10;
    private final Integer maxTargetMillis = 2000;

    public Long getStartDate() {
        return startDate;
    }

    private void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    private void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getTargetTime() {
        return targetTime;
    }

    private void setTargetTime(Long targetTime) {
        this.targetTime = targetTime;
    }

    /**
     * Picks a random amount of target time for the reaction,
     * between minTargetMillis and maxTargetMillis
     */
    public void randomizeTargetTime() {
        Random r = new Random();

        Integer l = r.nextInt(maxTargetMillis - minTargetMillis) + minTargetMillis;
        setTargetTime(l.longValue());
    }

    public ReactionTimer(){
        Long now = new Date().getTime();
        setStartDate(now);
        setEndDate(now);
        randomizeTargetTime();
    }

    /**
     * Starts the reaction timer.
     * @return the Long representation of the start date (in milliseconds)
     */
    public Long start(){
        setStartDate(new Date().getTime());
        return getStartDate();
    }

    /**
     * Stops the reaction timer.
     * @return the Long representation of the end date (in milliseconds)
     */
    public Long stop(){
        setEndDate(new Date().getTime());
        return getEndDate();
    }

    /**
     * After the timer has stopped, determine how much time has elapsed
     * @return the Long amount of time that has passed, in milliseconds
     */
    private Long getElapsedTime(){
        return Math.max(getEndDate() - getStartDate(), 0);
    }

    /**
     *
     * @return the amount of time that has passed after the target
     */
    public Long getTimeDelta() {
        return getElapsedTime() - getTargetTime();
    }


    @Override
    public int compareTo(ReactionTimer another) {
        return getTimeDelta().compareTo(another.getTimeDelta());
    }


}
