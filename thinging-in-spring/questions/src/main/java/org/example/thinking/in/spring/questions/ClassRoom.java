package org.example.thinking.in.spring.questions;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @auhtor llm
 * @data 2022/8/25 15:29
 */
public class ClassRoom {

    private String name;

    @Autowired
    private Collection<Student> students;

    public String getName() {
        return name;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
