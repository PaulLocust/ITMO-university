package models;

import java.time.ZonedDateTime;
import java.util.*;

public class StudyGroups extends HashSet<StudyGroup> {
    private final ZonedDateTime initDate;

    public StudyGroups() {
        initDate = ZonedDateTime.now();
    }

    public void update(HashSet<StudyGroup> hashSet){
        Set<StudyGroup> space = Collections.synchronizedSet(hashSet);
        synchronized (space){
            this.clear();
            this.addAll(space);
        }
    }

    @Override
    public String toString() {
        return  "Collection type: HashSet\n" +
                "Initialization date: " + initDate.toString() + "\n" +
                "Number of elements: " + this.size();
    }
}