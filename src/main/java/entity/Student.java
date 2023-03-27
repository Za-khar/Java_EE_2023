package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private String surname;
    private String age;
    private String email;
    private String group;
    private String faculty;
}
