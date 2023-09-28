package dev.priyanshu.productserviceassignment.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
public class BaseModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    public BaseModel (){
        // i want to set the current date and time for createdAt field
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }
}
