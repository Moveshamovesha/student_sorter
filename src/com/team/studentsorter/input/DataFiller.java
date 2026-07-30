package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.util.List;

public interface DataFiller {
    List<Student> fill(int size);
}