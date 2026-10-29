package com.site;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExtensionStudent extends Student {

    @Override
    public String getStudentType() {
        return "Extension Student";
    }
}