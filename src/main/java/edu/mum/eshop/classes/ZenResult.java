package edu.mum.eshop.classes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZenResult {
    private boolean success;

    private List<String> errors;

    private Integer id;

    private Object value;

    public ZenResult(){
        success = true;
        errors = new ArrayList<>();
        id = 0;
    }

    public ZenResult(String error){
        success = false;
        errors = new ArrayList<>();
        errors.add(error);
        id = 0;
    }

    public void addError(String error){
        success = false;
        errors.add(error);
    }
}
