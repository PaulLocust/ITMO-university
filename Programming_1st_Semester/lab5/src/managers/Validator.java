package managers;


import collection.StudyGroupPackage.StudyGroup;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class Validator {
    HashSet<StudyGroup> studyGroupHashSet;

    public Validator(HashSet<StudyGroup> studyGroupHashSet) {
        this.studyGroupHashSet = studyGroupHashSet;
    }


    public HashSet<StudyGroup> validate(){
        for(Iterator<StudyGroup> iterator = studyGroupHashSet.iterator(); iterator.hasNext(); ){
            StudyGroup org = iterator.next();
            if(org.getId() <= 0) iterator.remove();
            if(org.getName() == null || org.getName().isEmpty()) iterator.remove();

            if(org.getCoordinates() != null){
                if(org.getCoordinates().getY() == null || org.getCoordinates().getY() > 849) iterator.remove();
            }

            if(org.getCreationDate() == null) iterator.remove();
            if(org.getStudentsCount() <= 0) iterator.remove();
            if(org.getTransferredStudents() <= 0) iterator.remove();
            if(org.getAverageMark() <= 0) iterator.remove();
            if(org.getSemesterEnum() == null) iterator.remove();

            if(org.getGroupAdmin() != null) {
                if (org.getGroupAdmin().getName() == null || org.getGroupAdmin().getName().isEmpty()) iterator.remove();
                if (org.getGroupAdmin().getHeight() < 0) iterator.remove();
                if (org.getGroupAdmin().getEyeColor() == null) iterator.remove();
                if (org.getGroupAdmin().getLocation() != null) {
                    if(org.getGroupAdmin().getLocation().getX() == null) iterator.remove();
                    if(org.getGroupAdmin().getLocation().getY() == null) iterator.remove();
                    if(org.getGroupAdmin().getLocation().getZ() == null) iterator.remove();
                    if(org.getGroupAdmin().getLocation().getX() == null) iterator.remove();
                }
            }
        }
        return studyGroupHashSet;
    }

}